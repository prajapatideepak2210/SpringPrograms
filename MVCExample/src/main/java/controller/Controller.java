package controller;

import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  

@org.springframework.stereotype.Controller
public class Controller {  
    @RequestMapping("/hello")
    public ModelAndView helloWorld() {  
    	System.out.println("gvbcjsd bvksdb");
        String message = "HELLO SPRING MVC HOW R U";  
        return new ModelAndView("hellopage", "message", message);
    }  
}  
