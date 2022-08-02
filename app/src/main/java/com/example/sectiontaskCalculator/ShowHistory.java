package com.example.sectiontaskCalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ShowHistory extends AppCompatActivity {
    TextView history;
    Button deleteHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        setTitle("History");

        deleteHistory = findViewById(R.id.buttonClearHistory);
        history = findViewById(R.id.textViewHistory);
        read(MainActivity.HISTORY_FILE);

        deleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    private void delete() {
        File path = new File(getApplicationContext().getFilesDir().getPath());
        File file = new File(path, MainActivity.HISTORY_FILE);
        if (file.delete()){
            Toast.makeText(this, "History was deleted", Toast.LENGTH_SHORT).show();
            history.setVisibility(View.GONE);
            deleteHistory.setVisibility(View.INVISIBLE);
        } else
            Toast.makeText(this, "Unable to delete history", Toast.LENGTH_SHORT).show();
    }

    public void read(String fileName) {
        File path = new File(getApplicationContext().getFilesDir().getPath());
        File file = new File(path, fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
                stringBuilder.append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        history.setText(stringBuilder.toString());
    }
}