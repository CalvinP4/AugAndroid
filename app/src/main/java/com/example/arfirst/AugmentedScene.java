package com.example.arfirst;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.net.URL;
import java.util.ArrayList;

public class AugmentedScene extends AppCompatActivity {
    public static ArrayList<String> models = new ArrayList<>();

    private ArFragment arFragment;
    //private String[] models;
    private int currentModel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_augmented_scene);



        //get reference of fragment
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        // this function is called whenever user taps on the detected plane
        arFragment.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            //anchor is a fixed position in 3d world
            Anchor anchor = hitResult.createAnchor();


            if (currentModel < models.size()){
                //build a model to be displayed
                ModelRenderable.builder().setSource(this, Uri.parse(models.get(currentModel++))).build()
                        .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage(throwable.getMessage()).show();
                            return null;
                        });

            }




        }));


    }

    public static void addModel(String modelName){
        models.add(modelName);
    }


    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        //create a new anchor node on top of anchor
        AnchorNode anchorNode = new AnchorNode(anchor);

        //to create something that can be moved create a transformable node
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);

        // add model on top of transformable node
        transformableNode.setRenderable(modelRenderable);


        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }

    private void removeModelFromScene(){

    }

}
