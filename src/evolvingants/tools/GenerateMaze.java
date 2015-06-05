package evolvingants.tools;

import GeneticAlgorithm.RandomGenerator;
import antframework.common.Matrix;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * This is a utility class. It handles all the logic related to the
 * creation of mazes in which to test Evolving Ants.
 * The major contribution of this class is to create a logical mapping between a maze
 * and a graph in order to leverage what is already constructed for the 
 * AntFramework.
 * 
 * The mazes created here are known as Perfect Mazes. The algorithm to build them
 * is an implementation from a pseudo code found in http://www.mazeworks.com/mazegen/mazetut/index.htm
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class GenerateMaze {
    /*
     * Given a cell, this function return the neighboors of that cell.
     */
    protected static ArrayList getNeighbors(int numberOfCells,int cell,int cellPerRow){
        ArrayList Neighbors = new ArrayList();
        //We know the maze is multiple of 'cellPerRow' in dimension
        if(cell %cellPerRow == 0 ){//We are at a left-hand corner
            //We are at the begining, we have one and 'cellPerRow' as an option
            if(cell == 0){
                Neighbors.add(1);
                Neighbors.add(cellPerRow);
            }else if(cellPerRow == (numberOfCells-cell)){//We are at the bottom left-hand corner
                Neighbors.add(cell+1);
                Neighbors.add(cell-cellPerRow);
            }else{
                //Add top cell
                Neighbors.add(cell-cellPerRow);
                //Add bottom cell
                Neighbors.add(cell+cellPerRow);
                //Add right cell
                Neighbors.add(cell+1);
            }
        }else if(cell>0 && cell<cellPerRow-1){//The top row
            Neighbors.add(cell-1);
            Neighbors.add(cell+1);
            Neighbors.add(cell+cellPerRow);            
        }else if(cell>(numberOfCells-cellPerRow) && cell<numberOfCells-1){//The bottom row
            Neighbors.add(cell-1);
            Neighbors.add(cell+1);
            Neighbors.add(cell-cellPerRow);
        }else if((cell+1) % cellPerRow == 0){//The right-hand corner
            if(cell == cellPerRow-1){
                Neighbors.add(cellPerRow-2);
                Neighbors.add(2*cellPerRow-1);                
            }else if(cell == (numberOfCells-1)){
                Neighbors.add(cell-1);
                Neighbors.add(cell-cellPerRow);                
            }else{
                //Add top cell
                Neighbors.add(cell-cellPerRow);
                //Add bottom cell
                Neighbors.add(cell+cellPerRow);
                //Add right cell
                Neighbors.add(cell-1);                
            }
        }else{
            //Add top
            Neighbors.add(cell-cellPerRow); 
            //Add bottom
            Neighbors.add(cell+cellPerRow); 
            //Add left
            Neighbors.add(cell-1); 
            //Add right
            Neighbors.add(cell+1); 
        }
        return Neighbors;
    }
    /*
     * Given a cell, this function return the neighboors of that cell that
     * have walls all around them.
     */    
    protected static ArrayList allWall(int currentCell,ArrayList cells, Matrix M){
        //System.out.println("currentCell = "+currentCell+",neighbors cells = "+cells);
        //Variable for neighbors
        ArrayList neighbors = null; 
        ArrayList neighborsAllWall = new ArrayList(); 
        for(int i=0;i<cells.size();i++){
            //System.out.println("Processing cell: "+Integer.parseInt(cells.get(i).toString()));
            //System.out.println("currentCell("+currentCell+") neighbors("+i+") = "+neighbors.get(i));
            int currentNeig = Integer.parseInt(cells.get(i).toString());
            boolean addNeig = true;
            for(int k=0;k<M.getColumns() && addNeig;k++){
                //System.out.println("\t checking M("+currentNeig+","+k+")"+M.position(currentNeig, k));
                if(M.position(currentNeig, k) != -1){
                    addNeig = false;
                }
            }
            if(addNeig){
                neighborsAllWall.add(currentNeig);
            }
        }
        //System.out.println("These are the neig with all walls: "+neighborsAllWall);
        return neighborsAllWall;
    }
    /*
     * Creates a random maze. 
     */    
    protected static Matrix RandomMaze(int dimension,int cellPerRow,boolean randomWeights) throws Exception{
        if(dimension %10 !=0 || dimension<10){
            throw new Exception("The maze dimensions must be multiple of 10 and greater than 10");
        }
        Matrix M = new Matrix(dimension, dimension,-1);
        //System.out.println(M);
        //create a CellStack (LIFO) to hold a list of cell locations  
        Stack Cells = new Stack();
        for(int i=0;i<dimension;i++){
            Cells.push(i);
        }
        //set TotalCells = number of cells in grid  
        int TotalCells = dimension;
        //choose a cell at random and call it CurrentCell
        int currentCell = RandomGenerator.Random(dimension);
        //set VisitedCells = 1  
        int VisitedCells = 1,random = -1;
        while(VisitedCells<TotalCells){
            //find all neighbors of CurrentCell with all walls intact   
            ArrayList negWAllWalls = GenerateMaze.allWall(currentCell,GenerateMaze.getNeighbors(dimension, currentCell,cellPerRow), M);
            //System.out.println("negWAllWalls = "+negWAllWalls+",negWAllWalls.size() = "+negWAllWalls.size());
            //if one or more found  
            if(negWAllWalls.size()>0){
                //choose one at random  
                random = RandomGenerator.Random(negWAllWalls.size());
                int pos = Integer.parseInt(negWAllWalls.get(random).toString());
                //knock down the wall between it and CurrentCell
                if(randomWeights){
                    M.position(pos, currentCell,RandomGenerator.Random(100));
                    M.position(currentCell, pos,RandomGenerator.Random(100));
                }else{
                    M.position(pos, currentCell,2);
                    M.position(currentCell, pos,2);                    
                }
                //push CurrentCell location on the CellStack  
                Cells.push(currentCell);
                //make the new cell CurrentCell  
                currentCell = pos;
                //add 1 to VisitedCells
                VisitedCells++;                
            }else{
                //pop the most recent cell entry off the CellStack 
                //make it CurrentCell 
                currentCell = Integer.parseInt(Cells.pop().toString());
            }
        }
        return M;
    }
    /*
     * Save the Maze to a file. What is actually saved is the matrix representation
     * of the graph associted with the maze
     */
    protected static void saveMaze(String path,String filename,Matrix M) throws FileNotFoundException,IOException{
        /*
         * Make sure the path exists
         */
        File MazePath = new File(path);
        if(!MazePath.exists()){
            MazePath.mkdir();
        }
        /*
         * Make sure the file exists
         */
        File MazeFile=new File(path + filename);
        if(!MazeFile.exists()){
            MazeFile.createNewFile();
        }
        /*
         * Save the Maze
         */
        PrintStream out = new PrintStream(new FileOutputStream(path + filename));
        out.println(M);
        out.close();
    }
    /* This function creates 'n*amount' mazes separetad by increment 'increment', starting at 'start'. For instance
     * if start=10, n=10 and increment = 10, it will create mazes 10,20,30,...,100
     */
    public static void bulkGenerateMazes(String pathToMazes,int start,int n,int increment,int amount,int numberCellPerRow,boolean randomWeights) throws Exception{
        /*
         * Make sure we have a minimun value for the parameters
         */
       if(start == 10 && numberCellPerRow == 10){
           throw new Exception("You cannot have a maze dimension 10 with numberCellPerRow 10. It will not have a solution.");
       }
       
       /*
        * Print the parameters in the console so that we know what is going on
        */
       System.out.println("Bulk Maze Generator: \nParameters: \n\tstart \t\t = "      +start +
                                                            "\n\tend \t\t = "         +n+
                                                            "\n\tincrement \t = "     +increment+
                                                            "\n\tamount \t\t = "      +amount+
                                                            "\n\tnumberCellPerRow = " +numberCellPerRow);
       /*
        * Loop from the 'start' all the way to n
        */
       for(int i=start;i<=n;i=i+increment){
           System.out.print("Creating mazes with dimension = "+i+"....");
           if(i % numberCellPerRow != 0){
               System.err.println("This Maze could not be created because the dimension is not divisible by the number of cell per Row");
               continue;
           }
           //Generate the amount of mazes desired
           for(int j=0;j<=amount;j++){
               //Generate Maze
               Matrix M = GenerateMaze.RandomMaze(i,numberCellPerRow,randomWeights);
               //Save Maze
               GenerateMaze.saveMaze(pathToMazes+i+"/","maze"+i+"_"+numberCellPerRow+"_"+j+".mz", M);
           }
           //Report that the maze has been generated
           System.out.println(" Done!");
       }        
    }
    /*
     * TODO: make a terminal, graphical picture of the maze. Ideally, one could have a
     * graphical representation in Java Swing or another graphical tool.
     */
    public static void drawMaze(Matrix M){
        System.out.println("MAZE:");
        System.out.println(M.toString());
        for(int i=0;i<M.getColumns();i++){
            System.out.print(i+"\t");
            if(((i+1)%10 == 0)){
                System.out.println("");
            }
            for(int j=0;j<M.getColumns();j++){
                if(M.position(i, j) != -1){
                    System.out.print("|");
                }
            }
        }
        System.out.println("");
    }
}