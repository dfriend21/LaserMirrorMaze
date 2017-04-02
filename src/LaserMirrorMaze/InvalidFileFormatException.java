package LaserMirrorMaze;

public class InvalidFileFormatException extends Exception{

	private static final long serialVersionUID = 1L;
	public static String errorMessage = "The file was not formatted correctly.";
	
	public InvalidFileFormatException(){
		super(errorMessage);
	}
	
	public InvalidFileFormatException(String message){
		super(message);
	}
	
	public InvalidFileFormatException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidFileFormatException(Throwable cause){
		super(cause);
	}
}
