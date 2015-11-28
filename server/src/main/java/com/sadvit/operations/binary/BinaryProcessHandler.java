package com.sadvit.operations.binary;

import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.operations.ProcessHandler;

import java.awt.image.BufferedImage;
import java.util.Map;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by sadvit on 25.11.15.
 */
public class BinaryProcessHandler implements ProcessHandler {

    @Override
    public BufferedImage handle(BufferedImage image, Map params) {
        ImageFloat32 input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
        ImageUInt8 binary = new ImageUInt8(input.width, input.height);

        BinaryType binaryType = (BinaryType) params.get("1"); // TODO refactor

        switch (binaryType) {
            case MEAN:
                GThresholdImageOps.threshold(input, binary, ImageStatistics.mean(input), true);
                break;
            case OTSU:
                GThresholdImageOps.threshold(input, binary, GThresholdImageOps.computeOtsu(input, 0, 255), true);
                break;
            case ENTROPY:
                GThresholdImageOps.threshold(input, binary, GThresholdImageOps.computeEntropy(input, 0, 255), true);
                break;
            case SQUARE:
                GThresholdImageOps.localSquare(input, binary, 28, 1.0, true, null, null);
                break;
            case GAUSSIAN:
                GThresholdImageOps.localGaussian(input, binary, 42, 1.0, true, null, null);
                break;
            case SAUVOLA:
                GThresholdImageOps.localSauvola(input, binary, 5, 0.30f, true);
                break;
            case THRESOLD:
                ThresholdImageOps.threshold(input, binary, 10, true);
                break;
        }

        return VisualizeBinaryData.renderBinary(binary, false, null);
    }

}
