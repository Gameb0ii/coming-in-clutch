// @GENERATOR:play-routes-compiler
// @SOURCE:/home/lance/git/samplePlay/conf/routes
// @DATE:Tue Jan 21 10:29:22 EST 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
