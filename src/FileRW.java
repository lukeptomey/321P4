import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 *  @author Luke Ptomey
 */


public class FileRW {
    RandomAccessFile randomFile;
	long endOfFile;
	int recordLength;
	int degree; //t


/**
 * Constructor to make rw file
 * @param filename
 * @param degrees
 * @param subStringLength
 * @throws IOException
 */
    public FileRW (String filename, int degrees, int byteLength) throws IOException {
		randomFile = new RandomAccessFile(filename, "rw");// Open a new file as read/write with synchronized IO.
		this.degree = degrees;// Set degree
		endOfFile = 12;
		randomFile.setLength(endOfFile);// set file size
		randomFile.seek(0);//set offset where next read or write occurs
		randomFile.writeInt(degree); // Degree is at offset 0
		randomFile.seek(4);
		randomFile.writeInt(byteLength); // byte length is at offset 4
		randomFile.seek(8);
		randomFile.writeInt(?);// Root location is at offset 8
		recordLength=?
	}
	/**
	 * Creates new node that needs to be written into the file
	 * @return new node 
	 */
    public BTreeNode createNode(){
        try {
			randomFile.seek(endOfFile);// Go to end of file
			BTreeNode newNode = new BTreeNode(endOfFile, degree);// Create a new Node that starts there.
			endOfFile = (endOfFile + recordLength);					// Increase the size of the file by the length of a record.
			randomFile.setLength(randomFile.length() + recordLength);	// Write size change to file.
			return newNode;
		} catch (IOException e) {									// Fail on IOException.
            System.out.println("Node could not be created");
			System.exit(1);
			return null;
		}
	}
	/**
	 * Sets root location in file given location index
	 * @param i
	 */
    public void setRootLocation(Long i){

	}
	/**
	 * Write node to file
	 * @param node
	 */
	public void writeNode(BTreeNode node) {

	}
/**
 * Gets node at location in file
 * @param location
 * @return
 */
	public BTreeNode getNode(long location){
		//placeholder code
		BTreeNode newNode = new BTreeNode(endOfFile, degree); 
		return newNode;
	}
}
