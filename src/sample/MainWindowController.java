package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    DBconnect mdc = new DBconnect();
    Connection con = getConnection();
    Statement stmt;

    ObservableList<String> ciTimeList = FXCollections.observableArrayList("AM","PM");
    ObservableList<String> pkgsList = FXCollections.observableArrayList("Package 1","Package 2","Package 3");
    ObservableList<String> timeSlotList = FXCollections.observableArrayList("Day","Night");

    @FXML Button navBtn1,navBtn2,navBtn3,navBtn4,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
    @FXML Pane rsPane,rrPane,sPane,repPane;
    @FXML ChoiceBox ciTime,pkgs,timeSlot;

    private int ro1=0,ro2=0,ro3=0,ro4=0,ro5=0,ro6=0,ro7=0,ro8=0,ro9=0,ro10=0;

    private Connection getConnection() {
        Connection myConn = mdc.getMyConnection();
        return myConn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navBtn1.setStyle("-fx-background-color: #a3b5d9");
        ciTime.setItems(ciTimeList);
        pkgs.setItems(pkgsList);
        timeSlot.setItems(timeSlotList);
        reservedRoom();
        rsPane.toFront();
    }

    private void reservedRoom() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String myStatement;

        myStatement="select * from bill,bill_reservation,reservation where BillID = Bill_id and Reservation_id = ReservationID and (Check_in_date <= \""+date.format(now)+"\" and (Check_out_date >= \""+date.format(now)+"\" or Check_out_date is NULL)) and (Check_in_time <=\""+time.format(now)+"\" and (Check_out_time >= \""+time.format(now)+"\" or Check_out_time is NULL))";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(myStatement);

            while(rs.next()){
                if(rs.getInt("RoomNo") == 1){
                    ro1=3;
                    r1.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 2){
                    ro2=3;
                    r2.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 3){
                    ro3=3;
                    r3.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 4){
                    ro4=3;
                    r4.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 5){
                    ro5=3;
                    r5.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 6){
                    ro6=3;
                    r6.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 7){
                    ro7=3;
                    r7.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 8){
                    ro8=3;
                    r8.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 9){
                    ro9=3;
                    r9.setStyle("-fx-background-color: red");
                }else if(rs.getInt("RoomNo") == 10){
                    ro10=3;
                    r10.setStyle("-fx-background-color: red");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(myStatement);
    }

    public void NavBtn1OnAction(ActionEvent event){
        navBtn1.setStyle("-fx-background-color: #a3b5d9");
        navBtn2.setStyle("-fx-background-color: #0c447b");
        navBtn3.setStyle("-fx-background-color: #0c447b");
        navBtn4.setStyle("-fx-background-color: #0c447b");
        rsPane.toFront();

    }

    public void NavBtn2OnAction(ActionEvent event){
        navBtn2.setStyle("-fx-background-color: #a3b5d9");
        navBtn1.setStyle("-fx-background-color: #0c447b");
        navBtn3.setStyle("-fx-background-color: #0c447b");
        navBtn4.setStyle("-fx-background-color: #0c447b");
        rrPane.toFront();
    }

    public void NavBtn3OnAction(ActionEvent event){
        navBtn3.setStyle("-fx-background-color: #a3b5d9");
        navBtn2.setStyle("-fx-background-color: #0c447b");
        navBtn1.setStyle("-fx-background-color: #0c447b");
        navBtn4.setStyle("-fx-background-color: #0c447b");
        sPane.toFront();

    }

    public void NavBtn4OnAction(ActionEvent event){
        navBtn4.setStyle("-fx-background-color: #a3b5d9");
        navBtn2.setStyle("-fx-background-color: #0c447b");
        navBtn1.setStyle("-fx-background-color: #0c447b");
        navBtn3.setStyle("-fx-background-color: #0c447b");
        repPane.toFront();

    }

    public void r7OnAction(ActionEvent event) throws IOException {
        if (ro7 == 1) {
            r7.setStyle("-fx-background-color: green");
            ro7 = 0;
        }else if(ro7 == 0){
            r7.setStyle("-fx-background-color: #dede01");
            ro7 =1;
        }else if(ro7== 3){
            Parent root2 = FXMLLoader.load(getClass().getResource("CheckoutWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Check Out");
            stage.setScene(new Scene(root2));
            stage.show();
        }
    }


}
