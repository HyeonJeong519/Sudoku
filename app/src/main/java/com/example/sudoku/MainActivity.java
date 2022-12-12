package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomButton[][] customButton = new CustomButton[9][9];
    int[][] sudoku = new int[9][9];

    TextView textView;
    FrameLayout parentFrameLayout;
    TableLayout inputTableLayout;
    TableRow inputTableRow;
    TableRow.LayoutParams inputTableRowLayoutParams;
    TableLayout.LayoutParams inputTableLayoutParams;
    FrameLayout.LayoutParams frameLayoutParams;

    LayoutInflater memoLayoutInflater;
    LinearLayout memoLinearLayout;
    TableLayout memoTableLayout;
    Button deletememo, cancelMemo, setMemo;
    boolean[] memoBoolean = new boolean[9];
    ToggleButton[] memoToggleButton = new ToggleButton[9];

    int buttonClickRow;
    int buttonClickCol;

    ArrayList<Integer> resultCol = new ArrayList<>();
    ArrayList<Integer> resultRow = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        TableLayout table = (TableLayout) findViewById(R.id.tablelayout);

        memoLayoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        memoLayoutInflater.inflate(R.layout.memo_input_dialog,parentFrameLayout,true);
        memoLinearLayout = (LinearLayout) findViewById(R.id.memoDialogLinearLayout);
        memoLinearLayout.setVisibility(View.INVISIBLE);

        BoardGenerator boardGenerator = new BoardGenerator();
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.MATCH_PARENT, 1);

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT, 1);

        table.setPadding(7,7,7,7);

        for (int i = 0; i < 9; i++) {
            TableRow tableRow = new TableRow(this);
            layoutParams.setMargins(7, 7, 7, 7);
            tableRow.setLayoutParams(tableLayoutParams);

            for (int j = 0; j < 9; j++) {
                customButton[i][j] = new CustomButton(this, i, j);
                int number = boardGenerator.get(i, j);
                int x = (int) Math.floor(Math.random() * 10);

                if (x < 9) {
                    sudoku[i][j] = number;
                    customButton[i][j].set(number);
                }
                customButton[i][j].setLayoutParams(layoutParams);
                tableRow.addView(customButton[i][j]);
                final int finalI = i;
                final int finalJ = j;
                customButton[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonClickRow = finalJ;
                        buttonClickCol = finalI;
                        inputTableLayout.setVisibility(View.VISIBLE);
                    }
                });
                customButton[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        buttonClickRow = finalJ;
                        buttonClickCol = finalI;
                        memoLinearLayout.setVisibility(View.VISIBLE);
                        inputTableLayout.setVisibility(View.INVISIBLE);
                        return true;
                    }
                });
            }
            table.addView(tableRow);
        }

        inputTableRowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
        inputTableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1);
        frameLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        frameLayoutParams.gravity = Gravity.CENTER;
        inputTableLayout = new TableLayout(this);
        inputTableLayout.setBackgroundColor(Color.parseColor("#59C1BD"));
        inputTableLayout.setVisibility(View.INVISIBLE);

        textView = new TextView(this);
        textView.setText("Input Number");
        textView.setTextSize(25);
        textView.setTextColor(Color.parseColor("#0D4C92"));
        textView.setGravity(Gravity.CENTER);
        inputTableLayout.addView(textView,inputTableLayoutParams);

        inputTableLayout.setPadding(8,8,8,8);
        inputTableRowLayoutParams.setMargins(8,8,8,8);
        int number = 1;
        for (int i = 0; i < 4; i++) {
            inputTableRow = new TableRow(this);
            for (int j = 0; j < 3; j++) {
                Button button = new Button(this);
                button.setTextSize(20);
                button.setTextColor(Color.parseColor("#0D4C92"));
                button.setBackgroundColor(Color.parseColor("#A0E4CB"));
                button.setGravity(Gravity.CENTER);
                switch (number) {
                    case 1:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(1);
                                sudoku[buttonClickCol][buttonClickRow] = 1;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 2:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(2);
                                sudoku[buttonClickCol][buttonClickRow] = 2;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 3:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(3);
                                sudoku[buttonClickCol][buttonClickRow] = 3;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 4:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(4);
                                sudoku[buttonClickCol][buttonClickRow] = 4;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 5:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(5);
                                sudoku[buttonClickCol][buttonClickRow] = 5;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 6:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(6);
                                sudoku[buttonClickCol][buttonClickRow] = 6;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 7:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(7);
                                sudoku[buttonClickCol][buttonClickRow] = 7;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 8:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(8);
                                sudoku[buttonClickCol][buttonClickRow] = 8;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 9:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText(String.valueOf(number));
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(9);
                                sudoku[buttonClickCol][buttonClickRow] = 9;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 10:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText("Finish");
                        button.setTextSize(16);
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(checkAll()==true){
                                    for(int i= 0;i<9;i++){
                                        for(int j=0;j<9;j++)
                                            customButton[i][j].setBackgroundColor(Color.YELLOW);
                                    }
                                    inputTableLayout.setVisibility(View.INVISIBLE);
                                }else {
                                    inputTableLayout.setVisibility(View.INVISIBLE);
                                }
                            }
                        });
                        break;
                    case 11:

                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText("cancel");
                        button.setTextSize(16);
                        number++;
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;

                    case 12:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText("del");
                        button.setTextSize(16);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(0);
                                sudoku[buttonClickCol][buttonClickRow] = 0;
                                for(int i= 0;i<9;i++){
                                    for(int j=0;j<9;j++)
                                        customButton[i][j].setBackgroundColor(Color.WHITE);
                                }
                                checkRowNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                checkColNumber();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                check3by3Number();
                                for (int i = 0; i < resultCol.size(); i++)
                                    customButton[resultCol.get(i)][resultRow.get(i)].setBackgroundColor(Color.RED);
                                resultCol.clear();
                                resultRow.clear();
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                }
            }
            inputTableLayout.addView(inputTableRow, inputTableLayoutParams);
        }
        parentFrameLayout.addView(inputTableLayout, frameLayoutParams);

        cancelMemo = (Button) findViewById(R.id.memoBtnCancel);
        cancelMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memoLinearLayout.setVisibility(View.INVISIBLE);
            }
        });
        deletememo = (Button) findViewById(R.id.memoBtnDelete);
        deletememo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customButton[buttonClickCol][buttonClickRow].deleteMemo();
                memoLinearLayout.setVisibility(View.INVISIBLE);
            }
        });
        memoTableLayout = (TableLayout) findViewById(R.id.memoTableLayout);
        int k =0;
        for (int i =0; i<3;i++){
            TableRow memoTableRow = (TableRow) memoTableLayout.getChildAt(i);
            for(int j =0 ; j<3; j++){
                final int checked = k;
                memoToggleButton[k] = (ToggleButton) memoTableRow.getChildAt(j);
                memoToggleButton[k].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        memoBoolean[checked] = b;
                    }
                });
                k++;
            }
        }
        setMemo = (Button) findViewById(R.id.memoBtnOk);
        setMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customButton[buttonClickCol][buttonClickRow].getMemo(memoBoolean);
                memoLinearLayout.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void checkRowNumber() {
        for(int z=0;z<9;z++){
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if(sudoku[x][z]==0)
                        continue;
                    else if (x != y) {
                            if (sudoku[x][z] == sudoku[y][z]) {
                                resultRow.add(z);
                                resultCol.add(x);
                        } } } } }
    }

    public void checkColNumber() {
        for(int z=0;z<9;z++){
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if(sudoku[z][x]==0)
                        continue;
                    else if (x != y) {
                        if (sudoku[z][x] == sudoku[z][y]) {
                            resultRow.add(x);
                            resultCol.add(z);
                        } } } } }
    }

    public void check3by3Number() {
        for(int i =0;i<9;i++){
            for(int j =0;j<9;j++){
                int row = i / 3 * 3;
                int col = j / 3 * 3;
                    for (int y = row; y < row + 3; y++) {
                        for (int x = col; x < col+3; x++) {
                            if(y==i&&x==j){
                                continue;
                            }
                            else if (sudoku[y][x] == sudoku[i][j] && sudoku[i][j]!=0) {
                                resultCol.add(y);
                                resultRow.add(x);
                            } } } } }
    }

    public boolean checkAll(){
        boolean x;
        boolean y;
        boolean z;
        boolean h;
        checkRowNumber();
        if (resultCol.size()==0)
            x = true;
        else x = false;
        resultCol.clear();
        resultRow.clear();
        checkColNumber();
        if (resultCol.size()==0)
            y = true;
        else y = false;
        resultCol.clear();
        resultRow.clear();
        check3by3Number();
        if (resultCol.size()==0)
            z = true;
        else z = false;
        resultCol.clear();
        resultRow.clear();
        for(int i=0;i<9;i++){
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j]==0) {
                    resultRow.add(i);
                    resultCol.add(j);
                }
            }
        }
        if(resultCol.size()==0)
            h= true;
            else h=false;
        if(x==true && y==true && z==true && h==true)
            return true;
        else return false;
    }

}