package cmd;

import mvc.DrawingModel;
import shapes.HexagonAdapter;

public class CommandAddHexagon implements Command {
	
	private DrawingModel model;
	private HexagonAdapter hexagon;
	private int index = -10;
	
	public CommandAddHexagon(DrawingModel model, HexagonAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;
	}

	@Override
	public void execute() {
		if(index != -10){
			model.insertShapeToIndex(index, hexagon);			
		}	
		model.add(hexagon);
	}

	@Override
	public void unexecute() {
		this.index = model.getIndexOfShape(hexagon);
		model.remove(hexagon);
	}
}
