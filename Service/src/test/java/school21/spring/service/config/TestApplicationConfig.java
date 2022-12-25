package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "school21.spring.service")
public class TestApplicationConfig {
    @Bean
    public DataSource embeddedDatabase() {
        DataSource ds = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
        return ds;
    }

    @Bean
    public UsersRepository usersRepositoryTest() {
        return new UsersRepositoryJdbcTemplateImpl(embeddedDatabase());
    }

    @Bean
    public UsersService usersServiceTest() {
        return new UsersServiceImpl(usersRepositoryTest());
    }
}
