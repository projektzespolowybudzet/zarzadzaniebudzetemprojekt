package am.jsl.personalfinances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Klasa, która służyła do ładowania i uruchamiania aplikacji Spring.
 */
@SpringBootApplication
public class PersonalFinancesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalFinancesApplication.class, args);
    }
}