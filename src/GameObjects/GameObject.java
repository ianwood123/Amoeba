/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import DataModel.DataObject;

/**
 *
 * @author woodi
 */
public abstract class GameObject extends DataObject{

    GameObject(Long id){
    super(id);
    }
    protected Long dirt = System.currentTimeMillis();
    
    public void resetDirt(){
        dirt = System.currentTimeMillis();
    }
    
    public boolean tooDirty(){
         
        return System.currentTimeMillis() > dirt + 1000;
    }
}
