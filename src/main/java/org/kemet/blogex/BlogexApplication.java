package org.kemet.blogex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogexApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "8095");
		//System.setProperty("server.servlet.context-path", "/bnex-api");
		SpringApplication.run(BlogexApplication.class, args);
	}
}
