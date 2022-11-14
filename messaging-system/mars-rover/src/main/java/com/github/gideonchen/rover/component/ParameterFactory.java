package com.github.gideonchen.rover.component;

import com.github.gideonchen.rover.model.Parameters;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ParameterFactory {
    public static Parameters getParameter() {
        Random random = new Random();
        Parameters p = new Parameters(
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat()
        );
        return p;
    }

}
