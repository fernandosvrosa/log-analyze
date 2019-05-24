package br.com.quake.loganalyze;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.quake")
public class Application {


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);


  }

}
