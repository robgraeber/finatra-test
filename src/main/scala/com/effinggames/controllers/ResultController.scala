package com.effinggames.controllers

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.response.Mustache

@Mustache("index")
case class ResultView (
  asdf: Int = 6
)
object ResultController extends Controller {
  def getResult(request: Request) = {
    ResultView()
  }
}