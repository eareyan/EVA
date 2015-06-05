package GeneticAlgorithm.Strings;
import GeneticAlgorithm.*;

/**
 * 
 * This is an example of a concrete implementation of a Genetic Algorithm 
 * that evolves strings to match a target string.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class GA_Strings extends GeneticAlgorithm.Metaheuristic{
    /*
     * Constructor
     */
    public GA_Strings(Environment_Strings Population, int maxNumberIterations, double mutationRate, double crossoverRate,int eliteNumber,int targetValue){
        super(Population,maxNumberIterations,mutationRate,crossoverRate,eliteNumber,targetValue);
    }
    /*
     * Reproduction logic. This function takes two strings of the population
     * and combines them at a random point. It generate two offsrpings
     * that then replace both parents.
     */
    @Override
    protected void reproduce(){
        int crossoverpoint=0;
        for(int i=this.eliteNumber+1;i<this.Population.getPopulationSize()-1;i=i+2){
            if(RandomGenerator.Random()<this.crossoverRate){
                //Randomly choose the crossover point
                crossoverpoint = RandomGenerator.Random(this.Population.getIndividual(i).getIndividualLength());
                //Create offspring
                Individual offspring1 = new Individual_String(this.Population.getIndividual(i).getIndividualGenotype().substring(0,crossoverpoint) + "" + this.Population.getIndividual((i+1)).getIndividualGenotype().substring(crossoverpoint));
                Individual offspring2 = new Individual_String(this.Population.getIndividual((i+1)).getIndividualGenotype().substring(0,crossoverpoint) + "" + this.Population.getIndividual(i).getIndividualGenotype().substring(crossoverpoint));
                //Let these new individuals take the place of their parents in the population
                this.Population.setIndividual(offspring1, i);
                this.Population.setIndividual(offspring2, (i+1));
            }
        }
        /*
        System.out.println("After crossover");
        this.Population.print();
        System.out.println("--------"); 
         */
    }
    /*
     * Mutate logic. It goes through each character of the string and
     * changes it with a certain probability (usually very low.
     */
    @Override
    protected void mutate(){
        //Get population size
        int populationSize = this.Population.getPopulationSize();
        //For each individual of the population
        for(int i=0;i<populationSize;i++){
            //Get population size
            int individualSize = this.Population.getIndividual(i).getIndividualLength();
            //For each gene in the individual
            for(int j=0;j<individualSize;j++){
                //Test to see if the mutation rate is satisfied
                if(this.mutationRate > RandomGenerator.Random()){
                    //Mutate this gene single random gene
                    this.Population.getIndividual(i).changeGene(j);
                }
            }
        }
    }    
}
