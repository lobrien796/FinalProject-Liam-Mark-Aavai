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
    int huntStartRow = 1;

    public MazeGenerator(int size) {
        mazeSize = size * 2 + 1;
        maze = new char[mazeSize][mazeSize];
        clear();
        maze[1][0] = startChar;
        maze[mazeSize - 2][mazeSize - 1] = endChar;
        maze[currentRow][currentColumn] = pathChar;

        
        long startTime = System.nanoTime();
        while (!mazeDone) {
        	while(!atDeadEnd()) {
        		walk();
        	}
        	hunt();
        }

        long endTime = System.nanoTime();
		long elapsedTime = (long) ((long) (endTime - startTime)/1_000_000.0); // in milliseconds
        System.out.println((mazeSize-1)/2 + "x" + (mazeSize-1)/2 + " maze generated in " + elapsedTime + "ms");
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
    	for (int i = huntStartRow; i < mazeSize; i+=2) {
            boolean rowFullyCarved = true;
    		for (int j = 1; j < mazeSize; j+=2) {
    			if(maze[i][j] == blankChar) {
                    rowFullyCarved = false;
                    if(j+2 < mazeSize && maze[i][j+2] == pathChar){ //right
                        maze[i][j+1] = pathChar;
                        maze[i][j] = pathChar;
                        currentColumn = j;
                        currentRow = i;
                        return;
                        
                    }else if(j-2 > 0 && maze[i][j-2] == pathChar){ //left
                        maze[i][j-1] = pathChar;
                        maze[i][j] = pathChar;
                        currentColumn = j;
                        currentRow = i;
                        return;

                    }else if(i+2 < mazeSize && maze[i+2][j] == pathChar){ //down
                        maze[i+1][j] = pathChar;
                        maze[i][j] = pathChar;
                        currentColumn = j;
                        currentRow = i;
                        return;
                        
                    }else if(i-2 > 0 && maze[i-2][j] == pathChar){ //up
                        maze[i-1][j] = pathChar;
                        maze[i][j] = pathChar;
                        currentColumn = j;
                        currentRow = i;
                        return;
                    }
    			}
    		}
            if (rowFullyCarved) huntStartRow += 2;
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
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < mazeSize; i++) {
        for (int j = 0; j < mazeSize; j++) {
            sb.append(maze[i][j] + " ");
        }
        sb.append('\n');
    }
    System.out.print(sb);
    }
}