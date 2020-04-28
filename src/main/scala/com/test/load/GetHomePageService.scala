package com.test.load

import io.gatling.core.Predef._
import io.gatling.http.Predef._


object GetHomePageService {

  val homePageActions = scenario("Medium Home Page")
    .exec(flushSessionCookies)
    .exec(
      http("view medium home page")
        .get("/")
        .check(status.is(200))
    )
    .exec(
      http("sign in")
        .post("_/batch")
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .check(status.is(200))
    )
}
