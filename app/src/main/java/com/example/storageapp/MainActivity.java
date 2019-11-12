package com.example.storageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME="namafile.txt";
    public static final String PREFNAME="com.example.storageapp";
    private TextView textBaca;
    private EditText editFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBaca=findViewById(R.id.text_baca);
        editFile=findViewById(R.id.edit_file);
    }

    //Shared Pref
    public void simpanFIlePref(View view) {
        String isiFile=editFile.getText().toString();
        SharedPreferences sharedPreferences=getSharedPreferences(PREFNAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(FILENAME, isiFile);
        editor.commit(); //boleh commit boleh apply

    }

    public void bacaFilePref(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences(PREFNAME,MODE_PRIVATE);
        if (sharedPreferences.contains(FILENAME)) { //cek file yang disimpan ada atau tidak
            String mytext =sharedPreferences.getString(FILENAME,"");
            textBaca.setText(mytext);
        } else {
            textBaca.setText("");
        }
    }

    public void hapusFilePref(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences(PREFNAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    //Internal
    public void simpanFileIs(View view){
        String isiFile=editFile.getText().toString();
        File path=getDir("NEWFOLDER", MODE_PRIVATE);
        File file=new File(path.toString(),FILENAME);
        FileOutputStream outputStream=null;

        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bacaFileIs(View view){
        File path=getDir("NEWFOLDER", MODE_PRIVATE);
        File file=new File(path.toString(),FILENAME);
        if(file.exists()){
            StringBuilder text=new StringBuilder();
            try {
                BufferedReader br=new BufferedReader(new FileReader(file));
                String line=br.readLine();
                while (line!=null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textBaca.setText(text.toString());
        } else {
            textBaca.setText("");

        }
    }

    public void hapusFileIs(View view){
        File path=getDir("NEWFOLDER", MODE_PRIVATE);
        File file=new File(path.toString(),FILENAME);
        if(file.exists()){
            file.delete();
        }

    }

    //Eksternal Storage
    public void simpanFile(View view){
        String isiFile=editFile.getText().toString();
        File path= Environment.getExternalStorageDirectory();
        File file=new File(path.toString(),FILENAME);
        FileOutputStream outputStream=null;

        try {
            file.createNewFile();
            outputStream=new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bacaFile(View view){
        File path=Environment.getExternalStorageDirectory();
        File file=new File(path.toString(),FILENAME);
        if(file.exists()){
            StringBuilder text=new StringBuilder();
            try {
                BufferedReader br=new BufferedReader(new FileReader(file));
                String line=br.readLine();
                while (line!=null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            textBaca.setText(text.toString());
        } else {
            textBaca.setText("");

        }
    }

    public void hapusFile(View view){
        File path=Environment.getExternalStorageDirectory();
        File file=new File(path.toString(),FILENAME);
        if(file.exists()){
            file.delete();
        }

    }
}
