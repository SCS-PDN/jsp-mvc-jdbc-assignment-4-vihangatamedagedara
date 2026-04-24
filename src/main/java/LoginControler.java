@Controller
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String showLogin() {
        return "login"; // login.jsp
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        String sql = "SELECT * FROM students WHERE email=? AND password=?";
        List<Map<String, Object>> users =
                jdbcTemplate.queryForList(sql, email, password);

        if (!users.isEmpty()) {
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}