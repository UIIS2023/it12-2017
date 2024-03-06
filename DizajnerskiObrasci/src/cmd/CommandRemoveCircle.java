package cmd;

import mvc.DrawingModel;
import shapes.Circle;

public class CommandRemoveCircle implements Command {
	private DrawingModel model;
	private Circle circle;
	private int index;
	
	public CommandRemoveCircle(DrawingModel model, Circle circle) {
		this.model = model;
		this.circle = circle;
		this.index = model.getIndexOfShape(circle);

	}

	@Override
	public void execute() {
		model.remove(circle);
	}

	@Override
	public void unexecute() {
		model.insertShapeToIndex(index, circle);
	}
}

