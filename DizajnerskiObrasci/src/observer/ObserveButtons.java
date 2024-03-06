package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObserveButtons {
	
	private boolean deleteButtonEnabled;
    private boolean modifyButtonEnabled;
    private boolean bringToFrontButtonEnabled;
    private boolean bringToBackButtonEnabled;
    private boolean toBackButtonEnabled;
    private boolean toFrontButtonEnabled;
    
    private PropertyChangeSupport propertyChangeSupport;

    public ObserveButtons(){
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        propertyChangeSupport.removePropertyChangeListener(pcl);
    }

    public void enableDeleteButton(boolean activated){
        propertyChangeSupport.firePropertyChange("buttonDelete", this.deleteButtonEnabled, activated);
        this.deleteButtonEnabled = activated;
    }

    public void enableModifyButton(boolean activated){
        propertyChangeSupport.firePropertyChange("buttonModify", this.modifyButtonEnabled, activated);
        this.modifyButtonEnabled = activated;
    }
    public void enableBringToFrontButton(boolean activated) {
        propertyChangeSupport.firePropertyChange("bringToFrontButton", this.bringToFrontButtonEnabled, activated);
        this.bringToFrontButtonEnabled = activated;
    }

    public void enableBringToBackButton(boolean activated) {
        propertyChangeSupport.firePropertyChange("bringToBackButton", this.bringToBackButtonEnabled, activated);
        this.bringToBackButtonEnabled = activated;
    }

    public void enableToFrontButton(boolean activated) {
        propertyChangeSupport.firePropertyChange("toFrontButton", this.toFrontButtonEnabled, activated);
        this.toFrontButtonEnabled = activated;
    }

    public void enableToBackButton(boolean activated) {
        propertyChangeSupport.firePropertyChange("toBackButton", this.toBackButtonEnabled, activated);
        this.toBackButtonEnabled = activated;
    }
	
    
    

}
