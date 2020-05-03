import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 *  @author Luke Ptomey
 */


public class FileRW {
    RandomAccessFile randomFile;
	Long endOfFile;
	int recordLength;
	int degree;



    public FileRW (String filename, int degrees, int subStringLength) throws IOException {
		randomFile = new RandomAccessFile(filename, "rw");					// Open a new file as read/write with synchronized IO.
		this.degree = degrees;									// Degree is given.
		endOfFile = 16;
		randomFile.setLength(endOfFile + 1);							// Give the file a size, otherwise it's empty.
		randomFile.seek(0);                                          //set offset where next read or write occurs
		randomFile.writeInt(degree); 								// Degree is at offset 0
		randomFile.seek(4);
		randomFile.writeInt(subStringLength); 							// Substring Length is at offset 4
		randomFile.seek(8);
		randomFile.writeInt(16); 									// Root location is at offset 8
		recordLength = (2 * degree - 1) * 8 + (2 * degree - 1) * 4 + (2 * degree * 4) + 12;	// Record Size
    }
    public BTreeNode createNode(){
        try {
			randomFile.seek(endOfFile);								// Go to the last byte of the file.
			BTreeNode newNode = new BTreeNode(endOfFile, degree);			// Create a new Node that starts there.
			endOfFile = (endOfFile + recordLength);					// Increase the size of the file by the length of a record.
			randomFile.setLength(randomFile.length() + recordLength);	// Write size change to file.
			return newNode;
		} catch (IOException e) {									// Fail on IOException.
            System.out.println("Node could not be created");
			System.exit(1);
			return null;
		}
    }
    public void setRootLocation(Long i){

    }

}
