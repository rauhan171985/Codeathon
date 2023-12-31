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

    public String getLocation() {
        return location;
    }

    public String getKeyCloakIngAddress() {
        return keyCloakIngAddress;
    }

    private String location;
    private String desiredAllocation;
    private String currentAllocation;
    private String status;

    public void setKeyCloakIngAddress(String keyCloakIngAddress) {
        this.keyCloakIngAddress = keyCloakIngAddress;
    }

    private String keyCloakIngAddress;

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


}