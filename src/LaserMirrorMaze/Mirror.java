package LaserMirrorMaze;

public class Mirror {
	
	//R is 1, L is -1
	public int orientation;
	public int type;
	
	public Mirror(){
		orientation = 0;
	}
	
	public Mirror(int orientation){
		this.orientation = orientation;
		type = 0;
	}
	public Mirror(int orientation, int type){
		this.orientation = orientation;
		this.type = type;
	}
	
	public int getOrientation(){
		return orientation;
	}
	
	public int getType(){
		return type;
	}
	
	public boolean isTwoWay(){
		if(type!=0){
			return true;
		} else {
			return false;
		}
	}
	public void setOrientation(int orientation)
	{
		this.orientation = orientation;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
}
