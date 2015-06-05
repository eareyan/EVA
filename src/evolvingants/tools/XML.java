
package evolvingants.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

/**
 * Taken and adapted from http://www.developerfusion.com/code/2064/a-simple-way-to-read-an-xml-file-in-java/
 */
public class XML {
    
    public static ArrayList<String> read(String configFileName){
        /* Return parameters as an ArrayList */
        ArrayList<String> Parameters = new ArrayList();
        /* Try to read the XML file */
        try {
            /* objects to work with XML */
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File(configFileName));
            // normalize text representation
            doc.getDocumentElement ().normalize ();
            // Get parameter elements
            NodeList listOfParameters = doc.getElementsByTagName("parameter");
            /* For each parameter element in the XML file*/
            for(int s=0; s<listOfParameters.getLength() ; s++){
                //Get data from parameter node
                Node parameterNode = listOfParameters.item(s);
                if(parameterNode.getNodeType() == Node.ELEMENT_NODE){
                    String type = "";
                    try{
                        type = parameterNode.getAttributes().getNamedItem("type").getNodeValue();
                    }catch(Exception e){
                        System.err.println("Missing type tag on the parameter element");
                        System.exit(1);                        
                    }
                    Element parameterElement = (Element)parameterNode;
                    /*Process ACO Parameters*/
                    if("ACO".equals(type)){
                        /*
                         * Add parameters
                         */
                        Parameters.add(0,getValue(parameterElement,"maxNumIterations"));
                        Parameters.add(1,getValue(parameterElement,"pheromonesEvaporationRate"));
                        Parameters.add(2,getValue(parameterElement,"alpha"));
                        Parameters.add(3,getValue(parameterElement,"beta"));
                        Parameters.add(4,getValue(parameterElement,"numberOfAnts"));
                    /*Process GA Parameters*/
                    }else if("GA".equals(type)){
                        /*
                         * Add parameters
                         */
                        Parameters.add(5,getValue(parameterElement,"geneType"));
                        Parameters.add(6,getValue(parameterElement,"maxNumIterations"));
                        Parameters.add(7,getValue(parameterElement,"eliteNumber"));
                        Parameters.add(8,getValue(parameterElement,"mutationRate"));
                        Parameters.add(9,getValue(parameterElement,"crossoverRate"));
                    /*Process EVA Parameters*/
                    }else if("EVA".equals(type)){
                        /*
                         * Add parameters
                         */
                        Parameters.add(10,getValue(parameterElement,"acoImportance"));
                        Parameters.add(11,getValue(parameterElement,"agImportance"));
                    /*Process General Parameters*/
                    }else if("General".equals(type)){
                        Parameters.add(12,getValue(parameterElement,"resultFile"));                        
                        Parameters.add(13,getValue(parameterElement,"root"));                        
                        Parameters.add(14,getValue(parameterElement,"pathToMazes"));                        
                        Parameters.add(15,getValue(parameterElement,"pathToResults"));                        
                    }else{
                        if(evolvingants.Main.verbose){
                            System.err.println("Unrecognizable XML member");
                        }
                    }
                }//end of if clause
            }//end of for loop with s var
        }catch (SAXParseException err) {
            System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
            System.out.println(" " + err.getMessage ());
        }catch (SAXException e) {
            Exception x = e.getException ();
            ((x == null) ? e : x).printStackTrace ();
        }catch (Throwable t) {
            t.printStackTrace ();
        }
        return Parameters;
    }
     /*
      * Given an elment an a tag name, return its value
      */
    protected static String getValue(Element elem,String name){
        NodeList nodeList = elem.getElementsByTagName(name);
        Element firstNameElement = (Element)nodeList.item(0);
        NodeList textFNList = firstNameElement.getChildNodes();
        return ((Node)textFNList.item(0)).getNodeValue().trim();
    }
    public static void printXMLHeader(ArrayList<String> Parameters) throws FileNotFoundException, IOException{
        /*Save a header info in the results file*/
        evolvingants.tools.FileHandling.saveResults(evolvingants.Main.pathToResults, evolvingants.Main.resultFile,
                "Mode XML Parameters\n"
                + "ACO"
                + "\nmaxNumIterations\t" + Parameters.get(0)
                + "\npheromonesEvaporationRate\t" + Parameters.get(1)
                + "\nalpha\t" + Parameters.get(2)
                + "\nbeta\t" + Parameters.get(3)
                + "\nnumberOfAnts\t" + Parameters.get(4)
                + "\nGA"
                + "\ngeneType\t" + Parameters.get(5)
                + "\nmaxNumIterations\t" + Parameters.get(6)
                + "\neliteNumber\t" + Parameters.get(7)
                + "\nmutationRate\t" + Parameters.get(8)
                + "\ncrossoverRate\t" + Parameters.get(9)
                + "\nEVA"
                + "\nacoImportance\t" + Parameters.get(10)
                + "\nagImportance\t" + Parameters.get(11)
                + "\nMaze\tdimension\tcol\t#\t#ok\t#no\tavg ok\n");

    }
    /*
     * Check that the general parameters, i.e., paths to working directories, exists.
     */
    public static void checkGeneralParameters(ArrayList<String> XMLParameters) throws Exception{
        //Check if the parameter result file is not empty
        if("".equals(XMLParameters.get(12))){
            throw new Exception("parameter resultFile in general parameter is mandatory... check XML file");
        }else{
            evolvingants.Main.resultFile = XMLParameters.get(12);
        } 
        //Check if the parameter root is not empty
        if("".equals(XMLParameters.get(13))){
            throw new Exception("parameter root in general parameter is mandatory... check XML file");
        }else{
            evolvingants.Main.root = XMLParameters.get(13);
        }
        //Check if the parameter pathToMazes is not empty
        if("".equals(XMLParameters.get(14))){
            throw new Exception("parameter pathToMazes in general parameter is mandatory... check XML file");
        }else{
            evolvingants.Main.pathToMazes = evolvingants.Main.root+XMLParameters.get(14);
        }
        //Check if the parameter pathToResults is not empty
        if("".equals(XMLParameters.get(15))){
            throw new Exception("parameter pathToResults in general parameter is mandatory... check XML file");
        }else{
            evolvingants.Main.pathToResults = evolvingants.Main.root+XMLParameters.get(15);
        }
    }    
}
