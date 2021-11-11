package de.conciso.solutions.solution1

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt

class GetRandomNumberSimulation extends Simulation {
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml,application/json")
    .acceptLanguageHeader("de,en-US;q=0.7,en;q=0.3")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Gatling")

  val scn: ScenarioBuilder = scenario("Request Random Number")
    .repeat(10) {
      exec(
        http("Simple Get")
          .get("/")
      )
      .pause(1.second)
    }

  setUp(
    scn.inject(
      atOnceUsers(1)
    ).protocols(httpProtocol))
}
