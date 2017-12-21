/**
 * @author Taavi Tuomela taavi.tuomela@cs.tamk.fi
 * @version 2017.1219
 * @since 1.8
 */
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.scene.paint.Paint;
import java.awt.Desktop;

public class GraphicalUserInterface extends Application {

    /**
     * The constructor method.
     */
    public GraphicalUserInterface() {

    }

    /**
     * The init method.
     */
    @Override
    public void init() {

    }

    /**
     * The start method. Sets up the GUI.
     */
    @Override
    public void start(Stage stage) {

        UserInputHandler handler = new UserInputHandler();
        BorderPane borderPane = new BorderPane();
        BorderPane topPane = new BorderPane();
        GridPane gridPane = generateGridPane();
        stage.setTitle("ShoppingList");
        Button addButton = new Button("Add");
        Scene scene = new Scene(borderPane, 640, 480);
        final TextField amount = new TextField();
        amount.setPromptText("Enter the amount");
        final TextField name = new TextField();
        name.setPromptText("Enter the name");
        Text listArea = new Text();
        String yourList = "Your list:\n";
        listArea.setText(yourList);
        
        // Initializing the top navigation bar.
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");

        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        MenuItem exit = new MenuItem("Exit");     
        menuFile.getItems().addAll(save,
                                    load,
                                    exit);

        Menu menuAbout = new Menu("About");
        MenuItem aboutList = new MenuItem("About this Shopping List");
        menuAbout.getItems().add(aboutList);
        
        menuBar.getMenus().addAll(menuFile, menuAbout);
        
        stage.setScene(scene);
        stage.show();

        // Setting positions and layouts for the elements.
        BorderPane.setAlignment(addButton, Pos.CENTER);
        borderPane.setPadding(new Insets(0, 0, 10, 0));
        gridPane.setPadding(new Insets(10));
        topPane.setTop(menuBar);
        borderPane.setTop(topPane);
        borderPane.setCenter(listArea);
        borderPane.setBottom(gridPane);
        gridPane.add(amount, 0, 0);
        gridPane.add(name, 1, 0);
        gridPane.add(addButton, 3, 0);

        // Set the function of the add button.
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (amount.getText().length() != 0 && name.getText().length() != 0) {
                    String input = amount.getText() + " " + name.getText() + ";";
                           
                    if (!handler.cutStrings(input)) {
                        
                        amount.setStyle("-fx-control-inner-background: rgb(213, 72, 56)");
                    } else {

                        listArea.setText(yourList + handler.getList());
                        amount.setStyle("");
                        name.setStyle("");
                    }
                } else {

                    amount.setStyle("-fx-control-inner-background: rgb(213, 72, 56)");
                    name.setStyle("-fx-control-inner-background: rgb(213, 72, 56)");
                }
            }
        });

        // Set the function of the save button.
        save.setOnAction((e) -> saveFile(listArea));

        // Set the function of the load button.
        load.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {

                    FileChooser chooser = new FileChooser();
                    File file = chooser.showOpenDialog(null);

                    if (file != null) {
                        try{
                            
                            handler.list.clear();
                            BufferedReader reader = new BufferedReader(new FileReader(file));

                            try{

                                String line;
                                int count = 0;

                                while ((line = reader.readLine()) != null) {
                                    
                                    if (count == 0) {

                                        count++;
                                    } else {

                                        handler.cutStrings(line + ";");
                                        count++;
                                    }
                                }
                                
                                listArea.setText(yourList + handler.getList());
                                handler.printList();
                            } catch (IOException ux) {

                                System.out.println("Not a valid file");
                            }
                        } catch (FileNotFoundException ex) {

                            System.out.println("File not found");
                        }
                    }
                }
            });
        
        // Set the function of the exit button.
        exit.setOnAction((e) -> Platform.exit());

        // Set the function of the about button.
        aboutList.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {

                            Desktop.getDesktop().open(new File("README.txt"));
                        }
                    } catch (IOException | IllegalArgumentException ex) {
                        System.out.println("Cannot find 'README.txt'.");
                    }
                }
            }
        });

        // Set the size of the window
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2); 
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    /**
     * Creates the gridpane.
     * 
     * @return gridpane layout.
     */
    public GridPane generateGridPane() {

        GridPane root = new GridPane();
        
        return root;
    }

    @Override
    public void stop() {

    }

    /**
     * Saves the list onto a file.
     * 
     * @param list is the list.
     */
    public void saveFile(Text list) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose location to save the shopping list");

        File selectedFile = null;
        File file = null;
        PrintWriter outFile = null;

        selectedFile = chooser.showSaveDialog(null);
        
        try {

            file = selectedFile;

            outFile = new PrintWriter(file);

            outFile.println(list.getText());
            
            outFile.close();
        } catch (Exception e) {

            System.out.println("File save canceled.");
        }
    }

    /**
     * Launches the gui.
     * 
     * @param args not used here.
     */
    public static void main(String args[]) {
        launch(args);
    }
}
