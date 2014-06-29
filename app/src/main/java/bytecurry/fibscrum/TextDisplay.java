package bytecurry.fibscrum;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TextDisplay extends DisplayActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);

        TextView content = (TextView) findViewById(R.id.itemContent);
        Intent intent = getIntent();
        String value = intent.getStringExtra("value");
        int style = intent.getIntExtra("style", R.style.DisplayText);

        content.setText(value);
        content.setTextAppearance(this, style);
    }

}
