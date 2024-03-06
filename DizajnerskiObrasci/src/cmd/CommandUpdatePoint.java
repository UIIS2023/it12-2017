package cmd;

import shapes.Point;

public class CommandUpdatePoint implements Command{

	private Point newPoint;
	private Point oldPoint;
	private Point original = new Point();
	
	public CommandUpdatePoint(Point oldPoint, Point newPoint) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
	}
	

	@Override
	public void execute() {
		original = oldPoint.clone(original);
		oldPoint = newPoint.clone(oldPoint);
	}

	@Override
	public void unexecute() {
		oldPoint = original.clone(oldPoint);
	}

}
