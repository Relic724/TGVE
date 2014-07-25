/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteag.jmeAwt;

import com.jme3.scene.Node;
import com.jme3.app.Application;
import com.jme3.system.AppSettings;

/**
 *
 * @author charles
 */
public class TgveMveRelative extends Application{

    protected Node rootNode = new Node("Root Node");
    protected Node guiNode = new Node("Gui Node");
    
    @Override 
    public void start(){
        if(settings == null){
            settings = new AppSettings(true);
            settings.setVSync(true);
        }
        
    }
    
    public TgveMveRelative(){
        super();
    }
    
    public Node getRootNode()
    {
        return this.rootNode;
    }
    
    public Node getGuiNode()
    {
        return this.guiNode;
    }
}
