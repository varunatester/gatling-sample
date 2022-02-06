//package com.test.load
//import io.gatling.core.Predef._
//import com.test.load.LoginService.neologinActions
//
//
//
//class LoginSimulation extends ServiceSimulation {
//
//  setUp(
//    neologinActions.inject(atOnceUsers(1))).
//    protocols(httpConf)
//    .assertions(
//      global.successfulRequests.percent.gt(90),
//      global.responseTime.percentile1.lt(1000),
//      global.responseTime.mean.lt(1000))
//}
