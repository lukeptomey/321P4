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
    static int cacheUsed, sizeOfCache, levelOfDebug;
    static String bTreeFilename, filenameQuery;
    static BTree tree;
    static File query;
    static FileRW file;

    public static void main(String[] args) throws IOException
    { 
        cacheUsed = Integer.parseInt(args[0]);
         bTreeFilename = args[1];
         filenameQuery = args[2];
         sizeOfCache = 0;

         //access to the BTree file
         file = new FileRW(bTreeFilename);
         //create the BTree in order to perform search
         //@TODO create new BTree constructor
         tree = new BTree(cacheUsed, file, sizeOfCache);
         //create new File object to allow for query file access
         query = new File(filenameQuery);
         

         //check for debug level in fourth argument
         if(args.length == 4 && cacheUsed == 0)
         {
             levelOfDebug = Integer.parseInt(args[3]);

             if(levelOfDebug != 0)
             {
                 System.out.println("Search only supports debug level of 0.");
                 System.exit(1);
             }
         }

         //check for cache size in fourth argument
         else if(args.length == 4 && cacheUsed == 1)
         {
             sizeOfCache = Integer.parseInt(args[3]);
             levelOfDebug = 0;
         }

         //check for if debug and cache size are specified
         else if(args.length == 5)
         {
             sizeOfCache = Integer.parseInt(args[3]);
             levelOfDebug = Integer.parseInt(args[4]);

             if(levelOfDebug != 0)
             {
                 System.out.println("Search only supports debug level of 0.");
                 System.exit(1);
             }
         }
    }
}
