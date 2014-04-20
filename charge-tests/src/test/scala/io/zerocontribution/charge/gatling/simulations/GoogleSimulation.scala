package io.zerocontribution.charge.gatling.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.config.HttpProtocolBuilder
import io.zerocontribution.charge.config.SimulationConfiguration

class GoogleSimulation extends Simulation {

  // TODO: This would only support one of-like-class simulation to run at a single time...
  object RunConfiguration extends SimulationConfiguration {
    var numUsers: Int = 1
  }

  def standardHttpProtocol(baseUrl: String): HttpProtocolBuilder = {
    val httpConf = HttpProtocolBuilder.default
      .baseURL(baseUrl)
      .acceptHeader("*/*")
      .acceptEncodingHeader("gzip,deflate,sdch")
      .acceptLanguageHeader("en-US,en;q=0.8")
      .connection("keep-alive")
      .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36")

    httpConf
  }

  val scn = scenario("Google")
    .exec(http("Home")
    .get("/"))

  setUp(scn.inject(atOnceUsers(RunConfiguration.numUsers)))
    .protocols(standardHttpProtocol("http://www.google.com"))

}
