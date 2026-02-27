package vn.edu.hcmut.cse.adse.lab.repository;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * StudentRepository - Data Access Layer
 * 
 * Extends JpaRepository to provide CRUD operations for Student entity.
 * Spring Data JPA automatically generates the implementation class at runtime
 * using Dynamic Proxy mechanism.
 * 
 * Methods provided by JpaRepository:
 * - save(): Insert or Update
 * - findById(): Find by primary key
 * - findAll(): Get all records
 * - delete(): Delete a record
 * - deleteAll(): Delete all records
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    
    /**
     * Search students by name (case-insensitive)
     * 
     * @param name Student name to search
     * @return List of students with matching name
     */
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> searchByName(@Param("name") String name);
    
    /**
     * Search students by email (case-insensitive)
     * 
     * @param email Student email to search
     * @return List of students with matching email
     */
    @Query("SELECT s FROM Student s WHERE LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<Student> searchByEmail(@Param("email") String email);
}
