package cmd;

import mvc.DrawingModel;
import shapes.Shape;

public class CommandBringToFront implements Command{
    private DrawingModel model;

    public CommandBringToFront(DrawingModel model){
        this.model = model;
    }


    @Override
    public void execute() {       
        for(int i = 0;i<=model.getShapes().size()-1;i++){
            if(model.getShapes().get(i).isSelected()) {
                if(i==model.getShapes().size()-1) {
                    return;
                }
                else {
                    Shape s = model.getShapes().get(i+1);
                    model.getShapes().set(i+1, model.getShapes().get(i));
                    model.getShapes().set(i, s);
                }
            }
        }
    }

    @Override
    public void unexecute() {
        for(int i = model.getShapes().size()-1;i>=0;i--){           
            if(model.getShapes().get(i).isSelected()) {                
                if(i==0) {
                    return;
                }
                else {
                    Shape s = model.getShapes().get(i-1);
                    model.getShapes().set(i-1, model.getShapes().get(i));
                    model.getShapes().set(i, s);
                }
            }
        }
    }

}
