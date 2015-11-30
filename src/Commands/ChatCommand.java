/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Game.GameWorld;
import Messages.ChatMessage;

/**
 *
 * @author woodi
 */
public class ChatCommand implements MessageCommand<ChatMessage>{
    ChatMessage chatmessage;
    GameWorld world;
    
    ChatCommand(ChatMessage cm, GameWorld gw){
        chatmessage = cm;
        world = gw;
    }
    
    @Override
    public void execute() {
        
    }
    
    
}
