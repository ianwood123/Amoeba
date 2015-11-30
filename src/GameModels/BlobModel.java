/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModels;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author woodi
 */
public class BlobModel extends Circle {

    
    public BlobModel(Double x, Double y, Double width, Color color){
        super(x,y,width/2,color);
    }
    
}
