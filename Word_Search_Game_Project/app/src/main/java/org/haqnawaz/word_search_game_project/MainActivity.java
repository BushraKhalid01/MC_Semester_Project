package org.haqnawaz.word_search_game_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.boardSize);
        spinner2 = findViewById(R.id.levels);

        List<String>boardSize=new ArrayList<>();
        List<String>levels=new ArrayList<>();

        boardSize.add(0,"Select Board Size");
        boardSize.add("3 by 3");
        boardSize.add("5 by 5");
        boardSize.add("7 by 7");
        boardSize.add("9 by 9");

        levels.add(0,"Select Level");
        levels.add("Easy");
        levels.add("Medium");
        levels.add("Hard");

        ArrayAdapter<String> boardSizeAdapter;
        ArrayAdapter<String> levelsAdapter;

        boardSizeAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,boardSize);
        boardSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        levelsAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,levels);
        levelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(boardSizeAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Select Board Size")){
                    //do nothing
                }
                else{
                    String s= parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "You Choose Board Size " + s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setAdapter(levelsAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Select Level")){
                    //do nothing
                }
                else{
                    String s= parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "You Choose Level " + s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}