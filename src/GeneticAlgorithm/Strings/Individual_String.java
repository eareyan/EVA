package GeneticAlgorithm.Strings;
import GeneticAlgorithm.Individual;

/**
 * 
 * This is an example of a concrete implementation of an individual of 
 * the Genetic Algorithm that evolves strings to match a target string.
 * The individual is simply an String.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class Individual_String extends Individual{
    
    public Individual_String(String I, int fitness) {
        super(I, fitness," abcdefghijklmnopqrstuvwxyz");
    }

    public Individual_String(String I) {
        super(I," abcdefghijklmnopqrstuvwxyz");
    }
    public Individual_String(String I,String Alleles){
        super(I,Alleles);
    }
}
