package cmd;

import mvc.DrawingModel;
import shapes.Shape;

public class CommandToBack implements Command{
    private DrawingModel model;

    public CommandToBack(DrawingModel model){
        this.model = model;
    }

    @Override
    public void execute() {
        for(int i = 0;i<=model.getShapes().size()-1;i++){            
            if(model.getShapes().get(i).isSelected()) {               
                if(i==0) {
                    return;
                }
                else {
                    Shape s = model.getShapes().get(i-1);
                    model.getShapes().set(i-1, model.getShapes().get(i));
                    model.getShapes().set(i, s);
                    return;
                }
            }
        }
    }

    @Override
    public void unexecute() {       
        for(int i = 0;i<=model.getShapes().size()-1;i++){
            if(model.getShapes().get(i).isSelected()) {
                if(i==model.getShapes().size()-1) {
                    return;
                }
                else {
                    Shape s = model.getShapes().get(i+1);
                    model.getShapes().set(i+1, model.getShapes().get(i));
                    model.getShapes().set(i, s);
                    return;
                }
            }
        }
    }
}
