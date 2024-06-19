package ch.ca;

import ch.ca.Console.ContextVariablePropertiesPanel;
import com.l7tech.policy.assertion.ext.AssertionEditor;
import com.l7tech.policy.assertion.ext.CustomAssertion;
import com.l7tech.policy.assertion.ext.CustomAssertionUI;

import javax.swing.*;
import java.io.Serializable;
//import java.util.Objects;

public class ContextVariableUI implements CustomAssertionUI, Serializable{
    @Override
    public AssertionEditor getEditor(CustomAssertion customAssertion) {
        if(!(customAssertion instanceof ContextVariableCustomAssertion)) {
            throw new IllegalArgumentException(ContextVariableCustomAssertion.class + " Type is required");
        }
        return new ContextVariablePropertiesPanel((ContextVariableCustomAssertion) customAssertion);
    }

    @Override
    public ImageIcon getLargeIcon() {
        return null;
    }

    @Override
    public ImageIcon getSmallIcon() {
        return null;
    }
}
