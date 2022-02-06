//package com.test.load
//import io.gatling.core.Predef._
//import io.gatling.http.Predef._
//
//
//object LoginService {
//  val csvFeeder = csv("login.csv").random
//  val neologinActions = scenario("Neo -Login")
//    .exec(flushSessionCookies)
//    .feed(csvFeeder)
//    .exec(
//      http("login")
//        .post("/login/validate")
//        .header("Content-Type", "application/json")
//        .header("Accept", "application/json")
//        .body(StringBody("{\"userId\":\"${userId}\",\"mpin\":\"123456\"}")).asJSON
//        .check(jsonPath("$.data").saveAs("tokenId"))
//    )
//    .exec( session => {
//      println( "Login Service:" )
//      println( session( "tokenId" ).as[String] )
//      session
//    })
//}
