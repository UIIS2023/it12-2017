package shapes;

import java.awt.*;

import hexagon.Hexagon;

public class HexagonAdapter extends FillShape {
	
	private Hexagon hexagon;
	
	public HexagonAdapter(Point center, int radius,  Color fillColor, Color edgeColor){
        this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
        this.hexagon.setAreaColor(fillColor);
        this.hexagon.setBorderColor(edgeColor);
        
    }

    public HexagonAdapter(){

    }
    
    public void setHexagon(Hexagon hexagon) {
        this.hexagon = hexagon;
        hexagon.setSelected(true);
    }
    
    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        hexagon.setSelected(selected);
    }

    @Override
    public boolean isSelected() {
        return hexagon.isSelected();
    }
    
    public Hexagon getHexagon() {
        return hexagon;
    }

    public Color getFillColor() {
        return hexagon.getAreaColor();
    }

    public void setFillColor(Color fillColor) {
        hexagon.setAreaColor(fillColor);
    }

    public Color getEdgeColor() {
        return hexagon.getBorderColor();
    }

    public void setEdgeColor(Color edgeColor) {
        hexagon.setBorderColor(edgeColor);
    }

    public double area() {
        return hexagon.getR() * hexagon.getR() * Math.PI;
    }

    public int getRadius() {
        return hexagon.getR();
    }

    public void setRadius(int radius) {
        hexagon.setR(radius);
    }

    public Point getCenter() {
        return new Point(hexagon.getX(),hexagon.getY());
    }


    @Override
    public void fill(Graphics g) {

    }

    @Override
    public boolean contains(int x, int y) {
        return hexagon.doesContain(x, y);
    }

    @Override
    public void draw(Graphics g) {
        hexagon.paint(g);
    }

    @Override
    public int compareTo(Object o) {
    	 if(o instanceof Hexagon) {
             Hexagon h = (Hexagon) o;
             return (int) (hexagon.getR() - h.getR());
         }
         else
             return 0;
    }

    @Override
    public void moveBy(int x, int y) {

    }
    
    
    public String toString() {
        return "Hexagon: (" + getCenter().getX() + ", " + getCenter().getY() + "), " + "Radius="+getRadius()+", Inside Color: ("+Integer.toString(getFillColor().getRGB())+")" + ", Outside Color: ("+Integer.toString(getEdgeColor().getRGB())+")";
    }
    
    
    
   /* public String toString() {
        return "Hexagon: (" + getCenter().getX() + ", " + getCenter().getY() + "), " + "Radius="+getRadius()+", Outside Color: ("+Integer.toString(getEdgeColor().getRGB())+")" + ", Inside Color: ("+Integer.toString(getFillColor().getRGB())+")";
    }*/
    
    
    public boolean equals(Object obj){
        if(obj instanceof HexagonAdapter){
            HexagonAdapter hexAdapter = (HexagonAdapter) obj;
            Point p1 = new Point(hexagon.getX(),hexagon.getY());
            Point p2 = new Point(hexAdapter.hexagon.getX(),hexAdapter.hexagon.getY());
            if(p1.equals(p2) && hexagon.getR() == hexAdapter.getHexagon().getR())
                return true;
            else
                return false;

        }
        else
            return false;
    }
    public HexagonAdapter clone(HexagonAdapter h) {
	    h.getCenter().setX(this.getCenter().getX());
        h.getCenter().setY(this.getCenter().getY());
        h.setRadius(this.getRadius()); 
        h.setFillColor(this.getFillColor());
        h.setEdgeColor(this.getEdgeColor());
       

        return h;
    }
    
    

}
