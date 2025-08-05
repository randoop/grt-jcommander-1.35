package com.beust.jcommander.converters;

import org.checkerframework.dataflow.qual.SideEffectFree;
import java.util.List;

/**
 * Convert a string representing several parameters (e.g. "a,b,c" or "d/e/f") into a
 * list of arguments ([a,b,c] and [d,e,f]).
 */
public interface IParameterSplitter {
  @SideEffectFree
  List<String> split(String value);
}
