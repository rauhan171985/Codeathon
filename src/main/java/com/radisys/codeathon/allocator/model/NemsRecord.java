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

    public String getNegId() {
        return negId;
    }

    private String desiredAllocation;
    private String location;

    public String getDesiredAllocation() {
        return desiredAllocation;
    }

    public String getLocation() {
        return location;
    }

    public String getCurrentAllocation() {
        return currentAllocation;
    }

    public String getStatus() {
        return status;
    }

    private String currentAllocation;
    private String status;
}