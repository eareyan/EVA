package evolvingants.tools;
/*
 * Taken and modified from http://java.sun.com/developer/technicalArticles/Programming/compression/
 */
import evolvingants.Main;
import java.io.*;
import java.util.zip.*;

public class FileHandling {
    /*Size of the Buffer */
    static final int BUFFER = 2048;
    /* Method to unzip a file into a folder with a numeric, integer value*/
    public static void unzip (String path,int folder,String filename) throws Exception{
        /*
         * Make sure the file exists
         */
        File MazeFile=new File(path + filename);
        if(!MazeFile.exists()){
            throw new Exception("The maze file cannot be found at: "+path+filename);
        }        
        /* Print control parameters */
        if(Main.verbose){
            System.out.println("path = "+path);
            System.out.println("folder = "+folder);
            System.out.println("filename = "+filename);
        }
        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(path + filename);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null) {
                if(Main.verbose){
                    System.out.println("Extracting: " +entry);
                }
                int count;
                byte data[] = new byte[FileHandling.BUFFER];
                // write the files to the disk
                /*
                * Make sure the folder in the path exists
                */
                File MazePath = new File(path+folder);
                if(!MazePath.exists()){
                    MazePath.mkdir();
                }
                int mid = entry.getName().lastIndexOf(".");
                if(!"mz".equals(entry.getName().substring(mid+1,entry.getName().length()))){
                    continue; //skip anything whose extension is not .mz, i.e., that is not a maze
                }

                FileOutputStream fos = new FileOutputStream(path+entry.getName());
                dest = new  BufferedOutputStream(fos, FileHandling.BUFFER);
                while ((count = zis.read(data, 0, FileHandling.BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            zis.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
   
    // Deletes all files and subdirectories under dir.
    // Returns true if all deletions were successful.
    // If a deletion fails, the method stops attempting to delete and returns false.
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // The directory is now empty so delete it
        return dir.delete();
    }
    /*
     * Save results of an experiment
     */
    public static void saveResults(String path,String filename,String Results) throws FileNotFoundException,IOException{
        /*
         * Make sure the path exists
         */
        File MazePath = new File(path);
        if(!MazePath.exists()){
            MazePath.mkdir();
        }
        /*
         * Make sure the file exists
         */
        File MazeFile=new File(path + filename);
        if(!MazeFile.exists()){
            MazeFile.createNewFile();
        }
        /*
         * Save the results
         */
        FileWriter fstream = new FileWriter(path + filename,true /*append data to file*/);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(Results);
        //Close the output stream
        out.close();
    }    
}

