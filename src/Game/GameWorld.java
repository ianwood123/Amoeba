/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Commands.Command;
import Commands.CommandFactory;
import GameObjects.GameObject;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import javafx.scene.Node;

/**
 *
 * @author woodi
 */
public class GameWorld implements Runnable{
    private final CommandProcessor commandProcessor;
    public final CommandFactory commandFactory;
    boolean exiting = false;
    protected ConcurrentLinkedQueue<Command> commands;
    protected ConcurrentLinkedQueue<GameObject> objects;
    

    
    public GameWorld() {
        this.commandProcessor = new CommandProcessor(this);
        this.commandFactory = new CommandFactory(this);
        commands = new ConcurrentLinkedQueue();
        objects = new ConcurrentLinkedQueue();
    }

    public void addGameObject(GameObject go){
        this.objects.add(go);
    }
    
    public ArrayList<Node> getModels(){
        ArrayList<Node> models = new ArrayList();
        
        for(GameObject go : this.objects){
            models.add(go.getModel());
        }
        return new ArrayList<>(models);
    }
    
    @Override
    public void run() {
            if(!commands.isEmpty()){
                commandProcessor.process(commands);
            }
            if(!objects.isEmpty())
            {   for(GameObject go: objects){
                    if(go.tooDirty()){
                        objects.remove(go);
                    }
                }
            }
            
        }


    public Iterable<GameObject> getObjects() {
        return this.objects;
    }
}