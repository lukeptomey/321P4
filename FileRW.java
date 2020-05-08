
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Class that reads and writes to random access file
 * 
 * @author Luke Ptomey
 */

public class FileRW {
	RandomAccessFile randomFile;
	long endOfFile;
	int degree; // t
	int byteLength;
	int subStringLength;

	/**
	 * Constructor that reads file
	 * 
	 * @param gbkFileName
	 * @throws IOException
	 */
	public FileRW(String gbkFileName) throws IOException {
		randomFile = new RandomAccessFile(gbkFileName, "r");// Create read file
		randomFile.seek(0); //go to begining of file
		degree=randomFile.readInt();
		byteLength =((2 * degree - 1) * 8 + (2 * degree - 1) * 4 + (2 * degree * 4) + 12)*2;
	}

/**
 * Constructor to make rw file
 * @param filename
 * @param degrees
 * @param subStringLength
 * @throws IOException
 */
    public FileRW (String gbkFileName, int degrees, int subStringLength) throws IOException {
		randomFile = new RandomAccessFile(gbkFileName, "rw");// Create read and write file
		this.degree = degrees;// Set degree
		this.subStringLength = subStringLength; 
		randomFile.seek(0);//go to begining of file
		randomFile.writeInt(degree); // Degree is at offset 0
		randomFile.writeLong(0); // root location is at offset 4
		endOfFile = randomFile.getFilePointer();
		byteLength =(2 * degree - 1) * 8 + (2 * degree - 1) * 4 + (2 * degree * 4) + 12;
		
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
	/**
	 * Grabs root node from random acess file
	 * @return root node
	 */
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
	try{
		randomFile.seek(node.getLocation()); //find location
		randomFile.writeLong(node.getLocation()); //write location
		randomFile.writeInt(node.getAmountOfChildren()); //amount of children
		randomFile.writeInt(node.getAmountOfKeys()); //amount of keys
		randomFile.writeBoolean(node.checkLeaf()); //write if leaf

		for (int i=0; i < node.getAmountOfChildren(); i++ ){ //write children to the file
			randomFile.writeLong(node.getChildAtIndex(i));
		}

		for ( int i =0; i < node.getAmountOfKeys(); i++){
			randomFile.writeLong(node.getKeyAtIndex(i).dna); //write dna long
			randomFile.writeInt(node.getKeyAtIndex(i).frequency); //write frequency
		}
	}
	catch (IOException e){
		e.printStackTrace();
		System.exit(1);
	}
	}
/**
 * Gets node at location in file
 * @param location
 * @return
 */
	public BTreeNode getNode(long location){
	try{
		randomFile.seek(location); //find begining of node
		BTreeNode readNode = new BTreeNode(location, degree);
		readNode.setLocation(randomFile.readLong()); //read location
		readNode.setNumbOfChildren(randomFile.readInt()); //read amount of children
		readNode.setKeyNumb(randomFile.readInt()); //read amount of keys
		readNode.setLeaf(randomFile.readBoolean()); //read leaf

		long[] grabChildren = new long[(2*degree)-1]; //read children
		for(int i=0; i < readNode.getAmountOfChildren(); i++ ){
			grabChildren[i]= randomFile.readLong();
		}
		readNode.setChildArray(grabChildren);

		BTreeObject empty = new BTreeObject(-1, 0);
		BTreeObject[] grabObjects = new BTreeObject [(2*degree) - 1]; //read keys 
		Arrays.fill(grabObjects,empty);							//set empty values for keys
		for(int i=0; i < readNode.getAmountOfKeys(); i++){
			long tempDNA = randomFile.readLong();
			int tempFrequency =randomFile.readInt();
			BTreeObject insert = new BTreeObject(tempDNA, tempFrequency);
			grabObjects[i] =insert;
		}
		readNode.setKeyArray(grabObjects);
			return readNode;

		}
		catch (IOException e){
			e.printStackTrace();
			System.exit(1);
			return null;
		}

	}

	/**
	 * Gets sequenceLength
	 * @return sequenceLength/subStringLength
	 */
	public int getSequenceLength(){
		return subStringLength;
	}


	
	
}
