package com.example.wwsskkaa.jsketch;

//// got from prof's mvc example for android studio

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class canvasview extends LinearLayout implements Observer {

    private Model model;
    private Button button;
    private CanvasShape currentShape;
    private int currentSelected=-1;

    private float startx=-1;
    private float starty=-1;
    private float endx=-1;
    private float endy=-1;
    private float preX, preY;

    private int[] colors={
            Color.argb(255, 150,188,235),//blue
            Color.argb(255, 201,255,224),//green
            Color.argb(255, 250,190,218)//pink
    };

    public canvasview(Context context, Model m) {
        super(context);

        View.inflate(context, R.layout.canvasview, this);

        model = m;
        model.addObserver(this);
    setWillNotDraw(false);

        invalidate();

    }
    public void updateFinalLocation(int i ,MotionEvent e) {

        model.updateKeeptrack();

    }
    public void updateLocation(int i ,MotionEvent e) {

        CanvasShape sh = model.getShapearray().get(i);

        float deltaX = sh.getEndXcoordinate() - sh.getXcoordinate();
        float deltaY = sh.getEndYcoordinate() - sh.getYcoordinate();


        float newX = -preX+e.getX();
        float newY = -preY+e.getY();


        float newEndX = newX + deltaX;
        float newEndY = newY + deltaY;

        model.setLocation(i,newX,newY,newEndX,newEndY);


        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.

        final int action = MotionEventCompat.getActionMasked(ev);
        if(model.getCurrentButton()==Buttons.SELECTION||model.getCurrentButton()==Buttons.FILL||model.getCurrentButton()==Buttons.ERASE)
        {

            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    float clickx = ev.getX();
                    float clicky = ev.getY();
                    ArrayList<CanvasShape> shapes=model.getShapearray();
                    for(int i=shapes.size()-1;i>=0;i--)
                    {
                        if(shapes.get(i).contains(clickx,clicky))
                        {
                            if(model.getCurrentButton()==Buttons.SELECTION) {
                                shapes.get(i).setSelected(true);
                                currentSelected=i;
                                model.setsthSelected(i);
                                for(int j=shapes.size()-1;j>=0;j--)
                                {
                                    if(j!=i)
                                    {
                                        shapes.get(j).setSelected(false);
                                    }
                                }
                                       model.setCurrentColor(shapes.get(i).getColor());

                                model.setCurrentThickness(shapes.get(i).getThick());

                                preX = clickx - shapes.get(i).getXcoordinate();
                                preY = clicky - shapes.get(i).getYcoordinate();
                           }
                            else if(model.getCurrentButton()==Buttons.FILL) {
                                //i.setSelected(true);
                                shapes.get(i).setFillcolor(model.getCurrentColor());
                                model.updateKeeptrack();
                                invalidate();

                            }
                            else if(model.getCurrentButton()==Buttons.ERASE) {
                               // i.setSelected(true);
                                model.removeShape(i);
                                invalidate();
                            }
                            break;
                        }

                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    float clickx = ev.getX();
                    float clicky = ev.getY();

                    if(model.getCurrentButton()==Buttons.SELECTION&&currentSelected!=-1)
                    {
                        updateLocation(currentSelected,ev);
                        invalidate();

                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    float clickx = ev.getX();
                    float clicky = ev.getY();

                    if(model.getCurrentButton()==Buttons.SELECTION&&currentSelected!=-1)
                    {
                        updateFinalLocation(currentSelected,ev);
                        invalidate();

                    }
                    break;
                }
            }
        }
        else if(model.getCurrentButton()==Buttons.LINE||model.getCurrentButton()==Buttons.OVAL||model.getCurrentButton()==Buttons.SQUARE)
        {
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    if(model.getCurrentThickness()!=0)
                    {
                        currentShape = new CanvasShape(model.getCurrentColor(), model.getCurrentButton(), model.getCurrentThickness());
                    }
                    startx = ev.getX();
                    starty =  ev.getY();

                    currentShape.setXcoordinate(startx);
                    currentShape.setYcoordinate(starty);

                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    endx =  ev.getX();
                    endy =  ev.getY();

                    invalidate();

                    if(currentShape!=null)
                        {
                            currentShape.setShape(startx,starty,endx,endy);
                            invalidate();
                        }


                    break;
                }
                case MotionEvent.ACTION_UP: {
                    //endx = ev.getX();
                    //endy =  ev.getY();

                    if(currentShape!=null&&startx!=-1&&starty!=-1&&endx!=-1&&endy!=-1)
                    {
                        model.setShape(currentShape);
                        currentShape = null;
                    }
                    invalidate();

                    startx=-1;
                    starty=-1;
                    endx=-1;
                    endy=-1;


                    break;



                }
            }
        }
        model.setChanged();
        model.notifyObservers();
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(255,255,255);
        if(model.getShapearray()!=null)
        {
        for(CanvasShape i:model.getShapearray())
        {
            i.draw(canvas);
        }}
        if(currentShape!=null&&startx!=-1&&starty!=-1&&endx!=-1&&endy!=-1)
        {
            currentShape.draw(canvas);
        }

    }



    // the model call this to update the view
    public void update(Observable observable, Object data) {

        if(model.getCurrentButton()!=Buttons.SELECTION)
        {
            currentSelected=-1;
            model.removeSelection();
        }

        invalidate();

    }
}
