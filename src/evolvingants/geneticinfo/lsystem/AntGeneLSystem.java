package evolvingants.geneticinfo.lsystem;

import GeneticAlgorithm.RandomGenerator;
import java.util.Vector;
/**
 * 
 * This class is a concrete implementation of GA_AntGene, i.e., an Ant's Genes,
 * and implements the representation of an of L-Systems.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class AntGeneLSystem extends evolvingants.geneticinfo.GA_AntGene{
    /* Axiom for the L_System*/
    protected String L_axiom;
    /* Rules for the L_System*/
    protected String[] L_rules;
    /* Alphabet fot the L_System*/
    protected String L_alphabet = "UDLR";
    /*
     * Constructor.
     */
    public AntGeneLSystem(int index) {
        super(index);
        this.initializeAxiom();
        this.initializeRules();
    }
    /*
     * Given a length, this function will return a String with random letter
     * from L_alphabet
     */
    protected String randomL_Letters(int length){
        /*Determine length of the alphabet*/
        int alphabetLength = this.L_alphabet.length();        
        /* Initialize the letters */
        String randomL_Letters = "";
        /* Fill the axiom with blank spaces*/
        for(int i=0;i<length;i++){
           randomL_Letters = " " + randomL_Letters;
        }
        /*Determine letters of the axiom*/
        StringBuilder buf = new StringBuilder(randomL_Letters);
        for(int i=0;i<length;i++){
            char L_letter = this.L_alphabet.charAt(RandomGenerator.Random(alphabetLength));            
            buf.setCharAt(i , L_letter);
        }
        /* Set the axiom*/
        return buf.toString();        
    }
    
    /*
     * Make a random axiom from the alphabet
     */
    protected void initializeAxiom(){
        /*Determine length of the axiom. */
            /*Length is this number plus 1 but we draw the number minus 1 to make sure is not zero*/
        int axiomLength = RandomGenerator.Random(3);
        //Make sure the length is not zero
        axiomLength++;
        this.L_axiom = this.randomL_Letters(axiomLength);
    }
    /*
     * Make random rules, one for each possible letter of the alphabet.
     */
    protected void initializeRules(){
        /*Determine length of the alphabet*/
        int alphabetLength = this.L_alphabet.length();
        /*Initialize the rules array*/
        this.L_rules = new String[alphabetLength];
        /*For each possible rule, create a random string*/
        for(int i=0;i<alphabetLength;i++){
            this.L_rules[i] = this.randomL_Letters(RandomGenerator.Random(3)+1);
        }
    }
    /*
     * Run the LSystem a fix number of iterations
     */
    public String runLSystem(int iterations){
        int numberRules = this.L_alphabet.length();
        String result = this.getAxiom(),temp = "";
        //For each iteration
        for(int i=0;i<iterations;i++){
            temp = "";
            //For each character of the string
            for(int k=0;k<result.length();k++){
                //For each rule
                for(int j=0;j<numberRules;j++){
                    if(result.charAt(k) == this.L_alphabet.charAt(j)){
                        temp = temp + this.L_rules[j];
                    }else{
                        StringBuilder x = new StringBuilder(result.charAt(k));
                        temp = temp + x.toString();
                    }
                }
            }
            result = temp;
        }
        return result;
    }
    /*
     * Concrete implementation of this method. Calculate proportions in LSystem
     */
    @Override
    public double[] calculateProportion(int iterations){
        String evolvedLString = this.runLSystem(iterations);
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
    /*
     * Getters.
     */
    public String getAxiom(){
        return this.L_axiom;
    }
    public String getRule(int i){
        return this.L_rules[i];
    }
    /*
     * Setters
     */
    public void setRules(String[] L_rules){
        this.L_rules = L_rules;
    }
    public void setRule(String L_rule,int i){
        this.L_rules[i] = L_rule;
    }
    /*
     * Change a rule randomly. Used primarly to mutate an individual
     */
    public void changeRandomRule(int i){
        this.setRule(this.randomL_Letters(RandomGenerator.Random(3)+1), i);
    }
    @Override
    public String toString(){
        String rules = "";
        for(int i=this.L_alphabet.length()-1;i>=0;i--){
            rules = "\n\t rule_"+i+" = "+this.L_alphabet.charAt(i)+ "->" +this.L_rules[i]+ "" + rules;
        }
        return "{Ant Genes:\n index \t= "+this.index+"\n rules: "+rules;
    }
    /*
     * Test a standalone LSystem in a particular Ant's gene.
     */
    public static void testLSystem(){
        /*
         * Set up the ant's gene with a particular axiom and rules
         * This example shows an implementation of Lindenmayer's original 
         * L-system for modelling the growth of algae. More on that can be found here:
         * http://en.wikipedia.org/wiki/L-system
         */
        AntGeneLSystem L = new AntGeneLSystem(-1);
        L.L_axiom = "A";
        L.L_alphabet = "AB";
        L.L_rules = new String[2];
        L.L_rules[0]="AB";
        L.L_rules[1]="A";
        System.out.println(L);
        for(int i=0;i<10;i++){
            String result = L.runLSystem(i);
            System.out.println("Result up to "+i+" generations:"+result);
        }
    }
}
