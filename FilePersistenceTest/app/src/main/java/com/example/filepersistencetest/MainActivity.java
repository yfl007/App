package com.example.filepersistencetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class MainActivity extends Activity {
    EditText inputEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        inputEdit = (EditText)findViewById(R.id.input);
        Button saveButton = (Button)findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputEdit.getText().toString();
                if (input!=null&&!input.equals("")){
                    save(input);
                    inputEdit.setText("");
                }else {
                    makeText(MainActivity.this, "Input is available!", LENGTH_SHORT).show();
                }
            }
        });
        final Button loadButton = (Button)findViewById(R.id.load);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String read =null;
                read = load();
                if (read!=null&&!read.equals("")){
                    makeText(MainActivity.this, read, Toast.LENGTH_LONG).show();
                }else {
                    makeText(MainActivity.this, "Read nothing!", LENGTH_SHORT).show();
                }
            }
        });
    }

    private void save(String inputStr){
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter =null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = openFileOutput("test.txt",MODE_PRIVATE);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(inputStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String load(){
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader = null;
        StringBuffer content = new StringBuffer();
        try {
            String line="";
            fileInputStream = openFileInput("test.txt");
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line=bufferedReader.readLine())!=null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return content.toString();
        }
    }
}
