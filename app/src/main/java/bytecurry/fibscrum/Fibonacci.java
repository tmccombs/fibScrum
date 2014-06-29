package bytecurry.fibscrum;

import java.util.ArrayList;
import java.util.Deque;

/**
 * Class to generate and store Fibonacci numbers (ignoring the first 1).
 */
public class Fibonacci {
    private ArrayList<Integer> values;

    public Fibonacci() {
        values = new ArrayList<Integer>();
        //initialize the first two values.
        values.add(1);
        values.add(2);
    }

    /**
     * Calculate the next number in the sequence and add it to the array.
     */
    private void calcNext() {
        int nextVal = values.get(values.size() - 1) + values.get(values.size() - 2);
        values.add(nextVal);
    }

    /**
     * Get the fibonacci number at the specified index (ignoring first 1).
     * Calculating up to that index if necessary.
     * @param i The index to get a number for.
     * @return The fibonacci number.
     * @throws IndexOutOfBoundsException If i is negative.
     */
    public int get(int i) throws IndexOutOfBoundsException {
        if (i < 0) {
           throw new IndexOutOfBoundsException();
        } else if (i >= values.size()) {
            return calculateUpTo(i);
        } else {
            return values.get(i);
        }
    }

    /**
     * Calculate all the values up to the given index.
     * @param i The index to calculate up to.
     * @return The value at index i.
     */
    public int calculateUpTo(int i) {
        while (i >= values.size()) {
            calcNext();
        }
        return values.get(i);
    }

    /**
     * @return The number of values computed so far.
     */
    public int size() {
        return values.size();
    }

    public int largestValue() {
        return values.get(values.size()-1);
    }
}
