package org.vsa.gui.listmodels;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.vsa.weka.VoiceStressInstance;
import org.vsa.weka.VoiceStressInstanceList;

/**
 * VoiceStressInstanceListModel
 */
public class VoiceStressInstanceListModel implements ListModel<VoiceStressInstance> {

    /**
     * interrogation
     */
    private final VoiceStressInstanceList voiceStressInstanceList;

    /**
     * Default constructor
     * 
     * @param voiceStressInstanceList 
     */
    public VoiceStressInstanceListModel(VoiceStressInstanceList voiceStressInstanceList) {
        this.voiceStressInstanceList = voiceStressInstanceList;
    }

    /**
     * getSize
     * 
     * @return 
     */
    @Override
    public int getSize() {
        return voiceStressInstanceList.numInstances();
    }

    /**
     * getElementAt
     * 
     * @param index
     * @return 
     */
    @Override
    public VoiceStressInstance getElementAt(int index) {
        return voiceStressInstanceList.getInstance(index);
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
