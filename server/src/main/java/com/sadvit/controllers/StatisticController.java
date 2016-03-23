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
import java.util.Arrays;
import java.util.Collections;

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
    public CacheObject getStatistic(@PathVariable String id) {

        BufferedImage buffered = imageService.getBufferedImage(id);
        ImageUInt8 gray = ConvertBufferedImage.convertFrom(buffered, (ImageUInt8) null);

        int histogram[] = new int[256];

        ImageStatistics.histogram(gray, histogram);

        double max = (double) Arrays.stream(histogram).max().getAsInt();

        for (int i = 0; i < histogram.length; i++) {
            histogram[i] = (int) (histogram[i] / max * 100);
        }

        return imageCache.addToCache(toByteArray(generateHistogram(histogram)));
    }

    // 500 * 200
    // 500 * 400
    private BufferedImage generateHistogram(int[] histogram) {
        int width = 500;
        int height = 400;
        int padding = 20;
        BufferedImage image = new BufferedImage(width + padding * 2, height + padding * 2, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        Font font = new Font(null, Font.PLAIN, 10);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(90), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g.setFont(rotatedFont);

        g.setColor(new Color(34, 35, 50));
        for (int i = 0; i < histogram.length; i++) {
            int j = histogram[i] * 4;
            int x = i * 2 + padding;
            int y1 = height + padding;
            int y2 = height - j + padding;
            if (y1 - y2 > 0) {
                g.drawLine(x, y1, x, y2);
            }
        }
        for (int i = 0; i <= 255; i += 5) {
            g.drawString("" + i, i * 2 + padding, height + padding);
        }
        g.dispose();
        return image;
    }

}
