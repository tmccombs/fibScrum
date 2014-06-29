package bytecurry.fibscrum;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import bytecurry.fibscrum.R;

public class ImageDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        int imageRes = getIntent().getIntExtra("image", R.drawable.ic_launcher);

        imageView.setImageResource(imageRes);
    }
}
