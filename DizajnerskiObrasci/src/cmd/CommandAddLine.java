package cmd;

import mvc.DrawingModel;
import shapes.Line;

public class CommandAddLine implements Command {
	
	public DrawingModel model;
	public Line line;
	private int index = -10;

	public CommandAddLine(DrawingModel model, Line line) {
		this.model = model;
		this.line = line;
	}
	@Override
	public void execute() {
		if(index != -10){
			model.insertShapeToIndex(index, line);			
		}
		model.add(line);
		
	}

	@Override
	public void unexecute() {
		this.index = model.getIndexOfShape(line);
		model.remove(line);
		
	}
	

}
