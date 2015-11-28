package com.sadvit.services;

import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.models.CacheObject;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by sadvit on 29.11.15.
 */
@Service
public class BinaryService {

    @Autowired
    private ImageCache imageCache;

    @Autowired
    private ImageService imageService;

    public CacheObject process(String id, BinaryParams params) {
        BufferedImage image = imageService.getBufferedImage(id);
        ImageFloat32 input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
        ImageUInt8 binary = new ImageUInt8(input.width, input.height);

        BinaryType binaryType = params.getType();

        int radius, thresold;
        double scale;
        float k;
        boolean isDown = params.isDown();
        switch (binaryType) {
            case MEAN:
                GThresholdImageOps.threshold(input, binary, ImageStatistics.mean(input), isDown);
                break;
            case OTSU:
                GThresholdImageOps.threshold(input, binary, GThresholdImageOps.computeOtsu(input, 0, 255), isDown);
                break;
            case ENTROPY:
                GThresholdImageOps.threshold(input, binary, GThresholdImageOps.computeEntropy(input, 0, 255), isDown);
                break;
            case SQUARE:
                radius = (int) params.getLocalSquare()[0];
                scale = params.getLocalSquare()[1];
                GThresholdImageOps.localSquare(input, binary, radius, scale, isDown, null, null); // 28, 1.0
                break;
            case GAUSSIAN:
                radius = (int) params.getLocalGaussian()[0];
                scale = params.getLocalGaussian()[1];
                GThresholdImageOps.localGaussian(input, binary, radius, scale, isDown, null, null); // 42, 1.0
                break;
            case SAUVOLA:
                radius = (int) params.getLocalSauvola()[0];
                k = (float) params.getLocalSauvola()[1];
                GThresholdImageOps.localSauvola(input, binary, radius, k, isDown); // 5, 0.30f
                break;
            case THRESOLD:
                thresold = params.getThresold();
                ThresholdImageOps.threshold(input, binary, thresold, isDown);
                break;
        }

        byte[] result = toByteArray(VisualizeBinaryData.renderBinary(binary, false, null));
        return imageCache.addToCache(result);

    }

}
