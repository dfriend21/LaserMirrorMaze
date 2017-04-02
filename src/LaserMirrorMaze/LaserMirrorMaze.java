package LaserMirrorMaze;

import java.util.ArrayList;

public class LaserMirrorMaze {

	ArrayList<ArrayList<Room>> rooms;
	int height;
	int width;
	
	public LaserMirrorMaze(){}
	
	public LaserMirrorMaze(int width, int height){
		this.height = height;
		this.width = width;
	}
	
	//makes a representation of the maze in the console
	public void printMaze(){
		ArrayList<String> stringArray = new ArrayList<String>(rooms.get(0).size());
		
		//initialize the array
		for(int i = 0; i<rooms.get(0).size(); i++){
			stringArray.add(i, "");
		}
		String lastRow = "  ";
		for(int i = 0; i < rooms.size() ; i++){
			//lastRow will list the numbers of the columns (at the bottom of the maze)
			lastRow += "   " + i + " ";
			for(int j = rooms.get(i).size()-1; j >= 0; j--){
				
				String symbol = "";
				if(rooms.get(i).get(j).hasMirror()){
					Mirror mirror = rooms.get(i).get(j).getMirror();
					
					if(rooms.get(i).get(j).getMirror().getOrientation() == -1){
						symbol = "\\";
					} else {
						symbol = "/";
					}
					if(mirror.isTwoWay()){
						if(mirror.type == 1){
							symbol = ":" + symbol;
						} else {
							symbol = symbol + ":";
						}
					}else {
						symbol = symbol + symbol;
					}
				} else {
					symbol = "  ";
				}
				stringArray.set(j, stringArray.get(j) + "| " + symbol + " ");
			}
		}
		for(int i = 0; i < rooms.get(0).size(); i++){
			stringArray.set(i, i + " " + stringArray.get(i) + "|");
		}
		//we have to print it backwards since we want the first row on the bottom
		for(int i = stringArray.size()-1; i >= 0; i--){
			System.out.println(stringArray.get(i));
		}
		System.out.println(lastRow);
	}
	
	public void setDimensions(int width, int height){
		this.width = width; 
		this.height = height; 
		rooms = new ArrayList<ArrayList<Room>>();
		for(int i = 0; i<width; i++){
			rooms.add(i, new ArrayList<Room>());
			for(int j = 0; j<height; j++){
				rooms.get(i).add(j, new Room());
			}
		}
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Room getRoom(int x, int y){
		return rooms.get(x).get(y);
	}
}
