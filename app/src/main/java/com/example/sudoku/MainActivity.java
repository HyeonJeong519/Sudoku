package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

    int buttonClickRow;
    int buttonClickCol;

    ArrayList<Integer> resultCol = new ArrayList<>();
    ArrayList<Integer> resultRow = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BoardGenerator boardGenerator = new BoardGenerator();
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.MATCH_PARENT, 1);

        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1);

        parentFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        TableLayout table = (TableLayout) findViewById(R.id.tablelayout);
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
            }
            table.addView(tableRow);
        }


        inputTableRowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1);
        inputTableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1);
        frameLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        frameLayoutParams.gravity = Gravity.CENTER;
        inputTableLayout = new TableLayout(this);
        inputTableLayout.setBackgroundColor(Color.DKGRAY);
        inputTableLayout.setVisibility(View.INVISIBLE);

        textView = new TextView(this);
        textView.setText("Input Number");

        int number = 1;
        for (int i = 0; i < 4; i++) {
            inputTableRow = new TableRow(this);
            for (int j = 0; j < 3; j++) {
                Button button = new Button(this);
                button.setTextSize(20);
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
                                inputTableLayout.setVisibility(View.INVISIBLE);
                            }
                        });
                        break;
                    case 10:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setVisibility(View.INVISIBLE);
                        number++;
                        break;
                    case 11:
                        inputTableRow.addView(button, inputTableRowLayoutParams);
                        button.setText("cancel");
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
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customButton[buttonClickCol][buttonClickRow].set(0);
                                int original = sudoku[buttonClickCol][buttonClickRow];
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
                            }
                        });
                        break;
                }
            }
            inputTableLayout.addView(inputTableRow, inputTableLayoutParams);
        }
        parentFrameLayout.addView(inputTableLayout, frameLayoutParams);

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

}