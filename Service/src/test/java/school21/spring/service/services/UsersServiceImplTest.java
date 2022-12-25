package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;

public class UsersServiceImplTest {

    @Test
    public void testSignUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        UsersServiceImpl usersService = context.getBean("usersServiceTest", UsersServiceImpl.class);
        Assertions.assertNotNull(usersService.signUp("jim@mail.ru"));
    }
}
