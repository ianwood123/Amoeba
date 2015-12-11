/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DataModel.DataObject;
import Game.GameWorld;
import GameModels.PelletModel;
import GameObjects.Pellet;
import Messages.PelletPositionMessage;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author woodi
 */
public class SetPelletCommand implements MessageCommand<PelletPositionMessage> {

    Random r = new Random();
    
    GameWorld world;
    PelletPositionMessage ppm;

    boolean consumed = false;
    
    public SetPelletCommand(PelletPositionMessage message, GameWorld world) {
        this.world = world;
        ppm = message;
    }

    @Override
    public void execute() {
            Pellet p;
            for(DataObject dom : world.getScreen().getDataQueue()){
                if(dom.id.get() == ppm.id){
                    p = (Pellet) dom;
                    p.colorProperty.set(Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble()));
                    p.xProperty.set(ppm.x);
                    p.yProperty.set(ppm.y);
                    consumed = true;
                }
            }
            if(!consumed){
                p = new Pellet(ppm.x,ppm.y,Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble()),ppm.id);
                PelletModel pm = new PelletModel(p);
                world.nodes.add(new PelletModel(pm));
            }
        }
}
