package ch.ca;

import com.l7tech.policy.assertion.ext.CustomAssertion;
import com.l7tech.policy.assertion.ext.CustomAssertionStatus;
import com.l7tech.policy.assertion.ext.ServiceInvocation;
import com.l7tech.policy.assertion.ext.message.CustomPolicyContext;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;
import java.util.Map;

public class ContextVariableServiceInvocation extends ServiceInvocation {
    @Override
    public CustomAssertionStatus checkRequest(CustomPolicyContext customPolicyContext) {
        if(!isAssertionValid(customAssertion)) {
            return CustomAssertionStatus.FAILED;
        }

        ContextVariableCustomAssertion assertion = (ContextVariableCustomAssertion) customAssertion;

        String[] variablesUsed = assertion.getVariablesUsed();
        Map<String, Object> vars = customPolicyContext.getVariableMap(variablesUsed);
        String expression = customPolicyContext.expandVariable(assertion.getExpression(), vars);

        double value;
        try {
            value = new ExpressionBuilder(expression).build().evaluate();
        } catch(Exception e) {
            auditWarn("There was a problem trying to evaluate the expression: " + expression + 
                ". This was caused because: " + e.getMessage());
            return CustomAssertionStatus.FAILED;
        }

        StringBuilder sb = new StringBuilder("#");
        if(assertion.getPrecision() > 0) {
            sb.append('.');
            for(int i = 0; i < assertion.getPrecision(); i++) {
                sb.append('#');
            }
        }

        DecimalFormat df = new DecimalFormat(sb.toString());
        customPolicyContext.setVariable(assertion.getOutputVariable(), df.format(value));
        return CustomAssertionStatus.NONE;
    }

    private boolean isAssertionValid(CustomAssertion assertion) {
        if(assertion instanceof ContextVariableCustomAssertion) {
            return true;
        } else {
            auditWarn(String.format("customAssertion must be of type [{%s}], but it is of type [{%s}] instead",
            ContextVariableCustomAssertion.class.getSimpleName(), customAssertion.getClass().getSimpleName()));
            return false;
        }
    }
}
