package cn.edu.bit.codesky.nativelearning.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.edu.bit.codesky.nativelearning.R;

/**
 * 自定义人脸追踪框
 */
public class FaceView extends View {
    private Rect rect;

    private static final String TAG = FaceView.class.getName();

    public FaceView(Context context) {
        this(context, null);
    }

    public FaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint(context);
    }

    private void initPaint(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(context.getResources().getColor(R.color.color_face_rect));
    }

    private Paint mPaint;

    /**
     * 开始画矩形框
     *
     * @param rect1
     */
    public void drawFaceRect(Rect rect1) {
        this.rect = new Rect();
        float ratio = 1.5f;
        this.rect.left = (int) (rect1.top * ratio);
        this.rect.right = (int) (rect1.bottom * ratio);
        this.rect.top = (int) ((rect1.right) * ratio);
        this.rect.bottom = (int) ((rect1.left) * ratio);

        Log.d(TAG, "left:" + rect.left);
        Log.d(TAG, "right:" + rect.right);
        Log.d(TAG, "top:" + rect.top);
        Log.d(TAG, "bottom:" + rect.bottom);

        //在主线程发起绘制请求
        postInvalidate();
    }

    public void clearRect() {
        rect = null;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (rect != null) {
            //左侧竖线
            canvas.drawLine(rect.left, rect.top, rect.left, rect.bottom, mPaint);

            //右侧竖线
            canvas.drawLine(rect.right, rect.top, rect.right, rect.bottom, mPaint);

            //上横线
            canvas.drawLine(rect.left, rect.top, rect.right, rect.top, mPaint);

            //下横线
            canvas.drawLine(rect.left, rect.bottom, rect.right, rect.bottom, mPaint);

        }
    }
}

