package com.sadvit.operations.binary;

import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.gui.binary.VisualizeBinaryData;
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
        Integer thresold = (Integer) params.get("thresold");
        ImageFloat32 input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
        ImageUInt8 binary = new ImageUInt8(input.width, input.height);
        ThresholdImageOps.threshold(input, binary, thresold, true);
        return VisualizeBinaryData.renderBinary(binary, null);
    }

}
