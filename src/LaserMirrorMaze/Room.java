package LaserMirrorMaze;

public class Room {
	Mirror mirror;
	
	public Room(){
		mirror = null;
	}
	
	public Room(Mirror mirror){
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
}
