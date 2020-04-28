package com.test.load2


import io.gatling.core.Predef.*
import scala.`Predef$`;
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.config.HttpProtocolBuilder
import io.gatling.http.request.builder.HttpRequestBuilder
import scala.concurrent.duration.Duration
import scala.reflect.`ClassTag$`

open class FirstSimulation : Simulation() {
    val httpConf: HttpProtocolBuilder = http().baseURL("https://medium.com")
            .acceptLanguageHeader(stringToExpression("en-US,en;q=0.5", `ClassTag$`.`MODULE$`.apply(String.javaClass)))
            .acceptEncodingHeader(stringToExpression("gzip, deflate", `ClassTag$`.`MODULE$`.apply(String.javaClass)))
            .userAgentHeader(stringToExpression("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0", `ClassTag$`.`MODULE$`.apply(String.javaClass)))
    private val builder: HttpRequestBuilder = http(stringToExpression("Home page request", `ClassTag$`.`MODULE$`.apply<String>(String.javaClass))).get(stringToExpression("/", `ClassTag$`.`MODULE$`.apply(String.javaClass)))
    val scn: ScenarioBuilder = scenario("HomePageSimulation")
            .exec(builder as ActionBuilder)
            .pause(intToFiniteDuration(5))
//            .pause(io.gatling.core.`Predef$`.`MODULE$`.intToFiniteDuration(5) as Duration)
}