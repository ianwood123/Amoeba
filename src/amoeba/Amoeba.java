/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amoeba;

import Exceptions.MessageException;
import Exceptions.NonExistentFieldException;
import Game.GameController;
import MentalWookieYodels.Codec;
import MentalWookieYodels.CustomCodec;
import MentalWookieYodels.MessageQueue;
import MentalWookieYodels.NetworkController;
import MentalWookieYodels.NetworkMessageQueue;
import Messages.DefaultMessageFactory;
import Messages.LoginMessage;
import Messages.LogoutMessage;
import Messages.MoveTowardCoordinatesMessage;
import Messages.MessageFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import screen.Screen;

/**
 *
 * @author woodi
 */
public class Amoeba extends Application {
    NetworkController nc;
    NetworkMessageQueue outgoing;
    NetworkMessageQueue incoming;
    MessageQueue in;
    MessageQueue out;
    MessageFactory mf;
    GameController gc;
    Codec codec = new CustomCodec();
        final Rectangle r = new Rectangle(0,0,800,600);
    
    @Override
    public void start(Stage primaryStage) {

        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.BLACK);
        
        mf = new DefaultMessageFactory();
        in = new MessageQueue();
        out = new MessageQueue();
        outgoing = new NetworkMessageQueue();
        incoming = new NetworkMessageQueue();
        nc = new NetworkController("localhost", 8080, codec, incoming, outgoing);
        
        Screen screen = new Screen();
        screen.getGroup().getChildren().add(r);
        
        gc = new GameController(incoming, outgoing, mf, in, out, screen);
        
        
        StackPane container = new StackPane();
        container.getChildren().add(screen.getGroup());
        container.setMinSize(800, 600);
        Button btn = new Button();
        
        TextField username = new TextField();
        username.setEditable(true);
        username.setPromptText("Username");
        username.setMaxSize(200, 50);
        username.setAlignment(Pos.CENTER);
        
        PasswordField pass = new PasswordField();
        pass.setPromptText("Password");
        pass.setEditable(true);
        pass.setMaxSize(200,50);
        pass.setAlignment(Pos.CENTER);
        

        
        btn.setText("Play");
        btn.setOnAction((ActionEvent event) -> {
            try {
                outgoing.add(mf.createNetworkMessage(new LoginMessage(username.getText(), pass.getText())));
                nc.connect();
                                                                        
                //if successful, switch to game view
            } catch (MessageException | NonExistentFieldException ex) {
                Logger.getLogger(Amoeba.class.getName()).log(Level.SEVERE, null, ex);
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error 3401");
                a.setHeaderText("Error 3401: Programmers stuck in limbo.");
                a.setContentText("We have dispatched a team of monkeys to fix this error. In the meantime, please try again.");
                a.show();
            }
            finally{
                if(nc.checkConnection()){
                    System.out.println("Successfully connected to server. ");
                    primaryStage.setScene(new Scene(container));              
                }
                else{
                    System.exit(1);
                }
            }
        });
        VBox vb =  new VBox(username, pass, btn);
        
        
        vb.setAlignment(Pos.CENTER);
        StackPane root = new StackPane(vb);
        
        
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            LogoutMessage m = new LogoutMessage();
            try {
                outgoing.add(mf.createNetworkMessage(m));
                Thread.sleep(100);
            } catch (MessageException | NonExistentFieldException | InterruptedException ex) {
                Logger.getLogger(Amoeba.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);         
        });
        
        container.setOnMouseDragged((e)->{
        MoveTowardCoordinatesMessage m = new MoveTowardCoordinatesMessage(e.getX(),e.getY());
        try {
                outgoing.add(mf.createNetworkMessage(m));
            } catch (MessageException | NonExistentFieldException ex) {
                Logger.getLogger(Amoeba.class.getName()).log(Level.SEVERE, null, ex);
            }        
        });
        Timeline time = new Timeline();
        time.getKeyFrames().add(new KeyFrame(Duration.millis(100),(e)->{
                screen.update();
        }));
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
        
        primaryStage.setTitle("Amoeba");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}