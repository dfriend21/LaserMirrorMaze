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
	
	public void printMaze(){
		System.out.println("rooms.size(): " + rooms.size());
		System.out.println("rooms.get(0).size(): " + rooms.get(0).size());
		ArrayList<String> stringArray = new ArrayList<String>(rooms.get(0).size());
		
		//initialize the array
		for(int i = 0; i<rooms.get(0).size(); i++){
			stringArray.add(i, "");
		}
		String lastRow = "  ";
		for(int i = 0; i < rooms.size() ; i++){
			//lastRow will list the numbers of the columns (at the bottom of the maze)
			lastRow += "  " + i + " ";
			for(int j = rooms.get(i).size()-1; j >= 0; j--){
				
				String symbol = "";
				if(rooms.get(i).get(j).hasMirror()){
					System.out.println(i + "," + j + " " + rooms.get(i).get(j).getMirror().getOrientation());
					if(rooms.get(i).get(j).getMirror().getOrientation() == -1){
						symbol = "\\";
					} else {
						symbol = "/";
					}
				} else {
					symbol = " ";
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
		this.width = width; // I don't actually need these two - just check array size
		this.height = height; //
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
/*
	public void addMirror(int x, int y, Mirror mirror){
		rooms.get(x).get(y).addMirror(mirror);
	}
	
	public Mirror getMirror(int x, int y){
		return rooms.get(x).get(y).getMirror();
	}
	
	public boolean hasMirror(int x, int y){
		if(rooms.get(x).get(y).getMirror().getOrientation()==0){
			return false;
		} else {
			return true;
		}
	}*/

}
