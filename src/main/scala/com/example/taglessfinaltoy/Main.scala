package com.example.taglessfinaltoy

import cats.effect.IO
import cats.implicits._
import cats.{Applicative, Id, Monad}

trait QuotesDsl[F[_]] {
  def getRandomQuote(): F[String]
}

object Interpreters {
  implicit val quotesInterpreterIO = new QuotesDsl[IO] {
    override def getRandomQuote(): IO[String] =
      Quotes.getRandom().map(_.quoteText)
  }

  implicit val quotesInterpreterId = new QuotesDsl[Id] {
    val quotes =
      List(
        "Muchachos, voy por cigarrillos",
        "Welcome to the party, pal",
        "Yes, I can hear you, Simon",
        "Pucha Jorge, y ahora?",
        "Alguno caliente mis magras",
        "No puedo estar 5 segundos sin hacer el ridiculo"
      )
    override def getRandomQuote(): Id[String] = {
      val index = (math.random() * 6).toInt
      quotes(index)
    }
  }
}

object Main {
  def getQuotes[F[_]: Monad](
    n: Int
  )(implicit interpreter: QuotesDsl[F]): F[Set[String]] = {
    if (n == 0) Monad[F].pure(Set())
    else {
      for {
        rest <- getQuotes[F](n - 1)
        first <- Monad[F].iterateWhile(interpreter.getRandomQuote())(
          rest.contains
        )

      } yield rest + first
    }
  }

  def run() = {
    import Interpreters._

    println(getQuotes[IO](3).unsafeRunSync().mkString("\n"))
    println(getQuotes[Id](3).mkString("\n"))
  }
}
