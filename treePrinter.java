import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * This class uses auxilary functions to find out 
 * DNA characters, string them into pairs / sequences
 * and print them as requested with treePrint. Invoked in
 * genebankcreatebtree
 */
public class treePrinter {
    static treePrinter tp;

	/**
	 * constructor for treePrint. needed because constructors cannot be recursive methods
	 * @param root
	 * @param B
	 * @param seqLength
	 */
    treePrinter(BTreeNode root,BTree B, int seqLength){
        tp.treePrint(root,B,seqLength);
	}
	
	// needed because a constructor with no paramaters must be located for Java,
	//otherwise the wanted constructor wont work
	treePrinter(){
	}

	/**
     * Uses prior methods to transform tree input into 
     * dump file output as requested. 
     */
    void treePrint(BTreeNode r,BTree btree, int sequenceL){
        FileRW TTfile = btree.getFileRW();
         if (r != null) {
             //specifies dump file location to print to
			 Path filePath  = Paths.get("./dump.txt");
			 if (r.getAmountOfKeys() < r.getAmountOfChildren()) {
                treePrint(TTfile.getNode(r.getChildAtIndex(r.getAmountOfKeys())),btree, sequenceL);
            }
             for (int i = 0; i < r.getAmountOfChildren(); i++) {
                 if (i < r.getAmountOfChildren()) {
                     treePrint(TTfile.getNode(r.getChildAtIndex(i)),btree, sequenceL);
				}
                //use standard java functions to turn tree into dna sequence
                List<String> strings = Arrays.asList(binToPairs(zeroCount(Long.toBinaryString(r.getKeyAtIndex(i).dna), sequenceL))
                + ": " + r.getKeyAtIndex(i).frequency);
                // append characters to the file writer for printing
                try {
                    Files.write(filePath, strings, Charset.forName("UTF-8"),StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
	 * Figures out DNA character given a binary input
	 * @param dnastr
	 * @param binary1
	 * @param binary2
	 * @return The correct dna character from binary
	 */
	String binToDna(String dnastr, int binary1, int binary2) {
		String convert = dnastr;
		if (binary1 == 0 && binary2 == 0) {
			convert += "A";
		} else if (binary1 == 0 && binary2 == 1) {
			convert += "C";
		} else if (binary1 == 1 && binary2 == 0) {
			convert += "G";
		} else if (binary1 == 1 && binary2 == 1) {
			convert += "T";
		}
		return convert;
    }

    /**
 * returns the number of zeros on the left side of the dna sequence
 * @param binary
 * @return string conversion of zero checks
 */
String zeroCount(String binary, int seqlength) {
    String convert = binary;
    if (convert.length() != (seqlength * 2)) {
        String adder = convert;
        convert = "";
        int diff = (seqlength * 2) - adder.length();
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
	String binToPairs(String binString) {
		String convert = "";
		int pairItem1 = 2; // to represent binary numbers 1 & 0
		int Item2 = 2;// 2 is default or error
		int pairCount = 0;
		for (int i = 0; i < binString.length(); i++) {
            // find pairs if possible
			// revert the variables back to their original state
			if (pairCount == 1) {
				String tmp1 = "" + binString.charAt(i);
				Item2 = Integer.parseInt(tmp1);
				pairCount++;
			}
			// add to pairCount when pairs are found
			if (pairCount == 2) {
				convert = binToDna(convert, pairItem1, Item2);
				pairItem1 = 2;
				Item2 = 2;
				pairCount = 0;
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
}