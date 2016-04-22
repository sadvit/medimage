package com.sadvit.controllers;

import boofcv.alg.misc.ImageStatistics;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.to.StatisticTO;
import com.sadvit.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.util.*;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by sadvit on 27.12.15.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public StatisticTO getStatistic(@PathVariable String id) {

        StatisticTO info = new StatisticTO();

        BufferedImage buffered = imageService.getBufferedImage(id);
        ImageUInt8 gray = ConvertBufferedImage.convertFrom(buffered, (ImageUInt8) null);

        int histogram[] = new int[256];

        ImageStatistics.histogram(gray, histogram);

        double max = (double) Arrays.stream(histogram).max().getAsInt();

        Map<Integer, Integer> res = new HashMap<>();
        for (int i = 0; i < histogram.length; i++) {
            int swap = (int) (histogram[i] / max * 100);
            if (swap != 0) {
                res.put(i, swap);
            }
        }

        info.setHistogram(res);
        info.setName(id);
        info.setHeight(buffered.getHeight());
        info.setWidth(buffered.getWidth());
        info.setFormat("BMP");

        return info;
    }

}
