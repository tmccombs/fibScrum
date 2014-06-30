package bytecurry.fibscrum;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Class to handle fling Gestures (Right or Left).
 */
abstract public class OnFling extends GestureDetector.SimpleOnGestureListener {
    private final ViewConfiguration viewConfig;
    public OnFling(ViewConfiguration vc) {
        viewConfig = vc;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distance = e2.getX() - e1.getX();
        if ( Math.abs(distance) > viewConfig.getScaledPagingTouchSlop() && velocityX > viewConfig.getScaledMinimumFlingVelocity()) {
            if (distance > 0) {
                return onRightFling();
            } else {
                return onLeftFling();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    public boolean onRightFling() {
        return false;
    }
    public boolean onLeftFling() {
        return false;
    }
}
