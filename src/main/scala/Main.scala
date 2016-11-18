import com.effinggames.{Constants, MainRouter}
import com.effinggames.util.CustomMustacheModule

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter

object Main extends MainServer

class MainServer extends HttpServer {
  override val defaultFinatraHttpPort: String = s":${Constants.port}"

  override def mustacheModule = CustomMustacheModule

  override def postInjectorStartup(): Unit = {
    super.postInjectorStartup()
  }

  def configureHttp(router: HttpRouter): Unit = {
    router.filter[CommonFilters]
    router.add(MainRouter)
  }
}