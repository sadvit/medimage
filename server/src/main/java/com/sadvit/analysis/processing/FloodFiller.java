package com.sadvit.analysis.processing;


import com.sadvit.analysis.beetle.ByteImage;

/**
 * "http://en.wikipedia.org/wiki/Flood_fill"
 */
public class FloodFiller {

    int maxStackSize = 500;
    int[] xstack = new int[maxStackSize];
    int[] ystack = new int[maxStackSize];
    int stackSize;
    ByteImage byteImage;

    public FloodFiller(ByteImage byteImage) {
        this.byteImage = byteImage;
    }

    /**
     * Четырехсторонняя заливка
     */
    public boolean fill(int x, int y) {
        int width = byteImage.getWidth();
        int height = byteImage.getHeight();
        int color = byteImage.getPixel(x, y);
        fillLine(byteImage, x, x, y);
        int newColor = byteImage.getPixel(x, y);
        byteImage.setPixel(x, y, color);
        if (color == newColor) return false;
        stackSize = 0;
        push(x, y);
        while (true) {
            x = popx();
            if (x == -1) return true;
            y = popy();
            if (byteImage.getPixel(x, y) != color) continue;
            int x1 = x;
            int x2 = x;
            while (byteImage.getPixel(x1, y) == color && x1 >= 0) x1--;
            x1++;
            while (byteImage.getPixel(x2, y) == color && x2 < width) x2++;
            x2--;
            fillLine(byteImage, x1, x2, y);
            boolean inScanLine = false;
            for (int i = x1; i <= x2; i++) {
                if (!inScanLine && y > 0 && byteImage.getPixel(i, y - 1) == color) {
                    push(i, y - 1);
                    inScanLine = true;
                } else if (inScanLine && y > 0 && byteImage.getPixel(i, y - 1) != color)
                    inScanLine = false;
            }
            inScanLine = false;
            for (int i = x1; i <= x2; i++) {
                if (!inScanLine && y < height - 1 && byteImage.getPixel(i, y + 1) == color) {
                    push(i, y + 1);
                    inScanLine = true;
                } else if (inScanLine && y < height - 1 && byteImage.getPixel(i, y + 1) != color)
                    inScanLine = false;
            }
        }
    }

    final void push(int x, int y) {
        stackSize++;
        if (stackSize == maxStackSize) {
            int[] newXStack = new int[maxStackSize * 2];
            int[] newYStack = new int[maxStackSize * 2];
            System.arraycopy(xstack, 0, newXStack, 0, maxStackSize);
            System.arraycopy(ystack, 0, newYStack, 0, maxStackSize);
            xstack = newXStack;
            ystack = newYStack;
            maxStackSize *= 2;
        }
        xstack[stackSize - 1] = x;
        ystack[stackSize - 1] = y;
    }

    final int popx() {
        if (stackSize == 0)
            return -1;
        else
            return xstack[stackSize - 1];
    }

    final int popy() {
        int value = ystack[stackSize - 1];
        stackSize--;
        return value;
    }

    final void fillLine(ByteImage byteImage, int x1, int x2, int y) {
        if (x1 > x2) {
            int t = x1;
            x1 = x2;
            x2 = t;
        }
        for (int x = x1; x <= x2; x++)
            byteImage.setPixel(x, y, 127);
    }

}
