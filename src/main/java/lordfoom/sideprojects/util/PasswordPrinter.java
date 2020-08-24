package lordfoom.sideprojects.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordPrinter {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Keep it subtle. Keep it safe.");
        System.out.println(passwordEncoder.encode("subtledoorknobofdestiny"));
    }
}
