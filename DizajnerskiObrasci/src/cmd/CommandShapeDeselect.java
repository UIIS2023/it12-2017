package cmd;

import mvc.DrawingController;
import shapes.Shape;

public class CommandShapeDeselect implements Command{
    private DrawingController controller;
    private Shape shape;

    public CommandShapeDeselect(DrawingController controller, Shape shape) {
        this.controller = controller;
        this.shape = shape;
    }


    @Override
    public void execute() {
    	shape.setSelected(false);
        int index  = controller.getModel().getIndexOfShape(shape);
        if (index > -1) {
            controller.getModel().getById(index).setSelected(false);
        }
        controller.getSelectedShapes().remove(shape);
    
    }

    @Override
    public void unexecute() {
    	shape.setSelected(true);
        int index  = controller.getModel().getIndexOfShape(shape);
        controller.getModel().getById(index).setSelected(true);
        controller.getSelectedShapes().add(shape);
    	
    }
}