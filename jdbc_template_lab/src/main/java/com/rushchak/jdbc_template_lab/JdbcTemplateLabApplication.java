package com.rushchak.jdbc_template_lab;

import com.rushchak.jdbc_template_lab.view.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcTemplateLabApplication implements CommandLineRunner {

    @Autowired
    private MainView view;

    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateLabApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        view.show();
    }
}
