/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DataModel.DataObject;
import Game.GameWorld;
import GameModels.BlobModel;
import GameObjects.Blob;
import Messages.BlobStateMessage;
import javafx.scene.paint.Color;

/**
 *
 * @author woodi
 */

public class SetBlobCommand implements MessageCommand<BlobStateMessage> {

    GameWorld world;
    BlobStateMessage bsm;
    boolean consumed = false;
    public SetBlobCommand(BlobStateMessage message, GameWorld gw) {
        world = gw;
        bsm = message;

    }

    @Override
    public void execute() {
        Blob b;
        for(DataObject gom : world.getScreen().getDataQueue()){
            if(gom.id.get() == bsm.id){
                b = (Blob) gom;
                b.colorProperty.set(Color.valueOf(bsm.color));
                b.radiusProperty.set(bsm.size/2);
                b.xProperty.set(bsm.x);
                b.yProperty.set(bsm.y);
                consumed = true;
            }
        }
        if(!consumed){
            b = new Blob(bsm.x,bsm.y,bsm.size,Color.valueOf(bsm.color),bsm.username,bsm.id);
            BlobModel bm = new BlobModel(b);
            world.nodes.add(new BlobModel(bm));
        }
    }

}
