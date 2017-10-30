package net.zymen.retryablerequests.tests.app;

import net.zymen.retryablerequests.RetryableRequestsFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = RetryableRequestsFacade.class)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
