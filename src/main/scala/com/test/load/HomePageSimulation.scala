package com.test.load
import scala.concurrent.duration._
import io.gatling.core.Predef._
import com.test.load.GetHomePageService.homePageSignInActions

class HomePageSimulation extends ServiceSimulation {

  setUp(
    homePageSignInActions.inject(rampUsers(10) over (30 seconds))
  ).protocols(httpConf)
    .assertions(global.successfulRequests.percent.greaterThan(90),
      global.responseTime.percentile1.lessThan(1000),
      global.responseTime.mean.lessThan(1000))

  //setUp(homePageSignInActions.inject(atOnceUsers(10)))
}
