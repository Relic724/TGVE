/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteag.tgvefiletype;

import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataNode;
import org.openide.loaders.MultiDataObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author charles
 */
   public class TgveChildFactory extends ChildFactory<FileObject>{
        TgveDataObject referringInstance;
        public void setTGVEDO(TgveDataObject instance){
            referringInstance = instance;
        }
        
        @Override
        protected boolean createKeys(List<FileObject> toPopulate) {
            FileObject tempFO;
            tempFO = FileUtil.findBrother(referringInstance.getPrimaryFile(), "java");
            if(tempFO != null)
                toPopulate.add(tempFO);
            tempFO = FileUtil.findBrother(referringInstance.getPrimaryFile(), "j3o");
            if(tempFO != null)
                toPopulate.add(tempFO);
            return true; 
        }
        
        @Override
        protected Node createNodeForKey(FileObject key){
            AbstractNode resultNode;
            resultNode = new AbstractNode(Children.LEAF);
            if((key.getExt().equalsIgnoreCase("j3o"))){
                resultNode.setDisplayName("jME3 Deployable");
                resultNode.setName("jME3 file");
                resultNode.setIconBaseWithExtension("com/jme3/gde/core/icons/jme-logo.png");
                resultNode.setShortDescription("This is actually a File called: "+ key.getNameExt()+", but has not been deployed, yet. It will probably not be usable until deployment.");
            }
            if((key.getExt().equalsIgnoreCase("java"))){
                resultNode.setDisplayName("Java Deployable");
                resultNode.setName("Java file");
                resultNode.setIconBaseWithExtension("org/netbeans/modules/java/resources/class.png");
                resultNode.setShortDescription("This is actually a File called: "+ key.getNameExt()+", but has not been deployed, yet. It still has freemarker stuff in it like a template, so can't actually be used as is");
            }

            return resultNode;
        }
        
    }
