package cmd;

import shapes.Rectangle;

public class CommandUpdateRectangle implements Command{

	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle original = new Rectangle();

	public CommandUpdateRectangle(Rectangle oldRectangle, Rectangle newRectangle) {
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		original = oldRectangle.clone(original);
		oldRectangle = newRectangle.clone(oldRectangle);
	}

	@Override
	public void unexecute() {
		oldRectangle = original.clone(oldRectangle);
	}

}
