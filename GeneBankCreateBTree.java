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
    static private FileRW TTfile;
    static int insertCount =0; //number of complete inserts
    /**
     * Main method first catches argument errors then goes to readInputFile method
     */
    public static void main (final String[] args){
        long startTime = System.nanoTime();
    
        // args size

    if(args.length <4 ){ //if too few args are provided
            System.err.println("java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence length>  [<cache size>] [<debug level>]");
        }
        
    if (args.length ==5){ //optional cache size parameter debug is 0 since 5 args
        if(useCache==0){
            System.err.println("Cannot have cache size if cache is not used");
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
        System.err.println("First argument is 1 or 0");
    }
    useCache=Integer.parseInt(args[0]); //assign int to use cache from string

    //degree

    if(Integer.parseInt(args[1]) < 0){ //if degree is negative
        System.err.println("Degree cannot be negative");
    }
    else if (Integer.parseInt(args[1])==0){
        degree = 128;  //optimun degree
    }
    else{
        degree = Integer.parseInt(args[1]); //set degree
    }

    //sequenceLength

    if (Integer.parseInt(args[3]) < 1 || Integer.parseInt(args[3]) > 31) {
        System.err.println("Sequence length is not in range");
    }
    sequenceLength=Integer.parseInt(args[3]); //assign sequence length
    classTree = new GeneBankCreateBTree(); //create BTree

    if(useCache ==1 ){
        //CREATE CACHE 
    }
    readInputFile(sequenceLength, gbkFileName);
    long endTime = System.nanoTime();
    long seconds= (endTime-startTime)/1000000000;
    System.out.println("Seconds: " + seconds);
  
    // if user wants debugLevel 1
    if(debugLevel==1){
        File dump = new File("dump.txt");
        try{
        dump.delete();
        dump.createNewFile();

        }
        catch (IOException e){
            System.err.println("Error when makeing dump file");
            System.exit(1);
        }
        //@DanielMcDougall tree traversal
        treePrinter(tree.getBase());
    }
    System.out.println("Dump File Created");
}

/**
	 * Figures out DNA character given a binary input
	 * @param dnastr
	 * @param binary1
	 * @param binary2
	 * @return The correct dna character from binary
	 */
	public static String binToDna(String dnastr, int binary1, int binary2) {
		String convert = dnastr;
		if (binary1 == 0 && binary2 == 0) {
			convert += "A";
		} else if (binary1 == 0 && binary2 == 1) {
			convert += "C";
		} else if (binary1 == 1 && binary2 == 1) {
			convert += "T";
		} else if (binary1 == 1 && binary2 == 0) {
			convert += "G";
		}
		return convert;
	}

/**
 * returns the number of zeros on the left side of the dna sequence
 * @param binary
 * @return string conversion of zero checks
 */
private static String zeroCount(String binary) {
    String convert = binary;
    if (convert.length() != (sequenceLength * 2)) {
        String adder = convert;
        convert = "";
        int diff = (sequenceLength * 2) - adder.length();
        for (int i = 0; i < diff; i++) {
            convert += "0";
        }
        convert += adder;
        return convert;
    }
    return convert;
}

/**
	 * given a binary string, will return dna character pairs
	 * @param binString
	 * @return dna character pairs
	 */
	public static String binToPairs(String binString) {
		String convert = "";
		int pairItem1 = 2; // to represent binary numbers 1 & 0
		int Item2 = 2;// 2 is default or error
		int pairCount = 0;
		for (int i = 0; i < binString.length(); i++) {
            // find pairs if possible
            // revert the variables back to their original state
			if (pairCount == 2) {
				convert = binToDna(convert, pairItem1, Item2);
				pairItem1 = 2;
				Item2 = 2;
				pairCount = 0;
			}
			// add to pairCount when pairs are found
			if (pairCount == 1) {
				String tmp1 = "" + binString.charAt(i);
				Item2 = Integer.parseInt(tmp1);
				pairCount++;
			} else if (pairCount == 0) {
				String tmp0 = "" + binString.charAt(i);
				pairItem1 = Integer.parseInt(tmp0);
				pairCount++;
			} //else {
				//if (debugLevel == 0) {
				//	System.err.println("ERROR: binToPairs!");
				//}
			//}
		}
		convert = binToDna(convert, pairItem1, Item2);
		return convert.toLowerCase();
	}

    /**
     * Uses prior methods to transform tree input into 
     * dump file output as requested. 
     */
     public static void treePrinter(BTreeNode r)
     {
         TTfile = tree.getFileRW();
         if (r != null) {
             //specifies dump file location to print to
             Path filePath  = Paths.get("./dump.txt");
             for (int i = 0; i < r.getAmountOfChildren(); i++) {
                 if (i < r.getAmountOfChildren()) {
                     treePrinter(TTfile.getNode(r.getChildAtIndex(i)));
                }
                //use standard java functions to turn tree into dna sequence
                List<String> strings = Arrays.asList(binToPairs(zeroCount(Long.toBinaryString(r.getKeyAtIndex(i).dna)))
                + ": " + r.getKeyAtIndex(i).frequency);
                // append characters to the file writer for printing
                try {
                    Files.write(filePath, strings, Charset.forName("UTF-8"),StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (r.getAmountOfKeys() < r.getAmountOfChildren()) {
                treePrinter(TTfile.getNode(r.getChildAtIndex(r.getAmountOfKeys())));
            }
        }

}

    /**
     * Parses gbk file and puts sequences in BTree
     * @param sequenceLength2
     * @param gbkFileName2
     */
    private static void readInputFile( int sequenceLengthInput,String gbkFileNameInput) {
        boolean origin = false; // word with dna after in gbk file
        String grab = "";
        long insertBinarySequence = 0;
        String sequence = "";
        String oldSequence =  "";
        int characterCount =0;
        try{
             File gbk = new File(gbkFileNameInput); //utilize scanner
             Scanner scan = new Scanner(gbk);
             while(scan.hasNextLine()){//grabs each line
               String grabLine= scan.nextLine(); 
               StringTokenizer editLine = new StringTokenizer(grabLine);
               while(editLine.hasMoreTokens()){
                   if(origin == false){ //not DNA
                    grab = editLine.nextToken().toUpperCase();

                        if(grab.equals("ORIGIN")){
                            characterCount=0; //reset characterCount if there is more than one DNA block
                            origin = true; //hit orgin word in file
                        }
                   }
                   else if (origin == true){ //DNA
                    grab = editLine.nextToken().toUpperCase();
                    if (grab.equals("//")){
                       //debug??
                        insertBinarySequence = binaryStringToLong(sequence); //add last sequence
                        // check length??
                        tree.insert(insertBinarySequence);  //add to tree
                        sequence = ""; // reset in case file has other DNA block
                        origin = false;
                     }
                      else if (grab.charAt(0) == 'A' || grab.charAt(0) == 'C' || grab.charAt(0) == 'G'|| grab.charAt(0) == 'T'){
                          for(int i =0; i < grab.length(); i++){
                              if(characterCount == sequenceLengthInput){
                                  if(debugLevel == 0){
                                    //print subString??
                                  }
                                  oldSequence=sequence;

                                  insertBinarySequence = stringToBinary(sequence);
                                  if(debugLevel == 0){
                                    //print insertBinary
                                  }

                                  if(!sequence.contains("n")){

                                      tree.insert(insertBinarySequence);
                                      insertCount++;
                                  }

                                  sequence = ""; //reset sequence

                                  sequence  = oldSequence.substring(1);
                                  characterCount = sequenceLengthInput-1; 
                              }
                              if(grab.charAt(i) == 'n'){
                                  sequence += grab.charAt(i);
                                  characterCount++;
                              }
                              else{
                                  sequence += grab.charAt(i);
                                  characterCount++;
                              }
                            }
                        }

                    }
                    else{

                    }


                }
                
            }
              
            
            scan.close();
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
    static long stringToBinary( String dnaString){
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
        long convert=0;
        for(int i = bits.length - 1; i >= 0; i--){
            if (bits[i] == '1'){
                convert += (long) Math.pow(2,(bits.length -i -1));
            }
        }
        return convert;
    }
  

}
