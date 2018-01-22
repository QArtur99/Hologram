package com.artf.holographic;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ART_F on 2017-03-20.
 */

public class ViewPanel extends SurfaceView implements SurfaceHolder.Callback {

    public ViewPanelControl mapControl;
    Context context;
    private int m;


    public ViewPanel(Context context, int m) {
        super(context);
        this.m = m;
        this.context = context;
        getHolder().addCallback(this);
    }



    public void initHologram() {
        mapControl = new ViewPanelControl(this, m);
        SurfaceHolder surfaceHolder = getHolder();
        Canvas canvas = surfaceHolder.lockCanvas();
        render(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        initHologram();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }


    public void render(Canvas canvas) {
        mapControl.drawBackground(canvas);
        mapControl.drawGrid(canvas);
        mapControl.createHologram(canvas);
    }




}

