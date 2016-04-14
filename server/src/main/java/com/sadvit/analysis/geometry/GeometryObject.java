package com.sadvit.analysis.geometry;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Класс объект. Выполняет все операции с множеством точек - объектом.
 */
public class GeometryObject {
	
	private final static int MAX_OBJ_LENGTH = 2000;

    private PointsArray pointsArray;

    public PointsArray getPointsArray() {
        return pointsArray;
    }

    public GeometryObject() {
        pointsArray = new PointsArray(new int[MAX_OBJ_LENGTH], new int[MAX_OBJ_LENGTH], 0);
    }

    public void addPoint(Point p) {
        pointsArray.addPoint(p);
    }

    public int getLength() {
        return pointsArray.getLength();
    }

    public int getMinX() {
        int min = pointsArray.getPoint(0).getX();
        for (int i = 0; i < pointsArray.getLength(); i++) {
            if (pointsArray.getPoint(i).getX() < min) {
                min = pointsArray.getPoint(i).getX();
            }
        }
        return min;
    }

    public int getMaxX() {
        int max = pointsArray.getPoint(0).getX();
        for (int i = 0; i < pointsArray.getLength(); i++) {
            if (pointsArray.getPoint(i).getX() > max) {
                max = pointsArray.getPoint(i).getX();
            }
        }
        return max;
    }

    public int getMinY() {
        int min = pointsArray.getPoint(0).getY();
        for (int i = 0; i < pointsArray.getLength(); i++) {
            if (pointsArray.getPoint(i).getY() < min) {
                min = pointsArray.getPoint(i).getY();
            }
        }
        return min;
    }

    public int getMaxY() {
        int max = pointsArray.getPoint(0).getY();
        for (int i = 0; i < pointsArray.getLength(); i++) {
            if (pointsArray.getPoint(i).getY() > max) {
                max = pointsArray.getPoint(i).getY();
            }
        }
        return max;
    }

    public void translateCoordinates() {
        int minX = getMinX();
        int minY = getMinY();
        PointsArray pArray = new PointsArray(new int[MAX_OBJ_LENGTH], new int[MAX_OBJ_LENGTH], 0);
        for (int i = 0; i < pointsArray.getLength(); i++) {
            Point swap = pointsArray.getPoint(i);
            Point newPoint = new Point(swap.getX() - minX, swap.getY() - minY);
            pArray.addPoint(newPoint);
        }
        pointsArray = pArray;
    }

    /**
     * Рисует множество точек - объект.
     *
     * @return возвращает рисунок множества точек.
     */
    public BufferedImage getBufferedImage() {
        int maxX = getMaxX();
        int maxY = getMaxY();
        BufferedImage bf = new BufferedImage(maxX + 20, maxY + 20, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < pointsArray.getLength(); i++) {
            Point p = pointsArray.getPoint(i);
            bf.setRGB(p.getX() + 10, p.getY() + 10, Color.WHITE.getRGB());
        }
        return bf;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < pointsArray.getLength(); i++) {
            res += pointsArray.getPoint(i) + "\r\n";
        }
        return res;
    }

    /**
     * Функция печатает объект координатами точек
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * Метод сортирует координаты точек по Х.
     *
     * @return новый объект, с отсортированными по Х координатами.
     */
    public GeometryObject getSortedForX() {

        int min, tempx, tempy;

        int[] xpoints = pointsArray.getCopyX();
        int[] ypoints = pointsArray.getCopyY();

        for (int index = 0; index < xpoints.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < xpoints.length; scan++)
                if (xpoints[scan] < xpoints[min])
                    min = scan;

            // Swap the values
            tempx = xpoints[min];
            tempy = ypoints[min];

            xpoints[min] = xpoints[index];
            ypoints[min] = ypoints[index];

            xpoints[index] = tempx;
            ypoints[index] = tempy;

        }

        GeometryObject res = new GeometryObject();
        for (int i = 0; i < xpoints.length; i++) {
            res.addPoint(new Point(xpoints[i], ypoints[i]));
        }

        return res;
    }

}
