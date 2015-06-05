package evolvingants.geneticinfo;

import evolvingants.EVA_Ant;

/**
 * 
 * This class represent a population of Ants' genes to be used by the Genetic Algorithm.
 * The class extends a general Environment class of the abstract Genetic Algorithm.
 * 
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class GA_Environment extends GeneticAlgorithm.Environment{

    /*
     * Constructor.
     */
    public GA_Environment(int populationSize) {
        super(populationSize);
    }
  
    /*
     * Given an array of ants' object, take each ant and copy
     * its genes to the population of this Environment.
     */
    public void InitializePopulation(EVA_Ant[] AntsArray){
        for(int i=0;i<AntsArray.length;i++){
            this.Population[i] = AntsArray[i].getGenes();
        }        
    }
    /*
     * Fitness function 
     */
    @Override
    protected int fitness(GeneticAlgorithm.Individual I){
        /*
         * TODO: this fitness function is seeking the longer paths.
         * I may want not the longer path, but those that actually work.
         */
        return ((GA_AntGene)I).getSolutionSize();
    }
    
    /*
     * Print Population -  for debugging purposes
     */
    @Override
    public void print(){
        System.out.println("================================================");
        for(int i=0;i<this.populationSize;i++){
            System.out.println(((GA_AntGene)this.Population[i]).getIndex() + ", f = "+((GA_AntGene)this.Population[i]).getSolutionSize());
        }
        System.out.println("================================================");
    }    
}
