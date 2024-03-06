package cmd;

import mvc.DrawingModel;
import shapes.Shape;

public class CommandAdd implements Command{
	
	private DrawingModel model;
	private Shape shape;
	private int index = -10;
	
	public CommandAdd(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	@Override
	public void execute() {
		if(index != -10){
			model.insertShapeToIndex(index, shape);			
		}
		model.add(shape);
		
	}
	@Override
	public void unexecute() {
		this.index = model.getIndexOfShape(shape);
		model.remove(shape);		
		
	}
	

}
