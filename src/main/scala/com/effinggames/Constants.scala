package com.effinggames

import com.twitter.inject.Logging

object Constants extends Logging {
  //Gets the env variable with default.
  private def getEnvVariable(name: String, default: String): String = {
    sys.env.getOrElse(name, default)
  }

  //Gets the env variable, throws error if undefined.
  private def getEnvVariable(name: String): String = {
    try {
      sys.env.get(name).get
    } catch {
      case err: Throwable =>
        logger.error(s"$name is not defined!")
        throw err
    }
  }

  val port = getEnvVariable("PORT", "8000").toInt
  val productionMode = getEnvVariable("PRODUCTION_MODE", "false").toBoolean
}
