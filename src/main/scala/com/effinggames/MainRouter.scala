package com.effinggames

import com.effinggames.controllers.ResultController
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

object MainRouter extends Controller {
  get("/")(ResultController.getResult)
  get("/results")(ResultController.getResult)
  get("/results/:id")(ResultController.getResult)

  get("/assets/:fileName") { request: Request =>
    val fileName = request.params("fileName")
    response.ok.file(s"assets/$fileName")
  }
}
