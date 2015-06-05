/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvingants.geneticinfo.simple;

import GeneticAlgorithm.Environment;
import GeneticAlgorithm.RandomGenerator;

/**
 * 
 * This class is a concrete implementation of the Genetic Algorithm Metaheuristic
 * and implements the reproduction and mutation of population of Naive Genes.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class GA_Simple extends evolvingants.geneticinfo.EVA_GeneticAlgorithm{
    /*
     * Constructor.
     */
    public GA_Simple(Environment Population, int maxNumberIterations, double mutationRate, double crossoverRate, int eliteNumber) {
        super(Population, maxNumberIterations, mutationRate, crossoverRate, eliteNumber);
    }
    @Override
    protected void reproduce() {
        int crossoverpoint=0;
        for(int i=this.eliteNumber+1;i<this.Population.getPopulationSize()-1;i=i+2){
            if(RandomGenerator.Random()<this.crossoverRate){
                //Randomly choose the crossover point
                crossoverpoint = RandomGenerator.Random(100);
                //Create offspring
                String offspring1 = ((AntGeneSimple)this.Population.getIndividual(i)).LettersGene.substring(0,crossoverpoint) + "" + ((AntGeneSimple)this.Population.getIndividual((i+1))).LettersGene.substring(crossoverpoint);
                String offspring2 = ((AntGeneSimple)this.Population.getIndividual((i+1))).LettersGene.substring(0,crossoverpoint) + "" + ((AntGeneSimple)this.Population.getIndividual(i)).LettersGene.substring(crossoverpoint);
                //Let these new individuals take the place of their parents in the population
                ((AntGeneSimple)this.Population.getIndividual(i)).setGene(offspring1);
                ((AntGeneSimple)this.Population.getIndividual(i+1)).setGene(offspring2);
            }
        }
        
    }

    @Override
    protected void mutate(){
        //Get population size
        int populationSize = this.Population.getPopulationSize();
        //For each individual of the population
        for(int i=0;i<populationSize;i++){
            //Get population size
            int individualSize = 100;
            //For each gene in the individual
            for(int j=0;j<individualSize;j++){
                //Test to see if the mutation rate is satisfied
                if(this.mutationRate > RandomGenerator.Random()){
                    //Mutate this gene single random gene
                    ((AntGeneSimple)this.Population.getIndividual(i)).mutate();
                }
            }
        }
    }
    
}
