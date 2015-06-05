package evolvingants;

import evolvingants.geneticinfo.lsystem.AntGeneLSystem;
import evolvingants.geneticinfo.GA_AntGene;
import evolvingants.geneticinfo.simple.AntGeneSimple;

/**
 * 
 * This class represent an individual Ant to work with both the ACO and GA.
 * It extends the basic ant object of the antframework, and incorporates
 * the ant's genes.
 * 
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */public class EVA_Ant extends antframework.common.Ant{
    
    /*The ant's genes*/
    protected GA_AntGene Genes;

    /*
     * Constructor
     */
    public EVA_Ant(int index, int initialPos, String GeneType){
        //Create the ant object
        super(index, initialPos);
        //Set this ant's genes
        if("LSystem".equals(GeneType)){
            this.Genes = new AntGeneLSystem(index);
        }else if("Simple".equals(GeneType)){
            this.Genes = new AntGeneSimple(index);
        }else{
            System.err.println("Gene Type = "+GeneType+" unrecognizable");
        }
    }
    /*
     * Getters
     */
    //Genes
    public GA_AntGene getGenes(){
        return this.Genes;
    }
    //Index
    public int getIndex(){
        return this.index;
    }
    /*
     * String representation of an Ant. It shows the index of the Ant in the population
     * and the ant's Genes.
     */
    @Override
    public String toString(){
        return "{i = "+this.index+", solution: "+this.solution.toString()+", genes: "+this.Genes.toString()+"}";
    }
}
