package org.hphc.poc.passkit.function.pojo;




public class HPHCPassField {

    private String key;

    private String label;

    private String changeMessage;

    /**TODO
     * ANil - can you make this into an Enum of primary, secondary,back,aux
     */
    private String fieldLocation;

    private String activeFlag;

    private String value;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getChangeMessage() {
        return changeMessage;
    }

    public void setChangeMessage(String changeMessage) {
        this.changeMessage = changeMessage;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getFieldLocation() {
        return fieldLocation;
    }

    public void setFieldLocation(String fieldLocation) {
        this.fieldLocation = fieldLocation;
    }
}
