package shapes;

import java.awt.*;

public class Circle extends FillShape{

    private Point center = new Point();
    private int radius;

    public Circle() {

    }

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(Point center, int radius, Color fillColor, Color edgeColor) {
        this.center = center;
        this.radius = radius;
        setFillColor(fillColor);
        setEdgeColor(edgeColor);
    }

    public Circle(Point center, int radius, boolean selected) {
        this(center, radius);
        setSelected(selected);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void fill(Graphics g) {
        g.setColor(getFillColor());
        g.fillOval(this.center.getX()+1 - this.radius,
                this.center.getY()+1 - this.radius,
                (this.radius-1)*2,
                (this.radius-1)*2);
    }

    @Override
    public boolean contains(int x, int y) {
        return this.center.distance(x, y) <= radius ;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getEdgeColor());
        g.drawOval(this.center.getX() - this.radius,
                this.center.getY() - this.radius,
                this.radius*2,
                this.radius*2);

        
        fill(g);

        if (isSelected()) {
            g.setColor(Color.BLACK);
            g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
            g.drawRect(getCenter().getX() + getRadius() - 3, getCenter().getY() - 3, 6, 6);
            g.drawRect(getCenter().getX() - getRadius() - 3, getCenter().getY() - 3, 6, 6);
            g.drawRect(getCenter().getX() - 3, getCenter().getY() + getRadius() - 3, 6, 6);
            g.drawRect(getCenter().getX() - 3, getCenter().getY() - getRadius() - 3, 6, 6);
        }
    }

    public double area() {
        return radius * radius * Math.PI;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void moveBy(int x, int y) {
        center.moveBy(x, y);
    }
    
    public Circle clone(Circle c) {
        c.getCenter().setX(this.getCenter().getX());
        c.getCenter().setY(this.getCenter().getY());
        try {
            c.setRadius(this.getRadius());
        } catch (Exception e) {
            throw new NumberFormatException("Radius has to be greater then 0!");
        }
        c.setEdgeColor(this.getEdgeColor());
        c.setFillColor(this.getFillColor());

        return c;
    }
    public boolean equals(Object obj) {
        if (obj instanceof Circle) {
            Circle c = (Circle) obj;
            if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "Circle: (" + center.getX() + ", " + center.getY() + "), " + "Radius="+radius+ ", Inside Color: ("+Integer.toString(getFillColor().getRGB())+")" + ", Outside Color: ("+Integer.toString(getEdgeColor().getRGB())+")";
    }
}
