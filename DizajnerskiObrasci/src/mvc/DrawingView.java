package mvc;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;

public class DrawingView extends JPanel {
    private DrawingModel model;

    public DrawingView(DrawingModel model){
        this.model = model;
    }

    public void setModel(DrawingModel model){
        this.model = model;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(Shape shape: this.model.getShapes()){
            shape.draw(g);
        }
    }
}
