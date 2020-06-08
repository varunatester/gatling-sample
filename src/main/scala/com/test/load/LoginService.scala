package com.test.load

import io.gatling.core.Predef._
import io.gatling.http.Predef._


object LoginService {
  val csvFeeder = csv("login.csv").random
  val loginActions = scenario("Neo -Login")
    .exec(flushSessionCookies)
    .feed(csvFeeder)
    .exec(
      http("login")
        .post("/login/validate")
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .body(StringBody( """{"userId":"\"${userId}\","mpin":"123456"}""".stripMargin)).asJSON
        .check(jsonPath("$.token").saveAs("tokenId"))
    )
}
