package cmd;

import shapes.Line;

public class CommandUpdateLine implements Command{
	private Line oldLine;
	private Line newLine;
	private Line original = new Line();

	public CommandUpdateLine(Line oldLine, Line newLine) {
		this.oldLine = oldLine;
		this.newLine = newLine;
	}

	@Override
	public void execute() {
		original = oldLine.clone(original);
		oldLine = newLine.clone(oldLine);
	}

	@Override
	public void unexecute() {
		oldLine = original.clone(oldLine);
	}
}
