package cmd;

import mvc.DrawingModel;
import shapes.Point;

public class CommandAddPoint implements Command{
	public DrawingModel model;
	public Point point;
	private int index = -10;
	
	public CommandAddPoint(DrawingModel model, Point point) {
		this.model = model;
		this.point = point;
	}
	@Override
	public void execute() {
		if(index != -10){
			model.insertShapeToIndex(index, point);			
		}	
		model.add(point);
		
	}
	@Override
	public void unexecute() {
		this.index = model.getIndexOfShape(point);
		model.remove(point);
		
	}
	
	

}
