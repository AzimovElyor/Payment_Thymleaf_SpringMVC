package uz.pdp.thmleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AllMenusController {
    @GetMapping("/contact")
    public String getContactPage(){
        return "/all/contact";
    }
    @GetMapping("/about")
    public String getAboutPage(){
        return "/all/about";
    }
}
