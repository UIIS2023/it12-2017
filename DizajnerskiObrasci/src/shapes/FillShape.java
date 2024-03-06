package shapes;

import java.awt.*;

public abstract class FillShape extends Shape{

    private Color fillColor;

    
    public abstract void fill(Graphics g);

   
    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}

