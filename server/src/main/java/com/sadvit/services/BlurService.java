package com.sadvit.services;

import boofcv.alg.filter.blur.BlurImageOps;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.ImageFloat32;
import com.sadvit.models.CacheObject;
import com.sadvit.operations.blur.BlurParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by vitaly.sadovskiy on 24.12.2015.
 */
@Service
public class BlurService {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageCache imageCache;

    public BufferedImage processAsImage(BufferedImage image, BlurParams params) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        ImageFloat32 input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
        ImageFloat32 output = new ImageFloat32(input.width, input.height);
        int blurRadius = params.getRadius();
        switch (params.getType()) {
            case GAUSSIAN:
                BlurImageOps.gaussian(input, output, -1, blurRadius, null);
                break;
            case MEAN:
                BlurImageOps.mean(input, output, blurRadius, null);
                break;
            case MEDIAN:
                BlurImageOps.median(input, output, blurRadius);
                break;
            default:
                return null;
        }
        ConvertBufferedImage.convertTo(output, result);
        return result;

    }

    public CacheObject process(String id, BlurParams params) {
        BufferedImage image = imageService.getBufferedImage(id);
        BufferedImage binary = processAsImage(image, params);
        byte[] result = toByteArray(binary);
        return imageCache.addToCache(result);
    }

}
