import java.util.Random;


public class SimulatedPlayer extends Player {

	public SimulatedPlayer(int id, String name) {
		super(id, name);
	}
		
	@Override
	public void run() {
		
		Random rand = new Random();

		int  n = rand.nextInt(4);
		
		if (n == 0) this.getSnake().setCurrentDirection(Snake.Direction.RIGHT);
		if (n == 1) this.getSnake().setCurrentDirection(Snake.Direction.LEFT);
		if (n == 2) this.getSnake().setCurrentDirection(Snake.Direction.UP);
		if (n == 3) this.getSnake().setCurrentDirection(Snake.Direction.DOWN);
		
		this.getSnake().move();
		
	}
	
	@Override
	public boolean equals (Object o) {
		
		if (o.getClass().equals(SimulatedPlayer.class) && ((SimulatedPlayer)o).getPlayerID() == this.getPlayerID()) {
			return true;
		}
		
		return false;
		
	}

}
