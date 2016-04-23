package com.sadvit.analysis.geometry;


/**
 * Класс, проверяющий является ли объект артефактом.
 */
public class GeometryObjectChecker {

    private final static int MAX_OBJ_LENGTH = 2000;
    private final static int MIN_OBJ_LENGTH = 800;

    private GeometryObject object;

    public GeometryObjectChecker(GeometryObject object) {
        this.object = object;
    }

    // обычная проверка на длину объекта
    public boolean simpleCheck() {
        if ((object.getLength() > MIN_OBJ_LENGTH) & (object.getLength()) < MAX_OBJ_LENGTH) return true;
        return false;
    }

    // Проверяет, замкнут ли объект
    public boolean isClosed() {
        Point first = object.getPointsArray().getPoint(0);
        Point next = object.getPointsArray().getPoint(object.getLength() - 1);

        int x1 = first.getX();
        int x2 = next.getX();

        int y1 = first.getY();
        int y2 = next.getY();

        if ((x2 - x1 > 2) || (y2 - y1 > 2)) {
            return false;
        }

        return true;
    }

    // Проверяет, есть ли у объекта длинные прямые линии.
    public boolean fullCheck() {
        PointsArray pointsArray = object.getPointsArray();
        int length = 80;

        for (int i = 0; i < pointsArray.getLength() - length; i++) {
            int resX = recX(pointsArray, i, i + length, 0);
            if (resX > 60) return false;
            i += resX;
        }

        for (int i = 0; i < pointsArray.getLength() - length; i++) {
            int resY = recY(pointsArray, i, i + length, 0);
            if (resY > 60) return false;
            i += resY;
        }

        return true;
    }

    // Если дошли до рамок - возвращаем количество равных иксов, тоже и при неравенстве со следующим.
    private int recX(PointsArray pointsArray, int current, int max, int count) {
        if (current == max) {
            return count;
        }
        Point curr = pointsArray.getPoint(current);
        current++;
        Point next = pointsArray.getPoint(current);
        if (curr.getX() == next.getX()) {
            count++;
            return recX(pointsArray, current, max, count);
        }
        return count;
    }

    private int recY(PointsArray pointsArray, int current, int max, int count) {
        if (current == max) {
            return count;
        }
        Point curr = pointsArray.getPoint(current);
        current++;
        Point next = pointsArray.getPoint(current);
        if (curr.getY() == next.getY()) {
            count++;
            return recY(pointsArray, current, max, count);
        }
        return count;
    }

}
