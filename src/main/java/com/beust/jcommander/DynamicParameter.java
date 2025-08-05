package com.beust.jcommander;

import org.checkerframework.dataflow.qual.Pure;
import static java.lang.annotation.ElementType.FIELD;

import com.beust.jcommander.validators.NoValidator;
import com.beust.jcommander.validators.NoValueValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ FIELD })
public @interface DynamicParameter {
  /**
   * An array of allowed command line parameters (e.g. "-D", "--define", etc...).
   */
  @Pure
  String[] names() default {};

  /**
   * Whether this option is required.
   */
  @Pure
  boolean required() default false;

  /**
   * A description of this option.
   */
  @Pure
  String description() default "";

  /**
   * The key used to find the string in the message bundle.
   */
  @Pure
  String descriptionKey() default "";

  /**
   * If true, this parameter won't appear in the usage().
   */
  @Pure
  boolean hidden() default false;

  /**
   * The validation class to use.
   */
  @Pure
  Class<? extends IParameterValidator> validateWith() default NoValidator.class;

  /**
   * The character(s) used to assign the values.
   */
  @Pure
  String assignment() default "=";

  @Pure
  Class<? extends IValueValidator> validateValueWith() default NoValueValidator.class;
}
