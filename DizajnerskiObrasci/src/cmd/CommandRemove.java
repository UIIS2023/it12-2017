package cmd;

import mvc.DrawingModel;
import shapes.Shape;

public class CommandRemove implements Command {

	private DrawingModel model;
	private Shape shape;
	private int index;
	
	public CommandRemove(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		this.index = model.getIndexOfShape(shape);
	}
	@Override
	public void execute() {
		model.remove(shape);
		
	}
	@Override
	public void unexecute() {
		model.insertShapeToIndex(index, shape);	
		
	}
	
	
}
