package com.example.sectiontaskCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText inputNum1;
    EditText inputNum2;
    TextView result;
    Button add;
    Button sub;
    Button mul;
    Button div;
    Button history;
    Button clear;
    public static final String HISTORY_FILE = "history.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNum1 = findViewById(R.id.editTextFirstNumber);
        inputNum2 = findViewById(R.id.editTextSecondNumber);
        result = findViewById(R.id.textViewResult);
        add = findViewById(R.id.buttonAdd);
        sub = findViewById(R.id.buttonSub);
        mul = findViewById(R.id.buttonMul);
        div = findViewById(R.id.buttonDiv);
        history = findViewById(R.id.buttonHistory);
        clear = findViewById(R.id.buttonClear);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputNum1.getText().toString().equals("") || inputNum2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "please enter number first", Toast.LENGTH_SHORT).show();
                } else {
                    int num1 = Integer.parseInt(inputNum1.getText().toString());
                    int num2 = Integer.parseInt(inputNum2.getText().toString());
                    int res = calculate(num1, num2, Operation.ADD);
                    result.setText("Result: " + String.valueOf(res));
                    saveHistory(num1, num2, Operation.ADD.getSign(), res);
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputNum1.getText().toString().equals("") || inputNum2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "please enter number first", Toast.LENGTH_SHORT).show();
                } else {
                    int num1 = Integer.parseInt(inputNum1.getText().toString());
                    int num2 = Integer.parseInt(inputNum2.getText().toString());
                    int res = calculate(num1, num2, Operation.SUB);
                    result.setText("Result: " + String.valueOf(res));
                    saveHistory(num1, num2, Operation.SUB.getSign(), res);
                }
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputNum1.getText().toString().equals("") || inputNum2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "please enter number first", Toast.LENGTH_SHORT).show();
                } else {
                    int num1 = Integer.parseInt(inputNum1.getText().toString());
                    int num2 = Integer.parseInt(inputNum2.getText().toString());
                    int res = calculate(num1, num2, Operation.MUL);
                    result.setText("Result: " + String.valueOf(res));
                    saveHistory(num1, num2, Operation.MUL.getSign(), res);
                }
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputNum1.getText().toString().equals("") || inputNum2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "please enter number first", Toast.LENGTH_SHORT).show();
                } else {
                    int num1 = Integer.parseInt(inputNum1.getText().toString());
                    int num2 = Integer.parseInt(inputNum2.getText().toString());
                    int res = calculate(num1, num2, Operation.DIV);
                    result.setText("Result: " + String.valueOf(res));
                    saveHistory(num1, num2, Operation.DIV.getSign(), res);
                }
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHistory();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputNum1.setText("");
                inputNum2.setText("");
                result.setText("");
            }
        });

    }

    public int calculate(int num1, int num2, @NonNull Operation op) {
        int result = 0;
        switch (op) {
            case ADD:
                result = num1 + num2;
                break;
            case SUB:
                result = num1 - num2;
                break;
            case DIV:
                result = num1 / num2;
                break;
            case MUL:
                result = num1 * num2;
                break;
        }
        return result;
    }

    public void saveHistory(int num1, int num2, char operation, int result) {
        File path = getApplicationContext().getFilesDir();
        try (FileWriter fw = new FileWriter(new File(path, HISTORY_FILE), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.printf("%d %c %d = %d %n", num1, operation, num2, result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHistory() {
        Intent intent = new Intent(this, ShowHistory.class);

        startActivity(intent);
    }

}