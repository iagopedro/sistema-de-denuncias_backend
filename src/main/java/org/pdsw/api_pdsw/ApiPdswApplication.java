package org.pdsw.api_pdsw;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class ApiPdswApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPdswApplication.class, args);
	}

}
