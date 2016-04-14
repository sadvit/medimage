package com.sadvit.analysis.processing;


import com.sadvit.analysis.beetle.ByteImage;

/**
 * Класс, работающий с одним экземпляром класса ByteImage.
 * Расценивает его как БИНАРНОЕ изображение. Наследник ImageProcessor. Переделать под бинарное?
 * Делает все, что нужно для работы с бинарными изображениями. Именно БИНАРНЫМИ.
 */
public class BinaryImageProcessor {

    private ByteImage byteImage;

    public ByteImage getByteImage() {
        return byteImage;
    }

    public BinaryImageProcessor(ByteImage byteImage) {
        this.byteImage = byteImage;
    }

    public void setFillImage() {

        int foreground = 0;
        int background = 255;

        int width = byteImage.getWidth();
        int height = byteImage.getHeight();
        FloodFiller ff = new FloodFiller(byteImage);
        for (int y = 0; y < height; y++) {
            if (byteImage.getPixel(0, y) == background) ff.fill(0, y);
            if (byteImage.getPixel(width - 1, y) == background) ff.fill(width - 1, y);
        }
        for (int x = 0; x < width; x++) {
            if (byteImage.getPixel(x, 0) == background) ff.fill(x, 0);
            if (byteImage.getPixel(x, height - 1) == background) ff.fill(x, height - 1);
        }
        byte[] pixels = byteImage.getArrayImage();
        int n = width * height;
        for (int i = 0; i < n; i++) {
            if (pixels[i] == 127)
                pixels[i] = (byte) background;
            else
                pixels[i] = (byte) foreground;
        }
    }

    public void setLinesImage() {

        int yMin = 1, yMax = byteImage.getHeight() - 2, xMin = 1, xMax = byteImage.getWidth() - 2, width = byteImage.getWidth();

        int p1, p2, p3, p4, p5, p6, p7, p8, p9;

        ByteImageProcessor bip = new ByteImageProcessor(byteImage);

        int bgColor = 255;

        byte[] pixels2 = bip.getCopyByteImage().getArrayImage();

        int offset, v;
        int rowOffset = byteImage.getWidth();
        for (int y = yMin; y <= yMax; y++) {
            offset = xMin + y * width;
            p2 = pixels2[offset - rowOffset - 1] & 0xff;
            p3 = pixels2[offset - rowOffset] & 0xff;
            p5 = pixels2[offset - 1] & 0xff;
            p6 = pixels2[offset] & 0xff;
            p8 = pixels2[offset + rowOffset - 1] & 0xff;
            p9 = pixels2[offset + rowOffset] & 0xff;

            for (int x = xMin; x <= xMax; x++) {
                p1 = p2;
                p2 = p3;
                p3 = pixels2[offset - rowOffset + 1] & 0xff;
                p4 = p5;
                p5 = p6;
                p6 = pixels2[offset + 1] & 0xff;
                p7 = p8;
                p8 = p9;
                p9 = pixels2[offset + rowOffset + 1] & 0xff;


                v = p5;
                if (v != bgColor) {
                    if (!(p1 == bgColor || p2 == bgColor || p3 == bgColor || p4 == bgColor
                            || p6 == bgColor || p7 == bgColor || p8 == bgColor || p9 == bgColor))
                        v = bgColor;
                }


                byteImage.getArrayImage()[offset++] = (byte) v;
            }

        }

    }

    public void setErodeImage(int binaryCount) {
        int yMin = 1, yMax = byteImage.getHeight() - 2, xMin = 1, xMax = byteImage.getWidth() - 2, width = byteImage.getWidth();
        int p1, p2, p3, p4, p5, p6, p7, p8, p9;
        byte[] pixels2 = new ByteImageProcessor(byteImage).getCopyByteImage().getArrayImage();
        int offset, sum, count;

        int binaryForeground = 0;
        int binaryBackground = 255;

        for (int y = yMin; y <= yMax; y++) {
            offset = xMin + y * width;
            p2 = pixels2[offset - width - 1] & 0xff;
            p3 = pixels2[offset - width] & 0xff;
            p5 = pixels2[offset - 1] & 0xff;
            p6 = pixels2[offset] & 0xff;
            p8 = pixels2[offset + width - 1] & 0xff;
            p9 = pixels2[offset + width] & 0xff;
            for (int x = xMin; x <= xMax; x++) {
                p1 = p2;
                p2 = p3;
                p3 = pixels2[offset - width + 1] & 0xff;
                p4 = p5;
                p5 = p6;
                p6 = pixels2[offset + 1] & 0xff;
                p7 = p8;
                p8 = p9;
                p9 = pixels2[offset + width + 1] & 0xff;
                if (p5 == binaryBackground)
                    sum = binaryBackground;
                else {
                    count = 0;
                    if (p1 == binaryBackground) count++;
                    if (p2 == binaryBackground) count++;
                    if (p3 == binaryBackground) count++;
                    if (p4 == binaryBackground) count++;
                    if (p6 == binaryBackground) count++;
                    if (p7 == binaryBackground) count++;
                    if (p8 == binaryBackground) count++;
                    if (p9 == binaryBackground) count++;
                    if (count >= binaryCount)
                        sum = binaryBackground;
                    else
                        sum = binaryForeground;
                }
                byteImage.getArrayImage()[offset++] = (byte) sum;
            }
        }
    }

    public void setDilateImage(int binaryCount) {
        int yMin = 1, yMax = byteImage.getHeight() - 2, xMin = 1, xMax = byteImage.getWidth() - 2, width = byteImage.getWidth();

        int p1, p2, p3, p4, p5, p6, p7, p8, p9;

        byte[] pixels2 = new ByteImageProcessor(byteImage).getCopyByteImage().getArrayImage();
        int offset, sum, count;

        int binaryForeground = 0;
        int binaryBackground = 255;
        for (int y = yMin; y <= yMax; y++) {
            offset = xMin + y * width;
            p2 = pixels2[offset - width - 1] & 0xff;
            p3 = pixels2[offset - width] & 0xff;
            p5 = pixels2[offset - 1] & 0xff;
            p6 = pixels2[offset] & 0xff;
            p8 = pixels2[offset + width - 1] & 0xff;
            p9 = pixels2[offset + width] & 0xff;

            for (int x = xMin; x <= xMax; x++) {
                p1 = p2;
                p2 = p3;
                p3 = pixels2[offset - width + 1] & 0xff;
                p4 = p5;
                p5 = p6;
                p6 = pixels2[offset + 1] & 0xff;
                p7 = p8;
                p8 = p9;
                p9 = pixels2[offset + width + 1] & 0xff;


                if (p5 == binaryForeground)
                    sum = binaryForeground;
                else {
                    count = 0;
                    if (p1 == binaryForeground) count++;
                    if (p2 == binaryForeground) count++;
                    if (p3 == binaryForeground) count++;
                    if (p4 == binaryForeground) count++;
                    if (p6 == binaryForeground) count++;
                    if (p7 == binaryForeground) count++;
                    if (p8 == binaryForeground) count++;
                    if (p9 == binaryForeground) count++;
                    if (count >= binaryCount)
                        sum = binaryForeground;
                    else
                        sum = binaryBackground;
                }

                byteImage.getArrayImage()[offset++] = (byte) sum;
            }
        }

    }

    public void fillBorder() {
        for (int i = 0; i < byteImage.getWidth(); i++) {
            byteImage.setPixel(i, 0, 255);
            byteImage.setPixel(i, 1, 255);
            byteImage.setPixel(i, byteImage.getHeight() - 1, 255);
            byteImage.setPixel(i, byteImage.getHeight() - 2, 255);
        }
        for (int i = 0; i < byteImage.getHeight(); i++) {
            byteImage.setPixel(0, i, 255);
            byteImage.setPixel(1, i, 255);
            byteImage.setPixel(byteImage.getWidth() - 1, i, 255);
            byteImage.setPixel(byteImage.getWidth() - 2, i, 255);
        }
    }

}
