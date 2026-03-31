import java.util.Arrays;

public class MazeGenerator {
	char blankChar = ' ';
	char wallChar = '1';
	char startChar = 'S';
	char endChar = 'E';
	int mazeSize;
	
	public MazeGenerator(int size) {
		mazeSize = size*2;
		char[][] maze = new char[mazeSize][mazeSize];
		
		clear(maze);
		maze[0][0] = startChar;
		maze[mazeSize-1][mazeSize-1] = endChar;
		printArray(maze);
	}
	
	
	private void clear(char[][] array) {
		for (int i = 0; i < mazeSize-1; i++){
			for (int j = 0; j < mazeSize-1; j++) {
				array[i][j] = blankChar;
			}
		}
	}
	
	public void printArray(char[][] array) {
		String arrayString = Arrays.deepToString(array);
		arrayString = arrayString.substring(1, arrayString.length()-2);
		for (String section : arrayString.split("],")) {
			System.out.println(section.strip().replaceAll(",", "") + "]");
		}
	}
}
