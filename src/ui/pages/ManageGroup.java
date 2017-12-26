package ui.pages;
//<!--@Autor Dhiren Chotwani-->
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.core.services.microservices.database.DatabaseHelper;
import ui.core.services.microservices.database.corefunction.DatabaseHandler;
import ui.pages.constants.BasicController;

import java.io.File;
import java.util.ArrayList;

//This class is the controller class for ManageGroups.fxml file and this is used to initialize the elements of manageGroups.fxml
// the essentials of this groups are:
//1. The main stack pane is controlled in this file
//2.load all the corresponding fxml files into the Stackpane
//3.performs the task of loading and displaying the files in the so created group along with files using scrollPane

//NOTE- THIS CLASS AND ITS CORRESPONDING FXML WILL BE USED WHEN THE SERVER CLICKS Manage and Create Groups FROM THE DASHBOARD

public class ManageGroup implements BasicController{

    //Supporting objects for all the fxmls
    FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml//makeGroup.fxml"));
    ObservableList<String> items = FXCollections.observableArrayList ();
    ObservableList<String> items1 = FXCollections.observableArrayList ();
    ArrayList tp=new ArrayList();

    // Objects refrenced from FXML file for this controller i.e ManageGroups.fxml
    @FXML
    StackPane parent;

    AnchorPane timepass;

    @FXML
    FileChooser fileChooser = new FileChooser();
    @FXML
    BorderPane borderPane;

    @FXML
    ScrollPane groupsScrollPane;

    @FXML
    ScrollPane filesScrollPane;

    @FXML
    Button add;
    @FXML
    Button edit;
    @FXML
    Button delete;

    @FXML
    Button add1;

    @FXML
    Button delete1;

    @FXML
    ListView<String> list;
    @FXML
    ListView<String> list1;


    private Stage myStage=new Stage();
    //this method is used to set the stage object to be used in this class
    public void setStage(Stage stage) {
        myStage = stage;
    }

    //Methods that will initialze all the necessary element for manageGroups.fxml
    @FXML protected  void initialize()
    {

        System.out.println("hey hello i am inside dhirens initilize");
        MakeGroup mgController=null;

        try{
            // This will load the makeGroup.fxml file which is used to create groups
            loader.load();
            mgController= loader.getController();



            //method of makeGroupController.java to set the stack pane of the makeGroup.fxml file
            mgController.setStackParent(parent);

            //some work to be done before initial functionality begins
//        edit.setDisable(true);
            delete.setDisable(true);

            delete1.setDisable(true);
            System.out.println("************************ CHECK THIS ***********************");
            tp= DatabaseHelper.readBatchName();
            System.out.println(tp.toString());
            items.addAll("HI","HI","HI","HI","HI","HI");
            list.setItems(items);

            items1.addAll("HI","HI","HI","HI","HI","HI");
            list1.setItems(items1);

        }
        catch (Exception e){
            System.out.println("Hello World"+e);
        }

        //setting content of scrollpane is to be done here
        //groupsScrollPane.setContent(new ImageView(String.valueOf(getClass().getResource("..//resources//images(3).jpg"))));
        //filesScrollPane.setContent(new ImageView(getClass().getResource("..//resources//images(3).jpg").toExternalForm()));

        try

        {
            //loading the root of makeGroup.fxml in order to be added on the stack pane
            timepass = (AnchorPane)loader.getRoot();
            mgController.setMyRoot(timepass);
        }catch (Exception e){}

    }

    //funtion to be called when add button is called in order to add multiple files in that particular group
    @FXML public void openFile(ActionEvent ae){
        Button btn=(Button)ae.getSource();
        if(btn==add1 ) {
            ArrayList<File> list =
                    (ArrayList<File>) fileChooser.showOpenMultipleDialog(myStage);

            // code to be written here to be passed to the db for adding the file paths in db
        }
        else if(btn==add || btn==edit ){
            borderPane.setStyle("-fx-opacity:0.5");
            parent.getChildren().add(timepass);
        }
    }

    //setters and getters for every element
    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Button getAdd() {
        return add;
    }

    public Button getAdd1() {
        return add1;
    }

    public Button getDelete() {
        return delete;
    }

    public Button getDelete1() {
        return delete1;
    }

    public Button getEdit() {
        return edit;
    }



    public void setAdd(Button add) {
        this.add = add;
    }

    public void setAdd1(Button add1) {
        this.add1 = add1;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public void setDelete1(Button delete1) {
        this.delete1 = delete1;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Node getRoot(){
        refreshPage();
        return parent;
    }
    public void setPageKeeper(PageKeeper pg){
//        not required
    }
    public void refreshPage(){

    }

}
