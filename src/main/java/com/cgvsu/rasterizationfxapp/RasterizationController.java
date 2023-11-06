package com.cgvsu.rasterizationfxapp;

import com.cgvsu.rasterization.Rasterization;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RasterizationController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    int sx = 600;
    int sy = 350;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

//        Rasterization.drawRectangle(canvas.getGraphicsContext2D(), 200, 300, 200, 100, Color.CHOCOLATE);
//        Rasterization.drawRectangle(canvas.getGraphicsContext2D(), 250, 250, 50, 200, Color.AQUA);
//        Rasterization.drawLineBresenham(canvas.getGraphicsContext2D(), 100, 400, 100, 200, Color.RED, Color.BLUE);
//        Rasterization.drawLineBresenham(canvas.getGraphicsContext2D(), 150, 200, 150, 400, Color.RED, Color.BLUE);
//        Rasterization.drawLineBresenham(canvas.getGraphicsContext2D(), 100, 200, 300, 300, Color.RED, Color.BLUE);
//        Rasterization.drawLineBresenham(canvas.getGraphicsContext2D(), 350, 400, 250, 300, Color.RED, Color.BLUE);

//        int n = 128;
//        int x = 250, y = 250;
//        for (int i = 0; i < n; i++) {
//            double a = 2 * i * Math.PI / n;
//            Rasterization.drawLineBresenham(canvas.getGraphicsContext2D(), x, y, x + (int) (150 * Math.cos(a)), y + (int) (150 * Math.sin(a)), Color.RED, Color.BLUE);
//        }

        canvas.setOnMouseClicked(mouseEvent -> {
            sx = (int) mouseEvent.getX();
            sy = (int) mouseEvent.getY();
        });

        canvas.setOnMouseMoved(mouseEvent -> {
            canvas.getGraphicsContext2D().clearRect(0, 0, 1200, 700);
            Rasterization.drawLine(canvas.getGraphicsContext2D(), sx, sy, (int) mouseEvent.getX(), (int) mouseEvent.getY(), Color.RED, Color.BLUE);
        });
    }
}