package yoon.test.aopTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopTestApplication.class, args);
	}

}
