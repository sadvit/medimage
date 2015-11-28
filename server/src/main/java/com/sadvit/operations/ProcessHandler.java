package com.sadvit.operations;

import java.awt.image.BufferedImage;

/**
 * Created by sadvit on 25.11.15.
 */
public interface ProcessHandler {

    BufferedImage handle(BufferedImage image, Object params);

}
