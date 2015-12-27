package com.sadvit.services;

import boofcv.alg.feature.detect.edge.CannyEdge;
import boofcv.alg.feature.detect.edge.EdgeContour;
import boofcv.alg.filter.binary.BinaryImageOps;
import boofcv.alg.filter.binary.Contour;
import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.factory.feature.detect.edge.FactoryEdgeDetectors;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.gui.image.ShowImages;
import boofcv.io.UtilIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.ConnectRule;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageSInt16;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.models.CacheObject;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.binary.BinaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;

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

    public BufferedImage processAsImage(BufferedImage image, BinaryParams params) {
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

        return VisualizeBinaryData.renderBinary(binary, false, null);
    }

    public CacheObject process(String id, BinaryParams params) {
        BufferedImage image = imageService.getBufferedImage(id);
        BufferedImage binary = processAsImage(image, params);
        byte[] result = toByteArray(binary);
        return imageCache.addToCache(result);
    }

}
