/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteag.tgvefiletype;

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
        position = 2000)
@Messages("LBL_Tgve_VISUAL2=Absolute")
public final class TgveVisualElement2 extends JPanel implements MultiViewElement{
    
    private TgveDataObject obj;
    private JToolBar toolbar = new JToolBar();
    private transient MultiViewElementCallback callback;

    public TgveVisualElement2(Lookup lkp) {
        obj = lkp.lookup(TgveDataObject.class);
        assert obj != null;
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }

    @Override
    public String getName() {
        return "TgveVisualElement2";
    }

    @Override
    public JComponent getVisualRepresentation() {
        return this; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JComponent getToolbarRepresentation() {
        return toolbar; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Action[] getActions() {
        return new Action[0]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lookup getLookup() {
        return obj.getLookup(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentOpened() {
       
    }

    @Override
    public void componentClosed() {
 
    }

    @Override
    public void componentShowing() {
    }

    @Override
    public void componentHidden() {
    }

    @Override
    public void componentActivated() {
    }

    @Override
    public void componentDeactivated() {
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
