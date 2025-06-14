package types;

import java.util.Map;

public class UIInput {
    public String inputcode;
    public Map<String, String> parameters;
    public UIInput(String inputcode, Map<String, String> parameters) {
        this.inputcode = inputcode;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "inputcode=" + inputcode + ", parameters=" + parameters;
    }
}
