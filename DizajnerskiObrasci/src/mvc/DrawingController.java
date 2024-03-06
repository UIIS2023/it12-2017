package mvc;

import dialogs.*;
import hexagon.Hexagon;
import observer.HandleButtonEvent;
import observer.ObserveButtons;
import shapes.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cmd.*;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import strategy.SaveLog;
import strategy.SaveManager;

public class DrawingController {
	DrawingFrame frame;
    DrawingModel model;
    
    private Point startPoint;
    private ArrayList<Shape> selectedShapes;
    
    private PointDialog pointDialog;
    private LineDialog lineDialog;
    private RectangleDialog rectangleDialog;
    private CircleDialog circleDialog;
    private DonutDialog donutDialog;
    private HexagonDialog hexagonDialog;
    private HandleButtonEvent buttonEvent;
    private ObserveButtons observeButtons;
    private CommandStack cmdStack;
    
    private String line;
    private int logCount = 0;
    private boolean loggingFinish = false;
    private ArrayList<String> logList = new ArrayList<String>();
    
    public DrawingController(DrawingModel model, DrawingFrame frame){
        this.model = model;
        this.frame = frame;
        selectedShapes = new ArrayList<>();
        
        observeButtons = new ObserveButtons();
        buttonEvent = new HandleButtonEvent(this.frame);
        observeButtons.addPropertyChangeListener(buttonEvent);


        pointDialog = new PointDialog();
        lineDialog = new LineDialog();
        rectangleDialog = new RectangleDialog();
        circleDialog = new CircleDialog();
        donutDialog = new DonutDialog();
        hexagonDialog = new HexagonDialog();
        
        cmdStack = new CommandStack();

    }
    public void mouseClicked(MouseEvent e){
        if(frame.getToggleButtonPoint().isSelected()) {
        	pointDialog.getTextX().setText(String.valueOf(e.getX()));
            pointDialog.getTextY().setText(String.valueOf(e.getY()));
            
            pointDialog.getEdgeColorButton().setBackground(frame.getButtonEdgeColor().getBackground());
            pointDialog.setEdgeColor(frame.getButtonEdgeColor().getBackground());
        
            pointDialog.setVisible(true);
            pointDialog.pack();

            if(pointDialog.getWindowOk()){
            	Point p = new Point(Integer.parseInt(pointDialog.getTextX().getText()),
                        Integer.parseInt(pointDialog.getTextY().getText()),
                        pointDialog.getEdgeColor());               
            
            CommandAddPoint cmd = new CommandAddPoint(model, p);
            cmd.execute();
            frame.getLogArea().append("Added " + p + "\n");
            commandStackUpdate(cmd,p, "Removed", "Added");            
            repaint();
            frame.getButtonUndo().setEnabled(true);
            frame.getButtonRedo().setEnabled(false);
            }
        }
        else if(frame.getToggleButtonLine().isSelected()){
            if(startPoint == null){
                startPoint = new Point(e.getX(), e.getY());
            }
            else{
                lineDialog.getTextX().
                        setText(String.valueOf(startPoint.getX()));
                lineDialog.getTextY().
                        setText(String.valueOf(startPoint.getY()));
                lineDialog.getTextLineX().
                        setText(String.valueOf(e.getX()));
                lineDialog.getTextLineY().
                        setText(String.valueOf(e.getY()));
                
                lineDialog.getEdgeColorButton().setBackground(frame.getButtonEdgeColor().getBackground());
                lineDialog.setEdgeColor(frame.getButtonEdgeColor().getBackground());

                lineDialog.setVisible(true);
                lineDialog.pack();

                if(lineDialog.getWindowOk()){
                    Line line = new Line(startPoint,
                            new Point(Integer.parseInt(lineDialog.getTextLineX().getText()),
                                    Integer.parseInt(lineDialog.getTextLineY().getText())), lineDialog.getEdgeColor());

                    
                 CommandAddLine cmd = new CommandAddLine(model, line);
                 cmd.execute();
                 frame.getLogArea().append("Added " + line + "\n");
                 commandStackUpdate(cmd, line, "Removed", "Added");
                 repaint();
                 frame.getButtonUndo().setEnabled(true);
                 frame.getButtonRedo().setEnabled(false);
                }
                startPoint = null;
            }
        }
        
        else if(frame.getToggleButtonRectangle().isSelected()){
            rectangleDialog.getTextX().
                    setText(String.valueOf(e.getX()));
            rectangleDialog.getTextY().
                    setText(String.valueOf(e.getY()));
            
            rectangleDialog.getEdgeColorButton().setBackground(frame.getButtonEdgeColor().getBackground());
            rectangleDialog.setEdgeColor(frame.getButtonEdgeColor().getBackground());
            
            rectangleDialog.getFillColorButton().setBackground(frame.getButtonFillColor().getBackground());
            rectangleDialog.setFillColor(frame.getButtonFillColor().getBackground());
            
            
            

            rectangleDialog.setVisible(true);
            rectangleDialog.pack();

            if(rectangleDialog.getWindowOk()){
               Rectangle rectangle = new Rectangle(new Point(Integer.parseInt(rectangleDialog.getTextX().getText()),
                        Integer.parseInt(rectangleDialog.getTextY().getText())),
                        Integer.parseInt(rectangleDialog.getTextHeight().getText()),
                        Integer.parseInt(rectangleDialog.getTextWidth().getText()),
                        rectangleDialog.getFillColor(), rectangleDialog.getEdgeColor());
               
               CommandAddRectangle cmd = new CommandAddRectangle(model, rectangle);
               cmd.execute();
               frame.getLogArea().append("Added " + rectangle + "\n");
               commandStackUpdate(cmd,rectangle, "Removed", "Added");
               repaint();
               frame.getButtonUndo().setEnabled(true);
               frame.getButtonRedo().setEnabled(false);
            }
        }
     
        else if(frame.getToggleButtonCircle().isSelected()){
            circleDialog.getTextX().
                    setText(String.valueOf(e.getX()));
            circleDialog.getTextY().
                    setText(String.valueOf(e.getY()));
            
            circleDialog.getEdgeColorButton().setBackground(frame.getButtonEdgeColor().getBackground());
            circleDialog.setEdgeColor(frame.getButtonEdgeColor().getBackground());
            
            circleDialog.getFillColorButton().setBackground(frame.getButtonFillColor().getBackground());
            circleDialog.setFillColor(frame.getButtonFillColor().getBackground());

            circleDialog.setVisible(true);
            circleDialog.pack();

            if(circleDialog.getWindowOk()){
                Circle circle = new Circle(new Point(Integer.parseInt(circleDialog.getTextX().getText()),
                        Integer.parseInt(circleDialog.getTextY().getText())),
                        Integer.parseInt(circleDialog.getTextRadius().getText()),                      
                        circleDialog.getFillColor(), circleDialog.getEdgeColor());
                
                CommandAddCircle cmd = new CommandAddCircle(model, circle);
                cmd.execute();
                frame.getLogArea().append("Added " + circle + "\n");
                commandStackUpdate(cmd,circle, "Removed", "Added"); 
                repaint();
                frame.getButtonUndo().setEnabled(true);
                frame.getButtonRedo().setEnabled(false);
            }
        }
        
        else if(frame.getToggleButtonDonut().isSelected()) {
        	donutDialog.getTextX().setText(String.valueOf(e.getX()));
        	donutDialog.getTextY().setText(String.valueOf(e.getY()));
        	
        	donutDialog.getEdgeColorButton().setBackground(frame.getButtonEdgeColor().getBackground());
        	donutDialog.setEdgeColor(frame.getButtonEdgeColor().getBackground());
             
        	donutDialog.getFillColorButton().setBackground(frame.getButtonFillColor().getBackground());
        	donutDialog.setFillColor(frame.getButtonFillColor().getBackground());
        	
        	donutDialog.setVisible(true);
        	donutDialog.pack();
        	
        	if(donutDialog.getWindowOk()) {
        	Donut donut = new Donut(new Point(Integer.parseInt(donutDialog.getTextX().getText()),
        				Integer.parseInt(donutDialog.getTextY().getText())),
        				Integer.parseInt(donutDialog.getTextRadius().getText()),
        				Integer.parseInt(donutDialog.getTextInnerRadius().getText()),
        				donutDialog.getFillColor(), donutDialog.getEdgeColor()
        				);
        	CommandAddDonut cmd = new CommandAddDonut(model, donut);
            cmd.execute();
            frame.getLogArea().append("Added " + donut + "\n");
            commandStackUpdate(cmd, donut, "Removed", "Added"); 
        	repaint();
        	frame.getButtonUndo().setEnabled(true);
            frame.getButtonRedo().setEnabled(false);
        	}
        }
        
        else if(frame.getToggleButtonHexagon().isSelected()) {
        	hexagonDialog.getTextX().setText(String.valueOf(e.getX()));
        	hexagonDialog.getTextY().setText(String.valueOf(e.getY()));
        	
       
             
        	hexagonDialog.getFillColorButton().setBackground(frame.getButtonFillColor().getBackground());
        	hexagonDialog.setFillColor(frame.getButtonFillColor().getBackground());
        	
         	hexagonDialog.getEdgeColorButton().setBackground(frame.getButtonEdgeColor().getBackground());
        	hexagonDialog.setEdgeColor(frame.getButtonEdgeColor().getBackground());
        	
        	
        	hexagonDialog.setVisible(true);
        	hexagonDialog.pack();
        	
        	if(hexagonDialog.getWindowOk()) {
        	HexagonAdapter hexagon = new HexagonAdapter(new Point(Integer.parseInt(hexagonDialog.getTextX().getText()),
        				Integer.parseInt(hexagonDialog.getTextY().getText())),
        				Integer.parseInt(hexagonDialog.getTextRadius().getText()), 
        				hexagonDialog.getFillColor(),
        				hexagonDialog.getEdgeColor()
        				);
        	CommandAddHexagon cmd = new CommandAddHexagon(model, hexagon);
            cmd.execute();
            frame.getLogArea().append("Added " + hexagon + "\n");
            commandStackUpdate(cmd, hexagon, "Removed", "Added"); 
            repaint();
        	frame.getButtonUndo().setEnabled(true);
            frame.getButtonRedo().setEnabled(false);
        	}
        }

        
       //Multi-selekt
        else if(frame.getToggleButtonSelect().isSelected()){
        	int highest_shape = -1;
            for(Shape shape: model.getShapes())
            {
            	  if(shape.contains(e.getX(), e.getY()))
            	  {
                      int index =  model.getIndexOfShape(shape);
                      if (highest_shape < index)
                      {
                          highest_shape = index;
                      }
                  }
            }
            if (highest_shape != -1) 
            {
            	Shape shape = model.getById(highest_shape);
            	
            	 if(shape.isSelected()){
                 	
                     //Ako je selektovan radim deselect
                     CommandShapeDeselect cmd = new CommandShapeDeselect(this, shape);
                     cmd.execute();
                     //Upis sa novim redom posle njega
                     frame.getLogArea().append("Deselected " + shape + "\n");
                     commandStackUpdate(cmd, shape, "Selected", "Deselected");
                     frame.getButtonUndo().setEnabled(true);
                     frame.getButtonRedo().setEnabled(false);

                 }
                 else{
                     CommandShapeSelect cmd = new CommandShapeSelect(this, shape);
                     cmd.execute();
                     frame.getLogArea().append("Selected " + shape + "\n");
                     commandStackUpdate(cmd, shape, "Deselected", "Selected");
                     frame.getButtonUndo().setEnabled(true);
                     frame.getButtonRedo().setEnabled(false);
                 }            
                
                repaint();
            }
           
        }

        enableButtons();
    }
    
   
    //CMD Brisanje
    public void mouseClickedDelete(ActionEvent e){
        int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to proceed?");
        for(int i = selectedShapes.size()-1; i >= 0; i--) {
        	if(confirmation == 0){
        		
        		if (selectedShapes.get(i) instanceof Point) {
                    CommandRemovePoint cmd = new CommandRemovePoint(model, (Point) selectedShapes.get(i));
                    cmd.execute();
                   
                    frame.getLogArea().append("Removed " + selectedShapes.get(i) + "\n");
                    commandStackUpdate(cmd, (Point) selectedShapes.get(i), "Added", "Removed");
                    selectedShapes.remove(i);
                    repaint();

                }
                else if (selectedShapes.get(i)  instanceof Line) {
                	CommandRemoveLine cmd = new CommandRemoveLine(model, (Line) selectedShapes.get(i));
                	cmd.execute();
                	frame.getLogArea().append("Removed " + selectedShapes.get(i) + "\n");
                	commandStackUpdate(cmd, (Line) selectedShapes.get(i), "Added", "Removed");
                	selectedShapes.remove(i);
                	repaint();

                }
                else if (selectedShapes.get(i)  instanceof Rectangle) {
                	CommandRemoveRectangle cmd = new CommandRemoveRectangle(model, (Rectangle) selectedShapes.get(i));
                	cmd.execute();
                	frame.getLogArea().append("Removed " + selectedShapes.get(i) + "\n");
                	commandStackUpdate(cmd, (Rectangle) selectedShapes.get(i), "Added", "Removed");
                	selectedShapes.remove(i);
                	repaint();

                }
                else if (selectedShapes.get(i)  instanceof Circle) {
                	CommandRemoveCircle cmd = new CommandRemoveCircle(model, (Circle) selectedShapes.get(i));
                	cmd.execute();
                	frame.getLogArea().append("Removed " + selectedShapes.get(i) + "\n");
                	commandStackUpdate(cmd, (Circle) selectedShapes.get(i), "Added", "Removed");
                	selectedShapes.remove(i);
                	repaint();

                }
                else if (selectedShapes.get(i)  instanceof Donut) {
                	CommandRemoveDonut cmd = new CommandRemoveDonut(model, (Donut) selectedShapes.get(i));
                	cmd.execute();
                	frame.getLogArea().append("Removed " + selectedShapes.get(i) + "\n");
                	commandStackUpdate(cmd, (Donut) selectedShapes.get(i), "Added", "Removed");
                	selectedShapes.remove(i);
                	repaint();

                }
                else if (selectedShapes.get(i)  instanceof HexagonAdapter) {
                	CommandRemoveHexagon cmd = new CommandRemoveHexagon(model, (HexagonAdapter) selectedShapes.get(i));
                	cmd.execute();
                	frame.getLogArea().append("Removed " + selectedShapes.get(i) + "\n");
                	commandStackUpdate(cmd, (HexagonAdapter) selectedShapes.get(i), "Added", "Removed");
                	selectedShapes.remove(i);
                	repaint();

                }
        		
        	}
        	 enableButtons();
        }
        frame.getButtonUndo().setEnabled(true);
        frame.getButtonRedo().setEnabled(false);
    }
    
    public void mouseClickedModify(ActionEvent e){
        if(selectedShapes.get(0) instanceof Point){
            //Koristim ga da sacuvamo stare postavke
            Point tempPoint = (Point) selectedShapes.get(0);
            pointDialog.getTextX().setText(Integer.toString(tempPoint.getX()));
            pointDialog.getTextY().setText(Integer.toString(tempPoint.getY()));
            pointDialog.getEdgeColorButton().setBackground(tempPoint.getEdgeColor());
            
            pointDialog.setVisible(true);
            pointDialog.pack();
            
            
            if(pointDialog.getWindowOk()){
             
               Point p = new Point(
                        Integer.parseInt(pointDialog.getTextX().getText()),
                        Integer.parseInt(pointDialog.getTextY().getText()),
                        pointDialog.getEdgeColor()
                );
               
              
               CommandUpdatePoint cmd = new CommandUpdatePoint(tempPoint, p);              
             
               cmd.execute();  
               frame.getLogArea().append("Edited " + p + "\n");
               commandStackUpdate(cmd, p, "Edit", "Edit");               
               repaint();
            }
        }
        else if(selectedShapes.get(0) instanceof Line){
            Line tempLine = (Line) selectedShapes.get(0);
            lineDialog.getTextX().setText(Integer.toString(tempLine.getStart().getX()));
            lineDialog.getTextY().setText(Integer.toString(tempLine.getStart().getY()));
            lineDialog.getTextLineX().setText(Integer.toString(tempLine.getEnd().getX()));
            lineDialog.getTextLineY().setText(Integer.toString(tempLine.getEnd().getY()));
            lineDialog.getEdgeColorButton().setBackground(tempLine.getEdgeColor());
            lineDialog.setVisible(true);
            lineDialog.pack();
            if(lineDialog.getWindowOk()){
                
             Line line = new Line(
                        new Point(
                                Integer.parseInt(lineDialog.getTextX().getText()),
                                Integer.parseInt(lineDialog.getTextY().getText())
                        ),
                        new Point(
                                Integer.parseInt(lineDialog.getTextLineX().getText()),
                                Integer.parseInt(lineDialog.getTextLineY().getText())
                        ),
                        lineDialog.getEdgeColor()
                );
             CommandUpdateLine cmd = new CommandUpdateLine(tempLine, line);
             cmd.execute();
             frame.getLogArea().append("Edited " + line + "\n");
             commandStackUpdate(cmd, line, "Edit", "Edit");             
              repaint();
            }
        }
        else if(selectedShapes.get(0) instanceof Rectangle){
        	Rectangle tempRect = (Rectangle) selectedShapes.get(0);
        	rectangleDialog.getTextX().setText(Integer.toString(tempRect.getUpperLeftPoint().getX()));
        	rectangleDialog.getTextY().setText(Integer.toString(tempRect.getUpperLeftPoint().getY()));
        	rectangleDialog.getTextHeight().setText(Integer.toString(tempRect.getHeight()));
        	rectangleDialog.getTextWidth().setText(Integer.toString(tempRect.getWidth()));
        	rectangleDialog.getEdgeColorButton().setBackground(tempRect.getEdgeColor());
        	rectangleDialog.getFillColorButton().setBackground(tempRect.getFillColor()); 
            
        	rectangleDialog.setVisible(true);
        	rectangleDialog.pack();
            
            if(rectangleDialog.getWindowOk()){
             

               Rectangle rect = new Rectangle(new Point(Integer.parseInt(rectangleDialog.getTextX().getText()),
                        Integer.parseInt(rectangleDialog.getTextY().getText())),
                        Integer.parseInt(rectangleDialog.getTextHeight().getText()),
                        Integer.parseInt(rectangleDialog.getTextWidth().getText()),
                        rectangleDialog.getFillColor(), rectangleDialog.getEdgeColor());
               
               CommandUpdateRectangle cmd = new CommandUpdateRectangle(tempRect, rect);
               cmd.execute();
               frame.getLogArea().append("Edited " + rect + "\n");
               commandStackUpdate(cmd, rect, "Edit", "Edit");
                repaint();
            }
        }
        else if(selectedShapes.get(0).getClass().getName().equals("shapes.Circle")){
        	Circle tempCircle = (Circle) selectedShapes.get(0);
        	
        	circleDialog.getTextX().setText(Integer.toString(tempCircle.getCenter().getX()));
        	circleDialog.getTextY().setText(Integer.toString(tempCircle.getCenter().getY()));
        	circleDialog.getTextRadius().setText(Integer.toString(tempCircle.getRadius()));
        	circleDialog.getEdgeColorButton().setBackground(tempCircle.getEdgeColor());
        	circleDialog.getFillColorButton().setBackground(tempCircle.getFillColor()); 
            
        	circleDialog.setVisible(true);
        	circleDialog.pack();
            
            if(circleDialog.getWindowOk()){
              

                Circle circle = new Circle(new Point(Integer.parseInt(circleDialog.getTextX().getText()),
                        Integer.parseInt(circleDialog.getTextY().getText())),
                        Integer.parseInt(circleDialog.getTextRadius().getText()),                      
                        circleDialog.getFillColor(), circleDialog.getEdgeColor());

                CommandUpdateCircle cmd = new CommandUpdateCircle(tempCircle, circle);
                cmd.execute();
                frame.getLogArea().append("Edited " + circle + "\n");
                commandStackUpdate(cmd, circle, "Edit", "Edit");
                repaint();
            }
        }
        else if(selectedShapes.get(0).getClass().getName().equals("shapes.Donut")){
        	Donut tempDonut = (Donut) selectedShapes.get(0);
        	
        	donutDialog.getTextX().setText(Integer.toString(tempDonut.getCenter().getX()));
        	donutDialog.getTextY().setText(Integer.toString(tempDonut.getCenter().getY()));
        	donutDialog.getTextRadius().setText(Integer.toString(tempDonut.getRadius()));
        	donutDialog.getTextInnerRadius().setText(Integer.toString(tempDonut.getInnerRadius()));
        	donutDialog.getEdgeColorButton().setBackground(tempDonut.getEdgeColor());
        	donutDialog.getFillColorButton().setBackground(tempDonut.getFillColor()); 
            
        	donutDialog.setVisible(true);
        	donutDialog.pack();
            
            if(donutDialog.getWindowOk()){
              
             Donut donut = new Donut(new Point(Integer.parseInt(donutDialog.getTextX().getText()),
        				Integer.parseInt(donutDialog.getTextY().getText())),
        				Integer.parseInt(donutDialog.getTextRadius().getText()),
        				Integer.parseInt(donutDialog.getTextInnerRadius().getText()),
        				donutDialog.getFillColor(), donutDialog.getEdgeColor()
        				);
             
             CommandUpdateDonut cmd = new CommandUpdateDonut(tempDonut, donut);
             cmd.execute();
             frame.getLogArea().append("Edited " + donut + "\n");
             commandStackUpdate(cmd, donut, "Edit", "Edit");
             repaint();
        	
        	}
        }
        else if(selectedShapes.get(0) instanceof HexagonAdapter){
        	HexagonAdapter tempHex = (HexagonAdapter) selectedShapes.get(0);
        	
        	hexagonDialog.getTextX().setText(Integer.toString(tempHex.getCenter().getX()));
        	hexagonDialog.getTextY().setText(Integer.toString(tempHex.getCenter().getY()));
        	hexagonDialog.getTextRadius().setText(Integer.toString(tempHex.getRadius()));
        	
        	hexagonDialog.getEdgeColorButton().setBackground(tempHex.getEdgeColor());
        	hexagonDialog.getFillColorButton().setBackground(tempHex.getFillColor()); 
            
        	hexagonDialog.setVisible(true);
        	hexagonDialog.pack();
            
            if(hexagonDialog.getWindowOk()){
              

            	HexagonAdapter hexagon = new HexagonAdapter(new Point(Integer.parseInt(hexagonDialog.getTextX().getText()),
        				Integer.parseInt(hexagonDialog.getTextY().getText())),
        				Integer.parseInt(hexagonDialog.getTextRadius().getText()),        				
        				hexagonDialog.getFillColor(),hexagonDialog.getEdgeColor()
        				);
            	
            	CommandUpdateHexagon cmd = new CommandUpdateHexagon(tempHex, hexagon);
                cmd.execute();
                frame.getLogArea().append("Edited " + hexagon + "\n");
                commandStackUpdate(cmd, hexagon, "Edit", "Edit");
                repaint();       		
        		
        	}
        }
        frame.getButtonRedo().setEnabled(false);
        frame.getButtonUndo().setEnabled(true);

    }
        

	public void repaint(){
        frame.getView().revalidate();
        frame.getView().repaint();
    }
	
	 public void undo(){
	        
	        frame.getLogArea().append("Undone " +
	                cmdStack.getCommandsUndo().get(cmdStack.getCount()) + " " +
	                cmdStack.getShapes().get(cmdStack.getCount()) + "\n");

	       
	        if(cmdStack.getCommandsUndo().get(cmdStack.getCount()).equals("Added")){
	            CommandShapeSelect cmd = new CommandShapeSelect(this, cmdStack.getShapes().get(cmdStack.getCount()));
	            cmd.execute();
	        }

	        cmdStack.undo();
	        enableButtons();
	        repaint();
	    }
	 
	 public void redo(){	        
	        cmdStack.redo();
	        
	        frame.getLogArea().append("Redone " +
	                cmdStack.getCommandsRedo().get(cmdStack.getCount()) + " " +
	                cmdStack.getShapes().get(cmdStack.getCount()) + "\n");
	       
	        if(cmdStack.getCommandsRedo().get(cmdStack.getCount()).equals("Removed")){
	            CommandShapeDeselect cmd = new CommandShapeDeselect(this, cmdStack.getShapes().get(cmdStack.getCount()));
	            cmd.execute();
	        }

	        enableButtons();
	        repaint();
	    }
	 
	 public void bringToBack(){
	        frame.getButtonRedo().setEnabled(false);
	        CommandBringToBack cmd = new CommandBringToBack(model);
	        cmd.execute();
	        frame.getLogArea().append("Brought to back " + selectedShapes.get(0) + "\n");
	        commandStackUpdate(cmd, selectedShapes.get(0), "Brought to front", "Brought to back");
	        repaint();
	    }

	    public void bringToFront(){
	        frame.getButtonRedo().setEnabled(false);
	        CommandBringToFront cmd = new CommandBringToFront(model);
	        cmd.execute();
	        frame.getLogArea().append("Brought to front " + selectedShapes.get(0) + "\n");
	        commandStackUpdate(cmd, selectedShapes.get(0), "Brought to back", "Brought to front");
	        repaint();
	    }

	    public void toFront(){
	        frame.getButtonRedo().setEnabled(false);
	        CommandToFront cmd = new CommandToFront(model);
	        cmd.execute();
	        frame.getLogArea().append("Forward " + selectedShapes.get(0) + "\n");
	        commandStackUpdate(cmd, selectedShapes.get(0), "Backward", "Forward");
	        repaint();
	    }

	    public void toBack(){
	        frame.getButtonRedo().setEnabled(false);
	        CommandToBack cmd = new CommandToBack(model);
	        cmd.execute();
	        frame.getLogArea().append("Backward " + selectedShapes.get(0) + "\n");
	        commandStackUpdate(cmd, selectedShapes.get(0), "Forward", "Backward");
	        repaint();
	    }

	 
	
    
    public void enableButtons(){
        if(selectedShapes.size() != 0){
          
            if(selectedShapes.size() == 1){
                observeButtons.enableModifyButton(true);
                zAxisUpdate();
            }
            else {
                observeButtons.enableModifyButton(false);
                observeButtons.enableBringToFrontButton(false);
                observeButtons.enableBringToBackButton(false);
                observeButtons.enableToBackButton(false);
                observeButtons.enableToFrontButton(false);
            }
            
            observeButtons.enableDeleteButton(true);
        }
        else {
        	observeButtons.enableModifyButton(false);
            observeButtons.enableDeleteButton(false);
            observeButtons.enableBringToFrontButton(false);
            observeButtons.enableBringToBackButton(false);
            observeButtons.enableToBackButton(false);
            observeButtons.enableToFrontButton(false);
        }
    }
    
    private void zAxisUpdate(){
        for(Shape shape: model.getShapes()){           
            if(shape.isSelected()){
                
                if(model.getShapes().size() != 1){
                  
                    if(shape.equals(model.getById(model.getShapes().size()-1))){
                        observeButtons.enableToFrontButton(false);
                        observeButtons.enableToBackButton(true);
                        observeButtons.enableBringToFrontButton(false);
                        observeButtons.enableBringToBackButton(true);
                    }                   
                    else if(shape.equals(model.getById(0))){
                        observeButtons.enableToFrontButton(true);
                        observeButtons.enableToBackButton(false);
                        observeButtons.enableBringToFrontButton(true);
                        observeButtons.enableBringToBackButton(false);
                    }                   
                    else{
                        observeButtons.enableToFrontButton(true);
                        observeButtons.enableToBackButton(true);
                        observeButtons.enableBringToFrontButton(true);
                        observeButtons.enableBringToBackButton(true);
                    }
                }
            }
        }
    }
    
    private void commandStackUpdate(Command cmd, Shape shape, String undo, String redo) {
        cmdStack.deleteElementsAfterCount(cmdStack.getCount());
        cmdStack.getCommands().add(cmd);
        cmdStack.getShapes().add(shape);
        cmdStack.getCommandsUndo().add(undo);
        cmdStack.getCommandsRedo().add(redo);
        cmdStack.setCount(cmdStack.getCount() + 1);
    }
    
    public void saveLog(){
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter(".txt","txt"));

        if(chooser.showSaveDialog(frame.getParent()) == JFileChooser.APPROVE_OPTION){
            String path = chooser.getSelectedFile().getAbsolutePath();
            SaveManager manager = new SaveManager(new SaveLog());
            manager.save(frame.getLogArea(), new File(path + ".txt"));
        }
        repaint();
    }
    
    public void openLog(){
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setFileFilter(new FileNameExtensionFilter(".txt","txt"));

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                loadLog(chooser.getSelectedFile());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void loadLog(File file){
        try {
            loggingFinish = false;
            cmdStack.setCount(-1);
            cmdStack.getCommands().clear();
            cmdStack.getShapes().clear();
            cmdStack.getCommandsRedo().clear();
            cmdStack.getCommandsUndo().clear();
            model.getShapes().clear();
            selectedShapes.clear();
            logList.clear();
            logCount = 0;
            frame.getButtonUndo().setEnabled(false);
            frame.getButtonRedo().setEnabled(false);
            frame.getLogArea().setText("");
            enableButtons();

            //Dodamo svaku liniju iz fajla u logList listu stringova
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null){
                logList.add(line);
            }
            reader.close();
            frame.getButtonUndo().setEnabled(true);
            frame.getButtonLoadNext().setEnabled(true);
            loadNext();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
        
    /*public void loadNext(){
        Shape shape = null;

        if(logCount < logList.size()) {
            //Hvatamo liniju po liniju
            line=logList.get(logCount);

            if(line.contains("Point")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2,line.indexOf(")")));
                Color color = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                shape = new Point(x,y,color);

                //Ako smo na kraju zakljucavamo load next dugme
                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Line")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2,line.indexOf(")")));
                int x1 = Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ",", 1)));
                int y1 = Integer.parseInt(line.substring(ordinalIndexOf(line, ",", 1)+2, ordinalIndexOf(line, ")", 1)));
                Color color = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                shape = new Line(new Point(x,y),new Point(x1,y1),color);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Rectangle")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2, line.indexOf(")")));
                int h = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",0)+1, ordinalIndexOf(line,",",2)));
                int w = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",1)+1, ordinalIndexOf(line, ",", 3)));
                Color edgeColor = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                Color fillColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ")", 1))));
                shape = new Rectangle(new Point(x,y), h, w, fillColor, edgeColor);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Circle")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2, line.indexOf(")")));
                int r = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",0)+1, ordinalIndexOf(line,",",2)));
                Color edgeColor = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                Color fillColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ")", 1))));
                shape = new Circle(new Point(x,y), r, fillColor, edgeColor);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Donut")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2, line.indexOf(")")));
                int outerR = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",0)+1, ordinalIndexOf(line,",",2)));
                int innerR = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",1)+1, line.lastIndexOf(".")));
                Color edgeColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 2)+1, ordinalIndexOf(line, ")", 2))));
                Color fillColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ")", 1))));
                shape = new Donut(new Point(x,y), outerR, innerR, fillColor, edgeColor);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Hexagon")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2, line.indexOf(")")));
                int r = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",0)+1, ordinalIndexOf(line,",",2)));
                Color edgeColor = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                Color fillColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ")", 1))));
                shape = new HexagonAdapter(new Point(x,y), r, fillColor, edgeColor);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            }

           /* if(line.contains("Undone Added")) {
                CommandAdd cmd = new CommandAdd(model,shape);
                CommandShapeSelect cmdS = new CommandShapeSelect(this, shape);
                cmd.execute();
                cmdS.execute();
                frame.getLogArea().append("Added - " + shape + "\n");
                commandStackUpdate(cmd,shape,"Removed","Added");
            } else if(line.contains("Added")) {
                CommandAdd cmd = new CommandAdd(model,shape);
                cmd.execute();
                frame.getLogArea().append("Added - "+ shape +"\n");

                //undoRedoHelper(cmd);
                commandStackUpdate(cmd, shape,"Removed","Added");
************************************************************
            if(line.startsWith("Added")) {
                CommandAdd cmd = new CommandAdd(model,shape);
                cmd.execute();
                frame.getLogArea().append("Added - "+ shape +"\n");

                //undoRedoHelper(cmd);
                commandStackUpdate(cmd, shape,"Removed","Added");
            
            } else if (line.startsWith("Selected")) {
                for(int i=0; i< model.getShapes().size(); i++) {
                    if(shape.equals(model.getShapes().get(i))) {
                        shape = model.getShapes().get(i);
                    }
                }
                if(shape.isSelected()) {
                    selectedShapes.remove(shape);
                }
                CommandShapeSelect cmd = new CommandShapeSelect(this, shape);
                cmd.execute();
                frame.getLogArea().append("Selected - "+ shape +"\n");
                commandStackUpdate(cmd, shape,"Deselected","Selected");

            } else if(line.startsWith("Deselected")) {
                for(int i=0; i< model.getShapes().size(); i++) {
                    if(shape.equals(model.getShapes().get(i))) {
                        shape = model.getShapes().get(i);
                    }
                }
                CommandShapeDeselect cmd = new CommandShapeDeselect(this, shape);
                cmd.execute();
                frame.getLogArea().append("Deselected - "+ shape +"\n");


                //undoRedoHelper(cmd);
                commandStackUpdate(cmd, shape,"Selected","Deselected");

            } else if(line.startsWith("Removed")) {
                CommandRemove cmd = new CommandRemove(model, shape);
                CommandShapeDeselect cmdDS = new CommandShapeDeselect(this, shape);
                cmd.execute();
                cmdDS.execute();
                frame.getLogArea().append("Removed - "+ shape +"\n");
                commandStackUpdate(cmd, shape,"Added","Removed");
//------------
            } else if(line.startsWith("Edited")) {

                if(shape instanceof Point) {
                    Point point = (Point) shape;
                    Point temp = (Point) selectedShapes.get(0);
                    CommandUpdatePoint cmd = new CommandUpdatePoint(temp, point);
                    cmd.execute();
                    frame.getLogArea().append("Edited - "+ point +"\n");
                    commandStackUpdate(cmd, point,"Edited","Edited");
                } else if (shape instanceof Line) {
                    Line line = (Line) shape;
                    Line temp = (Line) selectedShapes.get(0);
                    CommandUpdateLine cmd = new CommandUpdateLine(temp, line);
                    cmd.execute();
                    frame.getLogArea().append("Edited - "+ line +"\n");
                    commandStackUpdate(cmd, line,"Edited","Edited");
                } else if (shape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) shape;
                    Rectangle temp = (Rectangle) selectedShapes.get(0);
                    CommandUpdateRectangle cmd = new CommandUpdateRectangle(temp, rectangle);
                    cmd.execute();
                    frame.getLogArea().append("Edited - "+ rectangle +"\n");
                    commandStackUpdate(cmd, rectangle,"Edited","Edited");
                } else if (shape instanceof Donut) {
                    Donut donut = (Donut) shape;
                    Donut temp = (Donut) selectedShapes.get(0);
                    CommandUpdateDonut cmd = new CommandUpdateDonut(temp, donut);
                    cmd.execute();
                    frame.getLogArea().append("Edited - "+ donut +"\n");
                    commandStackUpdate(cmd, donut,"Edited","Edited");
                } else if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;
                    Circle temp = (Circle) selectedShapes.get(0);
                    CommandUpdateCircle cmd = new CommandUpdateCircle(temp, circle);
                    cmd.execute();
                    frame.getLogArea().append("Edited - "+ circle +"\n");
                    commandStackUpdate(cmd, circle,"Edited","Edited");
                } else if (shape instanceof HexagonAdapter) {
                    HexagonAdapter hexagon = (HexagonAdapter) shape;
                    HexagonAdapter temp = (HexagonAdapter) selectedShapes.get(0);
                    CommandUpdateHexagon cmd = new CommandUpdateHexagon(temp, hexagon);
                    cmd.execute();
                    frame.getLogArea().append("Edited - "+ hexagon +"\n");
                    commandStackUpdate(cmd, hexagon,"Edited","Edited");
                }

            } 
            
            else if(line.startsWith("Undone")){
                undo();
            }
            else if(line.startsWith("Redone")){
                redo();
            }
            
            else if(line.startsWith("Brought to back")){
                CommandBringToBack cmd = new CommandBringToBack(model);
                cmd.execute();
                frame.getLogArea().append("Moved to back - "+selectedShapes.get(0)+"\n");
                commandStackUpdate(cmd,selectedShapes.get(0),"Brought to front","Brought to back");
            }
            else if(line.startsWith("Backward")){
                CommandToBack cmd = new CommandToBack(model);
                cmd.execute();
                frame.getLogArea().append("Moved backward - "+selectedShapes.get(0)+"\n");
                commandStackUpdate(cmd,selectedShapes.get(0),"Forward","Backward");
            }
            else if(line.startsWith("Brought to front")){
                CommandBringToFront cmd = new CommandBringToFront(model);
                cmd.execute();
                frame.getLogArea().append("Moved to front - "+selectedShapes.get(0)+"\n");
                commandStackUpdate(cmd,selectedShapes.get(0),"Brought to back","Brought to front");
            }
            else if(line.startsWith("Forward")) {
                CommandToFront cmd = new CommandToFront(model);
                cmd.execute();
                frame.getLogArea().append("Moved forward - "+selectedShapes.get(0)+"\n");
                commandStackUpdate(cmd,selectedShapes.get(0),"Backward","Forward");
            }
            logCount++;

        }
        enableButtons();
        repaint();
    }
    */
    
    public void loadNext(){
        Shape shape = null;

        if(logCount < logList.size()) {
            //Hvatamo liniju po liniju
            line=logList.get(logCount);

            if(line.contains("Point")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2,line.indexOf(")")));
                Color color = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                shape = new Point(x,y,color);

                //Ako smo na kraju zakljucavamo load next dugme
                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Line")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2,line.indexOf(")")));
                int x1 = Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ",", 1)));
                int y1 = Integer.parseInt(line.substring(ordinalIndexOf(line, ",", 1)+2, ordinalIndexOf(line, ")", 1)));
                Color color = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                shape = new Line(new Point(x,y),new Point(x1,y1),color);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Rectangle")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2, line.indexOf(")")));
                int h = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",0)+1, ordinalIndexOf(line,",",2)));
                int w = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",1)+1, ordinalIndexOf(line, ",", 3)));
                Color edgeColor = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                Color fillColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ")", 1))));
                shape = new Rectangle(new Point(x,y), h, w, fillColor, edgeColor);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Circle")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2, line.indexOf(")")));
                int r = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",0)+1, ordinalIndexOf(line,",",2)));
                Color edgeColor = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                Color fillColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ")", 1))));
                shape = new Circle(new Point(x,y), r, fillColor, edgeColor);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Donut")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2, line.indexOf(")")));
                int outerR = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",0)+1, ordinalIndexOf(line,",",2)));
                int innerR = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",1)+1, line.lastIndexOf(".")));
                Color edgeColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 2)+1, ordinalIndexOf(line, ")", 2))));
                Color fillColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ")", 1))));
                shape = new Donut(new Point(x,y), outerR, innerR, fillColor, edgeColor);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            } else if(line.contains("Hexagon")) {
                int x = Integer.parseInt(line.substring(line.indexOf("(")+1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",")+2, line.indexOf(")")));
                int r = Integer.parseInt(line.substring(ordinalIndexOf(line,"=",0)+1, ordinalIndexOf(line,",",2)));
                Color edgeColor = new Color(Integer.parseInt(line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"))));
                Color fillColor = new Color(Integer.parseInt(line.substring(ordinalIndexOf(line, "(", 1)+1, ordinalIndexOf(line, ")", 1))));
                shape = new HexagonAdapter(new Point(x,y), r, fillColor, edgeColor);

                if(logCount == logList.size()-1) {
                    frame.getButtonLoadNext().setEnabled(false);
                    setLoggingFinish(true);
                }
            }


            if(line.startsWith("Undone")){
                undo();
            }
            else if(line.startsWith("Redone")){
                redo();
            }

            else if(line.contains("Added")) {
                CommandAdd cmd = new CommandAdd(model,shape);
                cmd.execute();
                frame.getLogArea().append("Added - "+ shape +"\n");

                //undoRedoHelper(cmd);
                commandStackUpdate(cmd, shape,"Removed","Added");

            } else if (line.contains("Selected")) {
                for(int i=0; i< model.getShapes().size(); i++) {
                    if(shape.equals(model.getShapes().get(i))) {
                        shape = model.getShapes().get(i);
                    }
                }
                if(shape.isSelected()) {
                    selectedShapes.remove(shape);
                }
                CommandShapeSelect cmd = new CommandShapeSelect(this, shape);
                cmd.execute();
                frame.getLogArea().append("Selected - "+ shape +"\n");
                commandStackUpdate(cmd, shape,"Deselected","Selected");

            } else if(line.contains("Deselected")) {
                for(int i=0; i< model.getShapes().size(); i++) {
                    if(shape.equals(model.getShapes().get(i))) {
                        shape = model.getShapes().get(i);
                    }
                }
                CommandShapeDeselect cmd = new CommandShapeDeselect(this, shape);
                cmd.execute();
                frame.getLogArea().append("Deselected - "+ shape +"\n");


                //undoRedoHelper(cmd);
                commandStackUpdate(cmd, shape,"Selected","Deselected");

            } else if(line.contains("Removed")) {
                CommandRemove cmd = new CommandRemove(model, shape);
                CommandShapeDeselect cmdDS = new CommandShapeDeselect(this, shape);
                cmd.execute();
                cmdDS.execute();
                frame.getLogArea().append("Removed - "+ shape +"\n");
                commandStackUpdate(cmd, shape,"Added","Removed");

            } else if(line.contains("Edited")) {

                if (shape instanceof Point) {
                    Point point = (Point) shape;
                    Point temp = (Point) selectedShapes.get(0);
                    CommandUpdatePoint cmd = new CommandUpdatePoint(temp, point);
                    cmd.execute();
                    frame.getLogArea().append("Edited - " + point + "\n");
                    commandStackUpdate(cmd, point, "Edited", "Edited");
                } else if (shape instanceof Line) {
                    Line line = (Line) shape;
                    Line temp = (Line) selectedShapes.get(0);
                    CommandUpdateLine cmd = new CommandUpdateLine(temp, line);
                    cmd.execute();
                    frame.getLogArea().append("Edited - " + line + "\n");
                    commandStackUpdate(cmd, line, "Edited", "Edited");
                } else if (shape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) shape;
                    Rectangle temp = (Rectangle) selectedShapes.get(0);
                    CommandUpdateRectangle cmd = new CommandUpdateRectangle(temp, rectangle);
                    cmd.execute();
                    frame.getLogArea().append("Edited - " + rectangle + "\n");
                    commandStackUpdate(cmd, rectangle, "Edited", "Edited");
                } else if (shape instanceof Donut) {
                    Donut donut = (Donut) shape;
                    Donut temp = (Donut) selectedShapes.get(0);
                    CommandUpdateDonut cmd = new CommandUpdateDonut(temp, donut);
                    cmd.execute();
                    frame.getLogArea().append("Edited - " + donut + "\n");
                    commandStackUpdate(cmd, donut, "Edited", "Edited");
                } else if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;
                    Circle temp = (Circle) selectedShapes.get(0);
                    CommandUpdateCircle cmd = new CommandUpdateCircle(temp, circle);
                    cmd.execute();
                    frame.getLogArea().append("Edited - " + circle + "\n");
                    commandStackUpdate(cmd, circle, "Edited", "Edited");
                } else if (shape instanceof HexagonAdapter) {
                    HexagonAdapter hexagon = (HexagonAdapter) shape;
                    HexagonAdapter temp = (HexagonAdapter) selectedShapes.get(0);
                    CommandUpdateHexagon cmd = new CommandUpdateHexagon(temp, hexagon);
                    cmd.execute();
                    frame.getLogArea().append("Edited - " + hexagon + "\n");
                    commandStackUpdate(cmd, hexagon, "Edited", "Edited");
                }

            }
            else if(line.contains("Brought to back")){
                CommandBringToBack cmd = new CommandBringToBack(model);
                cmd.execute();
                frame.getLogArea().append("Moved to back - "+selectedShapes.get(0)+"\n");
                commandStackUpdate(cmd,selectedShapes.get(0),"Brought to front","Brought to back");
            }
            else if(line.contains("Backward")){
                CommandToBack cmd = new CommandToBack(model);
                cmd.execute();
                frame.getLogArea().append("Moved backward - "+selectedShapes.get(0)+"\n");
                commandStackUpdate(cmd,selectedShapes.get(0),"Forward","Backward");
            }
            else if(line.contains("Brought to front")){
                CommandBringToFront cmd = new CommandBringToFront(model);
                cmd.execute();
                frame.getLogArea().append("Moved to front - "+selectedShapes.get(0)+"\n");
                commandStackUpdate(cmd,selectedShapes.get(0),"Brought to back","Brought to front");
            }
            else if(line.contains("Forward")) {
                CommandToFront cmd = new CommandToFront(model);
                cmd.execute();
                frame.getLogArea().append("Moved forward - " + selectedShapes.get(0)+"\n");
                commandStackUpdate(cmd,selectedShapes.get(0),"Backward","Forward");
            }
            logCount++;

        }
        enableButtons();
        repaint();
    }
   

    public ArrayList<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
        this.selectedShapes = selectedShapes;
    }
    
    public CommandStack getCmdStack() {
        return cmdStack;
    }

    public void setCmdStack(CommandStack cmdStack) {
        this.cmdStack = cmdStack;
    }
    
    public DrawingFrame getFrame() {
        return frame;
    }

    public void setFrame(DrawingFrame frame) {
        this.frame = frame;
    }

    public DrawingModel getModel() {
        return model;
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public PointDialog getPointDialog() {
        return pointDialog;
    }

    public void setPointDialog(PointDialog pointDialog) {
        this.pointDialog = pointDialog;
    }

    public LineDialog getLineDialog() {
        return lineDialog;
    }

    public void setLineDialog(LineDialog lineDialog) {
        this.lineDialog = lineDialog;
    }

    public RectangleDialog getRectangleDialog() {
        return rectangleDialog;
    }

    public void setRectangleDialog(RectangleDialog rectangleDialog) {
        this.rectangleDialog = rectangleDialog;
    }

    public CircleDialog getCircleDialog() {
        return circleDialog;
    }

    public void setCircleDialog(CircleDialog circleDialog) {
        this.circleDialog = circleDialog;
    }

    public DonutDialog getDonutDialog() {
        return donutDialog;
    }

    public void setDonutDialog(DonutDialog donutDialog) {
        this.donutDialog = donutDialog;
    }

    public HexagonDialog getHexagonDialog() {
        return hexagonDialog;
    }

    public void setHexagonDialog(HexagonDialog hexagonDialog) {
        this.hexagonDialog = hexagonDialog;
    }

    public HandleButtonEvent getButtonEvent() {
        return buttonEvent;
    }

    public void setButtonEvent(HandleButtonEvent buttonEvent) {
        this.buttonEvent = buttonEvent;
    }

    public ObserveButtons getObserveButtons() {
        return observeButtons;
    }

    public void setObserveButtons(ObserveButtons observeButtons) {
        this.observeButtons = observeButtons;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getLogCount() {
        return logCount;
    }

    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    public boolean isLoggingFinish() {
        return loggingFinish;
    }

    public void setLoggingFinish(boolean loggingFinish) {
        this.loggingFinish = loggingFinish;
    }

    public ArrayList<String> getLogList() {
        return logList;
    }

    public void setLogList(ArrayList<String> logList) {
        this.logList = logList;
    }

    public int ordinalIndexOf(String str, String substr, int n) {
        int pos = -1;
        do {
            pos = str.indexOf(substr, pos + 1);
        } while (n-- > 0 && pos != -1);
        return pos;
    }


}
