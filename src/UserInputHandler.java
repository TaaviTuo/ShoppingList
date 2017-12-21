/**
 * @author Taavi Tuomela taavi.tuomela@cs.tamk.fi
 * @version 2017.1219
 * @since 1.8
 */
public class UserInputHandler {

    /**
     * Introduces the variable.
     */
    int amount;

    /**
     * Introduces the variable.
     */
    String name;

    /**
     * Introduces the variable.
     */
    String item;

    /**
     * Introduces the variable.
     */
    String toReturn = "";

    /**
     * Introduces the variable.
     */
    MyLinkedList<ListItem> list = new MyLinkedList<>();

    /**
     * The constructor method.
     */
    public UserInputHandler() {

        amount = 0;
        name = "";
        item = "";
    }

    /**
     * Splits the items from user's input to single items.
     *
     * @param items is the user input from the main class.
     */
    public boolean cutStrings(String items) {
        
        for (String s : items.split(";")) {
            
            item = s;
            if (!makeIntoItems(item)) {

                return false;
            }
        }

        return true;
    }
    
    /**
     * Searches the item for the amount and the name.
     *
     * @param item is a single item from the user input.
     */
    public boolean makeIntoItems(String item) {
        
        try {
        String[] parts = item.split(" ");
        int amount = Integer.parseInt(parts[0]);
        String name = parts[1];

        if (list.isEmpty()) {

            ListItem listItem = new ListItem(amount, name);
            list.add(listItem);
        } else {

            checkDuplicates(amount, name);
        }

        parts = new String[0];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {

            System.out.println("Please use valid format for items!");
            return false;
        }

        return true;
    }

    /**
     * Checks if the item exists on the list.
     *
     * @param amount is the amount of items to be added to/removed 
     * from the list.
     * @param name is the name of the item to be added to/removed
     * from the list.
     * 
     * @return true if duplicate is found, otherwise false.
     */
    public boolean checkDuplicates(int amount, String name) {

        ListItem toCheck = new ListItem();
        boolean duplicate = false;

        for (int j = list.size() - 1; j >= 0; j--) {
            
            toCheck = list.get(j);

            if (toCheck.getName().equalsIgnoreCase(name)) {

                duplicate = true;

                break;
            }
        }

        if (duplicate) {

            toCheck.setAmount(toCheck.getAmount() + amount);

            if (toCheck.getAmount() <= 0 && list.remove(toCheck)) {

                list.remove(toCheck);
            } else if (toCheck.getAmount() <= 0 && !list.remove(toCheck)){

                list.clear();
            }
        } else {

            ListItem listItem = new ListItem(amount, name);

            if (listItem.getAmount() >= 0) {
                
                list.add(listItem);
            }
        }

        return duplicate;
    }
    
    /**
     * Prints out the list to the console.
     */
    public void printList() {

        if (list.isEmpty()) {

            System.out.println("Your Shopping List now:");
            System.out.println("Seems to be empty");
        } else {
        
            System.out.println("Your Shopping List now:");

            for (int i = list.size() - 1; i >= 0; i--) {

                ListItem toPrint = list.get(i);

                System.out.print("  " + toPrint.getAmount() + " ");
                System.out.println(toPrint.getName());
            }
        }

        System.out.println("");
    }

    /**
     * Returns the list as a String so it can be used on the gui.
     * 
     * @return the list as a String
     */
    public String getList() {

        toReturn = "";

        String[] tmp = new String[list.size()];

        for (int i = list.size() - 1; i >= 0; i--) {
            
            ListItem item = list.get(i);

            tmp[i] = item.getAmount() + " " + item.getName() + "\n";
        }

        for (int i = tmp.length - 1; i >= 0; i--) {

            toReturn = toReturn + tmp[i];
        }

        return toReturn;
    }
}
