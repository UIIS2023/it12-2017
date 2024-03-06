package shapes;

import java.awt.*;

public class Rectangle extends FillShape{
    private Point upperLeftPoint = new Point();
    private int width;
    private int height;

    public Rectangle() {

    }

    public Rectangle(Point upperLeftPoint, int height, int width) {
        this.upperLeftPoint = upperLeftPoint;
        this.height = height;
        this.width = width;
    }

    public Rectangle(Point upperLeftPoint, int height, int width, Color fillColor, Color edgeColor) {
        this.upperLeftPoint = upperLeftPoint;
        this.height = height;
        this.width = width;
        setEdgeColor(edgeColor);
        setFillColor(fillColor);

    }

    public Rectangle(Point upperLeftPoint, int height, int width, Color fillColor, Color edgeColor, boolean selected) {
        this.upperLeftPoint = upperLeftPoint;
        this.height = height;
        this.width = width;
        setEdgeColor(edgeColor);
        setFillColor(fillColor);
        setSelected(selected);
    }

    public Point getUpperLeftPoint() {
        return upperLeftPoint;
    }

    public void setUpperLeftPoint(Point upperLeftPoint) {
        this.upperLeftPoint = upperLeftPoint;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void fill(Graphics g) {
        g.setColor(getFillColor());
        g.fillRect(this.upperLeftPoint.getX()+1,
                this.upperLeftPoint.getY()+1,
                this.width-1,
                this.height-1);
    }

    @Override
    public boolean contains(int x, int y) {
        if (this.upperLeftPoint.getX() <= x
                && x<= this.upperLeftPoint.getX() + this.width
                && this.upperLeftPoint.getY() <= y
                && y <= this.upperLeftPoint.getY() + this.height) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getEdgeColor());
        g.drawRect(this.upperLeftPoint.getX(),
                this.upperLeftPoint.getY(),
                this.width, this.height);

        fill(g);

        if (isSelected()) {
            g.setColor(Color.BLUE);
            g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() - 3, 6, 6);
            g.drawRect(this.upperLeftPoint.getX() - 3 + this.width, this.upperLeftPoint.getY() - 3, 6, 6);
            g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() - 3 + this.height, 6, 6);
            g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void moveBy(int x, int y) {
        upperLeftPoint.moveBy(x, y);
    }
    
    public Rectangle clone(Rectangle r) {

        r.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
        r.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
        r.setHeight(this.getHeight());
        r.setWidth(this.getWidth());
        r.setEdgeColor(this.getEdgeColor());
        r.setFillColor(this.getFillColor());

        return r;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            Rectangle r = (Rectangle) obj;
            if (this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.height == r.getHeight()
                    && this.width == r.getWidth()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "Rectangle: (" + upperLeftPoint.getX() + ", " + upperLeftPoint.getY() + "), " + "Height=" +height+ ",Width="+width+ ", Inside Color: ("+Integer.toString(getFillColor().getRGB())+")" + ", Outside Color: ("+Integer.toString(getEdgeColor().getRGB())+")";
    }
}
