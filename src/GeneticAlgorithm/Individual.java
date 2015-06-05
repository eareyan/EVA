package GeneticAlgorithm;
import java.util.Comparator;

/**
 * 
 * This class represent an abstract implementation of an individual to be 
 * used by the Genetic Algorithm Metaheuristic.
 * 
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class Individual implements Comparator{
    /* 
     * Individuals are represented by a String
     */
    protected String I;
    /*
     * List of alleles
     */
    protected String Alleles;
    /*
     * Individual current fitness
     */
    protected int fitness;
    /*
     * Constructor
     */
    public Individual(){
    }
    /*
     * Constructor with String I and Alleles
     */
    public Individual(String I,String Alleles){
        this.I = I;
        this.Alleles = Alleles;
    }
    /*
     * Another Contructor to put a fitness value
     */
    public Individual(String I,int fitness){
        this.I = I;
        this.fitness = fitness;
    }
    /*
     * Yet another constructor to set Alleles
     */
    public Individual(String I,int fitness,String Alleles){
        this.I = I;
        this.fitness = fitness;
        this.Alleles = Alleles;
    }
    /*
     * Get Individual length
     */
    public int getIndividualLength(){
        return this.I.length();
    }
    /*
     * Get Individual gene
     */
    public char getIndividualGene(int i){
        return this.I.charAt(i);
    }
    /*
     * Get entire genotype
     */
    public String getIndividualGenotype(){
        return this.I;
    }
    /*
     * Get Individual fitness
     */
    public int getFitness(){
        return this.fitness;
    }
    /*
     * Set individual fitness
     */
    public void setFitness(int f){
        this.fitness = f;
    }
    /*
     * Change a single character from a list of possible characters.
     * This is tipically used for point mutation
     */
    public void changeGene(int posChange){
        //Get the position we are going to change
        //int posChange = RandomGenerator.Random(this.getIndividualLength());
        //Get the replace gene
        char geneReplace = this.Alleles.charAt(RandomGenerator.Random(this.Alleles.length()));
        //Change from the individual genotype one gene
        StringBuilder buf = new StringBuilder(this.I);
        buf.setCharAt(posChange , geneReplace);
        //Set the new genotype
        this.I = buf.toString();
    }    
    /*
     * Compare (descending order) two individuals by their fitness value
     */
    @Override
    public int compare(Object I1,Object I2){
        int f1 = ((Individual)I1).getFitness();
        int f2 = ((Individual)I2).getFitness();
        if(f1 > f2)
            return -1;
        else if(f1 < f2)
            return 1;
        else
            return 0;    
    }
    /*
     * To string method (simply the individual string representation)
     */
    @Override public String toString() {
        return this.I;
    }
}
