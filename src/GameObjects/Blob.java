/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import GameModels.BlobModel;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 *
 * @author woodi
 */
public class Blob extends GameObject{
    volatile BlobModel model;
    
    public Blob(Double x, Double y, Double width, Color c, Long id){
        super(id);
        model = new BlobModel(x,y, width/2, c);
    }

    @Override
    public Node getModel() {
        return this.model;
    }
    
    @Override
    public void updateModel(Double x, Double y, Double width, Color c){
        this.model.centerXProperty().set(x);
        this.model.centerYProperty().set(y);
        this.model.radiusProperty().set(width/2);
    }
    
}
