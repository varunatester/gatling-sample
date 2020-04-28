package com.test.load

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder


class FirstSimulation2 extends Simulation {
  val httpConf = http
    .baseURL("https://medium.com")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  private val builder: HttpRequestBuilder =
    http("Home page request")
    .get("/").check(status.is(200))
  val scn = scenario("HomePageSimulation")
    .exec(builder)
    .pause(5)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)


}
