package LaserMirrorMaze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] Args){
		System.out.println("-----------------------------------------------");
		System.out.println("Welcome to Laser Maze Solver!");
		System.out.println("-----------------------------------------------");
		
		File folder = new File("src");
		File[] fileArray = folder.listFiles();
		File fileToUse;
		Scanner s = new Scanner(System.in);
		
		boolean keepGoing = true;
		boolean isDescriptiveMode = false;
		System.out.println("Please select from the following files (found in the 'src' folder of this project):");
		//list files
		if(fileArray.length != 0){
			for(int i = 0; i<fileArray.length; i++){
				System.out.println("(" + i + ") "+ fileArray[i].getName());
			}
		} else {
			System.out.println("No files found.");
		}

		int fileSelectionNum = -1;
		
		//file selection loop
		do{
			keepGoing = true;
			System.out.println("Please select a file by entering the corresponding number.");
			String input = s.next();
			
			//Make sure it's a number
			try{
				fileSelectionNum = Integer.parseInt(input);
			} catch (NumberFormatException e){
				keepGoing = false;
			}
			
			//Make sure it's a valid number
			if(keepGoing == true && (fileSelectionNum < 0 || fileSelectionNum >= fileArray.length)){
				keepGoing = false;
			}
			
			if(keepGoing == false){
				System.out.println("Please make a valid selection.\n");
			}
		} while(keepGoing == false);
		
		//select descriptive mode or basic mode
		do{
			keepGoing = true;
			System.out.println("Enter 'd' for descriptive mode and 'b' for basic mode.");
			String input = s.next();
			if(!input.equals("d") && !input.equals("b")){
				System.out.println("Please make a valid selection.\n");
				keepGoing = false;
			} else {
				if(input.equals("d")){
					isDescriptiveMode = true;
				} else {
					isDescriptiveMode = false;
				}
			}
		} while(keepGoing == false);
		fileToUse = fileArray[fileSelectionNum];
		s.close();
		
		//Now we'll read in the data from the file
		Scanner s2 = null;
		keepGoing = true;
		LaserMirrorMaze maze = new LaserMirrorMaze();
		Laser laser = new Laser();
		try { 
			s2 = new Scanner(fileToUse);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try{
			//get width-height data
			if(s2.hasNext()){
				String widthHeight = s2.next();
				//make sure its formatted correctly
				if(widthHeight.matches("\\d+,\\d+")){
					//split the string to get the x and y coordinates
					String[] parts = widthHeight.split(",");
					int width = Integer.parseInt(parts[0]);
					int height = Integer.parseInt(parts[1]);
					maze.setDimensions(width, height);
				} else {
					throw new InvalidFileFormatException(widthHeight + ": incorrect format");
				}
			}
			if(s2.hasNext()){
				if(!s2.next().equals("-1")){
					throw new InvalidFileFormatException();
				}
			}
			//get mirror data
			if(s2.hasNext()){
				String mirrorInfo = s2.next();
				//read in the info until we reach the next "-1"
				while(!mirrorInfo.equals("-1")){
					Mirror mirror = new Mirror();
					//make sure its formatted correctly
					if(mirrorInfo.matches("\\d+,\\d+[RL][RL]?")){
						//split the string into the parts we need and set the appropriate properties
						String[] parts = mirrorInfo.split(",|(?=[RL][RL]?)");
						if(parts[2].equals("R")){
							//set orientation to 1 (1 represents R)
							mirror.setOrientation(1);
						} else {
							//set orientation to -1 (-1 represents L)
							mirror.setOrientation(-1);
						}
						if(parts.length > 3){
							//type refers to whether it's two-way or normal
							if(parts[3].equals("R")){
								//set type to 1 (1 represents R)
								mirror.setType(1);
							} else {
								//set type to -1 (-1 represents L)
								mirror.setType(-1);
							}
						} else {
							//set type to 0 (0 represents a normal mirror)
							mirror.setType(0);
						}
						int x = Integer.parseInt(parts[0]);
						int y = Integer.parseInt(parts[1]);
						maze.getRoom(x,y).addMirror(mirror);
						mirrorInfo = s2.next();
					} else {
						System.out.println(mirrorInfo + ": incorrect format");
						throw new InvalidFileFormatException();
					}
				}
			}
			//get laser data
			if(s2.hasNext()){
				String laserEntry = s2.next();
				//make sure it's formatted correctly
				if(laserEntry.matches("\\d+,\\d+[HV]")){
					//split the string to get the coordinates and the direction
					String[] parts = laserEntry.split(",|(?=[HV])");
					laser.setXY(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
					laser.setPosNeg(1);
					//set the isVertical property of the laser
					//also check to see what direction the laser needs to start in
					if(parts[2].equals("H")){
						laser.setIsVertical(false);
						//this assumes that the laser will always enter through the sides...
						//which was in the specifications so that seems like a reasonable thing to assume
						if(laser.getX() != 0){
							laser.setPosNeg(-1);
						}
						
					} else {
						laser.setIsVertical(true);
						if(laser.getY() != 0){
							laser.setPosNeg(-1);
						}
					}
				} else {
					System.out.println(laserEntry + ": incorrect format");
					throw new InvalidFileFormatException();
				}
			}
			
		} catch(InvalidFileFormatException|IndexOutOfBoundsException e) {
			System.out.println("File was not formatted correctly.");
			return;
		} 
		s2.close();
		LaserMirrorMazeSimulator sim = new LaserMirrorMazeSimulator(maze, laser);
		//run the simulation!
		sim.runSim(isDescriptiveMode);
	}
}