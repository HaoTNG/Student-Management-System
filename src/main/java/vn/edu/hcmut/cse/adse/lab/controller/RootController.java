package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller để handle root path redirect
 */
@Controller
public class RootController {
    
    /**
     * Redirect từ "/" về "/students"
     */
    @GetMapping("/")
    public String redirectToStudents() {
        return "redirect:/students";
    }
}
