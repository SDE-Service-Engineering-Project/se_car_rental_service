package at.ac.fhcampuswien.car_rental;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = AbstractIT.DockerPostgresDataSourceInitializer.class)
@Testcontainers
public abstract class AbstractIT {
    public static PostgreSQLContainer<?> postgresDBCContainer = new PostgreSQLContainer<>("postgres:latest");

    static {
        postgresDBCContainer.start();
    }

    public static class DockerPostgresDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + postgresDBCContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresDBCContainer.getUsername(),
                    "spring.datasource.password=" + postgresDBCContainer.getPassword()
            );
        }
    }
}
