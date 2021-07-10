package com.example.arfirst;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Flower> mFlowerDataSet = new ArrayList<>();
    //String[] fNames = {"A","B","C","D","E","F","A","B","C","D","E","F"};
    String[] chair_Names={"0","BARSTOOL","LOUNGECHAIR","WHITECHAIR","WALLCLOCK"};
    String[] sofa_Names={"0","MATTESOFA","GREYSOFA","BLUESOFA","DESK"};
    private Button augmentButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        augmentButton = findViewById(R.id.augment);

        //setting up toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // Enable the Up button
        //ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);

        //Prepare DataSet
        mFlowerDataSet = prepareDataSet();
        //Initialize Grid View for programming
        GridView gridview = (GridView) findViewById(R.id.gridView);
        //Connect DataSet to Adapter
        FlowerAdapter flowerAdapter = new FlowerAdapter(this, mFlowerDataSet);
        //Now Connect Adapter To GridView
        gridview.setAdapter(flowerAdapter);
        //Add Listener For Grid View Item Click
        gridview.setOnItemClickListener(this);

        augmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AugmentedScene.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...

                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        AugmentedScene.addModel(mFlowerDataSet.get(position).getFlowerName()+".sfb");
        //Show Name Of The Flower
//        Toast.makeText(MainActivity.this, mFlowerDataSet.get(position).getFlowerName(),
//                Toast.LENGTH_SHORT).show();
    }

    //Creating Data Set By Adding 6 flower objects
    private ArrayList<Flower> prepareDataSet() {

        ArrayList<Flower> flowerData = new ArrayList<>();

        Flower flower;
        for(int i=1; i<5;i++){
            flower = new Flower();
            flower.setFlowerName(chair_Names[i]);
            flower.setPhotoPath(getResources().getIdentifier("chair"+i, "drawable", getPackageName()));
            flowerData.add(flower);
        }
        for(int i=1; i<5;i++){
            flower = new Flower();
            flower.setFlowerName(sofa_Names[i]);
            flower.setPhotoPath(getResources().getIdentifier("sofa"+i, "drawable", getPackageName()));
            flowerData.add(flower);
        }


        return flowerData;

    }
}
