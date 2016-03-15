package org.eck.navigatorr

import org.junit.{Test, Assert}

class NavigatorrTest {

  @Test
  def testSimpleRoutes = {
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

  @Test
  def testRoutesWithParams = {
    val navigatorr = new Navigatorr[String]
    navigatorr.addRoute("/foo/bla/:id", "SomeController")
    navigatorr.addRoute("/foo/bla/:id/blu", "SomeOtherController")
    navigatorr.addRoute("/foo/bla/:id/blu/:name", "SomeOtherController")

    var nr: NavigationRoute[String] = navigatorr.getRoute("/foo/bla/ble").get
    Assert.assertEquals("SomeController", nr.controller)
    Assert.assertTrue(nr.stringParam("id").isDefined)
    Assert.assertEquals("ble", nr.stringParam("id").get)

    nr = navigatorr.getRoute("/foo/bla/ble/blu").get
    Assert.assertEquals("SomeOtherController", nr.controller)
    Assert.assertTrue(nr.stringParam("id").isDefined)
    Assert.assertEquals("ble", nr.stringParam("id").get)

    nr = navigatorr.getRoute("/foo/bla/ble/blu/blo").get
    Assert.assertEquals("SomeOtherController", nr.controller)
    Assert.assertTrue(nr.stringParam("id").isDefined)
    Assert.assertEquals("ble", nr.stringParam("id").get)
    Assert.assertTrue(nr.stringParam("name").isDefined)
    Assert.assertEquals("blo", nr.stringParam("name").get)
  }

  @Test
  def testParamsOrders = {
    val navigatorr = new Navigatorr[String]
    navigatorr.addRoute("/foo/bla/:id", "SomeController")
    navigatorr.addRoute("/foo/bla/blu", "SomeOtherController")

    var nr: NavigationRoute[String] = navigatorr.getRoute("/foo/bla/blu").get
    Assert.assertEquals("SomeOtherController", nr.controller)
    Assert.assertFalse(nr.stringParam("id").isDefined)

    nr = navigatorr.getRoute("/foo/bla/ble").get
    Assert.assertEquals("SomeController", nr.controller)
    Assert.assertTrue(nr.stringParam("id").isDefined)
    Assert.assertEquals("ble", nr.stringParam("id").get)
  }
}
