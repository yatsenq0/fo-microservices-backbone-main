package com.nikriv.fo.servicebackbone;

import org.springframework.boot.SpringApplication;

public class TestServiceBackboneApplication {

    public static void main(String[] args) {
        SpringApplication.from(ServiceBackboneApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
