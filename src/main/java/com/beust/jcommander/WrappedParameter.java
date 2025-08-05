package com.beust.jcommander;

import org.checkerframework.dataflow.qual.Impure;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Encapsulates the operations common to @Parameter and @DynamicParameter
 */
public class WrappedParameter {
  private Parameter m_parameter;
  private DynamicParameter m_dynamicParameter;

  @SideEffectFree
  public WrappedParameter(Parameter p) {
    m_parameter = p;
  }

  @SideEffectFree
  public WrappedParameter(DynamicParameter p) {
    m_dynamicParameter = p;
  }

  @Pure
  public Parameter getParameter() {
    return m_parameter;
  }

  @Pure
  public DynamicParameter getDynamicParameter() {
    return m_dynamicParameter;
  }

  @Pure
  @Impure
  public int arity() {
    return m_parameter != null ? m_parameter.arity() : 1;
  }

  @Pure
  @Impure
  public boolean hidden() {
    return m_parameter != null ? m_parameter.hidden() : m_dynamicParameter.hidden();
  }

  @Pure
  @Impure
  public boolean required() {
    return m_parameter != null ? m_parameter.required() : m_dynamicParameter.required();
  }

  @Pure
  @Impure
  public boolean password() {
    return m_parameter != null ? m_parameter.password() : false;
  }

  @Pure
  @Impure
  public String[] names() {
    return m_parameter != null ? m_parameter.names() : m_dynamicParameter.names();
  }

  @Pure
  @Impure
  public boolean variableArity() {
    return m_parameter != null ? m_parameter.variableArity() : false;
  }

  @Pure
  @Impure
  public Class<? extends IParameterValidator> validateWith() {
    return m_parameter != null ? m_parameter.validateWith() : m_dynamicParameter.validateWith();
  }

  @Pure
  @Impure
  public Class<? extends IValueValidator> validateValueWith() {
    return m_parameter != null
        ? m_parameter.validateValueWith()
        : m_dynamicParameter.validateValueWith();
  }

  @Pure
  @Impure
  public boolean echoInput() {
	  return m_parameter != null ? m_parameter.echoInput() : false;
  }

  @Impure
  public void addValue(Parameterized parameterized, Object object, Object value) {
    if (m_parameter != null) {
      parameterized.set(object, value);
    } else {
      String a = m_dynamicParameter.assignment();
      String sv = value.toString();

      int aInd = sv.indexOf(a);
      if (aInd == -1) {
        throw new ParameterException(
            "Dynamic parameter expected a value of the form a" + a + "b"
                + " but got:" + sv);
      }
      callPut(object, parameterized, sv.substring(0, aInd), sv.substring(aInd + 1));
    }
  }

  @Impure
  private void callPut(Object object, Parameterized parameterized, String key, String value) {
    try {
      Method m;
      m = findPut(parameterized.getType());
      m.invoke(parameterized.get(object), key, value);
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch(IllegalAccessException e) {
      e.printStackTrace();
    } catch(InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
  }

  @Pure
  private Method findPut(Class<?> cls) throws SecurityException, NoSuchMethodException {
    return cls.getMethod("put", Object.class, Object.class);
  }

  @Pure
  @Impure
  public String getAssignment() {
    return m_dynamicParameter != null ? m_dynamicParameter.assignment() : "";
  }

  @Pure
  @Impure
  public boolean isHelp() {
    return m_parameter != null && m_parameter.help();
  }

}
