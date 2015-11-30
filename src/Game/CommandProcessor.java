/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Commands.Command;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author woodi
 */
public class CommandProcessor
{
    private final GameWorld world;
    
    public CommandProcessor(GameWorld world) {
                    this.world = world;
    }

    public void process(ConcurrentLinkedQueue<Command> commands){
        for(Command command : commands){
            command.execute();
        }
    }
}
