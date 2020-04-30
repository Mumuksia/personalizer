import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
//@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = (arrayOf("controller", "scheduller", "tasks", "sender", "service",
        "scrappers", "rozha", "rozha.controller", "rozha.service")))
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
