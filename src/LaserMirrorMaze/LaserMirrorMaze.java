package LaserMirrorMaze;

import java.util.ArrayList;

public class LaserMirrorMaze {

	ArrayList<ArrayList<Room>> Rooms;
	int height;
	int width;
	
	public LaserMirrorMaze(){}
	
	public LaserMirrorMaze(int width, int height){
		this.height = height;
		this.width = width;
	}

	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void runSimulation(){}
}
