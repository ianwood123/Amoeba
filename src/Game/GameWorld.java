/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Commands.Command;
import Commands.CommandFactory;
import DataModel.DataObject;
import FXModel.FXObjectGenerator;
import GameObjects.GameObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import screen.Screen;

/**
 *
 * @author woodi
 */
public class GameWorld implements Runnable{
    private final CommandProcessor commandProcessor;
    public final CommandFactory commandFactory;
    boolean exiting = false;
    protected ConcurrentLinkedQueue<Command> commands;
    private final Screen screen;
    
    public ObservableList<Long> deletionQueue = FXCollections.observableList(new ArrayList());
    
    public ObservableList<Node> nodes = FXCollections.observableList(new ArrayList());
    
    
    public GameWorld(Screen s) {
        this.commandProcessor = new CommandProcessor(this);
        this.commandFactory = new CommandFactory(this);
        this.commands = new ConcurrentLinkedQueue();
        this.screen = s;
        nodes.addListener((ListChangeListener.Change<? extends Node> o)->{
            while(o.next()){
                    if(o.wasAdded()){
                        for(Node n : o.getAddedSubList()){
                            if(n instanceof FXObjectGenerator){
                                FXObjectGenerator b = (FXObjectGenerator) n;
                                screen.getAdditionQueue().add((Node) b);
                            }
                        }
                    }
            }
        });
    }

    @Override
    public void run() {
                for(DataObject dom : screen.getDataQueue()){
                        GameObject go = (GameObject) dom;
                        if(go.tooDirty()){
                            deletionQueue.add(go.id.get());
                        }
                    }
                screen.getDataObjectRemovalQueue().addAll(deletionQueue);
                deletionQueue.clear();
                if(!commands.isEmpty()){
                    commandProcessor.process(commands);
                }
                
                
        
    }
    
    public synchronized Screen getScreen(){
        return this.screen;
    }
}