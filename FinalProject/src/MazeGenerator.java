import java.util.Arrays;
import java.util.Random;

public class MazeGenerator {
    char[][] maze;
    char blankChar = ' ';
    char verticalWall = '|';
    char horizontalWall = '-';
    char pathChar = '+';
    char startChar = 'S';
    char endChar = 'E';
    int mazeSize;
    int currentColumn = 1;
    int currentRow = 1;
    Random rand = new Random();
    boolean mazeDone = false;
    int adjacentDirection;

    public MazeGenerator(int size) {
        mazeSize = size * 2 + 1;
        maze = new char[mazeSize][mazeSize];
        clear();
        maze[1][0] = startChar;
        maze[mazeSize - 2][mazeSize - 1] = endChar;
        maze[currentRow][currentColumn] = pathChar;
        
        // printMaze();
        // while (!mazeDone) {
        // 	while(!atDeadEnd()) {
        // 		walk();
        //         //printMaze();
        // 	}
        //     System.out.println("at dead end");
        // 	hunt();
        //     System.out.println("Current: (" + currentColumn + ", " + currentRow + ")");
        // }
        // printMaze();
        while(!atDeadEnd()){
            walk();
        }
        printMaze();
    }

    public void setSize(int size) {
        size = size * 2 + 1;
    }

    private void clear() {
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (i % 2 == 0) {
                    maze[i][j] = horizontalWall;
                } else {
                    if (j % 2 == 0) {
                        maze[i][j] = verticalWall;
                    } else {
                        maze[i][j] = blankChar;
                    }
                }
            }
        }
    }
    
    public void hunt() {
    	for (int i = 0; i < mazeSize; i+=2) {
    		for (int j = 0; j < mazeSize; j+=2) {
    			if(maze[i][j] == blankChar) {
    				maze[i][j] = pathChar;
    				currentColumn = i;
    				currentRow = j;
                    //walk();
    				return;
    			}
    		}
    	}
    	mazeDone = true;
    }

    public void walk() {

        int direction = rand.nextInt(4); // 0=Right, 1=Left, 2=Down, 3=Up

        if (direction == 0
                && (currentColumn + 2 < mazeSize)
                && maze[currentRow][currentColumn + 2] == blankChar) {
        	
		            maze[currentRow][currentColumn + 1] = pathChar;
		            currentColumn += 2;
		            maze[currentRow][currentColumn] = pathChar;

        } else if (direction == 1
                && (currentColumn - 2 >= 1)
                && maze[currentRow][currentColumn - 2] == blankChar) {
        	
		            maze[currentRow][currentColumn - 1] = pathChar;
		            currentColumn -= 2;
		            maze[currentRow][currentColumn] = pathChar;

        } else if (direction == 2
                && (currentRow + 2 < mazeSize)
                && maze[currentRow + 2][currentColumn] == blankChar) {
        	
		            maze[currentRow + 1][currentColumn] = pathChar;
		            currentRow += 2;
		            maze[currentRow][currentColumn] = pathChar;

        } else if (direction == 3
                && (currentRow - 2 >= 1)
                && maze[currentRow - 2][currentColumn] == blankChar) {
        	
		            maze[currentRow - 1][currentColumn] = pathChar;
		            currentRow -= 2;
		            maze[currentRow][currentColumn] = pathChar;
        }
    }

    private boolean atDeadEnd() {
        boolean canMoveRight = (currentColumn + 2 < mazeSize)  && maze[currentRow][currentColumn + 2] == blankChar;
        boolean canMoveLeft  = (currentColumn - 2 >= 1)        && maze[currentRow][currentColumn - 2] == blankChar;
        boolean canMoveDown  = (currentRow + 2 < mazeSize)     && maze[currentRow + 2][currentColumn] == blankChar;
        boolean canMoveUp    = (currentRow - 2 >= 1)           && maze[currentRow - 2][currentColumn] == blankChar;
        return !(canMoveRight || canMoveLeft || canMoveDown || canMoveUp);
    }


    public void printMaze() {
    System.out.print("\033[H\033[2J");
    System.out.flush();

    String arrayString = Arrays.deepToString(maze);
    arrayString = arrayString.substring(1, arrayString.length() - 2);

    for (String section : arrayString.split("],")) {
        String row = section.strip().replaceAll(",", "") + "]";
        for (int i = 0; i < row.length(); i++) {
            System.out.print(row.charAt(i));
        }
        System.out.println();
    }
}
}