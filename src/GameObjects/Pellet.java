/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author woodi
 */
public class Pellet extends GameObject{
    public DoubleProperty xProperty;
    public DoubleProperty yProperty;
    public DoubleProperty radiusProperty;
    public ObjectProperty colorProperty;
    
    public Pellet(Double x, Double y, Color c, Long id){
        super(id);
        double xval = x;
        double yval = y;
        xProperty = new SimpleDoubleProperty(xval);
        yProperty = new SimpleDoubleProperty(yval);
        radiusProperty = new SimpleDoubleProperty(1);
        colorProperty = new SimpleObjectProperty(c);
        
    }   

    public Pellet(Pellet p) {
        super(p.id.get());
        xProperty = new SimpleDoubleProperty(p.xProperty.get());
        yProperty = new SimpleDoubleProperty(p.yProperty.get());
        radiusProperty = new SimpleDoubleProperty(p.radiusProperty.get());
        colorProperty = new SimpleObjectProperty(p.colorProperty.get());
    }
}
