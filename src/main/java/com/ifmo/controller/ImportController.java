package com.ifmo.controller;

import com.ifmo.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImportController {
    @Autowired
    private ImportService importService;

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importData(@RequestParam(value = "clearData", defaultValue = "true") String clearData,
                             @RequestParam(value = "generateData", defaultValue = "true") String generateData) {
        importService.importAllData(Boolean.valueOf(clearData), Boolean.valueOf(generateData));
        return "Success";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return importService.getProductType();
    }
}
