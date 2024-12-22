package ru.kata.pp_3_1_2_new.init;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import ru.kata.pp_3_1_2_new.Pp312NewApplication;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Pp312NewApplication.class);
    }

}
