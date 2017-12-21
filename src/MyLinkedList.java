/**
 * @author Taavi Tuomela taavi.tuomela@cs.tamk.fi
 * @version 2017.1219
 * @since 1.8
 */
public class MyLinkedList<E> implements MyList<E> {

    /**
     * Introduces the variable.
     */
    private Element first;

    /**
     * Introduces the variable.
     */
    private Element current;

    /**
     * Introduces the variable.
     */
    private int size;

    /**
     * The constructor method.
     */
    public MyLinkedList() {

        first = null;
        current = null;
        size = 0;
    }

    /**
     * Inserts the given object to the start of the list.
     *
     * @param o is the object to be added onto the list.
     */
    @Override
    public void add(E o) {

        if (first == null) {

            first = new Element();
            first.node = o;
            first.next = null;
        } else {

            Element newElement = new Element();
            newElement.node = o;
            newElement.next = first;
            first = newElement;
        }

        size++;
    }

    /**
     * Empties the list.
     */
    @Override
    public void clear() {

        first = null;

        size = 0;
    }

    /**
     * Returns the object on the given index.
     *
     * @param index is the index you want to get the object from.
     * 
     * @return the object from the given index.
     */
    @Override
    public E get(int index) {

        Element e = first;

        if (index < 0 || index >= size) {

            return null;
        } else {

            for (int i = 0; i < index; i++) {

                e = e.getNext();
            }

            return e.getNode();
        }
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false if not.
     */
    @Override
    public boolean isEmpty() {

        if (first == null) {

            return true;
        } else {

            return false;
        }
    }

    /**
     * Removes the object from the given index.
     *
     * @param index is the index you want to remove the object from.
     * 
     * @return the new object from the given index or null.
     */
    @Override
    public Object remove(int index) {

        if (index < 1 || index > size) {
            return null;
        }

        current = first;

        if (first != null) {

            for (int i = 0; i < index; i++) {

                if (current.getNext() == null) {

                    return null;
                }

                current = current.getNext();
            }

            current.setNext(current.getNext().getNext());

            size--;

            return current;
        }

        return null;
    }

    /**
     * Removes the given object from the list.
     *
     * @param o is the object you want to remove from the list.
     * 
     * @return true if the removal was successful, otherwise false.
     */
    @Override
    public boolean remove(E o) {

        current = first;

        while (current.next != null) {

            if (current.next.node == o) {

                current.next = current.next.next;
                size--;

                return true;
            } else if (first.node == o) {

                first = first.next;
                size--;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    /**
     * Returns the size of the list.
     * 
     * @return the the size of the list.
     */
    @Override
    public int size() {

        return size;
    }

    public class Element {

        /**
         * Introduces the variable.
         */
        E node;

        /**
         * Introduces the variable.
         */
        Element next;

        /**
         * Introduces the variable.
         */
        Element previous;

        /**
         * Sets the value of the variable node.
         *
         * @param o is the object you want to set into the variable.
         */
        public void setNode(E o) {

            node = o;
        }

        /**
         * Gets the value of the variable node.
         *
         * @return the value of the variable node.
         */
        public E getNode() {

            return node;
        }

        /**
         * Sets the value of the variable next.
         *
         * @param e is the object you want to set into the variable.
         */
        public void setNext(Element e) {

            next = e;
        }

        /**
         * Gets the value of the variable next.
         *
         * @return the value of the variable next.
         */
        public Element getNext() {

            return next;
        }

        /**
         * Sets the value of the variable previous.
         *
         * @param e is the object you want to set into the variable.
         */
        public void setPrevious(Element e) {
            
            previous = e;
        }

        /**
         * Gets the value of the variable previous.
         *
         * @return the value of the variable previous.
         */
        public Element getPrevious() {

            return previous;
        }
    }
}
