package com.example.springbookstore.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class TestController {

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {
        //ModelAndView model = new ModelAndView("home");
        Date date = new Date();

        model.addAttribute("currentdate", date);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        //ModelAndView model = new ModelAndView();
        return "login";
    }

    @GetMapping("/hello")
    public String helloPage(Model model) {
        return "hello";
    }

}




