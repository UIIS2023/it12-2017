package cmd;

import mvc.DrawingController;
import shapes.Shape;

public class CommandShapeSelect implements Command{
	
	private DrawingController controller;
	private Shape shape;
	
	public CommandShapeSelect(DrawingController controller, Shape shape) {
        this.controller = controller;
        this.shape = shape;
    }

    @Override
    public void execute() {
    	int index  = controller.getModel().getIndexOfShape(shape);
        if (index > -1) {
            controller.getModel().getById(index).setSelected(true);
        }
        shape.setSelected(true);
        controller.getSelectedShapes().add(shape);   	
    	
    }

    @Override
    public void unexecute() {
    	 int index  = controller.getModel().getIndexOfShape(shape);
         controller.getModel().getById(index).setSelected(false);
     	 shape.setSelected(false);
         controller.getSelectedShapes().remove(shape);
    }

}
