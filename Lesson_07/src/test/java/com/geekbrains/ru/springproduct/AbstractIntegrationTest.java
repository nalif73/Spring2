package com.geekbrains.ru.springproduct;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.TestcontainersConfiguration;

import javax.sql.DataSource;
import java.util.stream.Stream;

import static java.lang.String.format;


@SpringBootTest
public class AbstractIntegrationTest {

    public static GenericContainer<?> postgres = new PostgreSQLContainer<>("postgres:11.3")
            .withReuse(TestcontainersConfiguration.getInstance().environmentSupportsReuse());

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    public void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("db/populate.sql"));
        populator.setSqlScriptEncoding("UTF-8");
        populator.execute((dataSource));
    }

    static {
        Startables.deepStart(Stream.of(postgres)).join();
        System.setProperty("spring.datasource.url", format("jdbc:postgresql://%s:%d/test",
                postgres.getContainerIpAddress(), postgres.getMappedPort(5432)));
    }
}
