package com.example.wwsskkaa.jsketch;
//// got from prof's mvc example for android studio

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    Model model;
    private int counter;

    toolbarview view1;
    canvasview view2;
    ViewGroup v1;
    ViewGroup v2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load the base UI (has places for the views)
        setContentView(R.layout.activity_main);
        if(ModelHelp.gModel==null)
        {
            ModelHelp.gModel=new Model();
        }

        model=ModelHelp.gModel;

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        model.setChanged();
        model.notifyObservers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // can only get widgets by id in onPostCreate for activity xml res
        // create the views and add them to the main activity
        view1 = new toolbarview(this, model);
        v1 = (ViewGroup) findViewById(R.id.mainactivity1);
        v1.addView(view1);

        view2 = new canvasview(this, model);
        v2 = (ViewGroup) findViewById(R.id.mainactivity2);
        v2.addView(view2);


        // initialize views
        model.setChanged();
        model.notifyObservers();
    }
    // save and restore state (need to do this to support orientation change)

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void SquareOnClick(View v)
    {
         model.setCurrentButton(Buttons.SQUARE);
    }
    public void OvalOnClick(View v) {   model.setCurrentButton(Buttons.OVAL);}
    public void LineOnClick(View v) {   model.setCurrentButton(Buttons.LINE);}
    public void SelectOnClick(View v) {model.setCurrentButton(Buttons.SELECTION);}
    public void EraserOnClick(View v) {model.setCurrentButton(Buttons.ERASE);}
    public void FillOnClick(View v) {model.setCurrentButton(Buttons.FILL);}
    public void GreenOnClick(View v) {model.setCurrentColor(1);
                                    if(model.sthisSelected()!=-1){model.modifyColor(model.sthisSelected());}}
    public void PinkOnClick(View v) {model.setCurrentColor(2);if(model.sthisSelected()!=-1){model.modifyColor(model.sthisSelected());}}
    public void BlueOnClick(View v) {model.setCurrentColor(0);if(model.sthisSelected()!=-1){model.modifyColor(model.sthisSelected());}}

    public void ThinnestOnClick(View v) {model.setCurrentThickness(3);}
    public void MidThinOnClick(View v) {model.setCurrentThickness(10);}
    public void ThickestOnClick(View v) {model.setCurrentThickness(20);}
    public void EraserLongClick(View v) {model.setCurrentButton(Buttons.ERASE);model.cleanArray();}
    public void UndoOnClick(View v) {
        model.UndoState();
    }

    public void RedoOnClick(View v) {
        model.RedoState();
    }

}
