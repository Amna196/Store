package EcommerceProject.Store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = "EcommerceProject.Store.controller.ProductResource")
//@EnableJpaRepositories
//@EnableAutoConfiguration
public class StoreApplication { //implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
