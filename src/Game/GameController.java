/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import MentalWookieYodels.MessageQueue;
import MentalWookieYodels.NetworkMessageQueue;
import Messages.MessageFactory;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import screen.Screen;

/**
 *
 * @author woodi
 */
public class GameController {

    
    final GameOutputHandler goh;
    final GameInputHandler gih;
    
    final GameWorld world;

   
    
    ExecutorService exe = Executors.newFixedThreadPool(3);
    
    public GameController(NetworkMessageQueue input, NetworkMessageQueue output, MessageFactory mf, MessageQueue in, MessageQueue out, Screen s){

        world = new GameWorld(s);
      
        goh = new GameOutputHandler(output,out,world.commandFactory,mf);
        gih = new GameInputHandler(input,in,world.commandFactory,mf);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(16),(ActionEvent e)->{  
        
        exe.execute(world);
        exe.execute(goh);
        exe.execute(gih);

        }));
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    
    public GameWorld getWorld(){
        return this.world;
    }
}
