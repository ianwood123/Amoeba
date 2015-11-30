/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import GameModels.PelletModel;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 *
 * @author woodi
 */
public class Pellet extends GameObject {

    PelletModel model;

    public Pellet(Double x, Double y, Color color, Long id) {
        super(id);
        model = new PelletModel(x, y, color);
    }

    @Override
    public Node getModel() {
        return this.model;
    }

    @Override
    public void updateModel(Double x, Double y, Double width, Color c) {
        this.model.centerXProperty().set(x);
        this.model.centerYProperty().set(y);
    }
}
