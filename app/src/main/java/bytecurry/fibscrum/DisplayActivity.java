package bytecurry.fibscrum;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Superclass of all display activities. It takes care of the slide back functionality.
 */
abstract public class DisplayActivity extends Activity {

    private class OnRightFling extends OnFling {

        public OnRightFling(ViewConfiguration vc) {
            super(vc);
        }

        @Override
        public boolean onRightFling() {
            finish();
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            return true;
        }
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contentLayout = findViewById(android.R.id.content);
        OnRightFling fling = new OnRightFling(ViewConfiguration.get(this));
        final GestureDetector detector = new GestureDetector(this, fling);
        contentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
