package cmd;

import mvc.DrawingModel;
import shapes.Rectangle;

public class CommandAddRectangle implements Command {
	
	private DrawingModel model;
	private Rectangle rectangle;
	private int index = -10;
	
	public CommandAddRectangle(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		if(index != -10){
			model.insertShapeToIndex(index, rectangle);			
		}	
		model.add(rectangle);
		
	}

	@Override
	public void unexecute() {
		this.index = model.getIndexOfShape(rectangle);
		model.remove(rectangle);
	}

}
