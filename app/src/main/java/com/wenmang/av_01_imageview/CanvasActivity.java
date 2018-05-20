package com.wenmang.av_01_imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zhoujx on 2018/5/20.
 */

public class CanvasActivity extends AppCompatActivity implements View.OnTouchListener {
    private static final String TAG = "CanvasActivity";

    private Bitmap mBmCopy;
    private Paint mPaint;
    private Canvas mCanvas;

    private ImageView mIvCanvas;

    private int mStartX;
    private int mStartY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_canvas);

        initCanvas();
        initView();
        initListener();
    }

    private void initCanvas() {
        // 加载资源id 的图片
        Bitmap bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.av_imageview_canvas);

        // 创建副本
        mBmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());

        // 创建画笔
        mPaint = new Paint();

        // 创建画板
        mCanvas = new Canvas(mBmCopy);

        // 开始绘画
        mCanvas.drawBitmap(bmSrc, new Matrix(), mPaint);
    }

    private void initView() {
        mIvCanvas = (ImageView) findViewById(R.id.iv_canvas);
    }

    private void initListener() {
        mIvCanvas.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                // 记录绘画开始位置
                mStartX = (int) event.getX();
                mStartY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int newStartX = (int) event.getX();
                int newStartY = (int) event.getY();

                // 开始绘画
                mCanvas.drawLine(mStartX, mStartY, newStartX, newStartY, mPaint);

                // 重新记录绘画位置
                mStartX = newStartX;
                mStartY = newStartY;

                // 设置副本
                mIvCanvas.setImageBitmap(mBmCopy);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "手指离开了屏幕");
                break;
            default:
                break;
        }
        return true;
    }

    public void doGreen(View v) {
        mPaint.setColor(Color.GREEN);
    }

    public void doRed(View v) {
        mPaint.setColor(Color.RED);
    }

    public void doYellow(View v) {
        mPaint.setColor(Color.YELLOW);
    }

    public void doBrush(View v) {
        mPaint.setStrokeWidth(10);
    }
}
