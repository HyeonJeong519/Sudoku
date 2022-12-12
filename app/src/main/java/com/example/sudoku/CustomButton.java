package com.example.sudoku;

import androidx.annotation.NonNull;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CustomButton extends FrameLayout {

    private TextView textView;
    LayoutInflater memoLayoutInflater;
    TableLayout memoTableLayout;
    TextView[] memoTextView = new TextView[9];

    public CustomButton(@NonNull Context context) {
        super(context);
    }

    public CustomButton(Context context, int row, int col) {
        super(context);

        textView = new TextView(context);
        textView.setTextSize(40);
        textView.setTextColor(Color.parseColor("#0E5E6F"));
        textView.setGravity(Gravity.CENTER);
        addView(textView);

        setClickable(true);
        setBackgroundResource(R.drawable.button_selector);

        memoLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        memoTableLayout = (TableLayout) memoLayoutInflater.inflate(R.layout.layout_memo, null);
        addView(memoTableLayout);

        int k=0;
        for (int i=0;i<3;i++) {
            TableRow memoTableRow = (TableRow) memoTableLayout.getChildAt(i);
            for (int j=0;j<3;j++, k++) {
                memoTextView[k] = (TextView) memoTableRow.getChildAt(j);
                memoTextView[k].setVisibility(INVISIBLE);
            }
        }

    }

    public void set(int a) {
        if (a == 0)
            textView.setVisibility(INVISIBLE);
        else {
            textView.setVisibility(VISIBLE);
            textView.setText(String.valueOf(a));}
    }

    public void deleteMemo(){
        for(int i = 0;i<9;i++)
            memoTextView[i].setVisibility(INVISIBLE);
    }

    public void getMemo(boolean[] memo) {
        for (int i=0;i<9;i++) {
            if (memo[i]) {
                memoTextView[i].setVisibility(VISIBLE);
            } else {
                memoTextView[i].setVisibility(INVISIBLE);
            }
        }
    }

}
