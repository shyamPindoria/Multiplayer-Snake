
public class SimulatedPlayer extends Player {

	public SimulatedPlayer(int id, String name) {
		super(id, name);
	}
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean equals (Object o) {
		
		if (o.getClass().equals(SimulatedPlayer.class) && ((SimulatedPlayer)o).getPlayerID() == this.getPlayerID()) {
			return true;
		}
		
		return false;
		
	}

}
