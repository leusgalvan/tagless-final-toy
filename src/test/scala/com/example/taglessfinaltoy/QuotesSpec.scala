package com.example.taglessfinaltoy

import org.specs2.mutable.Specification

class QuotesSpec extends Specification {
  "Quotes" should {
    "provide random quote" in {
      val quote = Quotes.getRandom().unsafeRunSync()
      quote.quoteText.length > 0 should beTrue
    }
  }
}
