package ideas.weicong.com.notifymeright;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ListView;

/**
 * Created by wei cong on 9/1/2015.
 */
public class ZoomableListView extends ListView {
    private float mScaleFactor = 1.f;
    private ScaleGestureDetector scale;
    public ZoomableListView(Context context) {
        super(context);
        scale = new ScaleGestureDetector(context, new ScaleListener());
    }

    public ZoomableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scale = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scale.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();

            //ZoomableListView.this.setAdapter();
            return true;
        }
    }
}
