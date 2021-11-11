package de.conciso.solutions.solution5

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt

class GetGoogleTestResults extends Simulation {
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://google.de")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml,application/json")
    .acceptLanguageHeader("de,en-US;q=0.7,en;q=0.3")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Gatling")

  val scn: ScenarioBuilder = scenario("Request Random Number")
    .feed(csv("searchwords.csv").random)
    .repeat(3) {
      exec(
        http("Simple Get")
          .get("/search?q=${word}")
      ).pause(1.seconds)
    }

  setUp(
    scn.inject(
      atOnceUsers(1)
    ).protocols(httpProtocol))
}
