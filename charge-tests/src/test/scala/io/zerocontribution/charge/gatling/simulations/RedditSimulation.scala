package io.zerocontribution.charge.gatling.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.config.HttpProtocolBuilder

class RedditSimulation extends Simulation {

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

  val scn = scenario("Reddit")
    .exec(http("Frontpage")
    .get("/"))

  setUp(scn.inject(atOnceUsers(1)))
    .protocols(standardHttpProtocol("http://www.reddit.com"))

}
