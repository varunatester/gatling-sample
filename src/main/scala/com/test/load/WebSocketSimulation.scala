package com.test.load
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class WebSocketSimulation extends Simulation {
    val webSocketSimulation = scenario("Websocket Simulation")
      .exec("wss://echo.websocket.org/")
      .exec(ws("Connect WS").open("/"))
      .pause(3)
      .exec(ws("Send Text").sendText("""{"text" : "Hello, I'm Varuna"""))
      .exec(ws("Send Text").sendText("""{"text" : "Hello, I'm Varuna12"""))
      .exec(ws("Send Text").sendText("""{"text" : "Hello, I'm Varuna123"""))
      .exec(ws("Send Text").sendText("""{"text" : "Hello, I'm Varuna1234"""))
      .exec(ws("Send Text").sendText("""{"text" : "Hello, I'm new to KSec""")
        .check(wsListen.within(30 seconds).until(1).regex("KSec")))
      .pause(4)
      .exec(ws("Close WS").close)

    val wsConf = http.wsBaseURL("wss://echo.websocket.org/")
    setUp(webSocketSimulation.inject(rampUsers(5000) over (30 seconds))).protocols(wsConf)
    setUp(webSocketSimulation.inject(atOnceUsers(5000))).protocols(wsConf)
}
