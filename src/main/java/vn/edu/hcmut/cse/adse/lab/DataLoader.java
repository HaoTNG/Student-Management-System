package vn.edu.hcmut.cse.adse.lab;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 * DataLoader - Load Initial Sample Data
 * 
 * Implements CommandLineRunner interface to run code after application startup.
 * This component automatically inserts sample student data into the database
 * when the application starts.
 * 
 * Usage: Spring automatically detects this @Component and runs the run() method
 * after the application context is fully initialized.
 */
@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private StudentRepository repository;
    
    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to avoid duplicates
        if (repository.count() == 0) {
            System.out.println("📝 Loading sample student data...");
            
            // Create sample data
            List<Student> students = Arrays.asList(
                new Student("1", "Nguyen Van A", "vana@example.com", 20),
                new Student("2", "Tran Thi B", "thib@example.com", 21),
                new Student("3", "Le Van C", "levanc@example.com", 22),
                new Student("4", "Pham Thi D", "phamthid@example.com", 20),
                new Student("5", "Hoang Van E", "hovane@example.com", 21),
                new Student("6", "Vu Thi F", "vuthif@example.com", 23),
                new Student("7", "Tran Van G", "tranvang@example.com", 19),
                new Student("8", "Dinh Thi H", "dinthih@example.com", 22),
                new Student("9", "Ngo Van I", "ngovani@example.com", 20),
                new Student("10", "Bui Thi J", "buithij@example.com", 21),
                new Student("11", "Dang Van K", "dangvank@example.com", 24),
                new Student("12", "Ly Thi L", "lythil@example.com", 20)
            );
            
            // Save all students
            repository.saveAll(students);
            System.out.println("✅ Successfully loaded " + students.size() + " students!");
        } else {
            System.out.println("ℹ️ Database already has " + repository.count() + " students. Skipping data loading.");
        }
    }
}
