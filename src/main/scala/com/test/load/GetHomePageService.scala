package com.test.load
import io.gatling.core.Predef._
import io.gatling.http.Predef._
object GetHomePageService {

  val homePageSignInActions = scenario("Medium Home Page")
    .exec(flushSessionCookies)
    .exec(
      http("View Medium Home Page")
        .get("/")
        .check(status.is(200))
    )
    .exec(
      http("Medium Sign in Page")
        .post("_/batch")
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .check(status.is(200))
    )
}
