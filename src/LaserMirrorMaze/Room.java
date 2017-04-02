package LaserMirrorMaze;

import java.awt.Point;
import java.util.ArrayList;

public class Room {
	Mirror mirror;
	ArrayList<Point> prevRooms;
	
	public Room(){
		mirror = null;
	}
	
	public Room(Mirror mirror){
		this.mirror = mirror;
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
	
	public void addPrevRoom(Point prevRoom){
		prevRooms.add(prevRoom);
	}
	
	public boolean hasPrevRoom(Point prevRoom){
		if(prevRooms.contains(prevRoom)){
			return true;
		} else {
			return false;
		}
	}
}
