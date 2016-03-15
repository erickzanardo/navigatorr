# Navigatorr
Generic url router written in Scala using the `trie` tree

It's really simple to use, you just need to define which routes maps to some object, for example

```scala
val navigatorr = new Navigatorr[String]
navigatorr.addRoute("/foo/bla", "SomeController")

navigatorr.getRoute("/foo/bla") // Returns an Option filled with a NavigationRoute
navigatorr.getRoute("/foo/ble") // Returns an empty Option
```

You can also map `Path parameters`, for example:

```scala
navigatorr.addRoute("/foo/bla/:id", "SomeController")
val nr: NavigationRoute[String] = navigator.getRoute("/foo/bla/ble").get
nr.stringParam("id") // Returns an Option filled with the string ble
```
