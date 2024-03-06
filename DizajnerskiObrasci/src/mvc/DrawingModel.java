package mvc;

import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Shape;
import shapes.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public class DrawingModel {
    private ArrayList<Shape> shapes;

    public DrawingModel(){
        shapes = new ArrayList<>(); 
       
    }

    public ArrayList<Shape> getShapes(){
        return this.shapes;
    }
    
    public int getIndexOfShape(Shape shape){
        return shapes.indexOf(shape);
    }

    public void insertShapeToIndex(int index, Shape shape){
        shapes.add(index, shape);
    }

    public void add(Shape shape){
        shapes.add(shape);
    }

    public void remove(Shape shape){
        shapes.remove(shape);
    }

    public Shape getById(int index){
        return shapes.get(index);
    }


}
