import java.util.*;
import java.awt.Point;

public class PlayerAI extends ClientAI {

	public PlayerAI() {
		
		//Write your initialization here
	}

	@Override
	public Move getMove(Gameboard gameboard, Opponent opponent, Player player) throws NoItemException, MapOutOfBoundsException {
		
		getClosestPowerUpPath(gameboard,player,gameboard.getPowerUps());
		//Write your AI here
		return Move.NONE;
	}
	
	//least number of turn to get to power up, straight line is always preferred
	private void getClosestPowerUpPath(Gameboard gameboard,Player player, ArrayList<PowerUp> powerUps){
		// if there are no power ups
		if (powerUps.size()<=0){
			return;
		}
		
		Coordinate[] test = new Coordinate[powerUps.size()];
		
		for (int i = 0; i <test.length;i++){
			test[i] = getNumTurn(new Coordinate(player.x,player.y), new Coordinate (powerUps.get(i).x,powerUps.get(i).y),gameboard.getWidth(),gameboard.getHeight());
			System.out.println(test[i].x + "    "+test[i].y);
		}
		
		
	}
	
	private Coordinate getNumTurn(Coordinate player, Coordinate powerUp, int maxWidth, int maxHeight){
		int shiftX = -(player.x - powerUp.x);
		int shiftWrapX;
		if (player.x>powerUp.x){
			shiftWrapX = (maxWidth-player.x) + powerUp.x;
		}else{
			shiftWrapX = -(maxWidth-powerUp.x)-player.x;
		}
		
		if (Math.abs(shiftWrapX)<Math.abs(shiftX)){
			shiftX = shiftWrapX;
		}
		
		int shiftY = -(player.y - powerUp.y);
		int shiftWrapY;
		if (player.y>powerUp.y){ // go over bottom border
			shiftWrapY = (maxHeight-player.y) + powerUp.y;
		}else{
			shiftWrapY = -(maxHeight-powerUp.y)-player.y;
		}
		
		if (Math.abs(shiftWrapY)<Math.abs(shiftY)){
			shiftY = shiftWrapY;
		}
		
		return new Coordinate(shiftX,shiftY);
		
	}
}

class Coordinate {
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}
