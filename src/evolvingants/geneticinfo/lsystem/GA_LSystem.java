package evolvingants.geneticinfo.lsystem;

import GeneticAlgorithm.Environment;
import GeneticAlgorithm.RandomGenerator;
/**
 * 
 * This class is a concrete implementation of the Genetic Algorithm Metaheuristic
 * and implements the reproduction and mutation of L-Systems.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class GA_LSystem extends evolvingants.geneticinfo.EVA_GeneticAlgorithm{

    /*
     * Constructor.
     */
    public GA_LSystem(Environment Population, int maxNumberIterations, double mutationRate, double crossoverRate, int eliteNumber) {
        super(Population, maxNumberIterations, mutationRate, crossoverRate, eliteNumber);
    }
    /*
     * Implement reproduction of the L-System. The LSystem for Evolving Ants has
     * 4 rules. Reproduction will, given two parents, create two offsrping by
     * randomly selecting rules from both parents. First, a random number R betwen 0 and 4
     * is draw. The rules copied from one the first parent are those between 0 and R, and
     * the rules from the second parent are between R+1 and 4.
     */
    @Override
    protected void reproduce(){
        //Initialize parameters
        int crossoverpoint=0;
        AntGeneLSystem parent1,parent2;
        for(int i=this.eliteNumber+1;i<this.Population.getPopulationSize()-1;i=i+2){
            if(RandomGenerator.Random()<this.crossoverRate){
                //Get parents
                parent1 = ((AntGeneLSystem)this.Population.getIndividual(i));
                parent2 = ((AntGeneLSystem)this.Population.getIndividual(i+1));
                //Randomly choose the crossover point from the four possible rules
                crossoverpoint = RandomGenerator.Random(3);
                crossoverpoint++;
                String[] newGenes_L_rules_Offspring1 = new String[4];
                String[] newGenes_L_rules_Offspring2 = new String[4];
                //Create offspring.
                //Copy rules from parent 1
                for(int j=0;j<crossoverpoint;j++){
                    newGenes_L_rules_Offspring1[j] = parent1.getRule(j);
                    newGenes_L_rules_Offspring2[j] = parent2.getRule(j);
                }
                //Copy rules from parent 2
                for(int k=crossoverpoint;k<4;k++){
                    newGenes_L_rules_Offspring1[k] = parent2.getRule(k);
                    newGenes_L_rules_Offspring2[k] = parent1.getRule(k);
                }
                //Replace parents with two offsprings
                parent1.setRules(newGenes_L_rules_Offspring1.clone());
                parent2.setRules(newGenes_L_rules_Offspring2.clone());
            }   
        }
    }
    /*
     * Implement mutation of the L-System. In this context, a mutation
     * is to pick one of the 4 rules at random and completly change it.
     */
    @Override
    protected void mutate(){
        //Get population size
        int populationSize = this.Population.getPopulationSize();
        //For each individual of the population
        for(int i=0;i<populationSize;i++){        
            //Test to see if the mutation rate is satisfied
            if(this.mutationRate > RandomGenerator.Random()){
                //Mutate a random rule
                ((AntGeneLSystem)this.Population.getIndividual(i)).changeRandomRule(RandomGenerator.Random(3));
            }
        }        
    }
}
