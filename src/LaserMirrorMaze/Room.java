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
	
	public boolean hasPrevRoom(Point prevRoom){
		if(prevRooms.contains(prevRoom)){
			return true;
		} else {
			return false;
		}
	}
}
