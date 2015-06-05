package GeneticAlgorithm;

/**
 * 
 * This class is an abstract environment for the Genetic Algorithm Metaheuristic.
 * It must be extended by a concrete implementation
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public abstract class Environment {
    /* 
     * Population size
     */
    protected int populationSize;
    /* 
     * The actual population is stored as an array of individuals
     */
    protected Individual[] Population;
    /* 
     * Best individual 
     */
    protected Individual Best;    
     /*
     * Constructor
     */
    public Environment(int populationSize){
        this.populationSize = populationSize;
        this.Population = new Individual[this.populationSize];
        this.Best = new Individual("", -1);
    }
    public Environment(){
        
    }
    /*
     * Fitness function 
     */
    abstract protected int fitness(Individual I);    
    /*
     * Get Individual
     */
    public Individual getIndividual(int i){
        return this.Population[i];
    }
    /* 
     * Set Individual
     */
    public void setIndividual(Individual I,int pos){
        this.Population[pos] = I;
    }
    /*
     * Get Population
     */
    public Individual[] getPopulation(){
        return this.Population;
    }
    /*
     * Get Population size
     */
    public int getPopulationSize(){
        return this.populationSize;
    }
    /* 
     * Replace entire population
     */
    public void replacePopulation(Individual[] NewPopulation){
        this.Population = NewPopulation;
    }    
    /*
     * Order population according to fitness
     */
    protected void order(){
        int fitnessValue = 0;
        for(int i=0;i<this.populationSize;i++){
            fitnessValue = this.fitness(this.getIndividual(i));
            this.getIndividual(i).setFitness(fitnessValue);
            if(fitnessValue > this.Best.getFitness()){
                //This is best than the other
                this.Best = new Individual(this.getIndividual(i).toString(),fitnessValue);
            }
        }
        java.util.Arrays.sort(this.getPopulation(),new Individual("",""));
        //this.printFirst();
    }
    /*
     * Fitness sum of all the population
     */
    protected int sumFitness(){
        int fitnessTotalValue = 0;
        for(int i=0;i<this.populationSize;i++){
            fitnessTotalValue += this.getIndividual(i).fitness;
        }
        return fitnessTotalValue;
    }
    /*
     * Print Population -  for debugging purposes
     */
    public void print(){
        for(int i=0;i<this.populationSize;i++){
            System.out.println(this.Population[i].I + ", f = "+this.Population[i].fitness);
        }
    }
    /*
     * Print first individual - if the population is ordered this is the same as printing the best
     */
    public void printFirst(){
        System.out.println("\r"+this.Population[0]+ ", f = "+this.Population[0].fitness);
    }
}
