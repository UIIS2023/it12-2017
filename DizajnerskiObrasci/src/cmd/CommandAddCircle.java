package cmd;

import mvc.DrawingModel;
import shapes.Circle;

public class CommandAddCircle implements Command{
	private DrawingModel model;
	private Circle circle;
	private int index = -10;
	
	public CommandAddCircle(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}
	@Override
	public void execute() {
		if(index != -10){
			model.insertShapeToIndex(index, circle);			
		}
			model.add(circle);
		
	}
	@Override
	public void unexecute() {
		this.index = model.getIndexOfShape(circle);
		model.remove(circle);		
		
	}
	

}
