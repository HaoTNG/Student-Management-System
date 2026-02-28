package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    /**
     * GET /students
     * Display list of all students with search functionality
     */
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }

        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "students";
    }

    /**
     * GET /students/create
     * Show form for creating new student
     * MUST be before /{id} routes to avoid path variable matching
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    /**
     * POST /students/create
     * Create new student and save to database
     */
    @PostMapping("/create")
    public String createStudent(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age,
            Model model) {
        
        // Validation
        List<String> errors = validateStudentData(name, email, age);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("student", null);
            return "form";
        }

        // Generate ID (UUID for flexibility)
        String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Student student = new Student(id, name, email, age);
        service.saveStudent(student);

        return "redirect:/students";
    }

    /**
     * GET /students/delete?id=xxx
     * Delete student and redirect to list
     * MUST be before /{id} routes to avoid path variable matching
     */
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam String id) {
        service.deleteStudent(id);
        return "redirect:/students";
    }

    /**
     * GET /students/{id}/edit
     * Show edit form with student data pre-filled
     * MUST be before general /{id} route
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "form";
    }

    /**
     * POST /students/{id}/edit
     * Update student data and save to database
     */
    @PostMapping("/{id}/edit")
    public String editStudent(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age,
            Model model) {

        List<String> errors = validateStudentData(name, email, age);
        if (!errors.isEmpty()) {
            Student student = service.getById(id);
            model.addAttribute("errors", errors);
            model.addAttribute("student", student);
            return "form";
        }

        Student student = new Student(id, name, email, age);
        service.updateStudent(student);

        return "redirect:/students/" + id;
    }

    /**
     * GET /students/{id}
     * Display detail view for a specific student
     */
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "detail";
    }

    /**
     * Validate student input data
     */
    private List<String> validateStudentData(String name, String email, int age) {
        List<String> errors = new java.util.ArrayList<>();

        // Name validation
        if (name == null || name.trim().isEmpty()) {
            errors.add("❌ Họ và tên không được để trống");
        } else if (name.length() < 3) {
            errors.add("❌ Họ và tên phải có ít nhất 3 ký tự");
        } else if (name.length() > 100) {
            errors.add("❌ Họ và tên không vượt quá 100 ký tự");
        }

        // Email validation
        if (email == null || email.trim().isEmpty()) {
            errors.add("❌ Email không được để trống");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("❌ Email không hợp lệ (ví dụ: student@domain.com)");
        }

        // Age validation
        if (age < 1 || age > 100) {
            errors.add("❌ Tuổi phải nằm trong khoảng 1-100");
        }

        return errors;
    }
}
