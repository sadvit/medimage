package com.sadvit.services;

import boofcv.alg.misc.ImageStatistics;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.to.StatisticTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sadvit on 4/23/16.
 */
@Service
public class StatisticService {

    @Autowired
    private ImageService imageService;

    public StatisticTO getStatistic(String imageId) {

        StatisticTO info = new StatisticTO();

        BufferedImage buffered = imageService.getBufferedImage(imageId);
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
        info.setName(imageId);
        info.setHeight(buffered.getHeight());
        info.setWidth(buffered.getWidth());
        info.setFormat("BMP");

        return info;
    }

}
