package com.samsung.itschool.surfaceexample;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MyThread extends Thread {
    boolean isRunning = false;
    SurfaceHolder surfaceHolder;
    MySurfaceView mySurfaceView;
    long prevTime;


    public MyThread(SurfaceHolder holder, MySurfaceView surfaceView) {
        surfaceHolder = holder;
        mySurfaceView = surfaceView;
        prevTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        Canvas canvas;
        while (isRunning){
            if(!surfaceHolder.getSurface().isValid())
                continue;
            canvas = null;
            long nowTime = System.currentTimeMillis();
            long ellapsedTime = nowTime - prevTime;
            if(ellapsedTime > 30){
                prevTime = nowTime;
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder){
                    mySurfaceView.draw(canvas);
                }
                if (canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
    void setRunning(boolean f){
        isRunning = f;
    }
}
