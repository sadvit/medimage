package com.sadvit.analysis.beetle;



import com.sadvit.analysis.geometry.GeometryObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {
	
	public static BufferedImage byteToBuffered(ByteImage byteImage) {
		int width = byteImage.getWidth();
		int height = byteImage.getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = byteImage.getPixel(i, j);
				result.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
			}
		}
		return result;
	}

	public static ByteImage bufferedToBinary(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		ByteImage byteImage = new ByteImage(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color color = new Color(bufferedImage.getRGB(i, j));
				if (color.equals(Color.WHITE)) {
					byteImage.setPixel(i, j, BeetleFinder.WHITE_VALUE);
				} else {
					byteImage.setPixel(i, j, BeetleFinder.BLACK_VALUE);
				}
			}
		}
		return byteImage;
	}
	
	public static ByteImage bufferedToByte(BufferedImage bufferedImage, ImageChannel channel) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		byte[] arrayImage = new byte[width * height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int color = 0;
				switch (channel) {
				case RED:
					color = new Color(bufferedImage.getRGB(i, j)).getRed();
					break;
				case BLUE:
					color = new Color(bufferedImage.getRGB(i, j)).getBlue();
					break;
				case GREEN:
					if (bufferedImage.getRGB(i, j) != -1) {
						bufferedImage.getRGB(i, j);
					}
					color = new Color(bufferedImage.getRGB(i, j)).getGreen();
					break;
				}
				arrayImage[j * width + i] = (byte) color;
			}
		}		
		return new ByteImage(arrayImage, width, height);
	}
	
	public static BufferedImage geometryToBuffered(GeometryObject geometryObject) {
		return geometryObject.getBufferedImage();
	}
	
	public enum ImageChannel {
		RED, BLUE, GREEN
	}

}
