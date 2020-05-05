import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
         //TODO: This file creation needs a constructior @Luke
         file = new FileRW(bTreeFilename, cacheSize, cacheSize);
         //create the BTree in order to perform search
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
}
