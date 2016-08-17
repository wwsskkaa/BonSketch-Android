package com.example.wwsskkaa.jsketch;

//// got from prof's mvc example for android studio

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Color;
import android.util.*;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class toolbarview extends LinearLayout implements Observer {

    private Model model;
    private ImageButton squareButton,
            ovalButton,
            lineButton,
            selectButton,
            eraseButton,
            fillButton,
            pinkButton,
            blueButton,
            greenButton,
            thinButton,
            MedButton,
            ThickButton;
    public toolbarview(Context context, Model m) {
        super(context);


        // get the xml description of the view and "inflate" it
        // into the display (kind of like rendering it)
        View.inflate(context, R.layout.toolbarview, this);

        // save the model reference
        model = m;
        // add this view to model's list of observers
        model.addObserver(this);
        squareButton=(ImageButton) findViewById(R.id.Square);
        ovalButton=(ImageButton) findViewById(R.id.oval);
        lineButton=(ImageButton) findViewById(R.id.line);
        selectButton=(ImageButton) findViewById(R.id.select);
        eraseButton=(ImageButton) findViewById(R.id.erase);
        fillButton =(ImageButton) findViewById(R.id.fill);
        pinkButton=(ImageButton) findViewById(R.id.colorpink);
        blueButton=(ImageButton) findViewById(R.id.colorblue);
        greenButton=(ImageButton) findViewById(R.id.colorgreen);
        thinButton=(ImageButton) findViewById(R.id.thinnest);
        MedButton=(ImageButton) findViewById(R.id.medthin);
        ThickButton=(ImageButton) findViewById(R.id.thickest);

        eraseButton.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                model.setCurrentButton(Buttons.ERASE);
                model.cleanArray();
                model.updateKeeptrack();
                return true;
            }
        });
    }

    // the model call this to update the view
    public void update(Observable observable, Object data) {

        if(model.getCurrentButton()==Buttons.SQUARE)
        {
            squareButton.setBackgroundColor(Color.LTGRAY);
            ovalButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.WHITE);
            fillButton.setBackgroundColor(Color.WHITE);

        }
        else if((model.getCurrentButton()==Buttons.OVAL))
        {
            ovalButton.setBackgroundColor(Color.LTGRAY);
            squareButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.WHITE);
            fillButton.setBackgroundColor(Color.WHITE);
        }
        else if((model.getCurrentButton()==Buttons.LINE))
        {
            ovalButton.setBackgroundColor(Color.WHITE);
            squareButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.LTGRAY);
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.WHITE);
            fillButton.setBackgroundColor(Color.WHITE);
        }
        else if((model.getCurrentButton()==Buttons.SELECTION))
        {
            ovalButton.setBackgroundColor(Color.WHITE);
            squareButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            selectButton.setBackgroundColor(Color.LTGRAY);
            eraseButton.setBackgroundColor(Color.WHITE);
            fillButton.setBackgroundColor(Color.WHITE);
        }
        else if((model.getCurrentButton()==Buttons.ERASE))
        {
            ovalButton.setBackgroundColor(Color.WHITE);
            squareButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.LTGRAY);
            fillButton.setBackgroundColor(Color.WHITE);
        }
        else if((model.getCurrentButton()==Buttons.FILL))
        {
            ovalButton.setBackgroundColor(Color.WHITE);
            squareButton.setBackgroundColor(Color.WHITE);
            lineButton.setBackgroundColor(Color.WHITE);
            selectButton.setBackgroundColor(Color.WHITE);
            eraseButton.setBackgroundColor(Color.WHITE);
            fillButton.setBackgroundColor(Color.LTGRAY);
        }

        if(model.getCurrentColor()==2)
        {
            pinkButton.setBackgroundColor(Color.BLACK);
            blueButton.setBackgroundColor(Color.WHITE);
            greenButton.setBackgroundColor(Color.WHITE);
        }
        else if(model.getCurrentColor()==0)
        {
            pinkButton.setBackgroundColor(Color.WHITE);
            blueButton.setBackgroundColor(Color.BLACK);
            greenButton.setBackgroundColor(Color.WHITE);
        }
        else if(model.getCurrentColor()==1)
        {
            pinkButton.setBackgroundColor(Color.WHITE);
            blueButton.setBackgroundColor(Color.WHITE);
            greenButton.setBackgroundColor(Color.BLACK);
        }

        if(model.getCurrentThickness()==3)
        {
            thinButton.setBackgroundColor(Color.LTGRAY);
            MedButton.setBackgroundColor(Color.WHITE);
            ThickButton.setBackgroundColor(Color.WHITE);
        }
        else if(model.getCurrentThickness()==10)

        {
            thinButton.setBackgroundColor(Color.WHITE);
            MedButton.setBackgroundColor(Color.LTGRAY);
            ThickButton.setBackgroundColor(Color.WHITE);
        }
        else if(model.getCurrentThickness()==20)

        {
            thinButton.setBackgroundColor(Color.WHITE);
            MedButton.setBackgroundColor(Color.WHITE);
            ThickButton.setBackgroundColor(Color.LTGRAY);
        }
        // update button text with click count
        // (convert to string, or else Android uses int as resource id!)
    }
}
