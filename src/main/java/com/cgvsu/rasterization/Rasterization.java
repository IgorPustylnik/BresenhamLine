package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import static java.lang.Math.*;

public class Rasterization {
    public static void drawRectangle(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color) {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        for (int row = y; row < y + height; ++row)
            for (int col = x; col < x + width; ++col)
                pixelWriter.setColor(col, row, color);
    }

    public static void drawLine(
            final GraphicsContext graphicsContext,
            int x0, int y0,
            int x1, int y1,
            final Color color0, final Color color1) {
        class Inner {
            public static Color getColorForLine(int x, int y, int x0, int y0, int x1, int y1, Color color0, Color color1) {
                if (x0 == x1 && y0 == y1) {
                    return color1;
                }
                double roots = sqrt(pow(x - x0, 2) + pow(y - y0, 2)) / sqrt(pow(x1 - x0, 2) + pow(y1 - y0, 2));
                double r0 = color0.getRed();
                double g0 = color0.getGreen();
                double b0 = color0.getBlue();
                double r1 = color1.getRed();
                double g1 = color1.getGreen();
                double b1 = color1.getBlue();

                double r = r0 + roots * (r1 - r0);
                double g = g0 + roots * (g1 - g0);
                double b = b0 + roots * (b1 - b0);
                return new Color(r, g, b, 1);
            }
        }
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        int dx = abs(x1 - x0);
        int dy = abs(y1 - y0);
        int diry = (y1 - y0) > 0 ? 1 : -1;
        int dirx = (x1 - x0) > 0 ? 1 : -1;

        int y = y0;
        int x = x0;

        if (dx >= dy) {
            int error = dy * 2 - dx;
            int deltaerr = (dy - dx) * 2;
            for (; x != x1 + dirx; x += dirx) {
                pixelWriter.setColor(x, y, Inner.getColorForLine(x, y, x0, y0, x1, y1, color0, color1));
                if (error < 0) {
                    error += 2 * dy;
                } else {
                    y += diry;
                    error += deltaerr;
                }
            }
        } else {
            int error = dx * 2 - dy;
            int deltaerr = (dx - dy) * 2;
            for (; y != y1 + diry; y += diry) {
                pixelWriter.setColor(x, y, Inner.getColorForLine(x, y, x0, y0, x1, y1, color0, color1));

                if (error < 0) {
                    error += dx * 2;
                } else {
                    x += dirx;
                    error += deltaerr;
                }
            }
        }
    }
}
