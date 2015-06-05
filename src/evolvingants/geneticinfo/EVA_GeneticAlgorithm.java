package evolvingants.geneticinfo;

import GeneticAlgorithm.Environment;
import evolvingants.geneticinfo.lsystem.GA_LSystem;
import evolvingants.geneticinfo.simple.GA_Simple;

/**
 * 
 * This class is a concrete implementation of the Genetic Algorithm Metaheuristic
 * and yet it is still abstract. Concrete implementation to deal with different
 * ant genes must extend this class
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
abstract public class EVA_GeneticAlgorithm extends GeneticAlgorithm.Metaheuristic{
    /*
     * Constructor.
     */
    public EVA_GeneticAlgorithm(Environment Population, int maxNumberIterations, double mutationRate, double crossoverRate, int eliteNumber) {
        super(Population, maxNumberIterations, mutationRate, crossoverRate, eliteNumber, -1);
    }
    @Override
    protected void reproduce() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void mutate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /*
     * Factory method to instantiate concrete Ant Genetic Algorithm.
     */
    public static EVA_GeneticAlgorithm GA_Factory(String GAType,Environment Population, int maxNumberIterations, double mutationRate, double crossoverRate, int eliteNumber){
        EVA_GeneticAlgorithm ret = null;
        if("LSystem".equals(GAType)){
            ret = new GA_LSystem(Population, maxNumberIterations, mutationRate, crossoverRate, eliteNumber);
        }else if("Simple".equals(GAType)){
            ret = new GA_Simple(Population, maxNumberIterations, mutationRate, crossoverRate, eliteNumber);
        }else{
            System.err.println("Genetic algorithm for type = "+GAType+", unrecognizable");
        }
        return ret;
    }
}
