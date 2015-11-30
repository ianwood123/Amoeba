/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Commands.CommandFactory;
import Exceptions.MessageException;
import Exceptions.MissingClassReferenceException;
import MentalWookieYodels.MessageQueue;
import MentalWookieYodels.NetworkMessageQueue;
import Messages.Message;
import Messages.MessageFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author woodi
 */
public class GameInputHandler extends GameMessageHandler {

    public GameInputHandler(NetworkMessageQueue net, MessageQueue q, CommandFactory of, MessageFactory mf) {
        super(net, q, of, mf);
    }

    @Override
    public void run() {
            if(!net.isEmpty()){
                Message m = null;
                try {
                     m = mf.createMessage(net.getNextMessage());
                } catch (MessageException | MissingClassReferenceException ex) {
                    Logger.getLogger(GameInputHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(m != null){
                    this.queue.add(m);
                }
                CommandFactory.world.commands.add(CommandFactory.getCommandObject(queue.getNextMessage()));
            }
        }
    
}
