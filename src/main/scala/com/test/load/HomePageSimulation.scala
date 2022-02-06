package com.test.load
import io.gatling.core.Predef._
import com.test.load.GetHomePageService.homePageSignInActions

class HomePageSimulation extends ServiceSimulation {

  setUp(
    homePageSignInActions.inject(rampUsers(10).during(10))
  ).protocols(httpConf)
    .assertions(global.successfulRequests.percent.gt(90),
      global.responseTime.percentile1.lt(1000),
      global.responseTime.mean.lt(1000))

//  setUp(homePageSignInActions.inject(atOnceUsers(10)))
 // setUp(homePageSignInActions.inject( constantUsersPerSec(10).during(15)))
}
