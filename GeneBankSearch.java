import java.io.IOException;

public class GeneBankSearch 
{
    static int useCache, cacheSize;
    static String bTreeFilename, queryFilename;
    
    public static void main(String[] args) throws IOException
    {
         useCache = Integer.parseInt(args[0]);
         bTreeFilename = args[1];
         queryFilename = args[2];
         cacheSize = 0;
    }
}
