package com.sadvit.analysis.beetle;

/**
 * Все изображения, используемые в программе, хранятся в виде массива байт.
 * Класс может предоставлять пиксели из байт массива, а также их устанавливать.
 */
public class ByteImage {

	private byte[] arrayImage;
	
	public int width;
	public int height;

	public byte[] getArrayImage() {
		return arrayImage;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Создаем класс по байт - массиву и размерам изображения.
	 */
	public ByteImage(byte[] array, int width, int height) {
		arrayImage = new byte[array.length];
		System.arraycopy(array, 0, arrayImage, 0, array.length);
		this.width = width;
		this.height = height;
	}

	public ByteImage(int width, int height) {
		arrayImage = new byte[width * height];
		this.width = width;
		this.height = height;
	}

	public int getPixel(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height)
			return arrayImage[y * width + x] & 0xff;
		else
			return 0;
	}

	public void setPixel(int x, int y, int value) {
		arrayImage[y * width + x] = (byte) value;
	}

	public void invert() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (getPixel(i, j) == BeetleFinder.BLACK_VALUE) {
					setPixel(i, j, BeetleFinder.WHITE_VALUE);
				} else {
					setPixel(i, j, BeetleFinder.BLACK_VALUE);
				}
			}
		}
	}

}
