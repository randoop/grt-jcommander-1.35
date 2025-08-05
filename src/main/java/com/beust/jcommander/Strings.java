package com.beust.jcommander;
import org.checkerframework.dataflow.qual.Pure;

public class Strings {
  @Pure
  public static boolean isStringEmpty(String s) {
    return s == null || "".equals(s);
  }
}
