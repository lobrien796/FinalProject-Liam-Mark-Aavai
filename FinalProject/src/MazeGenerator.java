import java.util.Arrays;
import java.util.Random;

public class MazeGenerator {
	char[][] maze;
	char blankChar = ' ';
	char verticalWall = '|';
	char horizontalWall = '-';
	char startChar = 'S';
	char endChar = 'E';
	int mazeSize;
	int currentColumn = 0;
	int currentRow = 0;
	Random rand = new Random();
	
	public MazeGenerator(int size) {
		mazeSize = size*2+1;
		maze = new char[mazeSize][mazeSize];
		
		clear(maze);
		maze[1][0] = startChar;
		maze[mazeSize-2][mazeSize-1] = endChar;
		printMaze();
		System.out.print("\033[H\033[2J");
		System.out.flush();

	}
	
	
	private void clear(char[][] array) {
		for (int i = 0; i < mazeSize; i++){
			for (int j = 0; j < mazeSize; j++) {
				if (i%2 ==0) {
					array[i][j] = horizontalWall;
				}else {
					if (j%2==0) {
						array[i][j] = verticalWall;
					}else {
						array[i][j] = blankChar;
					}
				}
			}
		}
	}
	
	private void walk() {
		int direction = rand.nextInt(4)+1; //1 = right, 2 = left, 3 = up, 4 = down
		if (direction == 1 && array.length() ){

		}else if(direction == 2){

		}else if(direction == 3){

		}else{

		}
	}

	public void printArray() {
		String arrayString = Arrays.deepToString(maze); // Returns array as a long string
		arrayString = arrayString.substring(1, arrayString.length()-2); // we have to just get the substring because there are [] around the [],[],[],[]...
		for (String section : arrayString.split("],")) { //split the string by "], " so that we just have the [values
			System.out.println(section.strip().replaceAll(",", "") + "]"); //strip and add ]
		}
	}
}
