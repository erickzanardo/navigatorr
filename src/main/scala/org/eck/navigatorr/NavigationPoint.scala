package org.eck.navigatorr

import java.util

class NavigationPoint[T](_identifier: String) {
  val identifier = _identifier
  val isParam: Boolean = identifier.startsWith(":")
  val paramId: String = if (isParam) { identifier.replaceFirst(":", "") } else { "" }

  var controller: Option[T] = None
  private var children: List[NavigationPoint[T]] = List()

  def addNavigationPoint(point: String): NavigationPoint[T] = {
    val result = children.filter(item => item.identifier == point)
    if (result.size == 1) return result(0)

    val navigationPoint = new NavigationPoint[T](point)
    children = children :+ navigationPoint
    return navigationPoint
  }

  def findChild(point: String): Option[NavigationPoint[T]] = {
    val result = children.filter(navigationPoint => navigationPoint.identifier == point || navigationPoint.isParam)
    if (result.isDefinedAt(0)) {
      return Option(result(0))
    }
    None
  }
}
