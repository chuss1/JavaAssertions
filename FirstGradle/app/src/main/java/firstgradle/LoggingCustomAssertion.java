package firstgradle;

import com.l7tech.policy.assertion.ext.CustomAssertion;

public class LoggingCustomAssertion implements CustomAssertion {

    private String text;
    private boolean warning;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }
    

    @Override
    public String getName() {
        return "Log some text";
    }
    
}
