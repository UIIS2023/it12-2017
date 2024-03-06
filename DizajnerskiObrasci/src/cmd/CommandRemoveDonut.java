package cmd;

import mvc.DrawingModel;
import shapes.Donut;

public class CommandRemoveDonut implements Command {
	private DrawingModel model;
	private Donut donut;
	private int index;
	
	public CommandRemoveDonut(DrawingModel model, Donut donut) {
		this.model = model;
		this.donut = donut;
		this.index = model.getIndexOfShape(donut);
	}

	@Override
	public void execute() {
		model.remove(donut);
	}

	@Override
	public void unexecute() {
		model.insertShapeToIndex(index, donut);
	}
}