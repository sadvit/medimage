package com.sadvit.recognizer;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author meskill
 */
public class ImageProcess {

    static public final byte Sobel[][] = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
    static public final byte Scharr[][] = {{3, 0, -3}, {10, 0, -10}, {3, 0, -3}};
    static public final byte Prewitt[][] = {{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}};

    static public BufferedImage edgeDetection(BufferedImage source, byte[][] operator) {
        int max = 0;
        for (int i = 0; i < operator.length; i++) {
            max += Math.abs(operator[i][0]);
        }
        max <<= 8;
        max /= operator.length;
        max *= max;
        int s = operator.length;
        int width = source.getWidth() - s;
        int height = source.getHeight() - s;
        BufferedImage result = new BufferedImage(width + s, height + s, BufferedImage.TYPE_BYTE_BINARY);
        for (int i = s; i < width; i++) {
            for (int j = s; j < height; j++) {
                int gx = 0, gy = 0;
                for (int k = 0; k < operator.length; k++) {
                    for (int l = 0; l < operator.length; l++) {
                        int buf = source.getRGB(i - s + k, j - s + l);
                        buf = ((byte) buf + (byte) (buf >> 8) + (byte) (buf >> 16));
                        gx += operator[k][l] * buf;
                        gy += operator[l][k] * buf;
                    }
                }
                if (gx * gx + gy * gy > max) {
                    result.setRGB(i, j, -1);
                }
            }
        }
        return result;
    }

    static public BufferedImage process(BufferedImage source) throws IOException {
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        int width = source.getWidth() - 2;
        int height = source.getHeight() - 2;
        int error = 20;
        for (int i = 1; i < width; i++) {
            for (int j = 1; j < height; j++) {
                if (source.getRGB(i, j) == -1) {
                    result.setRGB(i, j, -1);
                    int k;
                    for (k = j + 1; k < height; k++) {
                        if (source.getRGB(i - 1, k) == -1 || source.getRGB(i + 1, k) == -1) {
                            break;
                        }
                    }
                    k += 3;
                    if (k++ - j < error) {
                        while (j <= k) {
                            result.setRGB(i, j++, -1);
                        }
                    }
                    break;
                }
            }
        }
        for (int i = 1; i < width; i++) {
            for (int j = height; j > 0; j--) {
                if (source.getRGB(i, j) == -1) {
                    result.setRGB(i, j, -1);
                    int k;
                    for (k = j - 1; k > 0; k--) {
                        if (source.getRGB(i - 1, k) == -1 || source.getRGB(i + 1, k) == -1) {
                            break;
                        }
                    }
                    k -= 3;
                    if (j - k-- < error) {
                        while (j >= k) {
                            result.setRGB(i, j--, -1);
                        }
                    }
                    break;
                }
            }
        }
        for (int i = 1; i < height; i++) {
            for (int j = width; j > 0; j--) {
                if (source.getRGB(j, i) == -1) {
                    result.setRGB(j, i, -1);
                    int k;
                    for (k = j - 1; k > 0; k--) {
                        if (source.getRGB(k, i - 1) == -1 || source.getRGB(k, i + 1) == -1) {
                            break;
                        }
                    }
                    k -= 3;
                    if (j - k-- < error) {
                        while (j >= k) {
                            result.setRGB(j--, i, -1);
                        }
                    }
                    break;
                }
            }
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                if (source.getRGB(j, i) == -1) {
                    result.setRGB(j, i, -1);
                    int k;
                    for (k = j + 1; k < width; k++) {
                        if (source.getRGB(k, i - 1) == -1 || source.getRGB(k, i + 1) == -1) {
                            break;
                        }
                    }
                    k += 3;
                    if (k++ - j < error) {
                        while (j <= k) {
                            result.setRGB(j++, i, -1);
                        }
                    }
                    break;
                }
            }
        }
        return result;
    }
    private static final byte path[] = {0, -1, 1, 0, 0, 1, -1, 0, 0, -1, 1, 0, 0, 1};

    static public BufferedImage pxEdge(BufferedImage source, int startX, int startY) {
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        int width = source.getWidth() - 2;
        int height = source.getHeight() - 2;
        for (int i = startY; i < height; i++) {
            for (int j = startX; j < width; j++) {
                if (source.getRGB(j, i) == -1 && result.getRGB(j, i) != -1) {
                    result.setRGB(j, i, -1);
                    byte grad = 0;
                    int x = j;
                    int y = i;
                    int number = 0;
                    do {
                        byte end = (byte) (grad + 8);
                        while (grad < end) {
                            if (source.getRGB(j + path[grad], i + path[grad + 1]) == -1) {
                                j += path[grad];
                                i += path[grad + 1];
                                result.setRGB(j, i, -1);
                                number++;
                                break;
                            }
                            grad += 2;
                        }
                        grad -= 2;
                        if (grad < 0) {
                            grad = 6;
                        }
                        if (grad > 6) {
                            grad -= 8;
                        }
                    } while (j != x || i != y);
                    if (number > 500) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public static Point getIntersect(BufferedImage img, Point start, float vectorX, float vectorY) {
        float x = start.x;
        float y = start.y;

        x += vectorX;
        y += vectorY;
        try {
            do {
                x += vectorX;
                y += vectorY;
            } while (img.getRGB((int) x, (int) y) != -1 && img.getRGB((int) x + 1, (int) y) != -1);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
        return new Point((int) x, (int) y);
    }

    public static void drawLine(BufferedImage img, Point start, Point end) {
        float dx = end.x - start.x;
        float dy = end.y - start.y;
        float length = (float) start.distance(end);
        dx /= length;
        dy /= length;
        float x = start.x;
        float y = start.y;
        while (Math.abs(x - end.x) > 3 || Math.abs(y - end.y) > 3) {
            x += dx;
            y += dy;
            img.setRGB((int) x, (int) y, -1);
        }
    }

    public static BufferedImage normalize(BufferedImage img) {
        Point extreme[] = getExtremePoints(img);
        Point left = extreme[0];
        Point right = extreme[1];
        Point center = extreme[2];
        Point up = extreme[3];
        Point down = extreme[4];
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage result = new BufferedImage(2 * Math.max(width, height), 2 * Math.max(width, height), BufferedImage.TYPE_BYTE_BINARY);
        int x = up.x - center.x, y = center.y - up.y;
        double fi = Math.acos((y) / Math.hypot(x, y));
        double cosfi = Math.cos(fi);
        double sinfi = Math.sin(fi);
        if (up.y > center.y) {
            sinfi = -sinfi;
        }

        for (int i = 2; i < width; i++) {
            for (int j = 2; j < height; j++) {
                if (img.getRGB(i, j) == -1) {
                    x = (int) ((i - center.x) * cosfi - (j - center.y) * sinfi);
                    y = (int) ((i - center.x) * sinfi + (j - center.y) * cosfi);
                    result.setRGB(x + center.x + 50, y + center.y + 50, -1);
                }
            }
        }
        return result;
    }

    public static BufferedImage prepareForRecognize(BufferedImage img) {
        BufferedImage result = ImageProcess.rescale(img);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        result = op.filter(result, null);
        result = ImageProcess.edgeDetection(result, ImageProcess.Prewitt);
        result = ImageProcess.pxEdge(result, 20, result.getHeight() / 2);
        result = ImageProcess.pxEdge(result, result.getWidth() / 2, 20);
        result = ImageProcess.smooth(result, null, 5);
        result = ImageProcess.smooth(result, result, 11);
        result = ImageProcess.smooth(result, result, 30);
        result = ImageProcess.smooth(result, result, 45);
        result = ImageProcess.pxEdge(result, 20, result.getHeight() / 2);
        return result;
    }

    public static Point[] getExtremePoints(BufferedImage img) {
        int width = img.getWidth() - 2;
        int height = img.getHeight() - 2;
        Point start = new Point();
        for (int i = height / 2; i < height; i++) {
            for (int j = 20; j < width; j++) {
                if (img.getRGB(j, i) == -1) {
                    start.x = j;
                    start.y = i;
                    i = height;
                    break;
                }
            }
        }
        Point next = new Point(start);                            //find a length of bactery
        int max = 0;
        Point left = new Point();
        Point right = new Point();
        Point current = new Point();
        byte nextgrad = 0;
        do {
            int number = 0;
            current.setLocation(next);
            boolean first = true;
            byte grad = nextgrad;
            do {
                byte end = (byte) (grad + 8);
                while (grad < end) {
                    if (img.getRGB(current.x + path[grad], current.y + path[grad + 1]) == -1) {
                        current.translate(path[grad], path[grad + 1]);
                        if (first) {
                            nextgrad = grad;
                            nextgrad -= 2;
                            if (nextgrad < 0) {
                                nextgrad = 6;
                            }
                            if (nextgrad > 6) {
                                nextgrad -= 8;
                            }
                            next.setLocation(current);
                            first = false;
                        }
                        int buf = (int) current.distanceSq(next);
                        if (buf > max) {
                            left.setLocation(next);
                            right.setLocation(current);
                            max = buf;
                        }
                        number++;
                        break;
                    }
                    grad += 2;
                }
                grad -= 2;
                if (grad < 0) {
                    grad = 6;
                }
                if (grad > 6) {
                    grad -= 8;
                }
            } while (!current.equals(start));
        } while (!next.equals(start));

        float lengthX = right.x - left.x;
        float lengthY = right.y - left.y;
        float length = (float) Math.hypot(lengthX, lengthY);
        lengthX /= length;
        lengthY /= length;
        float heightX = -lengthY;
        float heightY = lengthX;

        float x = right.x - 30 * lengthX;
        float y = right.y - 30 * lengthY;
        current.setLocation((int) x, (int) y);
        Point p1 = getIntersect(img, current, heightX, heightY);
        Point p2 = getIntersect(img, current, -heightX, -heightY);
        x = left.x + 30 * lengthX;
        y = left.y + 30 * lengthY;
        current.setLocation((int) x, (int) y);
        Point p3 = getIntersect(img, current, heightX, heightY);
        Point p4 = getIntersect(img, current, -heightX, -heightY);
        Point center = new Point((left.x + right.x) / 2, (left.y + right.y) / 2);
        Point down = getIntersect(img, center, heightX, heightY);
        Point up = getIntersect(img, center, -heightX, -heightY);
        if (p1.distanceSq(p2) < p3.distanceSq(p4)) {
            Point buf = left;
            left = right;
            right = buf;
            buf = down;
            down = up;
            up = buf;
        }
        if (left.x < right.x) {
        }
        return new Point[]{left, right, center, up, down};
    }

    private static double MethodHeightWidth(BufferedImage img, Point left, Point right, Point center, Point up, Point down) {
        return up.distance(down) / left.distance(right);
    }

    private static double MethodAsymmetry(BufferedImage img, Point left, Point right, Point center, Point up, Point down) {
        float lengthX = right.x - left.x;
        float lengthY = right.y - left.y;
        float length = (float) Math.hypot(lengthX, lengthY);
        lengthX /= length;
        lengthY /= length;
        float heightX = -lengthY;
        float heightY = lengthX;
        double upmax = 0;
        double downmax = 0;
        Point current = new Point(center);
        while (current.distanceSq(right) > 900) {
            current.translate((int) (10 * lengthX), (int) (10 * lengthY));
            upmax = Math.max(upmax, current.distance(getIntersect(img, current, heightX, heightY)));
            downmax = Math.max(downmax, current.distance(getIntersect(img, current, -heightX, -heightY)));
        }
        if (upmax < downmax) {
            return upmax / downmax;
        } else {
            return downmax / upmax;
        }
    }

    private static double MethodCurvature(BufferedImage img, Point left, Point right, Point center, Point up, Point down) {
        int width = img.getWidth() - 2;
        int height = img.getHeight() - 2;
        Point start = new Point();
        for (int i = height / 2; i < height; i++) {
            for (int j = 20; j < width; j++) {
                if (img.getRGB(j, i) == -1) {
                    start.x = j;
                    start.y = i;
                    i = height;
                    break;
                }
            }
        }
        Point current = new Point(start);
        int y[] = new int[]{start.y, start.y, start.y};
        int x = start.x;
        double maxK = 0, minK = Double.MAX_VALUE;
        byte grad = 0;
        do {
            byte end = (byte) (grad + 8);
            while (grad < end) {
                if (img.getRGB(current.x + path[grad], current.y + path[grad + 1]) == -1) {
                    current.translate(path[grad], path[grad + 1]);
                    if (current.x != x) {
                        x = current.x;
                        y[0] = y[1];
                        y[1] = y[2];
                        y[2] = current.y;
                        double K = Math.abs(y[2] - 2 * y[1] + y[0]) / Math.pow(1 + (y[2] - y[1]) * (y[2] - y[1]), 1.5);
                        if (K > 0) {
                            maxK = Math.max(maxK, K);
                            minK = Math.min(minK, K);
                        }
                    }
                    break;
                }
                grad += 2;
            }
            grad -= 2;
            if (grad < 0) {
                grad = 6;
            }
            if (grad > 6) {
                grad -= 8;
            }
        } while (!current.equals(start));
        return minK / maxK * 100;
    }

    private static double MethodDensity(BufferedImage img, Point left, Point right, Point center, Point up, Point down) {
        int width = img.getWidth() - 2;
        int height = img.getHeight() - 2;
        int length = 0, area = 0;
        for (int i = 2; i < width; i++) {
            int start = height, end = 0;
            for (int j = 2; j < height; j++) {
                if (img.getRGB(i, j) == -1) {
                    length++;
                    start = Math.min(start, j);
                    end = Math.max(end, j);
                }
            }
            if (start < height) {
                area += end - start;
            }
        }
        return (float) length * length / area;
    }

    private static double MethodMomentsOfInertia(BufferedImage img, Point left, Point right, Point center, Point up, Point down) {
        int width = img.getWidth() - 2;
        int height = img.getHeight() - 2;
        int length = 0;
        int allx = 0, ally = 0;
        for (int i = 2; i < width; i++) {
            for (int j = 2; j < height; j++) {
                if (img.getRGB(i, j) == -1) {
                    length++;
                    allx += i;
                    ally += j;
                }
            }
        }
        float Xc = (float) allx / length;
        float Yc = (float) ally / length;

        float Ux = 0, Uy = 0, Uxy = 0;
        for (int i = 2; i < width; i++) {
            for (int j = 2; j < height; j++) {
                if (img.getRGB(i, j) == -1) {
                    Ux += (i - Xc) * (i - Xc);
                    Uy += (j - Yc) * (j - Yc);
                    Uxy += (i - Xc) * (j - Yc);
                }
            }
        }
        return ((Ux + Uy) / 2 + Math.sqrt(Math.pow(Ux - Uy, 2) * 1 / 4 + Math.pow(Uxy, 2)))
                / ((Ux + Uy) / 2 - Math.sqrt(Math.pow(Ux - Uy, 2) * 1 / 4 + Math.pow(Uxy, 2)));
    }

    private static double MethodTrapecia(BufferedImage img, Point left, Point right, Point center, Point up, Point down) {
        float lengthX = right.x - left.x;
        float lengthY = right.y - left.y;
        float length = (float) Math.hypot(lengthX, lengthY);
        lengthX /= length;
        lengthY /= length;
        float heightX = up.x - center.x;
        float heightY = up.y - center.y;
        Point buf = new Point((int) (center.x + heightX / 3), (int) (center.y + heightY / 3));
        double A = getIntersect(img, buf, lengthX, lengthY).distance(buf);
        buf.translate((int) (heightX / 3), (int) (heightY / 3));
        double B = getIntersect(img, buf, lengthX, lengthY).distance(buf);
        return A / B;
    }

    private static double MethodTrapeciaAngle(BufferedImage img, Point left, Point right, Point center, Point up, Point down) {
        float lengthX = right.x - left.x;
        float lengthY = right.y - left.y;
        float length = (float) Math.hypot(lengthX, lengthY);
        lengthX /= length;
        lengthY /= length;
        float heightX = up.x - center.x;
        float heightY = up.y - center.y;
        Point buf = new Point((int) (center.x + heightX / 3), (int) (center.y + heightY / 3));
        Point A = getIntersect(img, buf, lengthX, lengthY);
        buf.translate((int) (heightX / 3), (int) (heightY / 3));
        Point B = getIntersect(img, buf, lengthX, lengthY);
        return ((A.x - left.x) * lengthX + (A.y - left.y) * lengthY) * ((B.x - A.x) * lengthX + (B.y - A.y) * lengthY) / A.distance(left) / B.distance(A);
    }

    private static double MethodSquares(BufferedImage img, Point left, Point right, Point center, Point up, Point down) {
        int width = img.getWidth() - 2;
        int height = img.getHeight() - 2;
        float lengthX = right.x - left.x;
        float lengthY = right.y - left.y;
        float length = (float) Math.hypot(lengthX, lengthY);
        lengthX /= length;
        lengthY /= length;
        float heightX = down.x - up.x;
        float heightY = down.y - up.y;
        float h = (float) (float) Math.hypot(heightX, heightY);
        heightX /= h;
        heightY /= h;
        Point current = new Point(left);
        double maxS = 0;
        byte grad = 0;
        do {
            byte end = (byte) (grad + 8);
            while (grad < end) {
                if (img.getRGB(current.x + path[grad], current.y + path[grad + 1]) == -1) {
                    current.translate(path[grad], path[grad + 1]);
                    try {
                        double a = current.distance(getIntersect(img, current, heightX, heightY));
                        double b = current.distance(getIntersect(img, current, lengthX, lengthY));
                        maxS=Math.max(maxS, a*b);
                    } catch (Exception e) {
                    }
                    break;
                }
                grad += 2;
            }
            grad -= 2;
            if (grad < 0) {
                grad = 6;
            }
            if (grad > 6) {
                grad -= 8;
            }
        } while (!current.equals(left));
        return maxS/length/h;
    }

    /**
     *
     * @param img
     * @return double[]
     */

    public static double[] getParams(BufferedImage img) {
        Point extreme[] = getExtremePoints(img);
        Point left = extreme[0];
        Point right = extreme[1];
        Point center = extreme[2];
        Point up = extreme[3];
        Point down = extreme[4];
        double params[] = new double[8];
        params[0] = MethodDensity(img, left, right, center, up, down);
        params[1] = MethodSquares(img, left, right, center, up, down);
        params[2] = MethodAsymmetry(img, left, right, center, up, down);
        params[3] = MethodCurvature(img, left, right, center, up, down);
        params[4] = MethodTrapeciaAngle(img, left, right, center, up, down);
        params[5] = MethodTrapecia(img, left, right, center, up, down);
        params[6] = MethodMomentsOfInertia(img, left, right, center, up, down);
        params[7] = MethodHeightWidth(img, left, right, center, up, down);
        return params;
    }

    public static double[] getParamsForNeuralNetwork(BufferedImage img) {
        Point extreme[] = getExtremePoints(img);
        Point left = extreme[0];
        Point right = extreme[1];
        Point center = extreme[2];
        Point up = extreme[3];
        Point down = extreme[4];

        double length = left.distance(right);
        Point p[] = new Point[30];
        int n = p.length / 2;
        float vectorX = up.x - center.x;
        float vectorY = up.y - center.y;
        float g = (float) (Math.PI / 2 / (n + 1));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                float buf = vectorX;
                vectorX = (float) (buf * Math.cos(g) - vectorY * Math.sin(g));
                vectorY = (float) (buf * Math.sin(g) + vectorY * Math.cos(g));
                float vector = (float) Math.hypot(vectorX, vectorY);
                vectorX /= vector;
                vectorY /= vector;
                p[n * i + j] = getIntersect(img, new Point((int) (center.x + 20 * vectorX), (int) (center.y + 20 * vectorY)), vectorX, vectorY);
                drawLine(img, center, p[n * i + j]);
            }
            vectorX = down.x - center.x;
            vectorY = down.y - center.y;
        }
        double h = down.distance(up);
        double params[] = new double[p.length];
        for (int i = 0; i < n; i++) {
            try {
                params[i] = p[i].distance(center) / length;
                params[n + i] = p[n + i].distance(center) / h;
            } catch (NullPointerException e) {
            }
        }
        drawLine(img, down, up);
        drawLine(img, left, right);
        return params;
    }

    static public BufferedImage rescale(BufferedImage source) {
        int width = source.getWidth() - 1;
        int height = source.getHeight() - 1;
        int delta = 0;
        for (int i = 1; i < width; i++) {
            for (int j = 1; j < height; j++) {
                Color c = new Color(source.getRGB(i, j));
                delta += c.getRed() / 3 + c.getGreen() / 3 + c.getGreen() / 3;
            }
        }
        delta /= width * height;
        delta = 128 - delta;
        return new RescaleOp(1.08f, delta, null).filter(source, null);
    }

    private static void Lagrange(BufferedImage bi, int x[], int y[], boolean mode) {
        int k = x[0];
        int start = y[0];
        int end;
        while (k <= x[3]) {
            double r = 0;
            for (int l = 0; l < 4; l++) {
                double buf = 1;
                for (int m = 0; m < 4; m++) {
                    if (l != m && x[l] != x[m]) {
                        buf *= ((double) k - x[m]) / ((double) x[l] - x[m]);
                    }
                }
                r += buf * y[l];
            }
            if (start > (int) r) {
                end = start;
                start = (int) r;
            } else {
                end = (int) r;
            }
            if (start < 0) {
                start = 3;
            }
            if (mode) {
                while (start <= end) {
                    bi.setRGB(start++, k, -1);
                }
            } else {
                while (start <= end) {
                    bi.setRGB(k, start++, -1);
                }
            }
            start = (int) r;
            k++;
        }
    }

    public static BufferedImage smooth(final BufferedImage source, BufferedImage resultImg, final int size) {
        if (resultImg == null) {
            resultImg = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        }
        final BufferedImage result = resultImg;
        final int width = source.getWidth() - 2;
        final int height = source.getHeight() - 2;
        final CountDownLatch cdl = new CountDownLatch(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int k = 0;
                int x[] = new int[4];
                int y[] = new int[4];
                for (int i = 2; i < width; i += size) {
                    for (int j = 2; j < height; j++) {
                        if (source.getRGB(i, j) == -1) {
                            x[k] = i;
                            y[k] = j;
                            k++;
                            if (k == 4) {
                                i -= size;
                                Lagrange(result, x, y, false);
                                k = 0;
                            }
                            break;
                        }
                    }
                }
                cdl.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int k = 0;
                int x[] = new int[4];
                int y[] = new int[4];
                for (int i = 2; i < width; i += size) {
                    for (int j = height; j > 2; j--) {
                        if (source.getRGB(i, j) == -1) {
                            x[k] = i;
                            y[k] = j;
                            k++;
                            if (k == 4) {
                                i -= size;
                                Lagrange(result, x, y, false);
                                k = 0;
                            }
                            break;
                        }
                    }
                }
                cdl.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int k = 0;
                int x[] = new int[4];
                int y[] = new int[4];
                for (int i = 2; i < height; i += size) {
                    for (int j = 2; j < width; j++) {
                        if (source.getRGB(j, i) == -1) {
                            x[k] = j;
                            y[k] = i;
                            k++;
                            if (k == 4) {
                                i -= size;
                                Lagrange(result, y, x, true);
                                k = 0;
                            }
                            break;
                        }
                    }
                }
                cdl.countDown();
            }
        }).start();
        int k = 0;
        int x[] = new int[4];
        int y[] = new int[4];
        for (int i = 2; i < height; i += size) {
            for (int j = width; j > 2; j--) {
                if (source.getRGB(j, i) == -1) {
                    x[k] = j;
                    y[k] = i;
                    k++;
                    if (k == 4) {
                        i -= size;
                        Lagrange(result, y, x, true);
                        k = 0;
                    }
                    break;
                }
            }
        }
        try {
            cdl.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(ImageProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
