package types;

import java.util.Map;

public class UIOutput {

        public String feedbackCode;
        public String errorCode;
        public Map<String, String> parameters;

        public UIOutput(String feedbackCode,String errorCode, Map<String, String> parameters) {
            this.feedbackCode = feedbackCode;
            this.errorCode = errorCode;
            this.parameters = parameters;
        }

        @Override
        public String toString() {
            return "feedbackCode=" + feedbackCode +" errorcode= "+errorCode+ ", parameters=" + parameters;
        }


}
