package com.test.juiceshop
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import scala.concurrent.duration._
import scala.language.postfixOps


class AddProductSimulation extends Simulation {

  val httpConf: HttpProtocolBuilder = http
    .baseUrl("http://192.168.64.2:3000/")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn: ScenarioBuilder = scenario("Add Product to Cart Simulation")
    .exec(addProductToCart())

  def addProductToCart(): ChainBuilder = {
    repeat(1) {
      exec(http("Login user")
        .post("rest/user/login")
        .body(ElFileBody("loginRequest.json")).asJson
        .check(status.is(200))
        .check(jsonPath("$.authentication.token").saveAs("token"))
        .check(jsonPath("$.authentication.bid").saveAs("bid")))

        .pause(5)
        .exec(http("Get All Products")
          .get("rest/products/search?q=")
          .header("Authorization", "Bearer " + "${token}")
          .check(status.is(201))
          .check(jsonPath("$.data[8].id").saveAs("productId")))


    }
  }



  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(1),
      rampUsers(1) during (10 seconds)
    ).protocols(httpConf.inferHtmlResources()))

}
