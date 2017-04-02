package LaserMirrorMaze;

import java.awt.Point;
import java.util.ArrayList;

public class Room {
	Mirror mirror;
	ArrayList<Point> prevRooms;
	
	public Room(){
		mirror = null;
		prevRooms = new ArrayList<Point>();
	}
	
	public Room(Mirror mirror){
		this.mirror = mirror;
		prevRooms = new ArrayList<Point>();
	}
	
	public void addMirror(Mirror mirror){
		this.mirror = mirror;
	}
	
	public boolean hasMirror(){
		if(mirror != null){
			return true;
		} else {
			return false;
		}
	}
	
	public Mirror getMirror(){
		return mirror;
	}
	
	//returns a boolean so that we can check if the maze is infinite
	public boolean addPrevRoom(Point prevRoom){
		boolean alreadyExists;
		if(prevRooms.contains(prevRoom)){
			alreadyExists = true;
		} else {
			alreadyExists = false;
		}
		prevRooms.add(prevRoom);
		return alreadyExists;
	}
}
