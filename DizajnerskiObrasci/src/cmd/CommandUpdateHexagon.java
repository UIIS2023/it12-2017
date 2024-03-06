package cmd;


import shapes.HexagonAdapter;
import shapes.Point;

public class CommandUpdateHexagon implements Command {
	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter original = new HexagonAdapter();

	public CommandUpdateHexagon(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}

	@Override
	public void execute() {

		HexagonAdapter h = new HexagonAdapter(
				new Point(
						oldHexagon.getHexagon().getX(),
						oldHexagon.getHexagon().getY()),
				oldHexagon.getHexagon().getR(),
				oldHexagon.getFillColor(),
				oldHexagon.getEdgeColor());;
		original.setHexagon(h.getHexagon());
		
		HexagonAdapter h1 = new HexagonAdapter(
				new Point(
						newHexagon.getHexagon().getX(),
						newHexagon.getHexagon().getY()),
				newHexagon.getHexagon().getR(),
				newHexagon.getFillColor(),
				newHexagon.getEdgeColor());;
		oldHexagon.setHexagon(h1.getHexagon());
	}

	@Override
	public void unexecute() {
		HexagonAdapter h2 = new HexagonAdapter(
				new Point(
					original.getHexagon().getX(),
					original.getHexagon().getY()),
				original.getHexagon().getR(),
				original.getFillColor(),
				original.getEdgeColor());;
		oldHexagon.setHexagon(h2.getHexagon());

	}
}
