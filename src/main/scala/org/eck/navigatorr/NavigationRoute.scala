package org.eck.navigatorr

import java.util

class NavigationRoute[T](_controller: T, _params: util.HashMap[String, String]) {
  val controller = _controller
  private val params = _params

  def stringParam(key: String): Option[String] = {
    Option(params.get(key))
  }
}
