/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameModels;

import FXModel.FXObjectGenerator;
import GameObjects.Blob;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author woodi
 */
public class BlobModel extends Circle implements FXObjectGenerator<Blob>{
    Blob dataModel;
    
    public BlobModel(Blob b){
        dataModel = b;
    }
    public BlobModel(BlobModel b){
        this.dataModel = new Blob(b.dataModel);
    }

    @Override
    public Blob generateDataObject() {
        this.idProperty().bind(dataModel.id.asString());
        this.centerXProperty().bind(dataModel.xProperty);
        this.centerYProperty().bind(dataModel.yProperty);
        this.radiusProperty().bind(dataModel.radiusProperty);
        this.setFill((Paint) dataModel.colorProperty.get());
        return this.dataModel;
    }

    @Override
    public StringProperty getIdProperty() {
        return this.idProperty();
    }
    
}
