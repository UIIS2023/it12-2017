package cmd;


import mvc.DrawingModel;
import shapes.HexagonAdapter;

public class CommandRemoveHexagon implements Command {
	
	private DrawingModel model;
	private HexagonAdapter hexagon;
	private int index;
	
	public CommandRemoveHexagon(DrawingModel model, HexagonAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;
		this.index = model.getIndexOfShape(hexagon);
	}

	@Override
	public void execute() {
		model.remove(hexagon);
	}

	@Override
	public void unexecute() {
		model.insertShapeToIndex(index, hexagon);
	}
}
