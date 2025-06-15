package types;

import java.util.Map;

public class UIInput {
    public String inputCode;
    public Map<String, String> parameters;

    public UIInput(String inputCode, Map<String, String> parameters) {
        this.inputCode = inputCode;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "inputCode=" + inputCode + ", parameters=" + parameters;
    }
}
