package LaserMirrorMaze;

public class LaserMirrorMazeSimulator {
	LaserMirrorMaze maze;
	Laser laser;
	
	public LaserMirrorMazeSimulator(LaserMirrorMaze maze, Laser laser){
		this.maze = maze;
		this.laser = laser;
	}
	
	public void runSim(){
		boolean hasEscaped = false;
		while(hasEscaped == false){
			//check if the room we're in has a mirror
			if(maze.getRoom(laser.getX(), laser.getY()).hasMirror()){
				System.out.println("MIRROR!!!");
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

			System.out.println("Begin Round:");
			System.out.println("laser loc:");
			System.out.println(laser.getX() + "," + laser.getY());
			System.out.println("laser posNeg:");
			System.out.println(laser.posNeg);

			System.out.println("After first step:");
			System.out.println(laser.getX() + "," + laser.getY() + "\n");
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
	}
}
