import java.util.*;
import java.awt.Point;

public class PlayerAI extends ClientAI {
	public PlayerAI() {
		
		//Write your initialization here
	}

	@Override
	public Move getMove(Gameboard gameboard, Opponent opponent, Player player) throws NoItemException, MapOutOfBoundsException {

		getClosestPowerUpPath(gameboard,player,gameboard.getPowerUps());
		getClosestTurretPath(gameboard,player,gameboard.getTurrets());
		//Write your AI here
		return Move.NONE;
	}
	
	//least number of turn to get to power up, straight line is always preferred
	private void getClosestPowerUpPath(Gameboard gameboard,Player player, ArrayList<PowerUp> powerUps){
		// if there are no power ups
		if (powerUps.size()<=0){
			return;
		}
		
		Coordinate[] shifts = new Coordinate[powerUps.size()];
		
		for (int i = 0; i <shifts.length;i++){
			shifts[i] = getShift(player, new Coordinate (powerUps.get(i).x,powerUps.get(i).y),gameboard.getWidth(),gameboard.getHeight());
			System.out.println("list of nearby powerups(x,y), +means right and down,(change direction turn included)");
			System.out.println(shifts[i].x + "       "+shifts[i].y);
		}
	}
	
	private boolean isWithinBoundary(Gameboard gameboard,Coordinate player, Coordinate point)
	{
		int maxWidth = gameboard.getWidth();
		int maxHeight = gameboard.getHeight();
		int lowerX = getRealCoordinate(maxWidth,player.x-5);
		int upperX = getRealCoordinate(maxWidth,player.x+5);
		int lowerY = getRealCoordinate(maxHeight, player.y-5);
		int upperY = getRealCoordinate(maxHeight, player.y+5);
		boolean checkX = false;
		boolean checkY = false;
		//wrap happened for x
		if (lowerX>upperX){
			checkX=point.x>=lowerX && point.x<=maxWidth || point.x<upperX;
		}else{
			checkX=point.x>=lowerX && point.x<=upperX;
		}
		
		//wrap happened for y
		if (lowerY>upperY){
			checkY=point.y>=lowerY && point.y<=maxWidth || point.y<upperY;
		}else{
			checkY=point.y>=lowerY && point.y<=upperY;
		}
		
		return checkX && checkY;
		
	}
	
	private int getRealCoordinate(int boundary,int coordinate){
		if (coordinate<0){
			return boundary+coordinate;
		}else if (coordinate>boundary){
			return coordinate-boundary;
		}else{
			return coordinate;
		}
	}
	
	private Coordinate getShift(Player player, Coordinate powerUp, int maxWidth, int maxHeight){
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
		//move right
		if (shiftX>0 && player.direction != Direction.RIGHT){
			shiftX++;
		}else if (shiftX<0 && player.direction != Direction.LEFT){
			shiftX--;
		}
		
		if (shiftY>0 && player.direction != Direction.DOWN){
			shiftY++;
		}else if (shiftY<0 && player.direction != Direction.UP){
			shiftY--;
		}
		return new Coordinate(shiftX,shiftY);	
	}

	/* Steve's part*/
	private void getClosestTurretPath(Gameboard gameboard,Player player, ArrayList<Turret> tlst){
		// if there are no power ups
		if (tlst.size()<=0){
			return;
		}
		Coordinate[] tcod = new Coordinate[tlst.size()];
		
		for (int i = 0; i <tcod.length;i++){
			tcod[i] = getShift(player, new Coordinate (tlst.get(i).x,tlst.get(i).y),gameboard.getWidth(),gameboard.getHeight());
			System.out.println("list of nearby turrets(x,y), +means right and down");
			System.out.println(tcod[i].x + "    "+tcod[i].y);
		}
	}

	private boolean isSafe(Gameboard gameboard, Coordinate destination) throws MapOutOfBoundsException{
		// care about as well wrap around 
		int w = gameboard.getWidth();
		int h = gameboard.getHeight();
		System.out.print("game board is "+w+" by "+h +": ");
		if (gameboard.isWallAtTile(destination.x,destination.y)){
			return false;
		}
		ArrayList<Turret> tlst = gameboard.getTurrets();
		//empty turrets
		if(tlst.size() <= 0 ){
			System.out.println("there is no turret exist");
		}
		else{
			System.out.print(tlst.get(0).x+","+tlst.get(0).y);
			//checkTurrets(tlst,w,h,target);
		}
		return false;
	}

	private int destroyWithSheild(Coordinate player, Opponent op, ArrayList<Turret> list){
		// player carry shield powerup and ready to destroy turret and opponent
		// return number of turns needed to destroy closest turret
		// given player coordinate, opponent and 
		int turns = 0;
		
		return turns;
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
