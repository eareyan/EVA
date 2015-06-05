package GeneticAlgorithm;

/**
 * 
 * This class is an abstract conceptualization of the Genetic Algorithm Metaheuristic.
 * It must be extended by a concrete implementation that then needs to define
 * function for the crossover and mutation operations.
 * It implements an standard roulette wheel selection mechanism.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
abstract public class Metaheuristic {
    /*
     * Properties of the algorithm
     */
    /** If true, print debug information over System.out.println*/
    public static final boolean debug = false;    
    /*The population is just an array of Individuals*/
    protected Environment Population;
    /*This parameter limits the number of iterations of the genetic algorithm (GA)*/
    protected int maxNumberIterations;
    /* Rate of mutation */
    protected double mutationRate;
    /* Rate of crossover */
    protected double crossoverRate;
    /* Number of elite individual to be selected for each generation*/
    protected int eliteNumber = 0;
    /* Target value*/
    protected int targetValue = 0;

    /*
     * Constructor. Set the parameters.
     */
    protected Metaheuristic(Environment Population, int maxNumberIterations, double mutationRate, double crossoverRate,int eliteNumber,int targetValue) {
        this.Population = Population;
        this.maxNumberIterations = maxNumberIterations;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.eliteNumber = eliteNumber;
        this.targetValue = targetValue;
    }
    /*
     * Getters & setters
     */
    public Environment getEnvironment(){
        return this.Population;
    }
    
    /************************************
     * Procedures of the algorithm      *
     ***********************************/
    
    /*
     * Reproduce. It must be implemented by a child class.
     */
    abstract protected void reproduce();
    
    /*
     * Mutate. It must be implemented by a child class.
     */
    abstract protected void mutate();
    /*
     * Roulette Wheel Selection
     */
    protected void rouletteWheelSelection(){
        //Get population size
        int k=0,populationSize = this.Population.getPopulationSize();
        //Order population according to each individual fitness.
        this.Population.order();
        //this.Population.print();
        /*
         * Build the roulette. In this context a roulette is simply
         * an array fill with pointers to each individual in the population.
         * E.g., if you have a population say 1,2,3 with fitness f(1)=2,f(2)=1,f(3)=1
         * the roullete will be the array [1][1][2][3]. The dimension of the array is equal
         * to the sum of the population's fitness, an each individual will take a number of
         * cells equal to its fitness.
         */
        int Roulette[] = new int[this.Population.sumFitness()];        
        /*
         * Assign cell in the roulette to each individual according to each fitness.
         * Because we have previously ordered the array, we can stop when we have an individual with 
         * fitness equal zero. 
         */
        for(int i=0;i<populationSize && this.Population.getIndividual(i).fitness>0;i++){
            for(int j = 0;j<this.Population.getIndividual(i).fitness;j++){
                Roulette[k] = i;
                k++;
            }
        }
        /*
         * Make the new population only with the fittest individuals
         */
        Individual[] NewPopulation = new Individual[populationSize];
        for(int i=0;i<populationSize;i++){
            //System.out.println("Selecting individual("+i+"): " + RandomGenerator.Random(k));
            NewPopulation[i] = this.Population.getIndividual(Roulette[RandomGenerator.Random(k)]);
        }
        //Replace current population
        //System.out.println("Old population");
        //this.Population.print();
        this.Population.replacePopulation(NewPopulation);
        //System.out.println("New population - unsorted");
        //this.Population.print();
        this.Population.order();
        //System.out.println("New population - sorted");
        //this.Population.print();
        //System.out.println("BEST");
        //this.Population.printFirst();
    }

    /*
     * GA general procedure
     */
    public void run(){
        int i=0;
        boolean finish = false;
        for(i=0;i<this.maxNumberIterations && !finish;i++){
            /* Select most fit individuals */
            this.rouletteWheelSelection();
            /* Reproduce */
            this.reproduce();
            /* Mutate */
            this.mutate();
            if(this.Population.getIndividual(0).fitness == this.targetValue){
                //We find the solution
                finish = true;
            }
        }
        if(Metaheuristic.debug){
            System.out.println("Iter: "+i + ", Best = "+this.Population.Best.toString()+ ", f = "+this.Population.Best.getFitness());
        }    
    }
}