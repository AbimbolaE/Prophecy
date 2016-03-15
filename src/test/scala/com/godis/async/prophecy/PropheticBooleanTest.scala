package com.godis.async.prophecy

import java.util.concurrent.Executors

import com.godis.async.prophecy.Prophecy.PropheticBoolean
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

class PropheticBooleanTest extends FeatureSpec with GivenWhenThen {

  val exceptionMessage = "My prophecies always come to pass"

  implicit val ec = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())

  feature("A Future of an Boolean: ifTrue") {

    scenario("I prophesy the Future of a Boolean which is true to result in a failure") {

      Given("I predict the future")
      val future = Future.successful(true)


      When("I manipulate time to cause a failure if there is a true")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ifTrue exception


      Then("my prediction shall come to pass")
      val caughtException = intercept[RuntimeException] {

        Await.result(manipulatedFuture, Duration.Zero)
        fail("...")
      }

      assert(caughtException.getMessage === exceptionMessage)
    }


    scenario("I prophesy the Future of an Boolean which is false to result in no failure") {

      Given("I predict the future")
      val future = Future.successful(false)


      When("I manipulate time to not cause a failure if there is a false")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ifTrue exception


      Then("my prediction shall come to pass")
      val result = Await.result(manipulatedFuture, Duration.Zero)

      assert(result === false)
    }
  }



  feature("A Future of an Boolean: ?") {

    scenario("I prophesy the Future of a Boolean which is true to result in a failure") {

      Given("I predict the future")
      val future = Future.successful(true)


      When("I manipulate time to cause a failure if there is a true")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ? exception


      Then("my prediction shall come to pass")
      val caughtException = intercept[RuntimeException] {

        Await.result(manipulatedFuture, Duration.Zero)
        fail("...")
      }

      assert(caughtException.getMessage === exceptionMessage)
    }


    scenario("I prophesy the Future of an Boolean which is false to result in no failure") {

      Given("I predict the future")
      val future = Future.successful(false)


      When("I manipulate time to not cause a failure if there is a false")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ? exception


      Then("my prediction shall come to pass")
      val result = Await.result(manipulatedFuture, Duration.Zero)

      assert(result === false)
    }
  }



  feature("A Future of an Boolean: ifFalse") {

    scenario("I prophesy the Future of an Boolean which is false to result in a failure") {

      Given("I predict the future")
      val future = Future.successful(false)


      When("I manipulate time to cause a failure if there is a false")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ifFalse exception


      Then("my prediction shall come to pass")
      val caughtException = intercept[RuntimeException] {

        Await.result(manipulatedFuture, Duration.Zero)
        fail("...")
      }

      assert(caughtException.getMessage === exceptionMessage)
    }


    scenario("I prophesy the Future of an Boolean which is true to result in no failure") {

      Given("I predict the future")
      val future = Future.successful(true)


      When("I manipulate time to not cause a failure if there is a true")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ifFalse exception


      Then("my prediction shall come to pass")
      val result = Await.result(manipulatedFuture, Duration.Zero)

      assert(result === true)
    }
  }



  feature("A Future of an Boolean: !") {

    scenario("I prophesy the Future of an Boolean which is false to result in a failure") {

      Given("I predict the future")
      val future = Future.successful(false)


      When("I manipulate time to cause a failure if there is a false")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ! exception


      Then("my prediction shall come to pass")
      val caughtException = intercept[RuntimeException] {

        Await.result(manipulatedFuture, Duration.Zero)
        fail("...")
      }

      assert(caughtException.getMessage === exceptionMessage)
    }


    scenario("I prophesy the Future of an Boolean which is true to result in no failure") {

      Given("I predict the future")
      val future = Future.successful(true)


      When("I manipulate time to not cause a failure if there is a true")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ! exception


      Then("my prediction shall come to pass")
      val result = Await.result(manipulatedFuture, Duration.Zero)

      assert(result === true)
    }
  }
}
