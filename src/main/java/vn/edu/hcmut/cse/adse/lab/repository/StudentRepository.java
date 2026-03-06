package vn.edu.hcmut.cse.adse.lab.repository;

import vn.edu.hcmut.cse.adse.lab.entity.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByNameContainingIgnoreCase(String keyword);
}