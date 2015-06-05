package evolvingants;

import evolvingants.geneticinfo.GA_Environment;
import antframework.common.Graph;
import antframework.common.Enviroment;
import antframework.algorithms.*;
import antframework.common.Ant;
import evolvingants.geneticinfo.EVA_GeneticAlgorithm;
import evolvingants.geneticinfo.GA_AntGene;
import evolvingants.tools.XML;
import java.util.ArrayList;
import java.util.Vector;
/**
 * 
 * This class ties together several objects in order to solve a Maze
 * Using ACO and GA with L-System. It extends AntSystem, which is an 
 * AntFramework's algorithm.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class EVA_MazeSolver extends antframework.algorithms.AntSystem{
    /**
     * Genetic Algorithm Parameters
     */
    protected GA_Environment GeneticEnvironment;
    protected int GAMaxNumberIterations = 5000,GAEliteNumber = 0;
    protected double GAMutationRate = 0.05, GA_crossoverRate = 0.6;
    protected String GAType = "";
    /*
     * EA parameters
     */
    protected double acoImportance = 0.5, agImportance = 0.5;    
    /*
     * Constructors
     */
    public EVA_MazeSolver(){}

    public EVA_MazeSolver(Enviroment E) {
        super(E);
    }
    /*
     * Main Function. Solve a Maze using both ACO and GA.
     */
    public EVA_MazeSolver( ArrayList<String> XMLParameters,String graphLocation, int problemInitialNode, int problemDestinationNode) throws Exception{

        /* Graph loaded from file*/
        //Graph G = new Graph("/Users/enriqueareyan/Documents/Universidad/Tesis/Framework/Graphs/SP/Problem1.txt");
        Graph G = new Graph(graphLocation);
        /* Set enviroment */
        AS_Environment Env = new AS_Environment( G /* Graph */ , true /* random pheromone trail */);
        this.setParam(EVA_MazeSolver.initialNode  , problemInitialNode);
        this.setParam(EVA_MazeSolver.destinationNode , problemDestinationNode);        
        /*ACO Parameters*/
        this.setParam(EVA_MazeSolver.maxNumIterations , Integer.parseInt(XMLParameters.get(0)));
        this.setParam(EVA_MazeSolver.pheromonesEvaporationRate, Double.parseDouble(XMLParameters.get(1)));
        this.setParam(EVA_MazeSolver.alpha, Double.parseDouble(XMLParameters.get(2)));
        this.setParam(EVA_MazeSolver.beta,  Double.parseDouble(XMLParameters.get(3)));
        Env.setNumberOfAnts(Integer.parseInt(XMLParameters.get(4)));
        /*Initialize genes' type*/
        this.GAType = XMLParameters.get(5);
        /*GA Parameters*/
        this.GAMaxNumberIterations  = Integer.parseInt(XMLParameters.get(6));
        this.GAEliteNumber          = Integer.parseInt(XMLParameters.get(7));
        this.GAMutationRate         = Double.parseDouble(XMLParameters.get(8));
        this.GA_crossoverRate       = Double.parseDouble(XMLParameters.get(9));
        /*EA Parameters*/
        this.acoImportance  = Double.parseDouble(XMLParameters.get(10));
        this.agImportance   = Double.parseDouble(XMLParameters.get(11));
        /* set ants of the type we want */
        Env.setAnts(this.GAType);
        /* set enviroment*/
        this.setE(Env);
        /* 
         *   Set Genetic Algorithm with these same ants
         */        
        GA_Environment P = new GA_Environment(Env.getNumberOfAnts());
        P.InitializePopulation((EVA_Ant[])Env.getAnts());
        this.GeneticEnvironment = P;
    }
    /*
     * The daemon action in this case is get the population of ants that finished
     * doing one iteraction of the ACO algorithm and run the appropriate genetic algorithm to
     * pick the best ones for survival. Before doing this, we need to report the
     * performance of each ant which is the length of the path constructed. Thus,
     * a phenotype can be seen as the length of the path constructed by the ant.
     */
    @Override
    public void daemonActions(){
        /* Store the performance of the Ant in the Ant's gene*/
        for(int i=0;i<this.numberOfAnts;i++){
            GA_AntGene gene = ((EVA_Ant)this.Ants[i]).Genes;
            gene.setSolutionSize(((Ant)this.Ants[i]).getSolutionSize());
            gene.setsolution(((Ant)this.Ants[i]).copySolution());
            //System.out.println("Ant:"+ ((GA_Ant)this.Ants[i]).getIndex()+ " solution: "+((GA_Ant)this.Ants[i]).getSolution());
        }
        /* Run the Genetic algorithm according to the type of GA we are using*/
        EVA_GeneticAlgorithm GA = EVA_GeneticAlgorithm.GA_Factory(this.GAType,this.GeneticEnvironment,this.GAMaxNumberIterations,this.GAMutationRate,this.GA_crossoverRate,this.GAEliteNumber);
        GA.run();
    }    
    @Override
    public double heuristicInfo(double number){
        return 1/number;
    }
    @Override
    public double f(Vector<Integer> Solution){
        int numberSolutionNodes = Solution.size();
        if(numberSolutionNodes != 0 && Solution.elementAt((numberSolutionNodes - 1)) != this.Parameters.get(AntSystem.destinationNode).intValue()){
            return Double.MAX_VALUE;
        }else{
            return super.f(Solution);
        }
    }
    @Override
    public Vector<Integer> constrains(int i, Vector<Integer> currentSolution){
        int cols = this.Graph.getM().getColumns();
        Vector<Integer> adjacents = new Vector<Integer>();
        //Calculate adjancent nodes
        for(int j=0; j < cols;j++){
            if(this.Graph.getM().position(i, j) < Integer.MAX_VALUE){
                //Is adyacent
                //Check to see if it is already in the solution
                if(currentSolution.indexOf(j)==-1){
                    adjacents.add(j);
                }
            }
        }
        return adjacents;
    }
    @Override
    public int decisionRule(int i , Vector currentSolution){
        /* counter of the number of times a node have been triying to selected a next node and maximun number of tries allowed*/
        int counter = 0,allowedNumberOfTries = 20; //allowedNumberOfTries = 2 * this.getNumberOfNodes(); 
        /* Get possible nodes */
        Vector<Integer> possibleNodes = this.constrains(i, currentSolution);
        //System.out.println("from "+i+" to "+possibleNodes);
        int cantPossibleNodes = possibleNodes.size();
        /* check if there is at least 1 possible node to be selected */
        if(cantPossibleNodes <= 0 ){
            //There aren't any possible next candidates, therefore
            /*
             * PENALIZE THOSE ANT THAT HIT A WALL
             */
            //System.out.println("Hit a dead end");
            return -1;
        }
        /* Get alpha (desicion rule) and beta (heuristic information) parameters */
        double localAlpha   =   this.Parameters.get(AntSystem.alpha);
        double localBeta    =   this.Parameters.get(AntSystem.beta);
        double total_pheromone = 0;
        double ACODecision = 0;
        double GADecision = 0;
        
        if(this.acoImportance + this.agImportance != 1){
            try {
                throw new Exception("ACODecision + GADecision must be equal to one");
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        /*
         * Calculate total probability for the ACO decision
         */
        for(int j=0; j < cantPossibleNodes;j++){
            total_pheromone += Math.pow(this.Pheromones.position(i, possibleNodes.get(j)), localAlpha)  * Math.pow(this.heuristicInfo(this.Graph.getM().position(i, possibleNodes.get(j))), localBeta);
        }
        /* Get this Ant's gene */
        EVA_Ant ant= (EVA_Ant)this.currentAnt;
        GA_AntGene Genes= ant.getGenes();
        /* Grow this Ant's LSystem. */
        double[] proportion = Genes.calculateProportion(4);
        //Construct a solution path
        do{
            for(int j=0; j < cantPossibleNodes; j++){
                /*
                 * Process genetic information
                 */
                if(i+1 == possibleNodes.get(j)){ /* Possible node is right from node i*/
                    GADecision = proportion[3];
                }else if(i-1 == possibleNodes.get(j)){/* Possible node is left from node i*/
                    GADecision = proportion[2];
                }else if(possibleNodes.get(j)>i){/* Possible node is down from node i*/
                    GADecision = proportion[1];                     
                }else{/* Possible node is up from node i*/
                    GADecision = proportion[0];                    
                }
                /* 
                 * Process ACO information (pheromone Trail)
                 */ 
                ACODecision = ((Math.pow(this.Pheromones.position(i, possibleNodes.get(j)), localAlpha) * Math.pow(this.heuristicInfo(this.Graph.getM().position(i, possibleNodes.get(j))) , localBeta))  / total_pheromone);
               /*
                * Make a decision to move (Average of both information).
                */
                if(Math.random() <= /* GA Rule*/ this.agImportance * GADecision + /* Ant Rule*/ this.acoImportance * ACODecision){
                    return possibleNodes.get(j);
                }
            }
            /* check to see if the maximum number of tries have been reached */
            counter = counter + cantPossibleNodes;
            if(counter >= allowedNumberOfTries){
                //System.out.println("Exhasuted allowed tries");
                return -1;
            }
        }while(true);
    }
}