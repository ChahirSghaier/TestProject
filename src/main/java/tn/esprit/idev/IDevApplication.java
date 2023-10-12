package tn.esprit.idev;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "tn.esprit.idev")
@EnableJpaRepositories
@EnableTransactionManagement

public class IDevApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplicationBuilder app = new SpringApplicationBuilder(IDevApplication.class);
        app.run();    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IDevApplication.class);
    }




}
