/*@Author Dhananjay
* this will show a page which will contain a page manager and dashboard options*/

package ui.pages;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import  javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.pages.constants.BasicController;
import ui.pages.constants.PageConstants;
import ui.pages.constants.PageContantsForDashboard;

public class Dashboard implements PageConstants,BasicController{

    /*Required objects of pageKeeper.fxml*/
    @FXML
    StackPane dashboardRoot;/*the main pane which containes all the other pans*/

        @FXML
        BorderPane defaultHolder;/*the default page*/

            @FXML
            AnchorPane defaultHolderTop;/*this is used in borderpane top to hold logo and the button which will open dashboard */

        @FXML
        AnchorPane dashboardHolder;/*pane which conatines dashboard*/

            @FXML
            AnchorPane dashBoardControlsHolder;/*the original dashboard which will be shown*/

        @FXML
        AnchorPane pageHolder;/*pane which containas Pagination*/



    /*Required object of fxml controllers*/
    ManageGroup manageGroup;
    ManageUser manageUser;
    Share share;
    Recent recent;
    ScrollableRecent scrollableRecent;


    //RecentsController recentsController;

    //ServerAccountController serverAccountController;

    DisplayYourIp yourIp;

    /*Supporting Objects*/
    TranslateTransition translateTransitionForDashboard;

    FXMLLoader manageUserLoader,manageGroupLoader,shareLoader,recentLoader,scrollableRecentLoader;

    BasicController currentPage;

    /*this method will initialize other fxml files cotrollers and add them to pageManager*/
    @FXML protected void initialize() {
        try {

        /*initilize all the pages*/
            initControllers();
            /*Init top and add label and button of Borderpane*/
            initDefaultHolder();
            /*init the dashboard*/
            initDashboardHolder();
            /*inint pages in pagination*/
            initPageHolder();
        }catch(Exception e){
            System.out.println("Inside initiakize in Dashboard"+e);
        }
        System.out.println("End of PageKeeper");
    }

    /*This will init all the pages so that they can be added*/
    private void initControllers(){
        try{

            manageUserLoader=new FXMLLoader();
            manageUserLoader.setLocation(getClass().getResource("fxml//manageUser.fxml"));
            manageUserLoader.load();
            manageUser=(ManageUser) manageUserLoader.getController();

            manageGroupLoader=new FXMLLoader();
            manageGroupLoader.setLocation(getClass().getResource("fxml//manageGroup.fxml"));
            manageGroupLoader.load();
            manageGroup=(ManageGroup)manageGroupLoader.getController();

            shareLoader=new FXMLLoader();
            shareLoader.setLocation(getClass().getResource("fxml//share.fxml"));
            shareLoader.load();
            share=(Share)shareLoader.getController();

            recentLoader=new FXMLLoader();
            recentLoader.setLocation(getClass().getResource("fxml//recent.fxml"));
            recentLoader.load();
            recent=(Recent) recentLoader.getController();

            /*FOR MAIN DISPLAY*/
            scrollableRecentLoader=new FXMLLoader();
            scrollableRecentLoader.setLocation(getClass().getResource("fxml//scrollableRecent.fxml"));
            scrollableRecentLoader.load();
            scrollableRecent=(ScrollableRecent)scrollableRecentLoader.getController();

            yourIp=new DisplayYourIp();

        }catch(Exception e){
            System.out.println("Iniside Dashboard.java initnocntrollers exception"+e);
        }
    }

    private void initDefaultHolder(){
        /*MenuItem mu;

        FontAwesomeIconView item = new FontAwesomeIconView(FontAwesomeIcon.ASL_INTERPRETING);
        item.getStyleClass().add("close_icon");
        FontAwesomeIconView item2 = new FontAwesomeIconView(FontAwesomeIcon.AMERICAN_SIGN_LANGUAGE_INTERPRETING);
        FontAwesomeIconView item3 = new FontAwesomeIconView(FontAwesomeIcon.APPLE);
        item2.getStyleClass().add("close_icon");
        item3.getStyleClass().add("close_icon");
        mu=new MenuItem("Dhananjay",new Button("Hello"));
        circlePopupMenu=new CirclePopupMenu(defaultHolder,MouseButton.SECONDARY);
        circlePopupMenu.getItems().add(mu);
        circlePopupMenu.getItems().add(new MenuItem("Dhananjya1",item2));
        circlePopupMenu.getItems().add(new MenuItem("Dhananjya2",item3));*/
        //circlePopupMenu.getItems().add(new MenuItem("Dhananjya3",item));


        Button show_dashboard;
        Label defaultHolderTitle;
        FontAwesomeIconView show_dashboard_Icon;

        show_dashboard=new Button();
        defaultHolderTitle=new Label("STUDY SHARE");
        show_dashboard_Icon= new FontAwesomeIconView(FontAwesomeIcon.REORDER);

        defaultHolderTitle.setPrefSize(500,100);
        defaultHolderTitle.setFont(Font.font(27));
        show_dashboard.setGraphic(show_dashboard_Icon);
        //show_dashboard_Icon.setOpacity(0.1);
        show_dashboard_Icon.getStyleClass().add("show_icon");
        show_dashboard.getStyleClass().add("show_dashboard");

        show_dashboard.setPrefSize(50,50);
        show_dashboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dashboardHolder.setVisible(true);
                animateIn();
            }
        });

        AnchorPane.setTopAnchor(show_dashboard, new Double("10"));
        AnchorPane.setLeftAnchor(show_dashboard,new Double("10"));
        AnchorPane.setTopAnchor(defaultHolderTitle,new Double("10"));
        AnchorPane.setLeftAnchor(defaultHolderTitle,new Double("340"));
        defaultHolderTop.getChildren().addAll(show_dashboard,defaultHolderTitle);
        System.out.println("*********************************************"+scrollableRecent.getRoot());
        defaultHolder.setBottom(scrollableRecent.getRoot());
        //defaultHolder.setCenter(yourIp.getRoot("192.767867"));
    }

    private void initDashboardHolder(){
        JFXButton hide_dashBoard;
        JFXButton showSharePage;
        JFXButton showManageGroupPage;
        JFXButton showManageUserPage;
        JFXButton showRecentPage;
        FontAwesomeIconView hide_dashboard_icon;

        hide_dashBoard=new JFXButton();
        showManageGroupPage=new JFXButton("Manage Group");
        showManageUserPage=new JFXButton("Manage User");
        showSharePage=new JFXButton("Share");
        showRecentPage=new JFXButton("Recents");

        hide_dashboard_icon = new FontAwesomeIconView(FontAwesomeIcon.REMOVE);
        hide_dashboard_icon.getStyleClass().add("hide_icon");
        hide_dashBoard.getStyleClass().add("hide_dashboard");
        hide_dashBoard.setGraphic(hide_dashboard_icon);
        hide_dashBoard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                animateOut();
            }
        });
        showManageGroupPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dashboardHolder.setVisible(false);
                pageHolder.setVisible(true);
                setCurrentPageIndex(PageContantsForDashboard.GROUP_PAGE);
            }
        });
        showManageUserPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dashboardHolder.setVisible(false);
                pageHolder.setVisible(true);
                setCurrentPageIndex(PageContantsForDashboard.USER_PAGE);
            }
        });
        showSharePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dashboardHolder.setVisible(false);
                pageHolder.setVisible(true);
                setCurrentPageIndex(PageContantsForDashboard.SHARE_PAGE);
            }
        });
        showRecentPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dashboardHolder.setVisible(false);
                pageHolder.setVisible(true);
                setCurrentPageIndex(PageContantsForDashboard.RECENT_PAGE);
            }
        });

        AnchorPane.setTopAnchor(hide_dashBoard,new Double("0"));
        AnchorPane.setLeftAnchor(hide_dashBoard,new Double("252"));
        AnchorPane.setTopAnchor(showManageGroupPage,new Double("100"));
        AnchorPane.setLeftAnchor(showManageGroupPage,new Double("50"));
        AnchorPane.setTopAnchor(showManageUserPage,new Double("200"));
        AnchorPane.setLeftAnchor(showManageUserPage,new Double("50"));
        AnchorPane.setTopAnchor(showSharePage,new Double("300"));
        AnchorPane.setLeftAnchor(showSharePage,new Double("50"));
        AnchorPane.setTopAnchor(showRecentPage,new Double("400"));
        AnchorPane.setLeftAnchor(showRecentPage,new Double("50"));

        dashBoardControlsHolder.getChildren().addAll(hide_dashBoard,showManageGroupPage,showManageUserPage,showSharePage,showRecentPage);

        dashboardHolder.setVisible(false);
    }

    private void initPageHolder(){
         /*Calls and sets the reuired page to be shown*/
        JFXButton hideCurrentPage;
        hideCurrentPage=new JFXButton();

        FontAwesomeIconView hide_dashboard_icon = new FontAwesomeIconView(FontAwesomeIcon.REMOVE);
        hide_dashboard_icon.getStyleClass().add("hide_icon");
        hideCurrentPage.getStyleClass().add("btn-raised");
        hideCurrentPage.setGraphic(hide_dashboard_icon);
        hideCurrentPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pageHolder.setVisible(false);
            }
        });
        AnchorPane.setLeftAnchor(hideCurrentPage,new Double("770"));
        AnchorPane.setTopAnchor(hideCurrentPage,new Double("40"));


        pageHolder.setVisible(false);

        /*Modified by dhs*/
        AnchorPane.setLeftAnchor(manageUser.getRoot(),new Double(0));
        AnchorPane.setTopAnchor(manageUser.getRoot(),new Double(0));
        AnchorPane.setLeftAnchor(manageGroup.getRoot(),new Double(0));
        AnchorPane.setTopAnchor(manageGroup.getRoot(),new Double(0));
        AnchorPane.setLeftAnchor(share.getRoot(),new Double(0));
        AnchorPane.setTopAnchor(share.getRoot(),new Double(0));
        AnchorPane.setLeftAnchor(recent.getRoot(),new Double(0));
        AnchorPane.setTopAnchor(recent.getRoot(),new Double(0));
        pageHolder.getChildren().addAll(manageGroup.getRoot(),manageUser.getRoot(),share.getRoot(),recent.getRoot());
        //pageHolder.getChildren().add(hideCurrentPage);
        /*Initial state of all the pages*/
        manageGroup.getRoot().setVisible(false);
        manageUser.getRoot().setVisible(false);
        share.getRoot().setVisible(false);
        recent.getRoot().setVisible(false);
        pageHolder.getChildren().add(hideCurrentPage);
      //  manageUser.getRoot().setVisible(true);

    }

    public void setCurrentPageIndex(Integer pageIndex) {
        try{
            System.out.println("Showing page at index 0");
            if(currentPage!=null)
                currentPage.getRoot().setVisible(false);
            currentPage=pageSelector(pageIndex);
            currentPage.getRoot().setVisible(true);
        }
        catch (Exception e){
        }
    }

    private BasicController pageSelector(int pageIndex) {

        if (pageIndex==PageContantsForDashboard.GROUP_PAGE){
            System.out.println(manageGroup);
            return manageGroup;
        }
        else if(pageIndex==PageContantsForDashboard.USER_PAGE){
           return manageUser;
        }
        else if(pageIndex==PageContantsForDashboard.SHARE_PAGE){
            return share;
        }
        else if(pageIndex==PageContantsForDashboard.RECENT_PAGE){
            return recent;
        }

        return null;
    }


    public void setPageKeeper(PageKeeper pk){
        /*Not required here*/
    }

    public Node getRoot(){
        refreshPage();
        return dashboardRoot;
    }

    public void refreshPage(){

    }

    public void animateIn(){
        translateTransitionForDashboard=new TranslateTransition(Duration.millis(600),dashBoardControlsHolder);
        translateTransitionForDashboard.setFromX(-600.0);
        translateTransitionForDashboard.setToX(0.0);
        translateTransitionForDashboard.play();
    }

    public void animateOut() {
        translateTransitionForDashboard=new TranslateTransition(Duration.millis(600),dashBoardControlsHolder);
        translateTransitionForDashboard.setFromX(0.0);
        translateTransitionForDashboard.setToX(-600.0);
        translateTransitionForDashboard.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dashboardHolder.setVisible(false);
            }
        });
        translateTransitionForDashboard.play();
    }

}

class DisplayYourIp extends AnchorPane{
    public Node getRoot(String ip){
        this.getChildren().add(new Label("Your Ip is "+ip));
        return this;
    }
}


