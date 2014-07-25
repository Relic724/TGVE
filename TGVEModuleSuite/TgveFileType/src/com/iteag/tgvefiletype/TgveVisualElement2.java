/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteag.tgvefiletype;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import com.jme3.system.JmeSystem;
import java.awt.Canvas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
/**
 *
 * @author charles
 */
@MultiViewElement.Registration(
        displayName = "#LBL_Tgve_VISUAL2",
        iconBase = "com/iteag/tgvefiletype/GUI_Icon.png",
        mimeType = "text/x-tgve",
        persistenceType = TopComponent.PERSISTENCE_NEVER,
        preferredID = "TgveVisual",
        position = 3000)
@Messages("LBL_Tgve_VISUAL2=Absolute")
public final class TgveVisualElement2 extends JPanel implements MultiViewElement{
    
    private TgveDataObject obj;
    private JToolBar toolbar = new JToolBar();
    private transient MultiViewElementCallback callback;
    private SimpleApplication app;
    private JmeCanvasContext context;
    private Canvas canvas;
    private static final Logger logger = Logger.getLogger(TgveVisualElement2.class.getName());

    public TgveVisualElement2(Lookup lkp) {
        obj = lkp.lookup(TgveDataObject.class);
        assert obj != null;
        
        AppSettings settings = new AppSettings(true);
        
        settings.setWidth(1024);
        settings.setHeight(960);
        settings.setAudioRenderer(null);
        JmeSystem.setLowPermissions(true);
        
                
        defineApp();
        app.setSettings(settings);
        app.createCanvas();
        
        context = (JmeCanvasContext) app.getContext();
        canvas = context.getCanvas();
        canvas.setSize(100, 100);
        
        
        logger.log(Level.INFO, "App has been started");
        
    }

    public void defineApp(){
        app = new SimpleApplication() {
            
            @Override
            public void simpleInitApp() {
                Box boxMesh = new Box(1,1,1);
                Geometry boxy = new Geometry("boxy", boxMesh);
                Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                material.setColor("Color", ColorRGBA.Blue);
                boxy.setMaterial(material);
                rootNode.attachChild(boxy);
                
            }
        };
    }
    
    @Override
    public String getName() {
        return "TgveVisualElement2";
    }

    @Override
    public JComponent getVisualRepresentation() {
        return this; 
    }

    @Override
    public JComponent getToolbarRepresentation() {
        return toolbar; 
    }

    @Override
    public Action[] getActions() {
        return new Action[0]; 
    }

    @Override
    public Lookup getLookup() {
        return obj.getLookup(); 
    }

    @Override
    public void componentOpened() {
       // TODO: Initialize and fire up the JME3
       add(canvas); 
    }

    @Override
    public void componentClosed() {
        //TODO: Stop the app
       remove(canvas);
       app.stop();
    }

    @Override
    public void componentShowing() {
        // TODO: App is running, set the surface; if paused, unpause
        canvas.setSize(getWidth(),getHeight());
        app.startCanvas();
        
    }

    @Override
    public void componentHidden() {
        // TODO: App is running, pause the engine
        app.stop();

    }

    @Override
    public void componentActivated() {
        // TODO: accept input handlings here
        app.getFlyByCamera().setDragToRotate(true);
    }

    @Override
    public void componentDeactivated() {
        // TODO: release the inputs...?
    }

    @Override
    public UndoRedo getUndoRedo() {
        return UndoRedo.NONE;
    }

    @Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback; 
    }

    @Override
    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }
    
}
