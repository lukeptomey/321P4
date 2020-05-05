import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads string sequences from a query file and searches a BTree file for matches.
 * 
 * @author Jeremy Bouchard
 */
public class GeneBankSearch 
{
    static int useCache, cacheSize, debugLevel;
    static String bTreeFilename, queryFilename;
    static BTree tree;
    static File query;
    static FileRW file;

    public static void main(String[] args) throws IOException
    { 
        useCache = Integer.parseInt(args[0]);
         bTreeFilename = args[1];
         queryFilename = args[2];
         cacheSize = 0;

         //access to the BTree file
         file = new FileRW(bTreeFilename);
         //create the BTree in order to perform search
         //@TODO create new BTree constructor
         tree = new BTree(useCache, file, cacheSize);
         //create new File object to allow for query file access
         query = new File(queryFilename);
         

         //check for debug level in fourth argument
         if(args.length == 4 && useCache == 0)
         {
             debugLevel = Integer.parseInt(args[3]);

             if(debugLevel != 0)
             {
                 System.out.println("Search only supports debug level of 0.");
                 System.exit(1);
             }
         }

         //check for cache size in fourth argument
         else if(args.length == 4 && useCache == 1)
         {
             cacheSize = Integer.parseInt(args[3]);
             debugLevel = 0;
         }

         //check for if debug and cache size are specified
         else if(args.length == 5)
         {
             cacheSize = Integer.parseInt(args[3]);
             debugLevel = Integer.parseInt(args[4]);

             if(debugLevel != 0)
             {
                 System.out.println("Search only supports debug level of 0.");
                 System.exit(1);
             }
         }
    }
    
    /**
     * Checks through the query file line by line and prints output
     * 
     * @throws FileNotFoundException
     */
    private static void queryCheck() throws FileNotFoundException
    {
        Scanner queryScan = new Scanner(query);
        String queryLine = queryScan.nextLine();

        //check for sequence length compatability
        if(queryLine.length() != file.getSequenceLength())
        {
            System.out.println("Error: Squence lengths do not match the lengths of the files.");
            System.exit(1);
        }

        //search for sequence and print output
        while(queryScan.hasNextLine())
        {
            System.out.print(queryLine + " ");
            int frequency = freqBTree(queryLine);
            queryLine = queryScan.nextLine();

            //May need to be greater than or equal to 0.
            if(frequency != 0)
            {
                System.out.println(": " + frequency);
            }

            else
            {
                System.out.println("[not found]");
            }

            //check again for matched sequence lengths
            if(queryLine.length() != file.getSequenceLength())
            {
                System.out.println("Error: Line sequence length is incorrect.");
            }
        }

        //gets last query line from the file
        System.out.print(queryLine + " ");
        int frequency = freqBTree(queryLine);

        //May need to be greater than or equal to 0.
        if(frequency != 0)
        {
            System.out.println(": " + frequency);
        }
        else
        {
            System.out.println("[not found]");
        }

        queryScan.close();
    }

    /**
     * Searches through BTree to find a string and returns frequency
     * 
     * @param queryLine the string being looked for
     */
    private static int freqBTree(String queryLine)
    {
        long key = GeneBankCreateBTree.stringToBinary(queryLine);
        return tree.search(file.getRootNode(), key);
    }

    /**
     * Prints how to use to the console.
     */
    private static void usagePrint()
    {
        System.out.println("Usage: java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache size>] [<debug level>]");
        System.out.println("Cache Size and Debug Level are optional inputs, however debug level is set to 0.");
    }
}
