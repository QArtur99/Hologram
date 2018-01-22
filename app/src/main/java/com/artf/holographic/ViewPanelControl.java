package com.artf.holographic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by ART_F on 2017-04-07.
 */

public class ViewPanelControl {
    private static final int COLOR_VALUE = 63;
    private static final int FIELD_WIDTH2 = 150;
    private static final int FIELD_HEIGHT2 = 150;

    private static final int FIELD_WIDTH = 60;
    private static final int FIELD_HEIGHT = 110;

    private static final double A = 1;
    private static final double B = 2 * Math.PI / A;

    private static int endX;
    private static int endY;
    private static int cellsDiameter;
    private static int cellsDiameter2;
    private Paint paint;
    private ViewPanel gamePanel;
    private Point fieldDimensions;
    private int m;

    ViewPanelControl(ViewPanel gamePanel, int m){
        this.m = m;
        this.gamePanel = gamePanel;
        this.paint = new Paint();
        loadDimentions();
    }

    private void loadDimentions() {
        cellsDiameter2 = gamePanel.getWidth() / FIELD_WIDTH2;
        cellsDiameter = gamePanel.getWidth() / FIELD_WIDTH;
        fieldDimensions = new Point(FIELD_WIDTH, FIELD_HEIGHT);
        endX = FIELD_WIDTH * cellsDiameter;
        endY = FIELD_HEIGHT * cellsDiameter;
    }


    public void drawBackground(Canvas canvas) {
        int bgColor = Color.rgb(224, 224, 224);
        paint.setColor(bgColor);
        canvas.drawRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight(), paint);
    }

    public void drawGrid(Canvas canvas){
        paint.setColor(Color.BLACK);
        for (int i = 0; i < fieldDimensions.x; i++) {
            int startX = i * cellsDiameter;
            canvas.drawLine(startX, 0, startX, endY , paint);
        }
        for (int i = 0; i < fieldDimensions.y; i++) {
            int startY = i * cellsDiameter;
            canvas.drawLine(0, startY, endX, startY, paint);
        }
    }


    public void createHologram(Canvas canvas){
        double t1[][] = new double[FIELD_WIDTH2][FIELD_HEIGHT2];
        t1 = set2dArrayBin(t1);
        for(int x = 0; t1[0].length > x; x++){
            for(int y = 0; t1.length > y; y++){
                drawHologram(canvas, new Point(x,y), t1[x][y]);
            }
        }
    }


    private void drawHologram(Canvas canvas, Point p, double tValue) {
        int x = p.x * cellsDiameter2 + 60;
        int y = p.y * cellsDiameter2 + 100;
        Rect dst = new Rect(x, y, x + cellsDiameter2, y + cellsDiameter2);

        int bgColor = getBgColor(tValue);
        paint.setColor(bgColor);
        canvas.drawRect(dst, paint);
    }

    private int getBgColor(double tValue) {
        int bgColor;
        if(tValue >= 4.0){
            bgColor = Color.rgb(0, 0, 0);
        }else if(tValue == 0.0) {
            bgColor = Color.rgb(255, 255, 255);
        }else{
            int colorGrey = (int) tValue * COLOR_VALUE;
            bgColor = Color.rgb(colorGrey, colorGrey, colorGrey);
        }
        return bgColor;
    }

    private double[][] set2dArraySin(double array[][]){
        for(int x = 0; array[0].length > x; x++){
            for(int y = 0; array.length > y; y++){
                double trueX = x * 0.066 - 5;
                double trueY = y * 0.066 - 5;
                array[x][y] = 2 * (1 + Math.cos(B * trueX - m  * Math.atan2(trueX, trueY)));
            }
        }
        return array;
    }

    private double[][] set2dArrayBin(double array[][]){
        for(int x = 0; array[0].length > x; x++){
            for(int y = 0; array.length > y; y++){
                double trueX = x * 0.066 - 5;
                double trueY = y * 0.066 - 5;
                array[x][y] = 2 * (1 + Math.signum(Math.cos(B * trueX - m  * Math.atan2(trueX, trueY))));
            }
        }
        return array;
    }

}
