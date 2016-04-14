package com.sadvit.analysis.beetle;

import com.sadvit.analysis.geometry.GeometryObject;
import com.sadvit.analysis.geometry.GeometryObjectChecker;
import com.sadvit.analysis.geometry.Point;

/**
 * Контроллер жука - создает жука на указанном изображении, и выдает изображение - результат его работы.
 */
public class BeetleController {
	
    // Функция возвращает первый правильный объект на изображении.
    public static GeometryObject seekGeometryObject(ByteImage original) {
        // забираем и анализируем объекты до тех пор, пока не найдем нужный.
        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {
                if (original.getPixel(i, j) == BeetleFinder.BLACK_VALUE) {
                    GeometryObject object = new BeetleFinder(original, new Point(i, j)).getObject();
                    if (object != null) {
                        GeometryObjectChecker objectChecker = new GeometryObjectChecker(object);
                        if (objectChecker.isClosed()) {
                            if (objectChecker.simpleCheck()) {
                                if (objectChecker.fullCheck()) {
                                    return object;
                                }
                            }
                        }
                    }
                } else {
                    original.getPixel(i, j);
                }
            }
        }
        return null;
    }

}
