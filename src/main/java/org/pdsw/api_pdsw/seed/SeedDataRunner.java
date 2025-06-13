package org.pdsw.api_pdsw.seed;

import org.pdsw.api_pdsw.entities.User;
import org.pdsw.api_pdsw.repositories.UserRepository;
import org.pdsw.api_pdsw.services.PasswordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedDataRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public SeedDataRunner(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setName("Alice Silva");
            user1.setEmail("alice.silva@example.com");
            user1.setPassword(passwordService.hashPassword("senha123"));
            user1.setType("user");
            userRepository.save(user1);

            User user2 = new User();
            user2.setName("Bruno Costa");
            user2.setEmail("bruno.costa@example.com");
            user2.setPassword(passwordService.hashPassword("senha456"));
            user2.setType("user");
            userRepository.save(user2);

            User user3 = new User();
            user3.setName("Carla Souza");
            user3.setEmail("carla.souza@example.com");
            user3.setPassword(passwordService.hashPassword("senha789"));
            user3.setType("admin");
            userRepository.save(user3);

            User user4 = new User();
            user4.setName("Daniel Pereira");
            user4.setEmail("daniel.pereira@example.com");
            user4.setPassword(passwordService.hashPassword(passwordService.hashPassword("senha321")));
            user4.setType("user");
            userRepository.save(user4);

            User user5 = new User();
            user5.setName("Elaine Rocha");
            user5.setEmail("elaine.rocha@example.com");
            user5.setPassword(passwordService.hashPassword("senha654"));
            user5.setType("user");
            userRepository.save(user5);

            User user6 = new User();
            user6.setName("Fernando Lima");
            user6.setEmail("fernando.lima@example.com");
            user6.setPassword(passwordService.hashPassword("senha987"));
            user6.setType("user");
            userRepository.save(user6);

            User user7 = new User();
            user7.setName("Gabriela Alves");
            user7.setEmail("gabriela.alves@example.com");
            user7.setPassword(passwordService.hashPassword("senha147"));
            user7.setType("admin");
            userRepository.save(user7);

            User user8 = new User();
            user8.setName("Henrique Martins");
            user8.setEmail("henrique.martins@example.com");
            user8.setPassword(passwordService.hashPassword("senha258"));
            user8.setType("user");
            userRepository.save(user8);

            User user9 = new User();
            user9.setName("Isabela Dias");
            user9.setEmail("isabela.dias@example.com");
            user9.setPassword(passwordService.hashPassword("senha369"));
            user9.setType("user");
            userRepository.save(user9);

            User user10 = new User();
            user10.setName("Julio Fernandes");
            user10.setEmail("julio.fernandes@example.com");
            user10.setPassword(passwordService.hashPassword("senha159"));
            user10.setType("user");
            userRepository.save(user10);
        }
    }
}