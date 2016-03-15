package org.eck.navigatorr

import java.util

class Navigatorr[T] {
  var root = new NavigationPoint[T]("")

  private def parseRoute(route: String): List[String] = {
    val trimmed: String = if (route.startsWith("/")) {
      route.replaceFirst("/", "")
    } else {
      route
    }

    trimmed.split("/").toList
  }

  def addRoute(route: String, controller: T): Unit = {
    var points = parseRoute(route)

    var navigationPoint = root
    while(points.size > 0) {
      navigationPoint = navigationPoint.addNavigationPoint(points(0))
      points = points.drop(1)
    }

    navigationPoint.controller = Option(controller)
  }

  def getRoute(route: String): Option[NavigationRoute[T]] = {
    var points = parseRoute(route)
    var navigationPoint = Option(root)

    val params = new util.HashMap[String, String]()
    while(points.size > 0 && navigationPoint.isDefined) {
      navigationPoint = navigationPoint.get.findChild(points(0))
      if (navigationPoint.get.isParam) params.put(navigationPoint.get.paramId, points(0))
      points = points.drop(1)
    }

    if(navigationPoint.isDefined && navigationPoint.get.controller.isDefined) {
      return Option(new NavigationRoute[T](navigationPoint.get.controller.get, params))
    }
    None
  }
}
