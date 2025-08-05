package com.beust.jcommander.converters;

import org.checkerframework.dataflow.qual.SideEffectFree;
import java.util.Arrays;
import java.util.List;

public class CommaParameterSplitter implements IParameterSplitter {

  @SideEffectFree
  public List<String> split(String value) {
    return Arrays.asList(value.split(","));
  }

}
