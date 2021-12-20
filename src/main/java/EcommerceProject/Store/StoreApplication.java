package EcommerceProject.Store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 * To fix: 
 * Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
 * Reason: Failed to determine a suitable driver class
*/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
