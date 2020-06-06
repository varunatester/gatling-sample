package com.test.load

import com.test.load.HyperSyncService.loginActions
import io.gatling.core.Predef._

import scala.concurrent.duration._

class HyperSyncSimulation extends ServiceSimulation {

  setUp(
    loginActions.inject(rampUsers(3) over (30 seconds))
  ).protocols(httpConf)
    .assertions(global.successfulRequests.percent.greaterThan(90),
      global.responseTime.percentile1.lessThan(1000),
      global.responseTime.mean.lessThan(1000))

  //setUp(homePageSignInActions.inject(atOnceUsers(10)))
}
