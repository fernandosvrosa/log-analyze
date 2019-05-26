package br.com.log.analyze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.log.analyze")
public class LogAnalyzeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAnalyzeApplication.class, args);
	}

}
