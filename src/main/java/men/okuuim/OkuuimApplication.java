package men.okuuim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OkuuimApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(OkuuimApplication.class, args);
    }

//    public static String getPosVersion(String... command) throws IOException {
//        ProcessBuilder p = new ProcessBuilder("#!/bin/bash", "-c", "cat pom.xml");
////        Process p = Runtime.getRuntime().exec(new String[] {"ls -1 -d */"});
//        BufferedReader reader = new BufferedReader(new InputStreamReader(p.start().getInputStream()));
//        StringBuilder output = new StringBuilder();
//
//        String string;
//        while((string = reader.readLine()) != null) {
//            output.append(string + "\n");
//        }
//
//        System.getProperty("java.class.path");
//        return output.toString();
//    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
