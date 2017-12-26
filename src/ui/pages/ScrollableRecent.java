package ui.pages;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ScrollPane;

import ui.core.Server;
import ui.core.services.microservices.database.DatabaseHelper;
import ui.pages.constants.BasicController;

import java.util.ArrayList;

public class ScrollableRecent implements BasicController {

    //Supporting objects
  //  FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml//startSharing.fxml"));

    @FXML ScrollPane sp;
    @FXML FlowPane fp;
    @FXML AnchorPane ap;

    //   Disabling scrollbars
    public void initialize()throws Exception{
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //getNumberOfRecentGroups();

        //linking the startSharingController with ShareController

    }

    //recents tab
    //method which will create buttons(Groups) which is accepting arraylist
    public void getNumberOfRecentGroups()  {
        try {
            ArrayList arr = DatabaseHelper.readBatchName();
            int arraySize = arr.size();
            int z = 0;


            JFXButton[] btn = new JFXButton[arraySize];
            while (z < arraySize) {
                btn[z] = new JFXButton(((String) arr.get(z)));
                System.out.println("Creating Buttons");
                //Anonymous method
                btn[z].setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        System.out.println("hey i was clicked");
                    }
                });
                btn[z].setId("btnRecent");
                btn[z].setPrefHeight(130);
                btn[z].setPrefWidth(188);
                fp.getChildren().add(btn[z]);
                btn[z].getStyleClass().add("all_btnBlueBackground");
                z++;
            }
            fp.setHgap(10);
        }catch (Exception e){
            System.out.println("Inside Exception of ashu");
            e.printStackTrace();
        }
    }


    public Node getRoot(){
        refreshPage();
        getNumberOfRecentGroups();
        System.out.println("Returning AnchorPane");
        return ap;
    }

    public void setPageKeeper(PageKeeper pg){
     /*Not required */
    }
    public void refreshPage(){
//        plz refresh it
    }

}





