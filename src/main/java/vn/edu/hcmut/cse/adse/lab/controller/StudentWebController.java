package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Luu y: su dung @Controller, KHONG dung @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(
            @RequestParam(required = false) String keyword,
            Model model) {

        List<Student> students;

        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }

        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword);

        return "students";
    }

    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = service.getById(id);

        if (student == null) {
            return "redirect:/students";
        }

        model.addAttribute("student", student);
        return "student-detail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/save")
    public String saveStudent(Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id);

        if (student == null) {
            return "redirect:/students";
        }

        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.delete(id);
        return "redirect:/students";
    }
}