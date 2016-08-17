package com.example.wwsskkaa.jsketch;
//// got from prof's mvc example for android studio

import android.graphics.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
	private int counter=0;
	private Buttons currentButton=Buttons.SQUARE;
	public  ArrayList<ArrayList<CanvasShape>> keepingTrackArray=new ArrayList<ArrayList<CanvasShape>>();
	public  ArrayList<ArrayList<CanvasShape>> keepingTrackRedoArray=new ArrayList<ArrayList<CanvasShape>>();

	public ArrayList<CanvasShape> shapearray=new ArrayList<CanvasShape>();
	int sthselected=-1;
	private int[] colors={
			Color.argb(255, 150,188,235),
			Color.argb(255, 201,255,224),
			Color.argb(255, 250,190,218)
	};
	private int currentColor=0;
	private int currentThickness=20;



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





	public void updateKeeptrack()
	{
		//System.out.println("***"+keepingTrackArray.size());
		for(ArrayList<CanvasShape> i:keepingTrackArray)
		{
			for(CanvasShape j:i)
			{
			//	System.out.println("  "+j.getXcoordinate()+" "+j.getYcoordinate()+" "+j.getEndXcoordinate()+" "+j.getEndYcoordinate()+" "+j.getW()+" "+j.getH());
			}
		}


		while (keepingTrackArray.size()>(counter+1)){
			keepingTrackArray.remove(keepingTrackArray.size()-1);
		}
//		for(int i=counter+1;i<keepingTrackArray.size();i++)
//		{
//			keepingTrackArray.remove(i);
//
//		}
		//System.out.println("Old Size: "+ keepingTrackArray.size());

		keepingTrackRedoArray.clear();

		ArrayList<CanvasShape> newarray=new ArrayList<CanvasShape>();
		for(CanvasShape i:shapearray)
		{
			bordercolor=i.getColor();
			fillcolor=i.getFillcolor();
			button=i.getButton();
			thick=i.getThick();
			selected=i.isSelected();
			Xcoordinate=i.getXcoordinate();
			Ycoordinate=i.getYcoordinate();
			endXcoordinate=i.getEndXcoordinate();
			endYcoordinate=i.getEndYcoordinate();
			w=i.getW();
			h=i.getH();
			CanvasShape newshape=new CanvasShape(bordercolor,button,thick);
			newshape.setFillcolor(fillcolor);
			newshape.setSelected(selected);
			newshape.setXcoordinate(Xcoordinate);
			newshape.setYcoordinate(Ycoordinate);
			newshape.setEndXcoordinate(endXcoordinate);
			newshape.setEndYcoordinate(endYcoordinate);
			newshape.setW(w);
			newshape.setH(h);

			newarray.add(newshape);
		}

		keepingTrackArray.add(newarray);
		//
		// System.out.println("Size: "+ keepingTrackArray.size());
		counter=keepingTrackArray.size()-1;

	}

	public int sthisSelected() {
		return sthselected;
	}

	public void setsthSelected(int selected) {
		this.sthselected = selected;

	}


	public void UndoState()
	//{
	{	if(counter>0) {
		counter--;

		shapearray.clear();
		for(CanvasShape i:keepingTrackArray.get(counter))
		{
			bordercolor=i.getColor();
			fillcolor=i.getFillcolor();
			button=i.getButton();
			thick=i.getThick();
			selected=i.isSelected();
			Xcoordinate=i.getXcoordinate();
			Ycoordinate=i.getYcoordinate();
			endXcoordinate=i.getEndXcoordinate();
			endYcoordinate=i.getEndYcoordinate();
			w=i.getW();
			h=i.getH();
			CanvasShape newshape=new CanvasShape(bordercolor,button,thick);
			newshape.setFillcolor(fillcolor);
			newshape.setSelected(selected);
			newshape.setXcoordinate(Xcoordinate);
			newshape.setYcoordinate(Ycoordinate);
			newshape.setEndXcoordinate(endXcoordinate);
			newshape.setEndYcoordinate(endYcoordinate);
			newshape.setW(w);
			newshape.setH(h);
			shapearray.add(newshape);
		}
		//	System.out.println("counter: " + counter);
		}
//		if(keepingTrackArray.size()>=2)
//		{
//		ArrayList<CanvasShape> newarray=new ArrayList<CanvasShape>();
//
//		for(CanvasShape i:keepingTrackArray.get(keepingTrackArray.size()-1))
//		{
//			bordercolor=i.getColor();
//			fillcolor=i.getFillcolor();
//			button=i.getButton();
//			thick=i.getThick();
//			selected=i.isSelected();
//			Xcoordinate=i.getXcoordinate();
//			Ycoordinate=i.getYcoordinate();
//			endXcoordinate=i.getEndXcoordinate();
//			endYcoordinate=i.getEndYcoordinate();
//			w=i.getW();
//			h=i.getH();
//			CanvasShape newshape=new CanvasShape(bordercolor,button,thick);
//			newshape.setFillcolor(fillcolor);
//			newshape.setSelected(selected);
//			newshape.setXcoordinate(Xcoordinate);
//			newshape.setYcoordinate(Ycoordinate);
//			newshape.setEndXcoordinate(endXcoordinate);
//			newshape.setEndYcoordinate(endYcoordinate);
//			newarray.add(newshape);
//		}
//		keepingTrackRedoArray.add(newarray);
//		keepingTrackArray.remove(keepingTrackArray.size()-1);
//			shapearray.clear();
//		for(CanvasShape i:keepingTrackArray.get(keepingTrackArray.size()-1))
//		{
//			bordercolor=i.getColor();
//			fillcolor=i.getFillcolor();
//			button=i.getButton();
//			thick=i.getThick();
//			selected=i.isSelected();
//			Xcoordinate=i.getXcoordinate();
//			Ycoordinate=i.getYcoordinate();
//			endXcoordinate=i.getEndXcoordinate();
//			endYcoordinate=i.getEndYcoordinate();
//			w=i.getW();
//			h=i.getH();
//			CanvasShape newshape=new CanvasShape(bordercolor,button,thick);
//			newshape.setFillcolor(fillcolor);
//			newshape.setSelected(selected);
//			newshape.setXcoordinate(Xcoordinate);
//			newshape.setYcoordinate(Ycoordinate);
//			newshape.setEndXcoordinate(endXcoordinate);
//			newshape.setEndYcoordinate(endYcoordinate);
//			shapearray.add(newshape);
//		}


		setChanged();
		notifyObservers();
	}
	public void RedoState()
	{
	//{
		if(counter<keepingTrackArray.size()-1)
		{
			counter++;

			shapearray.clear();
			for(CanvasShape i:keepingTrackArray.get(counter))
			{
				bordercolor=i.getColor();
				fillcolor=i.getFillcolor();
				button=i.getButton();
				thick=i.getThick();
				selected=i.isSelected();
				Xcoordinate=i.getXcoordinate();
				Ycoordinate=i.getYcoordinate();
				endXcoordinate=i.getEndXcoordinate();
				endYcoordinate=i.getEndYcoordinate();
				w=i.getW();
				h=i.getH();
				CanvasShape newshape=new CanvasShape(bordercolor,button,thick);
				newshape.setFillcolor(fillcolor);
				newshape.setSelected(selected);
				newshape.setXcoordinate(Xcoordinate);
				newshape.setYcoordinate(Ycoordinate);
				newshape.setEndXcoordinate(endXcoordinate);
				newshape.setEndYcoordinate(endYcoordinate);
				newshape.setW(w);
				newshape.setH(h);
				shapearray.add(newshape);
			}

		}
//		if(keepingTrackRedoArray!=null&&keepingTrackRedoArray.size()>0) {
//			shapearray.clear();
//			ArrayList<CanvasShape> newarray=new ArrayList<CanvasShape>();
//			for(CanvasShape i:keepingTrackRedoArray.get(keepingTrackArray.size()-1))
//			{
//				bordercolor=i.getColor();
//				fillcolor=i.getFillcolor();
//				button=i.getButton();
//				thick=i.getThick();
//				selected=i.isSelected();
//				Xcoordinate=i.getXcoordinate();
//				Ycoordinate=i.getYcoordinate();
//				endXcoordinate=i.getEndXcoordinate();
//				endYcoordinate=i.getEndYcoordinate();
//				w=i.getW();
//				h=i.getH();
//				CanvasShape newshape=new CanvasShape(bordercolor,button,thick);
//				newshape.setFillcolor(fillcolor);
//				newshape.setSelected(selected);
//				newshape.setXcoordinate(Xcoordinate);
//				newshape.setYcoordinate(Ycoordinate);
//				newshape.setEndXcoordinate(endXcoordinate);
//				newshape.setEndYcoordinate(endYcoordinate);
//				shapearray.add(newshape);
//				newarray.add(newshape);
//			}
//			keepingTrackArray.add(newarray);
//			keepingTrackRedoArray.remove(keepingTrackRedoArray.size()-1);
			setChanged();
			notifyObservers();

	}
	public void setShape(CanvasShape newshape) {
		if(shapearray==null)
		{
			shapearray=new ArrayList<CanvasShape>();
		}
		this.shapearray.add(newshape);
		updateKeeptrack();
		setChanged();
		notifyObservers();
	}

	public int getCurrentColor() {
		return currentColor;
	}
	public ArrayList<CanvasShape> getShapearray() {
		return shapearray;
	}
	public void setCurrentColor(int currentColor) {
		this.currentColor = currentColor;
		setChanged();
		notifyObservers();
	}
	public int getCurrentThickness() {
		return currentThickness;
	}

	public void setCurrentThickness(int currentThickness) {
		this.currentThickness = currentThickness;
		setChanged();
		notifyObservers();
	}

	Model() {
		counter = 0;

		ArrayList<CanvasShape> newarray=new ArrayList<CanvasShape>();
		keepingTrackArray.add(newarray);




	}
	public void setShapearray(ArrayList<CanvasShape> shapearray) {
		this.shapearray = shapearray;
	}


	public void setCurrentButton(Buttons B)
	{
		currentButton=B;
		setChanged();
		notifyObservers();
	}
	public Buttons getCurrentButton()
	{
		return currentButton;
	}



	public void removeShape(int i)
	{
		if(shapearray.size()>i)
		{
			shapearray.remove(i);
			updateKeeptrack();
			setChanged();
			notifyObservers();
		}
	}

	// Observer methods
	@Override
	public void addObserver(Observer observer) {
		super.addObserver(observer);
	}
	public void cleanArray()
	{
		shapearray.clear();
		setsthSelected(-1);
		//updateKeeptrack();
		setChanged();
		notifyObservers();
	}
	public void modifyColor(int index)
	{
		if(shapearray.size()>0) {

			if(shapearray.get(index).getColor()!=currentColor) {
				updateKeeptrack();
			}
			shapearray.get(index).setColor(currentColor);

		}
	}
	public void setFinalLocation(int index,float d,float e,float f,float g)
	{
		if(shapearray.get(index).getButton()==Buttons.OVAL)
		{
			shapearray.get(index).setXcoordinate(d);
			shapearray.get(index).setYcoordinate(e);
		}
		else if(shapearray.get(index).getButton()!=Buttons.OVAL)
		{
			shapearray.get(index).setXcoordinate(d);
			shapearray.get(index).setYcoordinate(e);
			shapearray.get(index).setEndXcoordinate(f);
			shapearray.get(index).setEndYcoordinate(g);
		}
		updateKeeptrack();
		setChanged();
		notifyObservers();
	}
	public void setLocation(int index,float d,float e,float f,float g)
	{
		if(shapearray.get(index).getButton()==Buttons.OVAL)
		{
			shapearray.get(index).setXcoordinate(d);
			shapearray.get(index).setYcoordinate(e);
		}
		else if(shapearray.get(index).getButton()!=Buttons.OVAL)
		{
			shapearray.get(index).setXcoordinate(d);
			shapearray.get(index).setYcoordinate(e);
			shapearray.get(index).setEndXcoordinate(f);
			shapearray.get(index).setEndYcoordinate(g);
		}

		setChanged();
		notifyObservers();
	}
	@Override
	public synchronized void deleteObservers()
	{
		super.deleteObservers();
	}

	public void removeSelection()
	{
		if(shapearray!=null&&shapearray.size()!=0)
		{
			for(CanvasShape i:shapearray)
			{
				i.setSelected(false);
			}
			setsthSelected(-1);
		}
	}
	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	@Override
	protected void setChanged() {
		super.setChanged();
	}

	@Override
	protected void clearChanged() {
		super.clearChanged();
	}
}