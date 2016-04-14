package com.sadvit.analysis.beetle;

import com.sadvit.analysis.geometry.GeometryObject;
import com.sadvit.analysis.geometry.Point;

/**
 * Жук работает с классом ByteImage.
 * После смерти возвращает как смысл жизни объект класса объект, который потом может быть проанализирован на корректность.
 * Ходит ТОЛЬКО по черным линиям.
 */
public class BeetleFinder {
	
	private final static int MAX_OBJ_LENGTH = 2000;
	public final static int WHITE_VALUE = 255;
    public final static int BLACK_VALUE = 0;

    private ByteImage byteImage;

    private int width;
    private int height;

    private GeometryObject object;
    private int length;
    private Point location;

    /**
     * Конструктор класса.
     * @param byteImage исходное изображение
     * @param location исходное положение жука
     */
    public BeetleFinder(ByteImage byteImage, Point location) {
        object = new GeometryObject();
        this.location = new Point(location.getX(), location.getY());
        this.byteImage = byteImage;
        width = byteImage.getWidth();
        height = byteImage.getHeight();
        length = 0;
        pointToObject();
        lifeCycle();
    }

    // Методы проверяют, можно ли ходить пиксели через одну координату.
    // Используются в спорных ситуациях, когда вокруг имеется несколько пикселей и жук не знает, на какой из них идти.
    // Если можно - возвращают значение пикселя 0 или 255.
    // Если нельзя - возвращают 2.
    private int get2UpPixel () {
        int X = location.getX();
        int Y = location.getY();
        if (Y - 2 >= 0) {
            return byteImage.getPixel(X, Y - 2);
        } else {
            return 2;
        }
    }
    private int get2DownPixel () {
        int X = location.getX();
        int Y = location.getY();
        if (Y + 2 < height) {
            return byteImage.getPixel(X, Y + 2);
        } else {
            return 2;
        }
    }
    private int get2LeftPixel () {
        int X = location.getX();
        int Y = location.getY();
        if (X - 2 >= 0) {
            return byteImage.getPixel(X - 2, Y);
        } else {
            return 2;
        }
    }
    private int get2RightPixel () {
        int X = location.getX();
        int Y = location.getY();
        if (X + 2 < width) {
            return byteImage.getPixel(X + 2, Y);
        } else {
            return 2;
        }
    }

    // Методы проверяют, можно ли ходить на окружающие с четырех сторон пиксели.
    // Если можно - возвращают значение пикселя 0 или 1.
    // Если нельзя - возвращают 2.
    private int getUpPixel () {
        int X = location.getX();
        int Y = location.getY();
        if (Y - 1 >= 0) {
            return byteImage.getPixel(X, Y - 1);
        } else {
            return 2;
        }
    }
    private int getDownPixel () {
        int X = location.getX();
        int Y = location.getY();
        if (Y + 1 < height) {
            return byteImage.getPixel(X, Y + 1);
        } else {
            return 2;
        }
    }
    private int getLeftPixel () {
        int X = location.getX();
        int Y = location.getY();
        if (X - 1 >= 0) {
            return byteImage.getPixel(X - 1, Y);
        } else {
            return 2;
        }
    }
    private int getRightPixel () {
        int X = location.getX();
        int Y = location.getY();
        if (X + 1 < width) {
            return byteImage.getPixel(X + 1, Y);
        } else {
            return 2;
        }
    }

    // Методы для перехода по четырем сторонам.
    // 1. Перемещают жука.
    // 2. Увеличивают длину пути жука.
    // 3. Заносят новую точку в коллекцию маршрута.
    private void up() {
        pointBlack();
        location.setY((location.getY() - 1));
        length++;
        pointToObject();
    }
    private void down() {
        pointBlack();
        location.setY(location.getY() + 1);
        length++;
        pointToObject();
    }
    private void left() {
        pointBlack();
        location.setX(location.getX() - 1);
        length++;
        pointToObject();
    }
    private void right() {
        pointBlack();
        location.setX(location.getX() + 1);
        length++;
        pointToObject();
    }

    // Метод сканирует окружиющие пиксели. Если находит белый, то сразу переходит на него и возвращает TRUE.
    // Если не находит белые пиксели, то остается на месте и возвращает FALSE.
    private boolean scanAndGo() {
        //System.out.println("length: " + length);
        if (length < MAX_OBJ_LENGTH) {
            // Ищем спаренные пиксели. Если они есть ,то сразу переходим. Если нет - то ходим по первому уровню.
            if ((getUpPixel() == BLACK_VALUE) & (get2UpPixel() == BLACK_VALUE)) {
                up();
                return true;
            }
            if ((getDownPixel() == BLACK_VALUE) & (get2DownPixel() == BLACK_VALUE)) {
                down();
                return true;
            }
            if ((getLeftPixel() == BLACK_VALUE) & (get2LeftPixel() == BLACK_VALUE)) {
                left();
                return true;
            }
            if ((getRightPixel() == BLACK_VALUE) & (get2RightPixel() == BLACK_VALUE)) {
                right();
                return true;
            }

            //--

            if (getUpPixel() == BLACK_VALUE) {
                up();
                return true;
            }
            if (getDownPixel() == BLACK_VALUE) {
                down();
                return true;
            }
            if (getRightPixel() == BLACK_VALUE) {
                right();
                return true;
            }
            if (getLeftPixel() == BLACK_VALUE) {
                left();
                return true;
            }
            return false;
        }
        return false;
    }

    // Метод заносит текущее положение жука в объект - маршрут путешествия.
    private void pointToObject() {
        object.addPoint(new Point(location.getX(), location.getY()));
    }

    // Метод делает белым пиксель на текущем положении жука .
    private void pointBlack() {
        byteImage.setPixel(location.getX(), location.getY(), WHITE_VALUE);
    }

    // Метод прокручивает жизнь жука, пока он не умрет (не окажется среди черных пикселей)
    private void lifeCycle() {
        while (scanAndGo());
    }

    /**
     * Метод возвращает объект - маршурут жука. Будет использоваться для дальнейшего анализа.
     * @return
     */
    public GeometryObject getObject() {
        return object;
    }


}
