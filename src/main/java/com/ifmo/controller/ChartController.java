package com.ifmo.controller;

import com.ifmo.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChartController {
    @Autowired
    private ChartService chartService;

    @RequestMapping(value = "/chart/price", method = RequestMethod.GET)
    public ModelAndView showPriceChart() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("price");
        mav.addObject("line", chartService.getDatePriceString());
        return mav;
    }

    @RequestMapping(value = "/chart/quantity", method = RequestMethod.GET)
    public ModelAndView showQuantityChart() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("quantity");
        mav.addObject("line", chartService.getDateQuantityString());
        return mav;
    }
}
