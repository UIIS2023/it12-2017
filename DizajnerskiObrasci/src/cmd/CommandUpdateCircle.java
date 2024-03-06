package cmd;

import shapes.Circle;

public class CommandUpdateCircle implements Command {
	private Circle oldCircle;
	private Circle newCircle;
	private Circle original = new Circle();

	public CommandUpdateCircle(Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
		original = oldCircle.clone(original);
		oldCircle = newCircle.clone(oldCircle);
	}

	@Override
	public void unexecute() {
		oldCircle = original.clone(oldCircle);
	}
}
