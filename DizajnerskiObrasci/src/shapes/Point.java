package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	
	private int x;
	private int y;
	
	  public Point(){
	
	    }
	
	    public Point(int x, int y){
	        this.x = x;
	        this.y = y;
	    }
	
	    public Point(int x, int y, Color color){	    	
	        this.x = x;
	        this.y = y;	        
	        setEdgeColor(color);
	    }
	
	    public Point(int x, int y, boolean selected){
	        this.x = x;
	        this.y = y;	        
	        setSelected(selected);
	    }
	
	    public Point(int x, int y, boolean selected, Color color){
	        this.x = x;
	        this.y = y;
	
	        setSelected(selected);
	        setEdgeColor(color);
	    }

	
    public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
		 @Override
		    public void draw(Graphics g) {
		        
		        g.setColor(getEdgeColor());
		        g.drawLine(this.x-2, this.y, this.x+2, this.y);	       
		        g.drawLine(this.x, this.y-2, this.x, this.y+2);
	
		       
		        if(isSelected()){	            
		            g.setColor(Color.BLACK);
		            g.drawRect(this.x-3, this.y-3, 6, 6);
		        }
		    }
	 

		 
		    public boolean contains(int x, int y){
		        if(distance(x, y) <= 3){
		            return true;
		        }
		        return false;
		    }

		    public double distance(int x, int y){
		        double dx = this.x - x;
		        double dy = this.y - y;
		        double d = Math.sqrt(dx*dx + dy*dy);
		        return  d;
		    }

		    @Override
		    public void moveBy(int x, int y) {
		        this.x = this.x + x;
		        this.y = this.y + y;
		    }
	
		    @Override
		    public int compareTo(Object o) {
		        return 0;
		    }
		    
		    public Point clone(Point p) {

		        p.setX(this.getX());
		        p.setY(this.getY());
		        p.setEdgeColor(this.getEdgeColor());

		        return p;
		    }
		    
		    public String toString() {
				return "Point: (" + x + ", " + y + "), " + "Color: ("+Integer.toString(getEdgeColor().getRGB())+")";
			}

			public boolean equals(Object obj) {
				if (obj instanceof Point) {
					Point p = (Point) obj;
					if (this.x == p.getX() &&
							this.y == p.getY()) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
}




  
