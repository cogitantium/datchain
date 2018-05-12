package dk.aau.cs.a311c.datchain.gui;

import dk.aau.cs.a311c.datchain.Block;
import dk.aau.cs.a311c.datchain.Blockchain;
import dk.aau.cs.a311c.datchain.Datchain;
import dk.aau.cs.a311c.datchain.Search;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.geometry.Pos.CENTER;


public class MainScreen {

    static ArrayList<Block> searchResults = new ArrayList<Block>();
    static ListView listView = new ListView<>();
    static Text firstNameText = new Text();
    static Text lastNameText = new Text();
    static Text birthdateText = new Text();
    static Text publicKeyText = new Text();
    static Text identityText = new Text();

    public static void screen(Stage primaryStage, Blockchain chain) {
        GridPane gridLeft = new GridPane();
        gridLeft.setVgap(10);
        gridLeft.setHgap(8);
        gridLeft.setPadding(new Insets(10, 20, 10, 20));
        primaryStage.setResizable(false);


        //top panel
        HBox topPanel = new HBox();
        topPanel.setPadding(new Insets(5, 0, 10, 0));

        Button login_button = new Button("Login as validator");
        login_button.setOnMouseClicked(event -> Login.login(primaryStage));
        login_button.setMinWidth(125);

        topPanel.getChildren().add(login_button);
        topPanel.setAlignment(CENTER);


        //Left panel
        Label firstname_label = new Label("First name:");
        firstname_label.setMinWidth(75);
        gridLeft.setConstraints(firstname_label, 1, 1);
        gridLeft.getChildren().add(firstname_label);

        Label lastname_label = new Label("Last name:");
        lastname_label.setMinWidth(75);
        gridLeft.setConstraints(lastname_label, 1, 2);
        gridLeft.getChildren().add(lastname_label);

        Label birthdateLabel = new Label("Birthdate:");
        birthdateLabel.setMinWidth(75);
        gridLeft.setConstraints(birthdateLabel, 1, 3);
        gridLeft.getChildren().add(birthdateLabel);

        Label pbkLabel = new Label("Public key:");
        pbkLabel.setMinWidth(75);
        gridLeft.setConstraints(pbkLabel, 1, 4);
        gridLeft.getChildren().add(pbkLabel);

        Label identityLabel = new Label("Identity:");
        identityLabel.setMinWidth(75);
        gridLeft.setConstraints(identityLabel, 1, 5);
        gridLeft.getChildren().add(identityLabel);


        Text text1 = new Text("            Chosen person");
        text1.setStyle("-fx-font-weight: bold");
        gridLeft.setConstraints(text1, 2, 0);
        gridLeft.getChildren().add(text1);


        gridLeft.setConstraints(firstNameText, 2, 1);
        gridLeft.getChildren().add(firstNameText);


        gridLeft.setConstraints(lastNameText, 2, 2);
        gridLeft.getChildren().add(lastNameText);


        gridLeft.setConstraints(birthdateText, 2, 3);
        gridLeft.getChildren().add(birthdateText);


        gridLeft.setConstraints(publicKeyText, 2, 4);
        gridLeft.getChildren().add(publicKeyText);


        gridLeft.setConstraints(identityText, 2, 5);
        gridLeft.getChildren().add(identityText);



        //right panel
        GridPane gridRight = new GridPane();
        gridRight.setVgap(10);
        gridRight.setHgap(8);
        gridRight.setPadding(new Insets(10, 20, 10, 100));



        listView.setMinWidth(200);
        listView.setMaxHeight(120);


        gridRight.setConstraints(listView, 0, 1, 1, 1);
        gridRight.getChildren().add(listView);

        /*search*/
        /*Label term_label = new Label("Input:");
        term_label.setMinWidth(150);
        gridRight.setConstraints(term_label,0,0);
        gridRight.getChildren().add(term_label);*/
        TextField term_text = new TextField();
        term_text.setPromptText("Search for name, date of birth, public key");
        term_text.setOnAction( e ->  {
            searchResults = getSearchResults(term_text.getText().toString(), chain);
            listView.getItems().clear();
            listView.getItems().add(0,searchResults.get(0).getIdentity());
            listView.getItems().add(1,searchResults.get(1).getIdentity());
            listView.getItems().add(2,searchResults.get(2).getIdentity());
        });

        gridRight.setConstraints(term_text, 0, 0);
        gridRight.getChildren().add(term_text);

        Button selectBlockButton = new Button("Select");
        selectBlockButton.setOnMouseClicked(e -> setChosenBlockDetails());
        gridRight.getChildren().add(selectBlockButton);
        selectBlockButton.setAlignment(CENTER);
        gridRight.setConstraints(selectBlockButton,0,2);



        //bottompanel
        HBox bottomPanel = new HBox();
        bottomPanel.setSpacing(150);
        bottomPanel.setAlignment(CENTER);
        bottomPanel.setPadding(new Insets(10, 0, 5, 0));

        String status = "Online";
        Text onlineLabel = new Text(status);
        if (status.equals("Online")) {
            onlineLabel.setFill(Color.GREEN);
        } else onlineLabel.setFill(Color.RED);


        int numberOfNodes = 10;
        Text nodesLabel = new Text("Nodes:  " + numberOfNodes);

        int numberOfBlocks = 497;
        Text blocksLabel = new Text("Blocks:  " + numberOfBlocks);

        bottomPanel.getChildren().addAll(nodesLabel, onlineLabel, blocksLabel);


        //close action
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });

        //setting scene
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topPanel);
        borderPane.setLeft(gridLeft);
        borderPane.setRight(gridRight);
        borderPane.setBottom(bottomPanel);

        Scene scene = new Scene(borderPane, 620, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void closeProgram(Stage primaryStage) {
        boolean answer = CloseProgram.display("Sure you want to exit?");
        if (answer) {
            primaryStage.close();
        }
    }

    private static ArrayList<Block> getSearchResults(String searchTerm, Blockchain chain) {
        System.out.println("Searching for " + searchTerm + " In the chain");
        Search search = new Search();
        ArrayList<Block> results = search.FuzzySearchIdentity((searchTerm), chain, 3);
        return results;
    }

    private static void setChosenBlockDetails() {
        ObservableList<String> string = listView.getSelectionModel().getSelectedItems();

        //only works if first name is not the same
        if (string.get(0).equals(searchResults.get(0).getIdentity())) {
            firstNameText.setText(searchResults.get(0).getIdentity());
            //lastNameText.setText(block.get(0).getIdentity());
            //birthdateText.setText(block.get(0).getIdentity());
            //publicKeyText.setText(searchResults.get(0).getIdentityPublicKey());
            //identityText.setText(block.get(0).getIdentity());
        }
        if (string.get(0).equals(searchResults.get(1).getIdentity())) {
            firstNameText.setText(searchResults.get(1).getIdentity());
            //lastNameText.setText(block.get(1).getIdentity());
            //birthdateText.setText(block.get(1).getIdentity());
            //publicKeyText.setText(searchResults.get(1).getIdentityPublicKey());
            //identityText.setText(block.get(1).getIdentity());
        }
        if (string.get(0).equals(searchResults.get(2).getIdentity())) {
            firstNameText.setText(searchResults.get(2).getIdentity());
            //lastNameText.setText(block.get(2).getIdentity());
            //birthdateText.setText(block.get(2).getIdentity());
            //publicKeyText.setText(searchResults.get(2).getIdentityPublicKey());
            //identityText.setText(block.get(2).getIdentity());
        }




    }

}
