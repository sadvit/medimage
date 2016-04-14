package com.sadvit.analysis.geometry;

/**
 * Массив точек. Будет представлять собой любой объект.
 */
public class PointsArray {

    private int[] x;
    private int[] y;

    /**
     * Возвращает копию нашего массива Х.
     * @return
     */
    public int[] getCopyX() {
        int[] result = new int[position];
        for (int i = 0; i < position; i++) {
            result[i] = x[i];
        }
        return result;
    }

    public int[] getCopyY() {
        int[] result = new int[position];
        for (int i = 0; i < position; i++) {
            result[i] = y[i];
        }
        return result;
    }

    private int maxLength;
    private int position;

    public int getLength() {
        return position;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public PointsArray(int[] x, int[] y, int position) {
        this.x = x;
        this.y = y;
        this.maxLength = x.length;
        this.position = position;
    }

    public void addPoint(Point p) {
        if (position < maxLength) {
            x[position] = p.getX();
            y[position] = p.getY();
            position++;
        } else {
            System.out.println("index out of bound");
        }
    }

    public Point getPoint(int position) {
        if (position < maxLength) {
            return new Point(x[position], y[position]);
        } else {
            System.out.println("index out of bound");
            System.out.println("кто то запросил " + position);
            return null;
        }

    }

}
