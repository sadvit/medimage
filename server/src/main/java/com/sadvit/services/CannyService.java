package com.sadvit.services;

import boofcv.alg.feature.detect.edge.CannyEdge;
import boofcv.alg.feature.detect.edge.EdgeContour;
import boofcv.alg.filter.binary.BinaryImageOps;
import boofcv.alg.filter.binary.Contour;
import boofcv.factory.feature.detect.edge.FactoryEdgeDetectors;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.gui.image.ShowImages;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.ConnectRule;
import boofcv.struct.image.ImageSInt16;
import boofcv.struct.image.ImageUInt8;
import com.sadvit.models.CacheObject;
import com.sadvit.operations.binary.BinaryParams;
import com.sadvit.operations.canny.CannyParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;

import static com.sadvit.utils.FileUtils.toByteArray;

/**
 * Created by sadvit on 4/24/16.
 */
@Service
public class CannyService {

    @Autowired
    private ImageCache imageCache;

    @Autowired
    private ImageService imageService;

    public BufferedImage processAsImage(BufferedImage image, CannyParams params) {

        ImageUInt8 gray = ConvertBufferedImage.convertFromSingle(image, null, ImageUInt8.class);
        ImageUInt8 edgeImage = gray.createSameShape();

        // Create a canny edge detector which will dynamically compute the threshold based on maximum edge intensity
        // It has also been configured to save the trace as a graph.  This is the graph created while performing
        // hysteresis thresholding.
        CannyEdge<ImageUInt8, ImageSInt16> canny = FactoryEdgeDetectors.canny(params.getBlurRadius(), params.isSaveTrace(), params.isDynamicThreshold(), ImageUInt8.class, ImageSInt16.class);

        // The edge image is actually an optional parameter.  If you don't need it just pass in null

        float threshHigh = params.getThreshHigh();
        float threshLow = params.getThreshLow();

        canny.process(gray, threshLow / 100, threshHigh / 100, edgeImage);

        // First get the contour created by canny
        List<EdgeContour> edgeContours = canny.getContours();
        // The 'edgeContours' is a tree graph that can be difficult to process.  An alternative is to extract
        // the contours from the binary image, which will produce a single loop for each connected cluster of pixels.
        // Note that you are only interested in external contours.
        List<Contour> contours = BinaryImageOps.contour(edgeImage, ConnectRule.EIGHT, null);

        // display the results
        BufferedImage visualBinary = VisualizeBinaryData.renderBinary(edgeImage, false, null);
        BufferedImage visualCannyContour = VisualizeBinaryData.renderContours(edgeContours, null, gray.width, gray.height, null);
        BufferedImage visualEdgeContour = new BufferedImage(gray.width, gray.height, BufferedImage.TYPE_INT_RGB);
        VisualizeBinaryData.renderExternal(contours, (int[]) null, visualEdgeContour);

        /*ListDisplayPanel panel = new ListDisplayPanel();
        panel.addImage(visualBinary, "Binary Edges from Canny");
        panel.addImage(visualCannyContour, "Canny Trace Graph");
        panel.addImage(visualEdgeContour, "Contour from Canny Binary");
        ShowImages.showWindow(panel, "Canny Edge", true);*/

        return visualCannyContour;
    }

    public CacheObject process(String id, CannyParams params) {
        BufferedImage image = imageService.getBufferedImage(id);
        BufferedImage binary = processAsImage(image, params);
        byte[] result = toByteArray(binary);
        return imageCache.addToCache(result);
    }

}
