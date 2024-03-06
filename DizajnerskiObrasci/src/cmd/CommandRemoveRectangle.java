package cmd;

import mvc.DrawingModel;
import shapes.Rectangle;

public class CommandRemoveRectangle implements Command {

	private DrawingModel model;
	private Rectangle rectangle;
	private int index;
	
	public CommandRemoveRectangle(DrawingModel model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
		this.index = model.getIndexOfShape(rectangle);
	}

	@Override
	public void execute() {
		model.remove(rectangle);
	}

	@Override
	public void unexecute() {
		model.insertShapeToIndex(index, rectangle);
	}

}
