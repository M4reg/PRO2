package cz.uhk.pro2_e.config;

import cz.uhk.pro2_e.model.Color;
import cz.uhk.pro2_e.model.Chemical;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.service.ColorService;
import cz.uhk.pro2_e.service.ChemicalService;
import cz.uhk.pro2_e.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private final UserService userService;
    private final ColorService colorService;
    private final ChemicalService chemicalService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserService userService, ColorService colorService, ChemicalService chemicalService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.colorService = colorService;
        this.chemicalService = chemicalService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            if (userService.getAllUsers().isEmpty()) {

                User user = new User();
                user.setName("User");
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("heslo"));
                user.setRole("USER");
                userService.saveUser(user);

                User admin = new User();
                admin.setName("Admin");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("heslo"));
                admin.setRole("ADMIN");
                userService.saveUser(admin);

                Color color1 = new Color();
                color1.setName("Olive Green");
                color1.setBrand("Tamiya");
                color1.setCode("XF-58");
                color1.setUser(admin); // nebo user, podle toho kdo má být vlastníkem
                colorService.saveColor(color1);

                Chemical chemical1 = new Chemical();
                chemical1.setName("Matt Varnish");
                chemical1.setBrand("Tamiya");
                chemical1.setType("Varnish");
                chemical1.setUser(admin);
                chemicalService.saveChemical(chemical1);
            }
        };
    }
}