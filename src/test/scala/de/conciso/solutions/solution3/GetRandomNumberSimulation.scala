package de.conciso.solutions.solution3

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

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
    .exec(
      http("Get Number")
        .get("/test")
        .check(status.is(200)
        ))


  setUp(
    scn
      .inject(
        rampUsers(5).during(10.seconds))
      .protocols(httpProtocol))
    .assertions(
      global.responseTime.max.lt(1500)
    )
}
