package cmd;

import mvc.DrawingModel;
import shapes.Point;

public class CommandRemovePoint implements Command{

	private DrawingModel model;
	private Point point;
	private int index;
	
	public CommandRemovePoint(DrawingModel model, Point point) {
		this.model = model;
		this.point = point;
		this.index = model.getIndexOfShape(point);
	}

	@Override
	public void execute() {
		model.remove(point);
	}

	@Override
	public void unexecute() {
		model.insertShapeToIndex(index, point);
	}

}
