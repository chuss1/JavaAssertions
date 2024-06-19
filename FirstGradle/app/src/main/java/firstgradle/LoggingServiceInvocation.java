package firstgradle;

import java.util.logging.Logger;

import com.l7tech.policy.assertion.ext.CustomAssertion;
import com.l7tech.policy.assertion.ext.CustomAssertionStatus;
import com.l7tech.policy.assertion.ext.ServiceInvocation;
import com.l7tech.policy.assertion.ext.message.CustomPolicyContext;

public class LoggingServiceInvocation extends ServiceInvocation {

    private LoggingCustomAssertion loggingAssertion;

    private final Logger logger = Logger.getLogger("com.l7tech." + getClass().getName());

    @Override
    public void setCustomAssertion(CustomAssertion customAssertion) {
        loggingAssertion = (LoggingCustomAssertion) customAssertion;
    }

    @Override
    public CustomAssertionStatus checkRequest(CustomPolicyContext customPolicyContext) {
        if(loggingAssertion.isWarning()) {
            logger.warning(loggingAssertion.getText());
        } else {
            logger.info(loggingAssertion.getText());
        }
        return CustomAssertionStatus.NONE;
    }
}
