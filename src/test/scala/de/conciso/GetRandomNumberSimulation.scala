package de.conciso

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{http, status}
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

class GetRandomNumberSimulation extends Simulation {
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://google.de")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml,application/json")
    .acceptLanguageHeader("de,en-US;q=0.7,en;q=0.3")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Gatling")

  val scn: ScenarioBuilder = scenario("Request Random Number")
    .exec(
      http("Simple Get")
        .get("/"))

  setUp(
    scn.inject(
      atOnceUsers(1)
    ).protocols(httpProtocol))
}
