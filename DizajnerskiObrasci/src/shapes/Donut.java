package shapes;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle{

    private int innerRadius;

    public Donut() {

    }

    public Donut(Point center, int radius, int innerRadius) {
        
        super(center, radius);
        this.innerRadius = innerRadius;
    }

    public Donut(Point center, int radius, int innerRadius, Color fillColor, Color edgeColor) {
        super(center, radius);
        this.innerRadius = innerRadius;

        if(fillColor == null) {
            setFillColor(Color.BLACK);
        } else {
            setFillColor(fillColor);
        }
        if(edgeColor == null) {
            setEdgeColor(Color.BLACK);
        } else {
            setEdgeColor(edgeColor);
        }
    }

    public int getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(int innerRadius) {
        this.innerRadius = innerRadius;
    }

    public void draw(Graphics g) {

       
        Ellipse2D outerCircle = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(),
                this.getCenter().getY() - this.getRadius(),
                this.getRadius()*2,
                this.getRadius()*2);
        Ellipse2D innerCircle = new Ellipse2D.Double(this.getCenter().getX() - this.innerRadius,
                this.getCenter().getY() - this.innerRadius,
                this.innerRadius*2,
                this.innerRadius*2);

        Area area = new Area(outerCircle);
       
        area.subtract(new Area(innerCircle));

      
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getFillColor());
        g2d.fill(area);
        g2d.setColor(getEdgeColor());
        g2d.draw(area);

        if (isSelected()) {
            g.setColor(Color.BLACK);

            g.drawRect(this.getCenter().getX() + this.innerRadius - 3, this.getCenter().getY()-3, 6, 6);
            g.drawRect(this.getCenter().getX() - this.innerRadius - 3, this.getCenter().getY()-3, 6, 6);
            g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + this.innerRadius -3, 6, 6);
            g.drawRect(this.getCenter().getX()  - 3, this.getCenter().getY() - this.innerRadius -3, 6, 6);

            g.drawRect(this.getCenter().getX() + this.getRadius() - 3, this.getCenter().getY()-3, 6, 6);
            g.drawRect(this.getCenter().getX() - this.getRadius() - 3, this.getCenter().getY()-3, 6, 6);
            g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + this.getRadius() -3, 6, 6);
            g.drawRect(this.getCenter().getX()  - 3, this.getCenter().getY() - this.getRadius() -3, 6, 6);
        }
    }

    public boolean contains(int x, int y) {
        double distanceFromCenter = this.getCenter().distance(x, y);
        return distanceFromCenter > this.innerRadius && super.contains(x, y);
    }

    public double area() {
        return super.area() - this.innerRadius * this.innerRadius * Math.PI;
    }
    
    public Donut clone(Donut d) {

        d.getCenter().setX(this.getCenter().getX());
        d.getCenter().setY(this.getCenter().getY());
        d.setInnerRadius(this.getInnerRadius());
        try {
            d.setRadius(this.getRadius());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new NumberFormatException("Radius has to be a value greater then 0!");
        }
        d.setEdgeColor(this.getEdgeColor());
        d.setFillColor(this.getFillColor());

        return d;
    }
    
    public String toString() {
        return "Donut: (" + getCenter().getX() + ", " + getCenter().getY() + "), " + "Radius="+getRadius()+ ", Inside Color: ("+Integer.toString(super.getFillColor().getRGB())+")" + ", Outside Color: ("+Integer.toString(super.getEdgeColor().getRGB())+")" + " Small: Radius="+innerRadius+".";
    }

    public boolean equals(Object obj) {
        if (obj instanceof Donut) {
            Donut d = (Donut) obj;
            if (this.getCenter().equals(d.getCenter()) &&
                    this.getRadius() == d.getRadius() &&
                    this.innerRadius == d.getInnerRadius()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
