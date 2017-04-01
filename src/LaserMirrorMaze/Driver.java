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
		
		try { 
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int width = 0;
		int height = 0;
		try{
			if(s.hasNext()){
				String widthHeight = s.next();
				//check if string is in correct format
				if(widthHeight.matches("\\d+,\\d+")){
					String[] parts = widthHeight.split(",");
					width = Integer.parseInt(parts[0]);
					height = Integer.parseInt(parts[1]);
				} else {
					throw new InvalidFileFormatException();
				}
			}
			if(s.hasNext()){
				if(s.next()!="-1"){
					throw new InvalidFileFormatException();
				}
			}
			if(s.hasNext()){
				String mirrorInfo = s.next();
				while(mirrorInfo != "-1"){
					
				}
			}
			
		} catch(InvalidFileFormatException e) {
			e.printStackTrace();
		}
		System.out.println(width);
		System.out.println(height);
		
		
		s.close();
		return null;
	}
}
