package LaserMirrorMaze;

import java.awt.Point;

public class Laser {
	
	Point location;
	//int x;
	//int y;
	
	//this tells us if the beam is moving vertically or horizontally
	boolean isVertical;
	
	//this tells us if the beam is moving positively or negatively (in a vertical or horizontal direction)
	int posNeg;
	
	public Laser(){
		location = new Point();
	}
	
	public Laser(int x, int y, char direction){
		location = new Point(x,y);
	}
	

	public void setX(int x){
		location.setLocation(x ,location.y);
	}
	
	public void setY(int y){
		location.setLocation(location.x, y);
	}
	
	public void toggleDirection(){
		isVertical = !isVertical;
	}
	
	public int getX(){
		return location.x;
	}
	
	public int getY(){
		return location.y;
	}
	
	public int getPosNeg(){
		return posNeg;
	}
	public boolean isVertical(){
		return isVertical;
	}
	public void setPosNeg(int num){
		posNeg = num;
	}
	
	public void setIsVertical(boolean isVertical){
		this.isVertical = isVertical;
	}
	
	public void setXY(int x, int y){
		location.setLocation(x, y);
	}
}
