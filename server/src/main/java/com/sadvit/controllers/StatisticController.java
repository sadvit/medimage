package com.sadvit.controllers;

import boofcv.alg.enhance.EnhanceImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.gui.ListDisplayPanel;
import boofcv.io.UtilIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.models.CacheObject;
import com.sadvit.services.ImageCache;
import com.sadvit.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.geom.AffineTransform;
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

    @Autowired
    private ImageCache imageCache;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Map<Integer, Integer> getStatistic(@PathVariable String id) {

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

        return res;
    }

}
