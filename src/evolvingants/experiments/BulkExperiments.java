/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvingants.experiments;

import evolvingants.EVA_MazeSolver;
import evolvingants.tools.FileHandling;
import java.io.File;
import java.util.ArrayList;

/**
 * 
 * This class handles bulk testing of EVA.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class BulkExperiments {
    /*
     * Experiments to solve maze with EvolvingAnts. 
     * This function loops through all mazes and for each one calls trialEvolvingAnts
     * to perform a fix number of trial on that maze.
     */    
    public static void experimentsEvolvingAnts(ArrayList<String> XMLParameters,int start,int end) throws Exception{
        /*Report the start of the expermients*/
        if(evolvingants.Main.verbose){
            System.out.println("\nStarting experiments ...");
        }
        /*Loop through all mazes*/
        for(int j=start;j<=end;j = j+10){
            if(evolvingants.Main.verbose){
                System.out.println("\n******************Working Mazes of dimension "+j + "************************************");
            }
            /*
             * Unzip mazes of this dimensions
             */
            FileHandling.unzip(evolvingants.Main.pathToMazes, j, j+".zip");
            /*Conduct experiments*/
            if(j % 5 == 0){ //Check if there are mazes with 5 cols for this dimension
                if(evolvingants.Main.verbose){
                    System.out.println("\n++++++++++++++++++++++++"+j+"_5++++++++++++++++++++++++\n");
                }
                for(int i = 0; i<10;i++){
                    BulkExperiments.trialEvolvingAnts(XMLParameters,"maze"+j+"_5_"+i+".mz", j, i, 5);                    
                }
            }
            if(j % 10 == 0){//Check if there are mazes with 10 cols for this dimension
                if(evolvingants.Main.verbose){
                    System.out.println("\n++++++++++++++++++++++++"+j+"_10++++++++++++++++++++++++\n");
                }
                for(int i = 0; i<10;i++){
                    BulkExperiments.trialEvolvingAnts(XMLParameters,"maze"+j+"_10_"+i+".mz", j, i, 10);                    
                }
            }
            if(j % 15 == 0){//Check if there are mazes with 15 cols for this dimension
                if(evolvingants.Main.verbose){
                    System.out.println("\n++++++++++++++++++++++++"+j+"_15++++++++++++++++++++++++\n");
                }
                for(int i = 0; i<10;i++){
                    BulkExperiments.trialEvolvingAnts(XMLParameters,"maze"+j+"_15_"+i+".mz", j, i, 15);                    
                }                
            }
            if(j % 20 == 0 && j>20){//Check if there are mazes with 20 cols for this dimension
                if(evolvingants.Main.verbose){
                    System.out.println("\n++++++++++++++++++++++++"+j+"_20++++++++++++++++++++++++\n");
                }
                for(int i = 0; i<10;i++){
                    BulkExperiments.trialEvolvingAnts(XMLParameters,"maze"+j+"_20_"+i+".mz", j, i, 20);                    
                }                
            }
            /*
             * Delete unziped file
             */
            if(!FileHandling.deleteDir(new File(evolvingants.Main.pathToMazes+j))){
                throw new Exception("Error deleting unzip folder in path: "+evolvingants.Main.pathToMazes+j);
            }else if(evolvingants.Main.verbose){
                System.out.println("Deleted unzip folder in path: "+evolvingants.Main.pathToMazes+j + " successfully");
            }
        }
    }
    /*
     * Given the location of a maze, its dimension and a parameter set, run several trials with it.
     */
    public static void trialEvolvingAnts( ArrayList<String> XMLParameters,String mazeFileName,int dimension,int type,int cols) throws Exception{
        int trials  = 100,
            solutionFound    = 0,
            solutionNotFound = 0,
            averageIters = 0;
            if(evolvingants.Main.verbose){
                System.out.println("Testing Maze: "+mazeFileName+", \ntrials:");
            }
            for(int i=0;i<trials;i++){
                if(evolvingants.Main.verbose){
                    System.out.print(i + ",");
                }
                /*
                 * Test a fix maze several times or trials
                 */
                EVA_MazeSolver AS = new EVA_MazeSolver(XMLParameters,evolvingants.Main.pathToMazes+dimension+"/"+mazeFileName,0,dimension-1);
                AS.solve();
                if(!AS.getBestSolution().isEmpty()){
                    solutionFound++;
                    averageIters = averageIters + AS.numberIterationsSolution;
                    //System.out.println("Solution found in "+AS.numberIterationsSolution+" iterations");
                    //System.out.println("Best Solution AS: " + AS.getBestSolution());
                    //System.out.println("Length of BestSolution AS:" + AS.getBestSolution().size());
                }else{
                    solutionNotFound++;
                    //System.out.println("===============Did not find a solution====================");
                }
            }
            if(evolvingants.Main.verbose){
                System.out.println("\nResults: Found:"+solutionFound+" avgIters = "+(double)((double)averageIters/(double)solutionFound)+", not found"+solutionNotFound);
            }
            evolvingants.tools.FileHandling.saveResults(evolvingants.Main.pathToResults, evolvingants.Main.resultFile, mazeFileName +"\t"+ dimension+"\t"+ cols+"\t"+ type + "\t" + solutionFound + "\t" + solutionNotFound + "\t" + (double)((double)averageIters/(double)solutionFound) + "\n");         
    }    
}
