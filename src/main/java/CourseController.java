package com.university.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.university.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/courses")
    public String showCourses(Model model, HttpSession session) {
        if (session.getAttribute("studentId") == null) {
            return "redirect:/login";
        }

        String sql = "SELECT course_id AS courseId, name, instructor, credits FROM courses";

        List<Course> courses = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Course.class)
        );

        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId,
                                 HttpSession session,
                                 Model model) {

        Object studentIdObj = session.getAttribute("studentId");

        if (studentIdObj == null) {
            return "redirect:/login";
        }

        int studentId = Integer.parseInt(studentIdObj.toString());

        String checkSql = "SELECT COUNT(*) FROM registrations WHERE student_id=? AND course_id=?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, studentId, courseId);

        if (count > 0) {
            model.addAttribute("message", "You have already registered for this course.");
            return "success";
        }

        String insertSql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, studentId, courseId, Date.valueOf(LocalDate.now()));

        model.addAttribute("message", "Course registration successful!");
        return "success";
    }
}