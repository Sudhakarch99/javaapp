package org.hphc.poc.passkit.function.pojo;


import java.util.Date;
import java.util.List;


public class Pass {

    /**
     * Unique ID
     */
    String id;

    /**
     * Serial Number
     */
    String serialNumber;

    /**
     * Date created
     */
    Date created;

    /**
     * Date modified
     */
    Date modified;

    /**
     * Member number
     */
    String memberNumber;

    /**
     * Document Type: applepass  OR applelog OR hphclog
     */
    String docType;

    /**
     * Authentication Token
     */
    String authenticationToken;


    private List<HPHCPassField> passFields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pass() {
    }

    public List<HPHCPassField> getPassFields() {
        return passFields;
    }

    public void setPassFields(List<HPHCPassField> passFields) {
        this.passFields = passFields;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
}
