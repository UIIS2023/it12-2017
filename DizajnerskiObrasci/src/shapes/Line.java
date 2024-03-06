package shapes;

import java.awt.*;

public class Line extends Shape{

    private Point start = new Point();
    private Point end = new Point();

	    public Line() {
	
	    }
	
	    public Line(Point start, Point end) {
	        this.start = start;
	        this.end = end;
	    }
	
	    public Line(Point start, Point end, Color color) {
	        this.start = start;
	        this.end = end;
	        setEdgeColor(color);
	    }
	
	    public Line(Point start, Point end, boolean selected) {
	        this.start = start;
	        this.end = end;
	        setSelected(selected);
	    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }  
   
			    @Override
			    public void draw(Graphics g) {
			        g.setColor(getEdgeColor());
			        g.drawLine(getStart().getX(), getStart().getY(), getEnd().getX(), getEnd().getY());
			
			        if (isSelected()) {
			            g.setColor(Color.BLACK);
			            g.drawRect(getStart().getX()  - 3, getStart().getY() - 3, 6, 6);
			            g.drawRect(getEnd().getX() - 3, getEnd().getY() - 3, 6, 6);
			        }
			    }
			    
			    @Override
			    public boolean contains(int x, int y) {
			        if((start.distance(x, y) + end.distance(x, y)) - length() <= 0.05)
			            return true;
			        return false;
			    }
			  
			    public double length() {
			        return start.distance(end.getX(), end.getY());
			    }
			
			
			    @Override
			    public int compareTo(Object o) {
			        return 0;
			    }
			
			    @Override
			    public void moveBy(int x, int y) {
			        this.start.moveBy(x, y);
			        this.end.moveBy(x, y);
			    }	
			    
			    public Line clone(Line l) {
			        l.getStart().setX(this.getStart().getX());
			        l.getStart().setY(this.getStart().getY());
			        l.getEnd().setX(this.getEnd().getX());
			        l.getEnd().setY(this.getEnd().getY());
			        l.setEdgeColor(this.getEdgeColor());
			        return l;
			    }
			    
			    public String toString() {
					//return startPoint + "-->" + endPoint;
					return "Line: "+"("+start.getX()+", "+start.getY()+") ("+end.getX()+", "+end.getY()+"), " + "Color: ("+Integer.toString(getEdgeColor().getRGB())+")";
				}

				public boolean equals(Object obj) {
					if (obj instanceof Line) {
						Line l = (Line) obj;
						if (this.start.equals(l.getStart()) &&
								this.end.equals(l.getEnd())) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
}
