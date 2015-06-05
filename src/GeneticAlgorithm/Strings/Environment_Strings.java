package GeneticAlgorithm.Strings;
import GeneticAlgorithm.*;

/**
 * 
 * This is an example of a concrete implementation of an environment for
 * a Genetic Algorithm that evolves strings to match a target string.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class Environment_Strings extends Environment{
    /*
     * Target String
     */
    protected String Target;
    
    /*
     * Constructor. It receives the size of the population
     * and initialize a random population.
     */
    public Environment_Strings(int populationSize){
        super(populationSize);
    }
    /*
     * Another Constructor in wich one can choose whethear or not
     * to initialize the population
     */
    public Environment_Strings(int populationSize,boolean initializePop) {
        super(populationSize);
        if(initializePop){
            this.InitializePopulation();
        }
    }
    /*
     * Yet another constructor. It receives the size of the population and the
     * target string and creates an initial population.
     */
    public Environment_Strings(int populationSize,String Target){
        super(populationSize);
        this.setTarget(Target);
        this.InitializePopulation();
    }
    /*
     * Getters & Setters
     */
    public void setTarget(String Target){
        this.Target = Target;
    }
    public String getTarget(){
        return this.Target;
    }
    public int getTargetLength(){
        return this.Target.length();
    }
    /*
     * Random Initialize Population
     */
    public void InitializePopulation(){
        for(int i=0;i<this.populationSize;i++){
            //Add a randomly generated individual to the population
            this.Population[i] = new Individual_String(RandomGenerator.RandomString(this.Target.length()) /*Match the individual length*/ , -1/*We don't know this individual fitness*/);
        }
    }
    /*
     * The fitness function simply calculates how many characters matches the target string.
     */
    @Override
    protected int fitness(Individual I){
        int individualLength = I.getIndividualLength();
        int fitness = 0;
        //Loop through the individual's genes
        for(int i=0;i<individualLength;i++){
            //Compare to target string
            if(I.getIndividualGene(i) == this.Target.charAt(i)){
                //gene match, increase fitness
                fitness++;
            }
        }
        return fitness;
    }    
}
