package org.vsa.gui.classes;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.vsa.api.Interrogation;
import org.vsa.api.VsaSystem;

/**
 * InterrogationListModel
 */
public class InterrogationListModel implements ListModel<Interrogation>{

    /**
     * vsaSystem
     */
    private final VsaSystem vsaSystem;

    /**
     * Default constructor
     * 
     * @param vsaSystem 
     */
    public InterrogationListModel(VsaSystem vsaSystem) {
        this.vsaSystem = vsaSystem;
    }

    /**
     * getSize
     * 
     * @return 
     */
    @Override
    public int getSize() {
        return vsaSystem.getInterrogationList().numInterrogations();
    }

    /**
     * getElementAt
     * 
     * @param index
     * @return 
     */
    @Override
    public Interrogation getElementAt(int index) {
        return vsaSystem.getInterrogationList().getInterrogation(index);
    }

    /**
     * addListDataListener
     * 
     * @param l 
     */
    @Override
    public void addListDataListener(ListDataListener l) {
    }

    /**
     * removeListDataListener
     * 
     * @param l 
     */
    @Override
    public void removeListDataListener(ListDataListener l) {
    }
}
