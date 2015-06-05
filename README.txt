____________________________________________________________________
-EvolvingAnts (EVA), version 1-
Author: Enrique Areyan
You can write me at: enrique3@gmail.com
More info at: http://www.enriqueareyan.com/?q=evolvingants
____________________________________________________________________
-TO RUN EVA-
Go to the dist/ folder and run the following command: java -jar EvolvingAnts.jar -h
The system will output further instructions.
____________________________________________________________________
-CONTENTS OF THIS ZIP-
This zip file contains the following directories:

EVA-dist
	|-- config
	|-- dist
	|-- mazes_deterministic
	|-- results
	|-- src
	|    |-- antframework 
	|    |-- evolvingants 
	|    |-- GeneticAlgorithm 

1) In config/ you will find the original .xml files which I use to test EVA. You can use these to pass whatever parameters you like to EVA.
2) In dist/ you will find the jar file for EVA. To run it, simply do java -jar EvolvingAnts.jar. If you pass the -h option you get this information on how to use the jar file:
--
java -jar EvolvingAnts.jar [start] [end] [config.xml] [-nv|-v]

start is the dimension of the mazes you want to start testing
end is the dimension of the mazes you want to end testing
config.xml is the name of the .xml file use to read all the EVA parameters
-nv means no verbose, -v means verbose
all arguments required except [-nv|-v].
--
3) mazes_deterministic/ contains the mazes used to test EVA (please refer to the original paper at http://www.enriqueareyan.com/sites/default/files/evolvingants/EvolvingAntsEnriqueAreyan.pdf for more information
4) results/ contains the results of the algorithm for each configuration in the .xml file
5) src/ contains EVA's Java source code. I included the antframework here for your convenience (more on that at http://www.enriqueareyan.com/?q=antframework)
