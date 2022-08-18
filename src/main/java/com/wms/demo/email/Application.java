package com.wms.demo.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner 
{
	@Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    
    @Override
    public void run(String... args) 
    {
    	// emailService.sendMail("sophiaaltaf@gmail.com", "Test Head", "Test Body");
    	
    	//emailService.sendPreConfiguredMail("Ho ho ho");
    		
    	 emailService.sendMailWithAttachment("sophiaaltaf@gmail.com", "Test Head", "Test Body", "D:\\Zia_java\\Demo.java");
    	
    	//emailService.sendMailWithInlineResources("sophiaaltaf@gmail.com", "Test Head", "D:\\sample.jpg");
    }
}
