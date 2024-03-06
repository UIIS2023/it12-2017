package cmd;

import shapes.Donut;

public class CommandUpdateDonut implements Command{
	private Donut oldDonut;
	private Donut newDonut;
	private Donut original = new Donut();
	
	public CommandUpdateDonut(Donut oldDonut, Donut newDonut) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}
	
	@Override
	public void execute() {
		original = oldDonut.clone(original);
		oldDonut = newDonut.clone(oldDonut);

	}

	@Override
	public void unexecute() {
		oldDonut = original.clone(oldDonut);

	}

}
