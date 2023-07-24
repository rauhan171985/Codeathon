package com.radisys.codeathon.allocator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//No @Entity concept here
public class NemsRecord implements Serializable {

    private static final long serialVersionUID = -7817224776021728682L;

    private String negId;
    private String location;
    private String desiredAllocation;
    private String currentAllocation;
    private String status;
    private String IpAddress;

    public String getNegId() {
        return negId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCurrentAllocation(String currentAllocation) {
        this.currentAllocation = currentAllocation;
    }

    public String getDesiredAllocation() {
        return desiredAllocation;
    }

    public String getCurrentAllocation() {
        return currentAllocation;
    }

    public String getStatus() {
        return status;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

}