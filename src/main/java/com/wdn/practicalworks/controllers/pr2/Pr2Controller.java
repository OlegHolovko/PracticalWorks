package com.wdn.practicalworks.controllers.pr2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Pr2Controller {
    @RequestMapping(value = "/pr2/queries", method = { RequestMethod.GET })
    public String queries(Model model){

        model.addAttribute("hasParent", "2");
        model.addAttribute("activeMenu", "pr2_queries");
        return "pr2/queries";
    }
}
