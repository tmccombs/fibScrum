package bytecurry.fibscrum;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;


public class FibScroller extends ListActivity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {
    /**
     * The maximum size allowed for the list.
     */
    private static final int MAX_SIZE = 18; //Coresponds to Fibonnacci number 987

    private FibListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FibListAdapter(getApplicationContext());
        setListAdapter(adapter);
        getListView().setOnScrollListener(this);
        getListView().setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fib_scroller, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_zero:
                showTextDisplay(R.string.zero, R.style.DisplayFractional);
                return true;
            case R.id.action_blackhole:
                showImageDisplay(R.drawable.black_hole);
                return true;
            case R.id.action_idk:
                showTextDisplay(R.string.idk, R.style.DisplayText);
                return true;
            case R.id.action_investigate:
                showTextDisplay(R.string.investigate_needed, R.style.DisplayTextSmall);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showTextDisplay(int textResource, int textStyle) {
        Intent intent = new Intent(this, TextDisplay.class);
        intent.putExtra("value", getString(textResource));
        intent.putExtra("style", textStyle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void showImageDisplay(int drawable) {
        Intent intent = new Intent(this, ImageDisplay.class);
        intent.putExtra("image", drawable);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisible, int visibleCount, int totalCount) {
        boolean loadMore = firstVisible + visibleCount  >= totalCount;
        if (loadMore) {
            int newSize = Math.min(totalCount + visibleCount, MAX_SIZE);
            adapter.ensureSize(newSize);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        Object value = adapter.getItem(pos);

        Intent intent = new Intent(getApplicationContext(), TextDisplay.class);
        intent.putExtra("value", value.toString());
        intent.putExtra("style", adapter.getStyle(pos));
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
