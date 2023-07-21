package com.radisys.codeathon.allocator.observable;

import com.radisys.codeathon.allocator.controller.NemsRecordController;
import com.radisys.codeathon.allocator.model.NemsRecord;
import com.radisys.codeathon.allocator.service.NemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Component
public class PCLObservable {

    final Logger logger = LoggerFactory.getLogger(PCLObservable.class);
    private PropertyChangeSupport support;
    private NemsRecord nemsRecord;
    NemsService nemsService;

    public PCLObservable() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public NemsRecord observeNemsRecord(NemsRecord nemsRecord) {
        support.firePropertyChange("nemsRecord", this.nemsRecord, nemsRecord);
        this.nemsRecord = nemsRecord;
        logger.info("NEMS status of associated NEG {} is {} ", this.nemsRecord.getNegId(), this.nemsRecord.getStatus());
        if ("DOWN".equals(this.nemsRecord.getStatus()) && "NEMS-BERLIN".equals(this.nemsRecord.getCurrentAllocation())) {
            this.nemsRecord.setCurrentAllocation("NEMS-FRANKFRUIT");
            return this.nemsRecord;
        }
        logger.info("NEMS status of associated NEG {} is {} ", this.nemsRecord.getNegId(), this.nemsRecord.getStatus());
        return null;
    }
}
