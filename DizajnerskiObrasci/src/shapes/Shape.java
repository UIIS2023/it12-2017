package shapes;

import java.awt.Color;
import java.awt.Graphics;


public abstract class Shape implements Moveable, Comparable{
	
	private boolean isSelected;
	private Color edgeColor;
	
	public Shape(){

	}
	public Shape(boolean isSelected){
	        this.isSelected = isSelected;
	}
	
	
    public abstract boolean contains(int x, int y);
    
    public abstract void draw(Graphics g);

	
    
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public Color getEdgeColor() {
		return edgeColor;
	}
	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}
	
}
