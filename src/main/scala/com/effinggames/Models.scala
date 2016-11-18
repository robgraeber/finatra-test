package com.effinggames

import java.time.{LocalDate, LocalDateTime}

case class AlgoResult(
  algoName: String,
  date: LocalDateTime,
  annReturns: Double,
  annVolatility: Double,
  maxDrawdown: Double,
  sharpe: Double,
  sortino: Double,
  calmar: Option[Double],
  historicalValues: Seq[Double],
  historicalDates: Seq[LocalDate],
  parentId: String = "foobar"
)