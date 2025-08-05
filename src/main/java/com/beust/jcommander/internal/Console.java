package com.beust.jcommander.internal;
import org.checkerframework.dataflow.qual.Impure;

public interface Console {

  @Impure
  void print(String msg);

  @Impure
  void println(String msg);

  @Impure
  char[] readPassword(boolean echoInput);
}
