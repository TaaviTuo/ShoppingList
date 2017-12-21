/**
 * @author Taavi Tuomela taavi.tuomela@cs.tamk.fi
 * @version 2017.1219
 * @since 1.8
 */
import java.util.Scanner;

public class ShoppingList {
    
    /**
     * Launches the program.
     * 
     * @param args used to launch the graphical userinterface.
     */
    public static void main(String[] args) {

        /**
         * Introduces the variable.
         */
        String userInput;

        /**
         * Introduces the variable.
         */
        Scanner scanner = new Scanner(System.in);

        /**
         * Introduces the variable.
         */
        UserInputHandler inputHandler = new UserInputHandler();

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("gui")) {

                GraphicalUserInterface.launch(GraphicalUserInterface.class);
            } else {
            
                System.out.println("SHOPPING LIST");
                System.out.println("Tampere University of Applied Sciences");

                while (true) {
                    
                    System.out.println
                        ("Give shopping list " + 
                            "(example: 1 milk;2 tomato;3 carrot;)");
                    userInput = scanner.nextLine();

                    if (userInput.equalsIgnoreCase("exit")) {

                        break;
                    } else {

                        inputHandler.cutStrings(userInput);
                    }

                    inputHandler.printList();
                }
            }
        } else {
            
            System.out.println("SHOPPING LIST");
            System.out.println("Tampere University of Applied Sciences");

            while (true) {
                
                System.out.println("Give shopping list " +
                    "(example: 1 milk;2 tomato;3 carrot;)");
                userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("exit")) {

                    break;
                } else {

                    inputHandler.cutStrings(userInput);
                }

                inputHandler.printList();
            }
        }

        scanner.close();
    }
}
