package ch.delay;

import com.l7tech.policy.assertion.UsesVariables;
import com.l7tech.policy.assertion.ext.CustomAssertion;
import com.l7tech.policy.variable.ContextVariablesUtils;

import java.util.Optional;

public class DelayCustomAssertion implements CustomAssertion, UsesVariables {
    private String delayMilliSec = null;
    
    // Verifying the value given is a valid delay value
    public static Optional<Long> getValidDelayOrNone(String strDelay) {
        if(strDelay.isEmpty()) {
            return Optional.empty();
        }

        final long delay;
        try {
            delay = Long.parseLong(strDelay);
        } catch(NumberFormatException ex) {
            return Optional.empty();
        }
        if(delay < 0) {
            return Optional.empty();
        }
        return Optional.of(delay);
    }

    @Override
    public String[] getVariablesUsed() {
        return ContextVariablesUtils.getReferencedNames(delayMilliSec);
    }

    @Override
    public String getName() {
        return "Delay Assertion";
    }

    public void setDelayMilliSec(String delayMilliSec) {
        this.delayMilliSec = delayMilliSec;
    }

    public String getDelayMilliSec() {
        return delayMilliSec;
    }
    
}
