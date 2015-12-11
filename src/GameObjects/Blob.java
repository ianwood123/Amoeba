/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import GameModels.BlobModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author woodi
 */
public class Blob extends GameObject{
    public DoubleProperty xProperty;
    public DoubleProperty yProperty;
    public DoubleProperty radiusProperty;
    public ObjectProperty colorProperty;
    public StringProperty usernameProperty;
    public Blob(Double x, Double y, Double width, Color c, String username, Long id){
        super(id); 
        double xval = x;
        double yval = y;
        double widthval = width;
        Color cval = c;
        String user = username;
        xProperty = new SimpleDoubleProperty(xval);
        yProperty = new SimpleDoubleProperty(yval);
        radiusProperty = new SimpleDoubleProperty(widthval/2);
        colorProperty = new SimpleObjectProperty(cval);
        usernameProperty = new SimpleStringProperty(user);
        
    }
    
    public Blob(Blob b){
        super(b.id.get());
        xProperty = new SimpleDoubleProperty(b.xProperty.get());
        yProperty = new SimpleDoubleProperty(b.yProperty.get());
        radiusProperty = new SimpleDoubleProperty(b.radiusProperty.get());
        colorProperty = new SimpleObjectProperty(b.colorProperty.get());
        usernameProperty = new SimpleStringProperty(b.usernameProperty.get());
        
    }
    
}
