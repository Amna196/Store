package EcommerceProject.Store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
//@ComponentScan(basePackages = "EcommerceProject.Store.controller.ProductResource")
//@EnableJpaRepositories
//@EnableAutoConfiguration
public class StoreApplication { //implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
