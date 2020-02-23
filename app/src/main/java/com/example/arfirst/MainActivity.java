package com.example.arfirst;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button sofaButton;
    private Button statueButton;
    private TextView info;
    private Button augmentButton;
    private int modelCount = 0;

    private List<String> models = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references
        sofaButton = findViewById(R.id.sofa_button);
        statueButton = findViewById(R.id.statue_button);
        info = findViewById(R.id.textView);
        augmentButton = findViewById(R.id.augment_button);

        String message = "No of models selected " + modelCount;
        info.setText(message);

        //when sofa button is clicked add reference of sofa button
        sofaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AugmentedScene.furn.add("Couch.sfb");
                updateInfo();
            }
        });

        // u there ?
        //

        statueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AugmentedScene.furn.add("ArcticFox_Posed.sfb");
                updateInfo();
            }
        });


        augmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AugmentedScene.class);
                startActivity(intent);
            }
        });
    }

    private void updateInfo() {
        modelCount++;
        String message = "No of models selected " + modelCount;
        info.setText(message);
    }

}
