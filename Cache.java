import java.util.LinkedList;
import java.util.ListIterator;
/**
 * Cache using linked list
 * @author Luke Ptomey
 *
 */
public class Cache <T> {
	private LinkedList<T> createdCache;
	private int cacheCapacity;
	private FileRW store;
	/**
	 * Constructor
	 * @param Cache size
	 */
	public Cache(int specifiedSize) {
		cacheCapacity=specifiedSize;
		createdCache= new LinkedList<>();
	}

	//Added method by @Jeremy.............................................................................
	public Cache(int specifiedSize, FileRW store)
	{
		this.cacheCapacity = specifiedSize;
		this.store = store;
		this.createdCache = new LinkedList<T>();
	}

	/**
	 * Returns capacity of cache
	 * @return capacity of cache
	 */
	public int cacheCapacity() {
		return cacheCapacity;
	}
	/**
	 * Amount of elements in cache
	 * @return number of elements in cache
	 */
	public int cacheSize() {
		return createdCache.size();
	}
	/**
	 * Add method
	 * @param Element to add to Cache
	 */
	public void addToCache(T element) {
		if	(createdCache.size() < cacheCapacity) {
			if(createdCache.contains(element)){
				createdCache.remove(element);
			}
			createdCache.addFirst(element);

		}
		else {
			if(createdCache.contains(element)){
				createdCache.remove(element);
				createdCache.addFirst(element);
			}
			else {
				createdCache.removeLast();
				createdCache.addFirst(element);
			}

		}
	}
	/**
	 * Remove method
	 * @param element to be removed from Cache
	 */
	public void removeFromCache(T element) {
		createdCache.remove(element);
	}
	/**
	 * Clear method
	 * Clears cache
	 */
	public void clear() {
		createdCache.clear();
	}
	/**
	 * Determines whether element is in Cache
	 * @param element to be searched for
	 * @return True or false depending if element is found
	 */
	public boolean found(T element) {
		return createdCache.contains(element);
	}
/**
 * Gets supplied element from cache then adds object to top of cache
 * @param element
 * @return element from cache
 */
	public T get(T element) {
		T object= createdCache.get(createdCache.indexOf(element));
		addToCache(object);
		return object ;

	}

	//Added method by @Jeremy.............................................................................
	@SuppressWarnings("unchecked")
	public BTreeNode cacheQuery(long address)
	{
		boolean cacheHit = false;
		T returnNode = null;
		ListIterator<BTreeNode> iterator = (ListIterator<BTreeNode>) createdCache.listIterator();
		while(iterator.hasNext() && !cacheHit)
		{
			returnNode = (T) iterator.next();
			if(((BTreeNode) returnNode).getLocation() == address)
			{
				cacheHit = true;
				iterator.remove();
			}
			if (cacheHit)
			{
				createdCache.addFirst(returnNode);
				return (BTreeNode) returnNode;
			}
			else
			{
				returnNode = (T) store.getNode(address);
				createdCache.addFirst(returnNode);
				if(createdCache.size() > cacheCapacity)
				{
					createdCache.removeLast();
				}
				return (BTreeNode) returnNode;
			}
		}
		//May be wrong type of return;
		return null;
	}

}
