import java.util.List;

public class Grid {
    private static int[][] twoDIntArray;
    private static int[][] aliveNeighboursArray;
    private static int[][] newGeneration; 

    private int[] startCoordinates;

    private static int numColumns; 
    private static int numRows;

    private static int X; 
    private static int Y;


    // create empty grid
    public static void createGrid(String input) {

        // read line, get grid size
        String inputGrid = input.substring(5);
        String grid[] = inputGrid.split(" ");
        numColumns = Integer.parseInt(grid[0]);
        numRows = Integer.parseInt(grid[1]);

        // create 2D array
        twoDIntArray = new int[numRows][numColumns];

        // fill 2D array 
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                twoDIntArray[i][j] = 0;
            }
        }
        
        // // print empty grid 
        // for (int i = 0; i <numRows; i++) {
        //     for (int j = 0; j < numColumns; j++) {
        //         System.out.print(twoDIntArray[i][j] + "  ");
        //     }
        //     System.out.println();
        // }

    }

    // declare start coordinates 
    public static void startXY(String input) {

        // read line, get coordinates
        String startXY = input.substring(6);
        String start[] = startXY.split(" ");
        Y = Integer.parseInt(start[0]);
        X = Integer.parseInt(start[1]);

        // create start coordinates array 
        int[] startCoordinates = {X, Y};

        // // print coordinates 
        // System.out.printf("Coordinates are (%d, %d)\n", startCoordinates[0], startCoordinates[1]);
    
    }

    // fill grid 
    public static void fillGrid(List<String> input) {

        int x = X;
        for (String s : input) {

            int y = Y;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '*') {
                    twoDIntArray[x][y] = 1;
                }

                y++;

            }

            x++;

        }

    }

    // return grid 
    public static void returnGrid() {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(twoDIntArray[i][j] + "  ");
            }
            System.out.println();
        }

    }

    // check neighbouring cells 
    public static void checkNeighbours() {

        aliveNeighboursArray = new int[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                
                int aliveNeighbours = 0; 

                int rowAbove = Math.max(i - 1,0);
                int rowBelow = Math.min(i + 1, numRows - 1);

                int colLeft = Math.max(j - 1, 0);
                int colRight = Math.min(j + 1, numColumns - 1);

                // System.out.println("for " + i + ", " + j);
                // System.out.printf("rowAbove: %d, rowBelow: %d, colLeft: %d, colRight: %d\n", rowAbove, rowBelow, colLeft, colRight);

                for (int k = rowAbove; k <= rowBelow; k++) {
                    for (int l = colLeft; l <= colRight; l++) {

                        // System.out.println(k + ", " + l);
                        aliveNeighbours += twoDIntArray[k][l];

                    }

                }

                aliveNeighbours -= twoDIntArray[i][j];
                aliveNeighboursArray[i][j] = aliveNeighbours;

            }

        }

        // // prints aliveNeighboursArray
        // for (int i = 0; i < numRows; i++) {
        //     for (int j = 0; j < numColumns; j++) {
        //         System.out.print(aliveNeighboursArray[i][j] + "  ");
        //     }
        //     System.out.println();
        // }

    }

    // count number of neighbouring cells alive
    public static void nextIteration() {

        // create new 2D array
        newGeneration = new int[numRows][numColumns];

        // fill 2D array 
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                newGeneration[i][j] = 0;
            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {

                int temp = aliveNeighboursArray[i][j];

                // if cell is alive in previous generation 
                if (twoDIntArray[i][j] == 1) {

                    // each cell with 1 or no neighbours dies 
                    if (temp == 0 || temp == 1) {
                        newGeneration[i][j] = 0; 
                    } 

                    // each cell with 2 or 3 neighbours survives 
                    else if (temp == 2 || temp == 3) {
                        newGeneration[i][j] = 1; 
                    }

                    // each cell with 4 or more neighbours dies 
                    else if (temp >= 4) {
                        newGeneration[i][j] = 0;
                    }

                } 
                
                // else cell is dead in previous generation
                else {

                    // each dead cell with 3 neighbours becomes populated in the next generation
                    if (temp == 3) {
                        newGeneration[i][j] = 1;
                    }

                }
                
               
            }
            
        }

        // prints newGeneration
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(newGeneration[i][j] + "  ");
            }
            System.out.println();
        }

        twoDIntArray = newGeneration;

    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

}
