package vn.edu.hcmut.cse.adse.lab.controller;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * StudentController - Presentation Layer (API Endpoints)
 * 
 * Handles HTTP requests and responses for Student management.
 * Acts as the gateway between client and business logic.
 * 
 * @RestController annotation:
 * - Marks this class as a REST API controller
 * - All methods return JSON (not HTML views)
 * - Equivalent to @Controller + @ResponseBody
 * 
 * @RequestMapping annotation:
 * - Defines the base URL path for all endpoints in this controller
 * - All endpoints will have prefix: /api/students
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    /**
     * Dependency Injection
     * Spring will automatically inject StudentService instance
     */
    @Autowired
    private StudentService service;
    
    /**
     * API 1: Get all students
     * 
     * HTTP Method: GET
     * Endpoint: /api/students
     * Full URL: http://localhost:8080/api/students
     * 
     * @return List of all students (JSON format)
     * 
     * Example Response:
     * [
     *   {
     *     "id": "1",
     *     "name": "Nguyen Van A",
     *     "email": "vana@example.com",
     *     "age": 20
     *   },
     *   {
     *     "id": "2",
     *     "name": "Tran Thi B",
     *     "email": "thib@example.com",
     *     "age": 21
     *   }
     * ]
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }
    
    /**
     * API 2: Get student by ID
     * 
     * HTTP Method: GET
     * Endpoint: /api/students/{id}
     * Full URL: http://localhost:8080/api/students/1
     * 
     * @param id Student ID from URL path
     * @return Student object (JSON format) if found, null otherwise
     * 
     * @PathVariable annotation:
     * - Extracts {id} from URL path
     * - Passes the value as method parameter
     * 
     * Example Response (if student exists):
     * {
     *   "id": "1",
     *   "name": "Nguyen Van A",
     *   "email": "vana@example.com",
     *   "age": 20
     * }
     * 
     * Example Response (if student doesn't exist):
     * null
     */
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return service.getById(id);
    }
}
