
/**
 * BTreeObjects stored in key array for node
 * @author Luke Ptomey
 */
public class BTreeObject {
    long dna;
    int frequency;
    public BTreeObject(long dna,int  frequency){
        this.dna=dna;
        this.frequency=frequency;
    }

    /**
     * Increment frequency
     */
    public void addFrequency(){
        frequency++;
    } 
    }