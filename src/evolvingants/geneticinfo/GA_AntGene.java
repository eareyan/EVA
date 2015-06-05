/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvingants.geneticinfo;

import java.util.Vector;

/**
 * 
 * This class is an concrete implementation of the Individual in the Genetic Algorithm Metaheuristic,
 * but still need to be extended to implement particular logic, e.g., LSystem. This class ties
 * together the ACO and GA frameworks to work in EVA.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public abstract class GA_AntGene extends GeneticAlgorithm.Individual{
    /* Number of the ant (that this gene belong to) in the population*/
    protected int index;
    /* Solution size. Equivalent to the phenotype*/
    protected int solutionSize;
    /* Store solution. Equivalent to the phenotype*/
    protected Vector<Integer> solution;
    /*
     * Constructor
     */
    public GA_AntGene(int index) {
        super();
        this.index = index;
        this.fitness = 1;
    }    
    /*
     * Getters.
     */
    public int getIndex(){
        return this.index;
    }
    public int getSolutionSize(){
        return this.solutionSize;
    }
    public Vector<Integer> getSolution(){
        return this.solution;
    }
    public void setSolutionSize(int solutionSize){
        this.solutionSize = solutionSize;
    }
    public void setsolution(Vector<Integer> solution){
        this.solution = solution;
    }
    /*
     * This method should be written by a concrete implementation of this class.
     * Given a number of iterations, calculate the proportion of the next decision.
     * This method must return an array of double with four positions mapping to
     * four possible actions in the maze, i.e. double[0] = up,double[1] = down,
     * double[2] = left,double[3] = right.
     */    
    public abstract double[] calculateProportion(int iterations);
}
