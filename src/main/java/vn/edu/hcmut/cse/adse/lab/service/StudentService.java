package vn.edu.hcmut.cse.adse.lab.service;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * StudentService - Business Logic Layer
 * 
 * Encapsulates business logic for student management.
 * Handles data processing, validation, and orchestration
 * between Controller and Repository layers.
 * 
 * @Service annotation:
 * - Marks this class as a service component
 * - Automatically registered in Spring Application Context
 * - Can be injected into other components via @Autowired
 */
@Service
public class StudentService {
    
    /**
     * Dependency Injection via @Autowired
     * 
     * Spring Container will:
     * 1. Search for StudentRepository bean in Application Context
     * 2. Create an instance if not exists
     * 3. Automatically inject into this field
     * 
     * Result: No need to manually instantiate (new StudentRepository())
     */
    @Autowired
    private StudentRepository repository;
    
    /**
     * Get all students
     * 
     * @return List of all students in the database
     */
    public List<Student> getAll() {
        return repository.findAll();
    }
    
    /**
     * Get a student by ID
     * 
     * @param id Student ID (as String)
     * @return Student object if found, null otherwise
     */
    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }
    
    /**
     * Get student count
     * 
     * @return Total number of students in database
     */
    public long getCount() {
        return repository.count();
    }
    
    /**
     * Search students by name or email
     * 
     * @param keyword Search keyword (case-insensitive)
     * @return List of students matching the keyword
     */
    public List<Student> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return getAll();
        }
        
        // Get all students and filter by name or email
        String lowerKeyword = keyword.toLowerCase();
        return repository.findAll().stream()
                .filter(student -> 
                    student.getName() != null && student.getName().toLowerCase().contains(lowerKeyword) ||
                    student.getEmail() != null && student.getEmail().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    /**
     * Save a new student
     * 
     * @param student Student object to save
     * @return Saved student
     */
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    /**
     * Update existing student
     * 
     * @param student Student object with updated data
     * @return Updated student
     */
    public Student updateStudent(Student student) {
        if (repository.existsById(student.getId())) {
            return repository.save(student);
        }
        return null;
    }

    /**
     * Delete student by ID
     * 
     * @param id Student ID to delete
     */
    public void deleteStudent(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
