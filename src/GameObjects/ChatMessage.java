/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import GameModels.ChatMessageModel;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 *
 * @author woodi
 */
public class ChatMessage extends GameObject {

    final String message;
    final Long userID;
    ChatMessageModel model;

    ChatMessage(String m, Long id) {
        super(new Long(-100));
        this.message = m;
        this.userID = id;
        model = new ChatMessageModel(m, id);
    }

    @Override
    public Node getModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateModel(Double x, Double y, Double width, Color c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
