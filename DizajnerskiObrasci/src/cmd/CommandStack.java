package cmd;

import java.util.ArrayList;

import shapes.Shape;

public class CommandStack {
	private ArrayList<Command> commands = new ArrayList<>();
	private ArrayList<Shape> shapes = new ArrayList<>();
	private ArrayList<String> commandsUndo = new ArrayList<>();
	private ArrayList<String> commandsRedo = new ArrayList<>();
	private int count = -1;
	public ArrayList<Command> getCommands() {
		return commands;
	}
	public void setCommands(ArrayList<Command> commands) {
		this.commands = commands;
	}
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	public ArrayList<String> getCommandsUndo() {
		return commandsUndo;
	}
	public void setCommandsUndo(ArrayList<String> commandsUndo) {
		this.commandsUndo = commandsUndo;
	}
	public ArrayList<String> getCommandsRedo() {
		return commandsRedo;
	}
	public void setCommandsRedo(ArrayList<String> commandsRedo) {
		this.commandsRedo = commandsRedo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	public void undo() {
		commands.get(count).unexecute();
		count--;
	}
	
	public void redo() {
		if(count == commands.size()-1) {
			return;
		}
		count++;
		commands.get(count).execute();		
	}
	
	public void deleteElementsAfterCount(int count)
    {
        if(commands.size()<1){
            return;
        }
        for(int i = commands.size()-1; i > count; i--)
        {
            commands.remove(i);
            shapes.remove(i);
            commandsUndo.remove(i);
            commandsRedo.remove(i);
        }
    }
	
	
}
