package com.smsoft.controller;

import com.smsoft.dto.StatusDto;
import com.smsoft.entity.Status;
import com.smsoft.service.CsvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller {
    @Autowired
    private CsvService service;

    @GetMapping(value = "/stat")
    public StatusDto getAllStatutesByDelay(@RequestParam(required = false) String delay){
        if (delay!=null) {
            return service.filterByDateGap(delay);
        }

        else
        return service.getStatusesDto();

    }
}
