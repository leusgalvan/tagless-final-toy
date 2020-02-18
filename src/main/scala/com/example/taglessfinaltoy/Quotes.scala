package com.example.taglessfinaltoy

import cats.effect._
import org.http4s.client.blaze._
import org.http4s.client.dsl.io._
import org.http4s.Method._
import org.http4s.circe._
import io.circe.generic.auto._

import scala.concurrent.ExecutionContext.global
import org.http4s.implicits._

object Quotes {
  case class Quote(quoteText: String,
                   quoteAuthor: String,
                   senderName: String,
                   senderLink: String,
                   quoteLink: String)
  implicit val cs: ContextShift[IO] = IO.contextShift(global)
  implicit val decoder = jsonOf[IO, Quote]

  def getRandom(): IO[Quote] = {
    val request =
      GET(
        uri"https://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=json&json=?"
      )
    BlazeClientBuilder[IO](global).resource
      .use { client =>
        client.fetchAs[Quote](request)
      }
      .handleErrorWith(_ => getRandom())
  }
}
