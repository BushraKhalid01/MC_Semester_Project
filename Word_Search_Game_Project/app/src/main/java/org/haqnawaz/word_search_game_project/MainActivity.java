package org.haqnawaz.word_search_game_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner=findViewById(R.id.spinner);
        List<String>words=new ArrayList<>();
        words.add(0,"6");
        words.add("8");
        words.add("9");
        words.add("12");
        ArrayAdapter<String> wordsAdapter;
        wordsAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,words);
        wordsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(wordsAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Select Level")){
                    //do nothing
                }
                else{
                    //save selected words to send with intent

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void startGame(View view){
        Intent intent = new Intent(this,GameBoard.class);
        startActivity(intent);
    }
}