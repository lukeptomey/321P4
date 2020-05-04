import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * Class that reads and writes to random access file
 *  @author Luke Ptomey
 */


public class FileRW {
    RandomAccessFile randomFile;
	long endOfFile;
	int degree; //t
	int byteLength;


/**
 * Constructor to make rw file
 * @param filename
 * @param degrees
 * @param subStringLength
 * @throws IOException
 */
    public FileRW (String filename, int degrees, int byteLength) throws IOException {
		randomFile = new RandomAccessFile(filename, "rw");// Create read and write file
		this.degree = degrees;// Set degree
		randomFile.seek(0);//set offset where next read or write occurs
		randomFile.writeInt(degree); // Degree is at offset 0
		randomFile.writeLong(0); // root location is at offset 4
		endOfFile = randomFile.getFilePointer();
		//byteLength
		
	}
	/**
	 * Creates new node that needs to be written into the file
	 * @return new node 
	 */
    public BTreeNode createNode(){
        try {
			randomFile.seek(endOfFile);// Go to end of file
			BTreeNode newNode = new BTreeNode(endOfFile, degree);// Create a new Node that starts there.
			endOfFile = (endOfFile + byteLength);					// Increase the size of file by length of node
			return newNode;
		} catch (IOException e) {									
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
		try {
			randomFile.seek(4);
			randomFile.writeLong(i);
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BTreeNode getRootNode(){
		try{
			randomFile.seek(4);
			return getNode(randomFile.readLong());
		}
		catch	(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
		}
		
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
