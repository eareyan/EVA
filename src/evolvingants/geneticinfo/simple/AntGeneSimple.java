package evolvingants.geneticinfo.simple;

import GeneticAlgorithm.RandomGenerator;

/**
 * 
 * This class is a concrete implementation of GA_AntGene, i.e., an Ant's Genes,
 * and implements the representation of a naive genes.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class AntGeneSimple extends evolvingants.geneticinfo.GA_AntGene{
    protected String LettersGene;
    protected String Alphabet = "UDLR";
    /*
     * Constructor
     */
    public AntGeneSimple(int index) {
        super(index);
        this.initializeGene();
    }
    /*
     * Change one letter at random
     */
    public void mutate(){
        StringBuilder buf = new StringBuilder(this.LettersGene);
        buf.setCharAt(RandomGenerator.Random(100) , this.Alphabet.charAt(RandomGenerator.Random(4)));
        this.setGene(buf.toString());
    }
    /*
     * Initializa Gene
     */
    protected void initializeGene(){
        int length = 100;
        
        String random_Letters = "";
        /*Set a random letter to be the gene*/
        for(int i=0;i<length;i++){
           random_Letters = " " + random_Letters;
        }        
        StringBuilder buf = new StringBuilder(random_Letters);
        for(int i=0;i<length;i++){
            buf.setCharAt(i , this.Alphabet.charAt(RandomGenerator.Random(4)));
        }
        /* Set the gene*/
        this.LettersGene = buf.toString();
    }
    public void setGene(String geneLetters){
        this.LettersGene = geneLetters;
    }
    /*
     * Proportion should be simple.
     */
    @Override
    public double[] calculateProportion(int iterations){
        String evolvedLString = this.LettersGene;
        //System.out.println(evolvedLString);
        double total=evolvedLString.length(),cantU=0,cantD=0,cantL=0,cantR=0;
        for(int k=0;k<total;k++){
            switch(evolvedLString.charAt(k)){
                case 'U':
                        cantU++;
                    break;
                case 'D':
                        cantD++;
                    break;
                case 'L':
                        cantL++;
                    break;
                case 'R':
                        cantR++;
                    break;
            }
        }
        double result[] = new double[4];
        result[0] = cantU/total;
        result[1] = cantD/total;
        result[2] = cantL/total;
        result[3] = cantR/total;
        return result;
    }
    
}
