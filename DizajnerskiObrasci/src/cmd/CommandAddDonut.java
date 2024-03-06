package cmd;

import mvc.DrawingModel;
import shapes.Donut;

public class CommandAddDonut implements Command {
	public DrawingModel model;
	public Donut donut;
	private int index = -10;
	
	
	public CommandAddDonut(DrawingModel model, Donut donut) {
		this.model = model;
		this.donut = donut;
		
	}
	@Override
	public void execute() {
		if(index != -10){
			model.insertShapeToIndex(index, donut);			
		}		
		model.add(donut);
		
	}
	@Override
	public void unexecute() {
		this.index = model.getIndexOfShape(donut);
		model.remove(donut);
		
	}
}
