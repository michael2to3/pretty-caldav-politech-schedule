package pretty.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pretty.schedule.Controllers")
public class App {
    @Value("${application.source}")
    static String source;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
