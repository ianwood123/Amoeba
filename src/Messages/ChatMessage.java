/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

/**
 *
 * @author woodi
 */
public class ChatMessage extends Message{

    public final long recipientPlayerId;
    public final String text;
    
    public ChatMessage(long rpi, String txt){
        this.recipientPlayerId = rpi;
        this.text = txt;
    }
    
}