package com.sadvit.operations;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by sadvit on 25.11.15.
 */
public interface ProcessHandler {

    BufferedImage handle(BufferedImage image, Map params);

}
