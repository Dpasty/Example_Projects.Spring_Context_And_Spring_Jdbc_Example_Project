package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.Random;

@Component
public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplateImpl") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String signUp(String email) {
        Random random = new Random();
        String tempPassword = String.valueOf(Math.abs(random.nextLong()));
        User user = new User(null, email, tempPassword);
        usersRepository.save(user);
        return tempPassword;
    }
}
