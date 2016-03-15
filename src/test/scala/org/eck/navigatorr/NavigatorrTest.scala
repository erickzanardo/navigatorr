package org.eck.navigatorr

import org.junit.{Test, Assert}

class NavigatorrTest {

  @Test
  def testNavigator = {
    val navigatorr = new Navigatorr[String]
    navigatorr.addRoute("/foo/bla", "SomeController")
    navigatorr.addRoute("/foo/bla/ble", "SomeOtherController")
    navigatorr.addRoute("/foo/bla/blu", "SomeAnotherController")
    navigatorr.addRoute("/", "RootController")

    Assert.assertEquals("SomeController", navigatorr.getRoute("/foo/bla").get.controller)
    Assert.assertEquals("SomeOtherController", navigatorr.getRoute("/foo/bla/ble").get.controller)
    Assert.assertEquals("SomeAnotherController", navigatorr.getRoute("/foo/bla/blu").get.controller)
    Assert.assertEquals("RootController", navigatorr.getRoute("/").get.controller)
    Assert.assertFalse(navigatorr.getRoute("/foo").isDefined)
  }
}
