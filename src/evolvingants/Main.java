package evolvingants;

import evolvingants.tools.*;
import GeneticAlgorithm.Strings.Environment_Strings;
import GeneticAlgorithm.Strings.GA_Strings;
import evolvingants.experiments.BulkExperiments;
import java.util.ArrayList;
/**
 * 
 * This class is the Main class of the EvolvingAnts project.
 * It contains the Main methods and other methods to run experiments.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class Main {
    /*
     * Path to root. Must be defined in the config file.
     */
    public static String root = "";
    /*
     * Path to Mazes' files. Must be defined in the config file. The path is root+pathToMazes.
     */
    public static String pathToMazes = "";
    /*
     * Path to Mazes' experiments results. Must be defined in the config file. The path is root+pathToResults.
     */
    public static String pathToResults = "";
    /*
     * Path to results. Must be defined in the config file
     */
    public static String resultFile = "";
    /*
     * Path to config folder. The config folder is assume to be in ../config always.
     */
    public static String pathToConfig = "../config/";
    /*
     * Name of the config file. Comes from command line.
     */
    public static String configFile = "";
    /*
     * Verbose. Allow or avoid printing anything on the terminal
     */
    public static boolean verbose = false;
    /**
     * Main function. It checks that all the necessary parameters were received through
     * the command line, and sets up the experiments.
     */
    public static void main(String[] args) throws Exception {
        /*
         * Parse terminal arguments.
         */
        if(args.length>0){
            if("-h".equals(args[0])){
                //Print help
                System.out.println("\nEvonvlingAnts");
                System.out.println("\n"
                        + "java -jar EvolvingAnts.jar [start] [end] [config.xml] [-nv|-v]\n\n"
                        + "start is the dimension of the mazes you want to start testing\n"
                        + "end is the dimension of the mazes you want to end testing\n"
                        + "config.xml is the name of the .xml file use to read all the EVA parameters\n"
                        + "-nv means no verbose, -v means verbose\n"
                        + "all arguments required except [-nv|-v].\n");
                System.exit(0);
            }
            if(args.length < 3){
                throw new Exception("There are at least three arguments needed to run the program, see -h");
            }
            int start   = Integer.parseInt(args[0].toString()),
                end     = Integer.parseInt(args[1].toString());
            Main.configFile = args[2];
            /*
             * Check verbose setting
             */
            if(args.length>3 && "-nv".equals(args[3])){
                Main.verbose = false;
            }else if(args.length>3 && "-v".equals(args[3])){
                Main.verbose = true;
            }
            /* Check XML File*/
            ArrayList<String> XMLParameters = XML.read(Main.pathToConfig+Main.configFile);
            XML.checkGeneralParameters(XMLParameters);
            /* Print variables if verbose mode is on*/
            if(Main.verbose){
                System.out.println("Searching root in:          " + Main.root);
                System.out.println("Searching pathToMazes in:   " + Main.pathToMazes);
                System.out.println("Searching pathToResults in: " + Main.pathToResults);
                System.out.println("Searching pathToConfig in:  " + Main.pathToConfig);
                System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=Evolving Ants+=+=+=+=+=+=+=+=+=+=+=+=");
            }
            /*Save a header info in the results file*/
            XML.printXMLHeader(XMLParameters);
            /*
             * Run experiments
             */            
            BulkExperiments.experimentsEvolvingAnts(XMLParameters,start,end);            
        }else{
            /*
             * We did not receive any parameters. Show this message.
             */
            System.out.println("Arguments missing... use -h for usage help");
        }
    }
    /*
     * Test a standalone Genetic Algorithm to match Strings to a given pattern.
     */
    public static void testGA(){
        //Set up environment
        Environment_Strings P = new Environment_Strings(300 /*Number of individuals in the population*/,
                                                        "this is an example string" /*Target String*/);
        //Set up parameters
        int maxNumberIterations = 300,eliteNumber = 25; 
        double mutationRate = 0.01, crossoverRate = 0.8;
        //Create the Genetic Algorithm object
        GA_Strings GAforStrings = new GA_Strings(P,maxNumberIterations,mutationRate,crossoverRate,eliteNumber,P.getTargetLength());
        GAforStrings.run();
    }
    /*
     * Generate a number of mazes automatically and saves it to files on disk.
     */    
    public static void generateMazes() throws Exception{
        /*
         * Generate random Mazes. The first line generates 10 mazes, starting with
         * dimension 10,20,...100, and for each dimension it create 10 mazes with
         * 5 cells per row.
         * The second line does the same but starting from 20 and havin 10 cells per row.
         *      GenerateMaze.bulkGenerateMazes(10, 10, 10, 10, 5,true);
         *      GenerateMaze.bulkGenerateMazes(20, 10, 10, 10, 10,true);
         */
        GenerateMaze.bulkGenerateMazes(Main.pathToMazes,20,500, 10, 9, 5, false);
        GenerateMaze.bulkGenerateMazes(Main.pathToMazes,20,500, 10, 9, 10,false);        
        GenerateMaze.bulkGenerateMazes(Main.pathToMazes,30,500, 10, 9, 15,false);
        GenerateMaze.bulkGenerateMazes(Main.pathToMazes,40,500, 10, 9, 20,false);
    }
}