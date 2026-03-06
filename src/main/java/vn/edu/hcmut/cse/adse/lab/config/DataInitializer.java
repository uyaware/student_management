package vn.edu.hcmut.cse.adse.lab.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(StudentRepository repo) {
        return args -> {

            if (repo.count() == 0) {

                for (long i = 1; i <= 10; i++) {

                    Student student = new Student();
                    student.setId(String.valueOf(i));
                    student.setName("Student " + i);
                    student.setEmail("student" + i + "@gmail.com");
                    student.setAge(16 + (int)i);

                    repo.save(student);
                }

                System.out.println("Seed 10 students done!");
            }
        };
    }
}