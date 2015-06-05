package evolvingants;

/**
 * 
 * This class contains the environment for the ACO algorithm.
 * THe difference with the standard environment is that this one contains
 * specialized ants that work for both ACO and GA.
 *
 * @version 1
 * @author Enrique Areyan, enrique3 at gmail.com
 *
 */
public class AS_Environment extends antframework.common.Enviroment{
    /*
     * Constructor
     */
    public AS_Environment(antframework.common.Graph G, boolean randomPheromones) {
        super(G, randomPheromones);
    }
    
    /**
     *	Sets the array of ants of this environment. It replace the normal ants with specialized
     *  ants for the genetic algorithm
     */
    public void setAnts(String type) throws antframework.common.AntFrameworkException{
         if(this.numberOfAnts <= 0){
            throw new antframework.common.AntFrameworkException("The number of ants cannot be less than or equal to zero (0)");
        }
        this.Ants = new EVA_Ant[this.numberOfAnts];
	for(int i = 0; i < this.numberOfAnts; i++){
            Ants[i] = new EVA_Ant(i,0,type);
        }
    }    
}
