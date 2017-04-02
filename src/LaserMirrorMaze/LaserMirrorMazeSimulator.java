package LaserMirrorMaze;

import java.awt.Point;

public class LaserMirrorMazeSimulator {
	LaserMirrorMaze maze;
	Laser laser;
	
	public LaserMirrorMazeSimulator(LaserMirrorMaze maze, Laser laser){
		this.maze = maze;
		this.laser = laser;
	}
	
	public void runSim(boolean isDescriptiveMode){
		if(isDescriptiveMode){
			System.out.println("-----------------------------------------------");
			maze.printMaze();
			System.out.println("-----------------------------------------------");
		}
		boolean hasEscaped = false;
		boolean isInfinite = false;
		int counter = 1;
		int originalX;
		int originalY;
		int originalPosNeg = laser.getPosNeg();
		boolean startedVertical;
		while(hasEscaped == false && isInfinite == false){
			//collect data that will be used for descriptive mode
			originalX = laser.getX();
			originalY = laser.getY();
			startedVertical = false;
			if(laser.isVertical()){
				startedVertical = true;
			} else {
				startedVertical = false;
			}
			
			//check if the room we're in has a mirror
			if(maze.getRoom(laser.getX(), laser.getY()).hasMirror()){
				Mirror mirror = maze.getRoom(laser.getX(), laser.getY()).getMirror();
				
				boolean twoWayReflectVertical = mirror.isTwoWay() && (laser.isVertical() && mirror.getType() * mirror.getOrientation() == laser.getPosNeg());
				boolean twoWayReflectHorizontal = mirror.isTwoWay() && (!laser.isVertical() && mirror.getType() * -1 == laser.getPosNeg());
				if(!mirror.isTwoWay()
						|| twoWayReflectVertical
						|| twoWayReflectHorizontal){
					// if it hits a mirror, it will always change directions; it will go from 
					//traveling horizontally to vertically, and vice versa*/
					laser.toggleDirection();
					//if the mirror is L (-1) then posNeg will change sign (laser will switch from 
					//a positive to a negative direction, and vice versa.) If it's R (1) then it won't change sign*/
					laser.setPosNeg(laser.getPosNeg()*mirror.getOrientation());
				}
			}
			//move the laser to the next room
			if(laser.isVertical){
				laser.setY(laser.getY() + laser.posNeg);
			} else {
				laser.setX(laser.getX() + laser.posNeg);
			}
			
			if(maze.getRoom(laser.getX(), laser.getY()).addPrevRoom(new Point(originalX, originalY))){
				isInfinite = true;
			}
			//Check if the laser has escaped yet
			if(laser.getX() >= maze.getWidth() || laser.getY() >= maze.getHeight() 
					|| laser.getX() < 0 || laser.getY() < 0){
				hasEscaped = true;
				//if it escaped, that means that the coordinates are actually beyond the maze dimensions
				//so we'll bring it back to the room it exited from by subtracting the posNeg (either 1 or -1)
				//from the x or y coordinate, depending on if the laser finished horizontally or vertically
				if(laser.isVertical()){
					laser.setY(laser.getY()-laser.getPosNeg());
				} else {
					laser.setX(laser.getX()-laser.getPosNeg());
				}
			}
			
			if(isDescriptiveMode){
				System.out.println("STEP " + counter);
				System.out.println("laser starting location: " + originalX + "," + originalY);
				String originalOrientation = "H";
				if(startedVertical){
					originalOrientation = "V";
				}
				System.out.println("laser starting orientation: " + originalOrientation);
				System.out.println("laser starting direction: " + originalPosNeg);
				if(maze.getRoom(originalX, originalY).hasMirror()){
					System.out.println("mirror!");
					Mirror mirror = maze.getRoom(originalX, originalY).getMirror();
					String mirrorOrientation = "R";
					if(mirror.getOrientation() == -1){
						mirrorOrientation = "L";
					}
					if(mirror.isTwoWay()){
						String mirrorType = "R";
						if(mirror.getType() == -1){
							mirrorType = "L";
						}
						System.out.println("mirror is two-way: " + mirrorOrientation + mirrorType);
						if(startedVertical != laser.isVertical()){
							System.out.println("laser reflects");
						} else {
							System.out.println("laser passes through");
						}
					} else {
						System.out.println("mirror is one-way: " + mirrorOrientation);
						System.out.println("laser reflects");
					}
				}
				System.out.println("laser ending location: " + laser.getX() + "," + laser.getY());
				String endingOrientation = "H";
				if(laser.isVertical() == true){
					endingOrientation = "V";
				}
				System.out.println("laser ending orientation: " + endingOrientation);
				System.out.println("laser ending direction: " + laser.getPosNeg());
				System.out.println("-----------------------------------------------");
			}
			counter++;
		}
		//print the results
		if(hasEscaped){
			System.out.println("It escaped!");
			System.out.println("Final room: " + laser.getX() + "," + laser.getY());
			String orientation = "H";
			if(laser.isVertical()){
				orientation = "V";
			}
			System.out.println("Orientation: " + orientation);
		}
		if(isInfinite){
			System.out.println("This maze has no solution - it's infinite!");
		}
	}
}
