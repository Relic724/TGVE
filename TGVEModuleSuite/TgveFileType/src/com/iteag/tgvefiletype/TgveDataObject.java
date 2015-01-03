/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteag.tgvefiletype;

import java.io.IOException;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.nodes.*;
import org.openide.loaders.DataLoaderPool;
import com.iteag.tgvefiletype.TgveMultiFileLoader;
import com.jme3.gde.core.assets.BinaryModelDataObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.openide.util.SharedClassObject;
import org.openide.util.lookup.Lookups;

@Messages({
    "LBL_Tgve_LOADER=Files of Tgve"    
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_Tgve_LOADER",
        mimeType = "text/x-tgve",
        extension = {"tgve", "TGVE"}
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200),
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300),
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500),
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600),
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800),
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000),
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200),
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300),
    @ActionReference(
            path = "Loaders/text/x-tgve/Actions",
            id =
            @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400)
})
public class TgveDataObject extends MultiDataObject {
    
    
    protected TgveChildFactory childFactory;
    private static final Logger logger = Logger.getLogger(TgveDataObject.class.getName());
    
        
    /**
     * This is the typical constructor, sure, but it is meant to be called by the
     * loader during "start up" for this data objects lifecycle. The responsibility
     * of this constructor is to make sure that the primary and secondary file objects
     * are registered correctly.  The super(primaryFileObject, loader) takes care 
     * of this by default.  That leaves it up to the extension to register the 
     * secondaries. There is a leaking constructor warning, but the "this" in question
     * looks to be referenced only for the sublcass MultiDataObject.Entry constructor.
     * So... should be safe? ish?
     * @param primaryFileObject
     * @param loader
     * @throws DataObjectExistsException
     * @throws IOException 
     */
    public TgveDataObject(FileObject primaryFileObject, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(primaryFileObject, loader);
        registerEditor("text/x-tgve", true);
    }
    
    public TgveDataObject(FileObject primaryFileObject, FileObject secondaryJavaFileObject, FileObject secondaryJ3oFileObject, TgveMultiFileLoader loader)
            throws DataObjectExistsException, IOException {
        super(primaryFileObject, loader);
    }
    
 
    @Override
    protected int associateLookup() {
        return 1;
    }
    
    @Override
    public Node createNodeDelegate(){
        childFactory = new TgveChildFactory();
        childFactory.setTGVEDO(this);
        DataNode delegateNode = new DataNode(this, Children.create(childFactory,false));
        FileObject tgveFO = FileUtil.getConfigFile("Loaders/text/x-tgve/Factories/com-iteag-tgvefiletype-TgveMultiFileLoader.instance");
        delegateNode.setIconBaseWithExtension((String)tgveFO.getAttribute("iconBase"));
        return delegateNode;
    }
    
    
    
    @MultiViewElement.Registration(
            displayName = "#LBL_Tgve_EDITOR",
            iconBase = "com/iteag/tgvefiletype/GUI_Icon.png",
            mimeType = "text/x-tgve",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "Tgve",
            position = 1000)
    @Messages("LBL_Tgve_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }
    
    
}
