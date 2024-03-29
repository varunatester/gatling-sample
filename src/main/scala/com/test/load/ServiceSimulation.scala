package com.test.load
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ServiceSimulation extends Simulation{
  val httpConf = http
    .baseUrl("http://192.168.64.2:3000/")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

}
