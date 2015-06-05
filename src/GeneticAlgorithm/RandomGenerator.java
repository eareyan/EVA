package GeneticAlgorithm;
import java.util.Random;

/**
 * 
 * This class encapsulates the logic of Random Generators for both 
 * Strings and numbers. It is used extensively in the Genetic Algorithm.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class RandomGenerator {
    /*
     * Generates an random integer number between 0 and n
     */
    public static int Random(int n){
        Random generator = new Random();
        return generator.nextInt(n);
    }
    /*
     * Generates a random, uniformily distributed real number between 0 and 1.
     */
    public static double Random(){
        //Random generator = new Random();
        //return generator.nextDouble();
        return Math.random();
    }
    /*
     * Generates a random string of length n, with the standard
     * alphabet (all lower case) and a blank space.
     */
    public static String RandomString(int n){
        String dict = new String(" abcdefghijklmnopqrstuvwxyz");
        int lengthDict = dict.length();
        char[] c = new char[n];
        for(int i=0;i<n;i++){
            c[i] = dict.charAt(RandomGenerator.Random(lengthDict));
        }
        return new String(c);
    }
}
