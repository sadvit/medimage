package com.sadvit.services;

import boofcv.alg.enhance.EnhanceImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.models.CacheObject;
import com.sadvit.operations.histogramEqualize.HistogramEqualizeParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by sadvit on 4/24/16.
 */
@Service
public class HistogramEqualizeService {

    @Autowired
    private ImageCache imageCache;

    @Autowired
    private ImageService imageService;

    public BufferedImage processAsImage(BufferedImage image, HistogramEqualizeParams params) {
        ImageUInt8 gray = ConvertBufferedImage.convertFromSingle(image, null, ImageUInt8.class);
        ImageUInt8 adjusted = gray.createSameShape();

        int histogram[] = new int[256];
        int transform[] = new int[256];

        ImageStatistics.histogram(gray, histogram);
        EnhanceImageOps.equalize(histogram, transform);

        if (params.isGlobal()) {
            EnhanceImageOps.applyTransform(gray, transform, adjusted);
            return ConvertBufferedImage.convertTo(adjusted, null);
        } else {
            EnhanceImageOps.equalizeLocal(gray, params.getRadius(), adjusted, histogram, transform);
            return ConvertBufferedImage.convertTo(adjusted, null);
        }

    }

    public CacheObject process(String id, HistogramEqualizeParams params) {
        BufferedImage image = imageService.getBufferedImage(id);
        BufferedImage binary = processAsImage(image, params);
        byte[] result = toByteArray(binary);
        return imageCache.addToCache(result);
    }

}
