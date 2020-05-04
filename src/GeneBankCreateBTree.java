// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.nio.charset.Charset;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.nio.file.StandardOpenOption;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Scanner;
// import java.util.StringTokenizer;
/**
 * Parses arguments and creates subsequences. Then inserts these
 * subsequences to BTree as binary long
 * @author Luke Ptomey
 */
public class GeneBankCreateBTree {
    static BTree tree;
    static int useCache;
    static int  degree;
    static String gbkFilename;
    static int sequenceLength;
    static int cacheSize;

    /**
     * Constuctor that creates BTree
     */
public GeneBankCreateBTree(){
    if(useCache==1){
    tree = new BTree(useCache, degree, gbkFilename, sequenceLength, cacheSize);
    }
    else {
        tree = new BTree(useCache, degree, gbkFilename, sequenceLength, cacheSize);
    }
    }
    /**
     * Converts string of DNA into long datatype
     * @param dnaString 
     * @return long variable type with binary representation of atcg
     */
    static long stringToBinary(String dnaString){
        String hold = "";
        char d;
        for (int i =0; i < sequenceLength; i++){
            d = dnaString.charAt(i);
            if (d=='a' || d =='A'){
                hold+="00";
            } else if (d == 'c' || d == 'C') {
				hold += "01";
            } else if (d == 'g' || d == 'G') {
                hold += "10";
            } else if (d == 't' || d == 'T') {
                hold += "11";
            }
        }

        long dnaLong = binaryStringToLong(hold);
        return dnaLong; 
    }

    /**
     * Converts binary string to binary long
     * @param binaryString
     * @return binary in long datatype
     */
    static long binaryStringToLong(String binaryString){
        char[] bits = binaryString.toCharArray();
        
    }
  

}
