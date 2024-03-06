package cmd;

import mvc.DrawingModel;
import shapes.Line;

public class CommandRemoveLine implements Command{
	private DrawingModel model;
	private Line line;
	private int index;
	
	public CommandRemoveLine(DrawingModel model, Line line) {
		this.model = model;
		this.line = line;
		this.index = model.getIndexOfShape(line);
	}

	@Override
	public void execute() {
		model.remove(line);
	}

	@Override
	public void unexecute() {
		model.insertShapeToIndex(index, line);
	}
}
