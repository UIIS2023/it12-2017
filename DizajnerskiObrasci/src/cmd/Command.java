package cmd;

import mvc.DrawingModel;
import shapes.Shape;

public interface Command {
	  void execute();
	  void unexecute();
	
}
