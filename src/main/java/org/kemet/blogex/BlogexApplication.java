package org.kemet.blogex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogexApplication /*extends SpringBootServletInitializer*/ {
	
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
		return app.sources(BlogexApplication.class);
	}*/

	public static void main(String[] args) {
//		System.out.println("********************************************************");
//		System.out.println("***************     BLOGNEX REST API     ****************");
//		System.out.println("********************************************************");
//		System.out.println("Running the REST API of \"Blocex App\"  ...");
		System.setProperty("server.port", "91");
		//System.setProperty("server.servlet.context-path", "/bnex-api");
		SpringApplication.run(BlogexApplication.class, args);
	}

}
