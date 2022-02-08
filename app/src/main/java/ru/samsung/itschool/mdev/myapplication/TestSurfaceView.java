package ru.samsung.itschool.mdev.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private MyThread myThread;
    private float x,y,r;
    private boolean flag = false;

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myThread = new MyThread();
        myThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.x = event.getX();
        this.y = event.getY();
        this.r = 0;
        this.flag = true;
        return true;
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);
            Canvas canvas = getHolder().lockCanvas();
            while(true) {
                canvas.drawColor(Color.BLUE);
                r += flag ? 5 : 0;
                canvas.drawCircle(x, y, r, paint);
                getHolder().unlockCanvasAndPost(canvas);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
