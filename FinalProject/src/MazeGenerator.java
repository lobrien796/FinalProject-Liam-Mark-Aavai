import java.util.Random;

public class MazeGenerator {
    private char[][] maze;
    private final char blankChar = ' ';
    private final char verticalWall = '|';
    private final char horizontalWall = '—';
    private final char pathChar = '+';
    private final char startChar = 'S';
    private final char endChar = 'E';
    private int mazeSize;
    private int currentColumn = 1;
    private int currentRow = 1;
    private Random rand = new Random();
    private boolean mazeDone = false;
    private int huntStartRow = 1;

    public MazeGenerator(int size) {
        setSize(size);
    }

    public void setSize(int size) {
        mazeSize = size * 2 + 1;
        maze = new char[mazeSize][mazeSize];
        huntStartRow = 1;
        mazeDone = false;
        currentColumn = 1;
        currentRow = 1;
        clear();
        maze[1][0] = startChar;
        maze[mazeSize - 2][mazeSize - 1] = endChar;
        maze[currentRow][currentColumn] = pathChar;
    }

    public void generate(){
        long startTime = System.nanoTime();
        while (!mazeDone) {
        	while(!atDeadEnd()) {
        		walk();
        	}
        	hunt();
        }

        long endTime = System.nanoTime();
		long elapsedTime = (long) ((long) (endTime - startTime)/1_000_000.0); // in milliseconds
        System.out.println(this.toString());
        System.out.println((mazeSize-1)/2 + "x" + (mazeSize-1)/2 + " maze generated in " + elapsedTime + "ms");
    }

    private void clear() {
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (i % 2 == 0) {
                    if (j == mazeSize-1 || j == 0){
                        maze[i][j] = verticalWall;
                    }else{
                        maze[i][j] = horizontalWall;
                    }
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

        int direction = rand.nextInt(4);

        if (direction == 0 //right
                && (currentColumn + 2 < mazeSize)
                && maze[currentRow][currentColumn + 2] == blankChar) {
        	
		            maze[currentRow][currentColumn + 1] = pathChar;
		            currentColumn += 2;
		            maze[currentRow][currentColumn] = pathChar;
                    try{
                        maze[currentRow-1][currentRow]=horizontalWall; //straight walls for better look
                        maze[currentRow+1][currentRow]=horizontalWall;
                    }catch (Exception e){
                        
                    }
                    

        } else if (direction == 1 //Left
                && (currentColumn - 2 >= 1)
                && maze[currentRow][currentColumn - 2] == blankChar) {
        	
		            maze[currentRow][currentColumn - 1] = pathChar;
		            currentColumn -= 2;
		            maze[currentRow][currentColumn] = pathChar;

        } else if (direction == 2 //Down
                && (currentRow + 2 < mazeSize)
                && maze[currentRow + 2][currentColumn] == blankChar) {
        	
		            maze[currentRow + 1][currentColumn] = pathChar;
		            currentRow += 2;
		            maze[currentRow][currentColumn] = pathChar;

        } else if (direction == 3 //UP
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


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                sb.append(maze[i][j]+ " ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
     public char[][] getMaze() {
		
    	 
    	 
    	 return maze;
     }
     
     public int getSize() {
		return mazeSize;
     }
}