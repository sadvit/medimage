package com.sadvit.controllers;

import boofcv.alg.enhance.EnhanceImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.gui.ListDisplayPanel;
import boofcv.io.UtilIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by sadvit on 27.12.15.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public int[] getStatistic(@PathVariable String id) {

        BufferedImage buffered = imageService.getBufferedImage(id);
        ImageUInt8 gray = ConvertBufferedImage.convertFrom(buffered, (ImageUInt8) null);
        ImageUInt8 adjusted = gray.createSameShape();

        int histogram[] = new int[256];
        int transform[] = new int[256];

        ListDisplayPanel panel = new ListDisplayPanel();

        ImageStatistics.histogram(gray, histogram);

        int max = ImageStatistics.max(gray);

        int[] hist = new int[32];

        for (int i = 0; i < histogram.length - 8; i += 8) {
            int sum = 0;
            for (int j = i; j < i + 8; j++) {
                sum += histogram[j];
            }
            hist[i / 8] = sum / 8;
        }

        //EnhanceImageOps.equalize(histogram, transform);
        //EnhanceImageOps.applyTransform(gray, transform, adjusted);
        /*panel.addImage(ConvertBufferedImage.convertTo(adjusted, null), "Global");

        EnhanceImageOps.equalizeLocal(gray, 50, adjusted, histogram, transform);
        panel.addImage(ConvertBufferedImage.convertTo(adjusted,null),"Local");

        panel.addImage(ConvertBufferedImage.convertTo(gray, null), "Original");

        panel.setPreferredSize(new Dimension(gray.width, gray.height));
        mainPanel.addItem(panel, "Histogram");*/

        return hist;
    }

}
