package org.eck.navigatorr

import java.util

class NavigationRoute[T](_controller: T, _params: util.HashMap[String, String]) {
  val controller = _controller
  val params = _params
}
