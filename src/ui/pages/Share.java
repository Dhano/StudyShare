//@author: Ashutosh

package ui.pages;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import ui.core.Server;
import ui.core.services.microservices.database.DatabaseHelper;
import ui.pages.constants.BasicController;

import java.util.ArrayList;
////This class is the controller class for Share.fxml file and this is used to initialize the elements of Share.fxml
// which enables the server to send  files to groups by clicking on the group buttons
//The essentials of this class is
//1. This class displays all the groups (in form of buttons) for the current server
//2. By clicking on the desired group a new scene appears which displays all the users for that particular group

//NOTE- THIS CLASS AND ITS CORRESPONDING FXML WILL BE USED WHEN THE SERVER CLICKS Share from dashboard options
public class Share implements BasicController{

    //Supporting objects
    FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml//startSharing.fxml"));

    //Objects required for this file
    @FXML
    Button btn_share;
    @FXML
    GridPane Grid;
    @FXML
    StackPane parent;

    AnchorPane startSharingRoot;

    //this method is used to initialize all the necessary components before the fxml is displayed
    public void initialize()
    {
        //linking the startSharingController with ShareController
        StartSharing ssC=null;
        try{
            loader.load();
            ssC=loader.getController();
            System.out.println("After loading");
            startSharingRoot=(AnchorPane)loader.getRoot();
            ssC.setMyRoot(startSharingRoot);
            ssC.setMyStackPane(parent);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //method which will create Groups(button) which is accepting arraylist
    //will be displaying buttons in column of 3
    public void showGroups() throws Exception {
        //ArrayList will be initialized by database
//        /Random random = new Random()
        //random.nextInt(9)+9;
        ArrayList arr = DatabaseHelper.readBatchName();
        int arraySize= arr.size();
        Button[] btn = new Button[arraySize];
        int k=0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3&&k<arraySize; j++) {
                    btn[k] = new Button((String) arr.get(k));

                    //event handler functions which loads the the panel of users list when the groups(button) are clicked
                    btn[k].setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            Server server=new Server(((Button)event.getSource()).getText());
                            parent.getChildren().add(
                                    startSharingRoot);
                        }
                    });
                    //populating the grid layout which displays the group names
                    btn[k].getStyleClass().add("all_btnBlueBackground");
                    btn[k].setId("share_btnGroup");
                    btn[k].setPadding(new Insets(5));
                    btn[k].setPrefHeight(200);
                    btn[k].setPrefWidth(250);
                    btn[k].setAlignment(Pos.CENTER);
                    Grid.add(btn[k], j, i);
                    k++;
                }
            }
        }
        //Grid.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid////    ********** getters for this file **********
    public Button getBtn_share() {
        return btn_share;
    }

    public GridPane getMyGrid() {
        return Grid;
    }

    public StackPane getMyParent() {
        return parent;
    }


//    ********** setters for this file **********


    public void setBtn_share(Button btn_share) {
        this.btn_share = btn_share;
    }

    public void setMyGrid(GridPane grid) {
        Grid = grid;
    }

    public void setMyParent(StackPane parent) {
        this.parent = parent;
    }


    public Node getRoot(){
        try {
            showGroups();

        }
        catch (Exception e){}
        return parent;
    }

    public void setPageKeeper(PageKeeper pg){
//        hey not required here
    }

    public void refreshPage(){
        /*Code for DHIREN*/
    }
}




