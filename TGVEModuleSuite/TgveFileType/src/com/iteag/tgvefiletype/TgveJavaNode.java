/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteag.tgvefiletype;

import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Sheet;

/**
 *
 * @author charles
 */
public class TgveJavaNode extends AbstractNode{
    public TgveJavaNode(Children children){
        super(children);
    }
    
    @Override
    protected Sheet createSheet(){
        Sheet sheet = Sheet.createDefault();
        
        return sheet;
    }
    
    @Override
    public Action[] getActions(boolean popup){
        Action[] actionSet = new Action[1];
        
        return actionSet;
    }
}
