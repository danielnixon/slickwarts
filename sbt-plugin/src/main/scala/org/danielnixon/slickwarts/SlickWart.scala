package org.danielnixon.slickwarts

import wartremover.Wart

object SlickWart {
  val BasicStreamingActionPartial: Wart = wart("BasicStreamingActionPartial")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.slickwarts.$name")
  }
}
