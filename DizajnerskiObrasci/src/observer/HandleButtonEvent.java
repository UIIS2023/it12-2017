package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class HandleButtonEvent implements PropertyChangeListener{

	private DrawingFrame frame;
	
	public HandleButtonEvent(DrawingFrame frame) {
		this.frame = frame;
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		 if(evt.getPropertyName().equals("buttonDelete")){
	            frame.getButtonDelete().setEnabled((boolean)evt.getNewValue());
	        }
	        if(evt.getPropertyName().equals("buttonModify")){
	            frame.getButtonModify().setEnabled((boolean)evt.getNewValue());
	        }
	        if(evt.getPropertyName().equals("bringToFrontButton")){
	            frame.getButtonBringToFront().setEnabled((boolean) evt.getNewValue());
	        }
	        if(evt.getPropertyName().equals("bringToBackButton")){
	            frame.getButtonBringToBack().setEnabled((boolean) evt.getNewValue());
	        }
	        if(evt.getPropertyName().equals("toFrontButton")){
	            frame.getButtonToFront().setEnabled((boolean) evt.getNewValue());
	        }
	        if(evt.getPropertyName().equals("toBackButton")){
	            frame.getButtonToBack().setEnabled((boolean) evt.getNewValue());
	        }
	       
		
	}
}
