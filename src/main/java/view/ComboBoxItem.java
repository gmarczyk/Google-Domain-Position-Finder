package view;

/**
 * Structure of an comboBox item, that contains label(displayed string) and own value
 * @author  Marczyk Grzegorz
 * @version 1.0.1
 */
public class ComboBoxItem {
    private String value;
    private String label;

    /**
     * @param value allows object to have its own value with no limits, often used arithmetically or to recognize object
     * @param label what is displayed
     */
    public ComboBoxItem(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    /**
     * @return the LABEL (display string), not the value.
     */
    @Override
    public String toString() {
        return label;
    }
}