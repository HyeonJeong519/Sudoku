package com.example.sudoku;

import androidx.annotation.NonNull;
import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CustomButton extends FrameLayout {
    int row;
    int col;
    int value;

    private TextView textView;

    public CustomButton(@NonNull Context context) {
        super(context);
    }

    public CustomButton(Context context, int row, int col) {
        super(context);

        textView = new TextView(context);
        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER);
        addView(textView);

        setClickable(true);
        setBackgroundResource(R.drawable.button_selector);
    }

    public void set(int a) {
        if (a == 0)
            textView.setVisibility(INVISIBLE);
        else {
            textView.setVisibility(VISIBLE);
            textView.setText(String.valueOf(a));}
    }
}
