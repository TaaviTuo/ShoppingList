/**
 * @author Taavi Tuomela taavi.tuomela@cs.tamk.fi
 * @version 2017.1219
 * @since 1.8
 */
public class ListItem {

    /**
     * Introduces the variable.
     */
    private int amount = 0;

    /**
     * Introduces the variable.
     */
    private String name;

    /**
     * The constructor method.
     * 
     * @param amount is the amount of the item on the list.
     * @param name is the name of the item on the list.
     */
    public ListItem(int amount, String name) {

        setName(name);
        setAmount(amount);
    }

    /**
     * The constructor method.
     */
    public ListItem() {

        setAmount(0);
        setName("");
    }

    /**
     * Sets the value of the variable name.
     *
     * @param name is the String you want to set as the name.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Sets the value of the variable amount.
     *
     * @param amount is the amount you want to set as the amount.
     */
    public void setAmount(int amount) {

        this.amount = amount;
    }

    /**
     * Gets the value of the variable name.
     *
     * @return is the name String of the object.
     */
    public String getName() {

        return name;
    }

    /**
     * Gets the value of the variable amount.
     *
     * @return is the amount of the object.
     */
    public int getAmount() {

        return amount;
    }
}
