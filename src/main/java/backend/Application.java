package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EntityScan(basePackages = { "backend.domain" })
//@EnableJpaRepositories
@SpringBootApplication
public class Application implements WebMvcConfigurer
{
   public static void main (String[] args)
   {
      SpringApplication.run (Application.class, args);
   }

   @Override
   public void addCorsMappings (CorsRegistry registry)
   {
      registry.addMapping ("/customers/**");
      registry.addMapping ("/sample/**");
   }
}
