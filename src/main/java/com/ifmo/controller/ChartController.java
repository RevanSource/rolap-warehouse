package com.ifmo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@RequestMapping("/chart")
public class ChartController {
    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public ModelAndView test(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("chart");
        mav.addObject("name", name);
        return mav;
    }
}
