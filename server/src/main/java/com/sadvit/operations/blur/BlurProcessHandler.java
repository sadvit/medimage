package com.sadvit.operations.blur;

import boofcv.alg.filter.blur.BlurImageOps;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.core.image.ConvertImage;
import boofcv.gui.image.VisualizeImageData;
import boofcv.struct.image.ImageFloat32;
import com.sadvit.operations.ProcessHandler;

import java.awt.image.BufferedImage;

/**
 * Created by sadvit on 25.11.15.
 */
public class BlurProcessHandler implements ProcessHandler {

    @Override
    public BufferedImage handle(BufferedImage image, Object params) {

        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        int blurRadius = 10;
        ImageFloat32 input = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
        ImageFloat32 output = new ImageFloat32(input.width, input.height);

        // Gaussian blur: Convolve a Gaussian kernel
        BlurImageOps.gaussian(input, output, -1, blurRadius, null);

        ConvertBufferedImage.convertTo(output, result);

        //BufferedImage outputImage = VisualizeImageData.
        return result;
    }

}
