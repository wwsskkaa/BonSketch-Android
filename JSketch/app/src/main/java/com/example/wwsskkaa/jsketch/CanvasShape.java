package com.example.wwsskkaa.jsketch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;


import java.util.Observable;
import java.util.Observer;
/**
 * Created by wwsskkaa on 2016-07-08.
 */
public class CanvasShape implements Observer{
    private int[] colors={
            Color.argb(255, 150,188,235),//blue
            Color.argb(255, 201,255,224),//green
            Color.argb(255, 250,190,218)//pink
    };
    private int bordercolor;
    private int fillcolor=-1;
    private Buttons button;
    private int thick;
    private boolean selected=false;
    private float Xcoordinate;
    private float Ycoordinate;
    private float endXcoordinate;
    private float endYcoordinate;
    private float w;
    private float h;
    float EPSILON = 20f;



    CanvasShape(int c,Buttons b,int t)
    {
        bordercolor=c;
        button=b;
        thick=t;
    }



    public void setShape(float startx,float starty,float endx,float endy){
        float width=Math.abs(startx-endx);
        float height=Math.abs(starty-endy);
        float x0 = Math.min(startx, endx);
        float y0 = Math.min(starty, endy);
        float diameter=Math.min(width, height);

        if(button==Buttons.SQUARE)
        {
            Xcoordinate=x0;
            Ycoordinate=y0;
            setEndXcoordinate(x0+width);
            setEndYcoordinate(y0+height);
            setW(width);
            setH(height);

        }
        else if(button==Buttons.OVAL)
        {
            if(endx>=startx)
            {
               // shape=new Ellipse2D.Double(x0, y0,diameter,diameter);
                Xcoordinate=x0;
                Ycoordinate=y0;
                setEndXcoordinate(-1);
                setEndYcoordinate(-1);
                setW(diameter);
                setH(diameter);
            }
            else
            {
                if(endy<=starty)
                {
                    //shape=new Ellipse2D.Double(start.getX()-diameter, start.getY()-diameter,diameter,diameter);
                    Xcoordinate=startx-diameter;
                    Ycoordinate=starty-diameter;
                    setEndXcoordinate(-1);
                    setEndYcoordinate(-1);
                    setW(diameter);
                    setH(diameter);
                }
                else
                {
                    //shape=new Ellipse2D.Double(start.getX()-diameter, start.getY(),diameter,diameter);
                    Xcoordinate=startx-diameter;
                    Ycoordinate=starty;
                    setEndXcoordinate(-1);
                    setEndYcoordinate(-1);
                    setW(diameter);
                    setH(diameter);

                }
            }
        }
        else if(button==Buttons.LINE)
        {
            //shape=new Line2D.Double(start.getX(), start.getY(),end.getX(),end.getY());
            Xcoordinate=startx;
            Ycoordinate=starty;
            setEndXcoordinate(endx);
            setEndYcoordinate(endy);
            setW(-1);
            setH(-1);

        }
    }

    public void drawFrame(Canvas canvas,Buttons b) {





        Paint fgPaintSel = new Paint();
        fgPaintSel.setColor(Color.DKGRAY);
        fgPaintSel.setStyle(Paint.Style.STROKE);
        fgPaintSel.setStrokeWidth(thick);
        fgPaintSel.setPathEffect(new DashPathEffect(new float[] {10,20}, 5));
        if(b==Buttons.LINE) {
            Path mPath;
            mPath = new Path();
            mPath.moveTo(Xcoordinate, Ycoordinate);
            mPath.lineTo(endXcoordinate,endYcoordinate);
            canvas.drawPath(mPath, fgPaintSel);
        }
        else if(b==Buttons.SQUARE) {
            canvas.drawRect(Xcoordinate, Ycoordinate, endXcoordinate, endYcoordinate, fgPaintSel);
        }
        else if(b==Buttons.OVAL) {
            canvas.drawCircle(Xcoordinate+(w/2), Ycoordinate+(w/2),w/2, fgPaintSel);
        }


    }
    public boolean circleContain(float clickx,float clicky)
    {

        return ((clickx-(Xcoordinate+(w/2)))*(clickx-(Xcoordinate+(w/2))))+((clicky-(Ycoordinate+(w/2)))*(clicky-(Ycoordinate+(w/2))))<=(w/2)*(w/2);
    }
    public boolean squareContain(float clickx,float clicky)
    {
        return (clickx>=Xcoordinate&&clickx<=endXcoordinate&&clicky>=Ycoordinate&&clicky<=endYcoordinate);
    }

    public boolean lineIntersect(float linePointAx, float linePointAy, float linePointBx,float linePointBy,float pointx,float pointy) {

        if (Math.abs(linePointAx - linePointBx) <= EPSILON) {
            // We've a vertical line, thus check only the x-value of the point.
            return (Math.abs(pointx - linePointAx) <= EPSILON&&pointy>=Math.min(linePointAy,linePointBy)&&pointy<=Math.max(linePointAy,linePointBy));
        } else {

            float m = (linePointBy - linePointAy) / (linePointBx - linePointAx);
            float b = linePointAy - m * linePointAx;
            return (Math.abs(pointy - (m * pointx + b)) <= EPSILON)&&pointy>=Math.min(linePointAy,linePointBy)&&pointy<=Math.max(linePointAy,linePointBy)&&(pointx>=Math.min(linePointAx,linePointBx))&&(pointx<=Math.max(linePointAx,linePointBx));
        }


    }

    public void draw(Canvas canvas) {
        Paint drawingpaint=new Paint();
        drawingpaint.setStyle(Paint.Style.STROKE);
        int color=colors[bordercolor];
        drawingpaint.setColor(color);
        drawingpaint.setStrokeWidth(thick);

        if(button==Buttons.LINE) {
            canvas.drawLine(Xcoordinate, Ycoordinate, endXcoordinate, endYcoordinate, drawingpaint);
            if(fillcolor!=-1)
            {
                drawingpaint.setColor(colors[fillcolor]);
                canvas.drawLine(Xcoordinate, Ycoordinate, endXcoordinate, endYcoordinate, drawingpaint);
            }

        }
        else if(button==Buttons.SQUARE) {
            if(fillcolor!=-1)
            {
                drawingpaint.setStyle(Paint.Style.FILL);
                drawingpaint.setColor(colors[fillcolor]);
                canvas.drawRect(Xcoordinate, Ycoordinate, endXcoordinate, endYcoordinate, drawingpaint);
            }
            drawingpaint.setStyle(Paint.Style.STROKE);
            drawingpaint.setColor(colors[bordercolor]);
            canvas.drawRect(Xcoordinate, Ycoordinate, endXcoordinate, endYcoordinate, drawingpaint);

        }
        else if(button==Buttons.OVAL) {
            if(fillcolor!=-1)
            {
                drawingpaint.setStyle(Paint.Style.FILL);
                drawingpaint.setColor(colors[fillcolor]);
                canvas.drawCircle(Xcoordinate+(w/2), Ycoordinate+(w/2),w/2, drawingpaint);
            }
            drawingpaint.setStyle(Paint.Style.STROKE);
            drawingpaint.setColor(colors[bordercolor]);
            canvas.drawCircle(Xcoordinate+(w/2), Ycoordinate+(w/2),w/2, drawingpaint);

        }

        if(selected)
        {

            drawFrame(canvas,button);
        }

    }

    public boolean contains(float clickx, float clicky) {
        return
                (button == Buttons.LINE)&&lineIntersect(getXcoordinate(),getYcoordinate(),getEndXcoordinate(),getEndYcoordinate(),clickx,clicky)||
                (button == Buttons.OVAL)&&circleContain(clickx,clicky) ||
                (button == Buttons.SQUARE)&&squareContain(clickx, clicky);

    }

    @Override
    public void update(Observable o, Object arg) {

    }
    public int getColor() {
        return bordercolor;
    }

    public void setColor(int color) {
        this.bordercolor = color;
    }
    public Buttons getButton(){
        return button;
    }


    public int getThick() {
        return thick;
    }

    public void setThick(int thick) {
        this.thick = thick;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

    }


    public int getFillcolor() {
        return fillcolor;
    }


    public void setFillcolor(int fillcolor) {
        this.fillcolor = fillcolor;
    }


    public float getXcoordinate() {
        return Xcoordinate;
    }


    public void setXcoordinate(float xcoordinate) {
        Xcoordinate = xcoordinate;
    }


    public float getYcoordinate() {
        return Ycoordinate;
    }


    public void setYcoordinate(float ycoordinate) {
        Ycoordinate = ycoordinate;
    }


    public float getEndXcoordinate() {
        return endXcoordinate;
    }


    public void setEndXcoordinate(float endXcoordinate) {
        this.endXcoordinate = endXcoordinate;
    }


    public float getEndYcoordinate() {
        return endYcoordinate;
    }


    public void setEndYcoordinate(float endYcoordinate) {
        this.endYcoordinate = endYcoordinate;
    }


    public float getW() {
        return w;
    }


    public void setW(float w) {
        this.w = w;
    }


    public float getH() {
        return h;
    }


    public void setH(float h) {
        this.h = h;
    }
}