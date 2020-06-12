package com.test.load

import com.test.load.LoginService.neologinActions
import io.gatling.core.Predef._

import scala.concurrent.duration._

class LoginSimulation extends ServiceSimulation {

  setUp(
    neologinActions.inject(atOnceUsers(1) )
  ).protocols(httpConf)
    .assertions(global.successfulRequests.percent.greaterThan(90),
      global.responseTime.percentile1.lessThan(1000),
      global.responseTime.mean.lessThan(1000))
}
