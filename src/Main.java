import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main { 

    public static void main(String[] args) throws IOException {
        
        // read .gol file 
        File golFile = new File("gol/glider.gol");

        // create buffered reader 
        FileReader fr = new FileReader(golFile);
        BufferedReader br = new BufferedReader(fr); 

        // Grid testGrid = new Grid();

       

        String line = "";

        while ((line = br.readLine()) != null) {

            if (line.startsWith("#")) {
                // ignore
                continue;

            } else if (line.startsWith("GRID")) {
                // create empty grid (2D array) 
                Grid.createGrid(line);


            } else if (line.startsWith("START")) {
                // declare start coordinates 
                Grid.startXY(line);


            } else if (line.startsWith("DATA")) {
                // enter data into grid 

                // create new data array to store data 
                List<String> dataArray = new ArrayList<>();

                String innerLine = "";

                // read lines into data array
                while ((innerLine = br.readLine()) != null) {
                    // System.out.println(innerLine);
                    dataArray.add(innerLine);

                }

                Grid.fillGrid(dataArray);

                // returns input grid 
                Grid.returnGrid();
                System.out.println();

            }


        }

        // close buffered reader 
        br.close();

        for (int i = 0; i < 5; i++) {
            Grid.checkNeighbours();
            Grid.nextIteration();
            System.out.println();

        }


    }

 
}