package chessEngine.position;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PositionConfig {

    @Bean
    CommandLineRunner commandLineRunner(PositionRepository repository) {
        return args -> {
            Position pos = new Position(1L,"fds", true);
            repository.saveAll(List.of(pos));
        };
    }
}
