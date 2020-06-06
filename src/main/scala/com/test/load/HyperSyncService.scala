package com.test.load

import io.gatling.core.Predef._
import io.gatling.http.Predef._


object HyperSyncService {

  private val hsinv = "HSINV2"
  val csvFeeder = csv("categories.csv").random
  val loginActions = scenario("HyperSync Service -Login")
    .exec(flushSessionCookies)
    .feed(csvFeeder)
    .exec(
      http("login")
        .post("/quick/user/login")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Accept", "*/*")
        .formParam("jData", "{\"usrId\":\"${userId}\",\"pwd\":\"qwe@234\",\"panOrDob\":\"asdfg1234a\",\"apkVersion\":\"0\",\"imei\":\"\",\"Source\":\"WEB\"}")
        .check(status.is(200))
        .check(jsonPath("$.usrSessId").saveAs("tokenId"))
    )

    .exec(
      http("Place order")
        .post("/quick/order/place")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Accept", "*/*")
        .formParam("jKey","${tokenId}")
        .formParam("jData","{\"es\":\"nse_cm\",\"pc\":\"NRML\",\"pr\":\"125\",\"ot\":\"L\",\"qt\":\"5\",\"rt\":\"DAY\",\"tk\":\"11536\",\"tp\":\"0\",\"ts\":\"TCS-EQ\",\"tt\":\"S\"}")
        .check(status.is(200))
    )
}
