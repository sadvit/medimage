package com.sadvit.geometry;

public class Point {

    private int X;
    private int Y;

    public void setX(int X) {
        this.X = X;
    }

    public int getX() {
        return X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getY() {
        return Y;
    }

    public Point(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public String toString() {
        return " X: " + getX() + " Y: " + getY();
    }

    public double getRadiusVector() {
        return Math.sqrt(X * X + Y * Y);
    }

    /**
     * Сравнивает две точки
      * @param point точка для сравнения
     * @return равны ли точки
     */
    public boolean equals(Point point) {
        if ((X == point.getX()) & (Y == point.getY())) {
            return true;
        }
        return false;
    }

}
