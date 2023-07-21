package com.radisys.codeathon.allocator.observer;

import com.radisys.codeathon.allocator.model.NemsRecord;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Component
public class PCLObserver implements PropertyChangeListener {

    private NemsRecord nemsRecord;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setNemsRecord((NemsRecord) evt.getNewValue());
    }

    private void setNemsRecord(NemsRecord newValue) {
        this.nemsRecord = newValue;
    }

    public NemsRecord getNemsRecord() {
        return this.nemsRecord;
    }

}