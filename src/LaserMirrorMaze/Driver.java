package LaserMirrorMaze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] Args){
		File folder = new File("src");
		File[] fileArray = folder.listFiles();
		File fileToUse;
		Scanner s = new Scanner(System.in);
		
		boolean keepGoing = true;
		
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
		}while(keepGoing == false);
		
		fileToUse = fileArray[fileSelectionNum];
		s.close();
		
		readFile(fileToUse);
	}
	
	public static LaserMirrorMaze readFile(File f){
		Scanner s = null;
		boolean keepGoing = true;
		LaserMirrorMaze maze = new LaserMirrorMaze();
		Laser laser = new Laser();
		
		try { 
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try{
			if(s.hasNext()){
				String widthHeight = s.next();
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
			if(s.hasNext()){
				if(!s.next().equals("-1")){
					throw new InvalidFileFormatException();
				}
			}
			if(s.hasNext()){
				String mirrorInfo = s.next();
				//read in the info until we reach the next "-1"
				while(!mirrorInfo.equals("-1")){
					Mirror mirror = new Mirror();
					//make sure its formatted correctly
					if(mirrorInfo.matches("\\d+,\\d+[RL][RL]?")){
						System.out.println(mirrorInfo + ": Pattern matches!");
						//split the string into the parts we need and set the appropriate properties
						String[] parts = mirrorInfo.split(",|(?=[RL][RL]?)");
						System.out.println("mirror init:");
						for(String str:parts){
							System.out.println(str);
						}
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
						mirrorInfo = s.next();
					} else {
						System.out.println(mirrorInfo + ": incorrect format");
						throw new InvalidFileFormatException();
					}
				}
			}
			if(s.hasNext()){
				String laserEntry = s.next();
				if(laserEntry.matches("\\d+,\\d+[HV]")){
					String[] parts = laserEntry.split(",|(?=[HV])");
					laser.setXY(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
					if(parts[2].equals("H")){
						laser.setIsVertical(false);
						
					} else {
						laser.setIsVertical(true);
					}
					laser.setPosNeg(1);
					System.out.println("Laser init:");
					for(String str:parts){
						System.out.println(str);
					}
				}
			}
			
		} catch(InvalidFileFormatException e) {
			e.printStackTrace();
		}
		maze.printMaze();
		
		LaserMirrorMazeSimulator sim = new LaserMirrorMazeSimulator(maze, laser);
		sim.runSim();
		
		
		s.close();
		return null;
	}
}
