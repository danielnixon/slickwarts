package org.danielnixon.slickwarts

import org.danielnixon.slickwarts.{ BasicStreamingActionPartial => BasicStreamingActionWart }
import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import slick.basic.BasicStreamingAction
import slick.dbio.Effect.All
import slick.dbio.NoStream

class BasicStreamingActionPartialTest extends FunSuite {

  val basicStreamingAction = new BasicStreamingAction[Nothing, Nothing, All] {
    override def head: ResultAction[Nothing, NoStream, All] = ???

    override def headOption = ???

    override def getDumpInfo = ???
  }

  test("can't call BasicStreamingAction#head") {
    val result = WartTestTraverser(BasicStreamingActionWart) {
      basicStreamingAction.head
    }
    assertResult(List("[wartremover:BasicStreamingActionPartial] BasicStreamingAction#head is disabled - use BasicStreamingAction#headOption instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("BasicStreamingActionPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(BasicStreamingActionWart) {
      @SuppressWarnings(Array("org.danielnixon.slickwarts.BasicStreamingActionPartial"))
      val foo = basicStreamingAction.head
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
