/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Game.GameWorld;
import GameObjects.Blob;
import GameObjects.GameObject;
import Messages.BlobStateMessage;
import java.util.Objects;
import javafx.scene.paint.Color;

/**
 *
 * @author woodi
 */

public class SetBlobCommand implements MessageCommand<BlobStateMessage> {

    GameWorld world;
    BlobStateMessage bsm;

    public SetBlobCommand(BlobStateMessage message, GameWorld gw) {
        world = gw;
        bsm = message;

    }

    @Override
    public void execute() {
        Long id = (long) -1;
        for (GameObject go : world.getObjects()) {
            if (Objects.equals(go.id, bsm.id)) {
                go.updateModel(bsm.x, bsm.y, bsm.size, Color.web(bsm.color));
                go.resetDirt();
                id = go.id;
            }
        }
        if (id == -1) {
            world.addGameObject(new Blob(bsm.x, bsm.y, bsm.size, Color.web(bsm.color), bsm.id));
        }
    }
}
