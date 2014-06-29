package bytecurry.fibscrum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by thayne on 6/27/14.
 */
public class FibListAdapter extends BaseAdapter implements ListAdapter {
    private static final String[] fractionals = {"1/8", "1/4", "1/2"};

    private static final int MAX_FIB_VALUE = 1000;

    private final Fibonacci fib = new Fibonacci();
    private Context context;

    public FibListAdapter(Context ctxt) {
        context = ctxt;
        //fib.calculateUpTo(7); //pre-load fib with 7 values.
    }

    @Override
    public int getCount() {
        return fractionals.length + fib.size();
    }

    @Override
    public Object getItem(int i) {
        if (i < fractionals.length) {
            return fractionals[i];
        } else {
            return fib.get(i - fractionals.length);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_item, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.itemContent);
        textView.setText(getItem(i).toString());
        return view;
    }

    public int getStyle(int i) {
        if (i < fractionals.length) {
            return R.style.DisplayFractional;
        } else {
            int value = fib.get(i - fractionals.length);
            if (value < 10) {
                return R.style.DisplaySingleDigit;
            } else if (value < 100) {
                return R.style.DisplayDoubleDigit;
            } else {
                return R.style.DisplayTriplerDigit;
            }
        }
    }

    public void ensureSize(int i) {
        //TODO run in seperate thread
        if (i >= fractionals.length && fib.largestValue() < MAX_FIB_VALUE) {
            fib.calculateUpTo(i - fractionals.length);
            notifyDataSetChanged();
        }
    }

}
