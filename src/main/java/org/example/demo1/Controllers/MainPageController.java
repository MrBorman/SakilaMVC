package org.example.demo1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MainPageController {
    @GetMapping()
    public String index(Model model) {
        return "index";
    }
}
