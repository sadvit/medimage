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
import com.sadvit.operations.chains.ChainElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by sadvit on 21.12.15.
 */
@Service
public class ChainService {

    @Autowired
    private ImageCache imageCache;

    @Autowired
    private ImageService imageService;

    @Autowired
    private BinaryService binaryService;

    @Autowired
    private BlurService blurService;

    public CacheObject processChain(String id, ChainElement[] chain) {
        BufferedImage currentImage = imageService.getBufferedImage(id);
        for (ChainElement chainElement : chain) {
            switch (chainElement.getOperationType()) {
                case BINARY:
                    currentImage = binaryService.processAsImage(currentImage, chainElement.getBinaryParams());
                    break;
                case BLUR:
                    currentImage = blurService.processAsImage(currentImage, chainElement.getBlurParams());
                    break;
            }
        }
        byte[] result = toByteArray(currentImage);
        return imageCache.addToCache(result);
    }

}
