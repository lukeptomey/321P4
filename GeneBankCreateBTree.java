import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Parses arguments and creates subsequences. Then inserts these
 * subsequences to BTree as binary long
 * @author Luke Ptomey
 */
public class GeneBankCreateBTree {
    static BTree tree;
    static int useCache;
    static int  degree;
    static String gbkFileName;
    static int sequenceLength;
    static int cacheSize;
    static GeneBankCreateBTree classTree;
    static int debugLevel;

    /**
     * Main method
     */
    public static void main (final String[] args){
    
        // args size

    if(args.length <4 ){ //if too few args are provided
            System.out.println("java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence length>  [<cache size>] [<debug level>]");
        }
        
    if (args.length ==5){ //optional cache size parameter debug is 0 since 5 args
        if(useCache==0){
            System.out.println("Cannot have cache size if cache is not used");
        }
            cacheSize=Integer.parseInt(args[4]); //assign cache size
            debugLevel =0; //assign debug level
    }
    if (args.length ==6){
        cacheSize = Integer.parseInt(args[4]);
			debugLevel = Integer.parseInt(args[5]);
    }

    gbkFileName=args[2]; //set file name

    if(Integer.parseInt(args[0]) > 2){ //if use cache is above 1
        System.out.println("First argument is 1 or 0");
    }
    useCache=Integer.parseInt(args[0]); //assign int to use cache from string

    //degree

    if(Integer.parseInt(args[1]) < 0){ //if degree is negative
        System.out.println("Degree cannot be negative");
    }
    else if (Integer.parseInt(args[1])==0){
        degree = 128;  //optimun degree
    }
    else{
        degree = Integer.parseInt(args[1]); //set degree
    }

    //sequenceLength

    if (Integer.parseInt(args[3]) < 1 || Integer.parseInt(args[3]) > 31) {
        System.out.println("Sequence length is not in range");
    }
    sequenceLength=Integer.parseInt(args[3]); //assign sequence length
    classTree = new GeneBankCreateBTree(); //create BTree

    if(useCache ==1 ){
        //CREATE CACHE 
    }
    
    parseInputFile(sequenceLength, gbkFileName);


    

}

    /**
     * Parses gbk file and puts sequences in BTree
     * @param sequenceLength2
     * @param gbkFileName2
     */
    //Figure out why gbk is not registered as a file @JeremyBouchard220 @DanielMcDougall
    private static void parseInputFile( int sequenceLengthInput,String gbkFileNameInput) {
        try{
             File gbk = new File(gbkFileNameInput); //utilize scanner
             Scanner scan = new Scanner(gbk);
             while(scan.hasNextLine()){
                System.out.println(scan.nextLine());
            } 
              
        }
        catch(final FileNotFoundException e){
			System.err.println( gbkFileName + " could not be opened.");
            System.exit(1);
        }
        

}

/**
 * Constuctor that creates BTree based on if cache is used or not
 */
public GeneBankCreateBTree(){
    if(useCache==1){
    tree = new BTree(useCache, degree, gbkFileName, sequenceLength, cacheSize);
    }
    else {
        tree = new BTree(useCache, degree, gbkFileName, sequenceLength, 0);
    }
}
    /**
     * Converts string of DNA into long datatype
     * @param dnaString 
     * @return long variable type with binary representation of atcg
     */
    static long stringToBinary(final String dnaString){
        String hold = "";
        char d;
        for (int i =0; i < sequenceLength; i++){
            d = dnaString.charAt(i);
            if (d=='a' || d =='A'){            //A->00
                hold+="00";
            } else if (d == 'c' || d == 'C') { //C->01
				hold += "01";
            } else if (d == 'g' || d == 'G') { //G->10
                hold += "10";
            } else if (d == 't' || d == 'T') { //T->11
                hold += "11";
            }
        }

        final long dnaLong = binaryStringToLong(hold);
        return dnaLong; 
    }

    /**
     * Converts binary string to binary long
     * @param binaryString
     * @return binary in long datatype
     */
    static long binaryStringToLong(final String binaryString){
        final char[] bits = binaryString.toCharArray();
        long retVal=0;
        for(int i = bits.length - 1; i >= 0; i--){
            if (bits[i] == '1'){
                retVal += (long) Math.pow(2,(bits.length -i -1));
            }
        }
        return retVal;
    }
  

}
