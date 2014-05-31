package com.pwootage.fasterflux.utils

import java.util.List

object MCUtils {
  /**
   * Clever hack courtesy of OpenComputers to get around untyped java lists
   */
  def untypedListAdd[T](list: List[T], value: Any) = list.add(value.asInstanceOf[T])
}