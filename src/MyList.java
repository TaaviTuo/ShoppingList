/**
 * @author Taavi Tuomela taavi.tuomela@cs.tamk.fi
 * @version 2017.1219
 * @since 1.8
 */
public interface MyList<E> {

    /**
     * Inserts the given object to the start of the list.
     *
     * @param e is the object to be added onto the list.
     */
    void add(E e);

    /**
     * Empties the list.
     */
    void clear();

    /**
     * Returns the object on the given index.
     *
     * @param index is the index you want to get the object from.
     * 
     * @return the object from the given index.
     */
    E get(int index);

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false if not.
     */
    boolean isEmpty();

    /**
     * Rmoves the object from the given index.
     *
     * @param index is the index you want to remove the object from.
     * 
     * @return the new object from the given index or null.
     */
    Object remove(int index);

    /**
     * Removes the given object from the list.
     *
     * @param o is the object you want to remove from the list.
     * 
     * @return true if the removal was successful, otherwise false.
     */
    boolean remove(E o);

    /**
     * Returns the size of the list.
     * 
     * @return the the size of the list.
     */
    int size();
}
