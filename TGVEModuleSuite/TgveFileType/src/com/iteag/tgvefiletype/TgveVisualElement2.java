/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteag.tgvefiletype;


import com.jme3.font.BitmapFont;
import com.jme3.gde.core.assets.ProjectAssetManager;
import com.jme3.gde.core.scene.PreviewRequest;
import com.jme3.gde.core.scene.SceneApplication;
import com.jme3.gde.core.scene.SceneListener;
import com.jme3.gde.core.scene.SceneRequest;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.awt.Color;
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
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import tonegod.gui.core.Screen;
import tonegod.gui.controls.windows.Window;

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
public class TgveVisualElement2 extends JPanel implements MultiViewElement, 
  SceneListener{
    
    private TgveDataObject obj;
    private JToolBar toolbar = new JToolBar();
    private SceneRequest request;
    private ProjectAssetManager pam;
    private Node absoluteNode = new Node("absoluteNode");
    private transient MultiViewElementCallback callback;
    private Component oglCanvas;
    private static final Logger logger = Logger.getLogger(TgveVisualElement2.class.getName());
    private Screen screen;

    private static ComponentAdapter tgveComponentListener = new ComponentAdapter(){
        @Override
         public void componentResized(final ComponentEvent e) {
            logger.info(e.getComponent().getClass().getName() + " resized to " + 
                    e.getComponent().getWidth() + ", " + e.getComponent().getHeight());
        }
        
        @Override
        public void componentMoved(final ComponentEvent e) {
            logger.info(e.getComponent().getClass().getName() + " moved, but resized to " + 
                    e.getComponent().getWidth() + ", " + e.getComponent().getHeight());
        }
        
        
    };
    
    public TgveVisualElement2(Lookup lkp) {
        obj = lkp.lookup(TgveDataObject.class);
        assert obj != null;
        setMinimumSize(new java.awt.Dimension(24, 24));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setLayout(new java.awt.GridLayout(1,0));
        setBackground(Color.DARK_GRAY);
        setFocusable(false);
        this.addComponentListener(tgveComponentListener);
        logger.info("TGVE Initialization complete");
        
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
       // TODO: Initialize and fire up the JME3... properly
        pam = new ProjectAssetManager();
        SceneApplication.getApplication().addSceneListener(this);
        
       // TODO: Load Tonegod Objects from TgveDataObject
        Box boxMesh = new Box(1,1,1);
        Geometry box = new Geometry("blue box", boxMesh);
        Material material = new Material(SceneApplication.getApplication().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", ColorRGBA.Blue);
        box.setMaterial(material);
        absoluteNode.attachChild(box);
        request = new SceneRequest(this, absoluteNode, pam);

        logger.info("TGVE Component Opened");
        
    }

    @Override
    public void componentClosed() {
        //TODO: Stop the app
       SceneApplication.getApplication().removeSceneListener(this);
    }

    @Override
    public void componentShowing() {
        // TODO: App is running, set the surface; if paused, unpause
        try {
            oglCanvas = SceneApplication.getApplication().getMainPanel();
            oglCanvas.setFocusable(false);
            add(oglCanvas);
        } catch (Exception e) { 
           SceneApplication.showStartupErrorMessage(e); 
        } catch (Error err) { 
             SceneApplication.showStartupErrorMessage(err); 
        } 
    
        SceneApplication.getApplication().openScene(request);
        logger.info("TGVE Component Showing");
    }

    @Override
    public void componentHidden() {
        // TODO: App is running, pause the engine
        remove(oglCanvas);
        SceneApplication.getApplication().closeScene(request);
        logger.info("TGVE Component Hidden");

    }

    @Override
    public void componentActivated() {
        // TODO: accept input handlings here
        
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

    @Override
    public void sceneOpened(SceneRequest theSceneRequest) {
        if(theSceneRequest.getRequester() == this){
            screen = new Screen(SceneApplication.getApplication());
            SceneApplication.getApplication().getGuiNode().addControl(screen);
            Window win = new Window(screen, "Sample Window", new Vector2f(10,10));
            win.setText("t0neg0d GUI Window");
            win.setTextVAlign(BitmapFont.VAlign.Center);
            win.setTextAlign(BitmapFont.Align.Center);
            screen.addElement(win);
            
            logger.info("TGVE recieved a request");
            
        }
    }

    @Override
    public void sceneClosed(SceneRequest theSceneRequest) {
        if(theSceneRequest.getRequester() == this){
            SceneApplication.getApplication().getGuiNode().removeControl(screen);
            screen = null;
            logger.info("TGVE recieved a request to Close");
        }
    }

    @Override
    public void previewCreated(PreviewRequest pr) {
    }

    
    
}
