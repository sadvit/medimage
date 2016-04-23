package com.sadvit.controllers;

import com.sadvit.services.StatisticService;
import com.sadvit.to.StatisticTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sadvit on 27.12.15.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(method = RequestMethod.GET, value = "{imageId}")
    public StatisticTO getStatistic(@PathVariable String imageId) {
        return statisticService.getStatistic(imageId);
    }

}
