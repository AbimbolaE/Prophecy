package com.godis.async.prophecy

import java.util.concurrent.Executors

import com.godis.async.prophecy.Prophecy.PropheticOption
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

class PropheticOptionTest extends FeatureSpec with GivenWhenThen {

  val exceptionMessage = "My prophecies always come to pass"

  implicit val ec = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())

  feature("A Future of an Option: onSome") {

    scenario("I prophesy the Future of an Option containing a Some to result in a failure") {

      Given("I predict the future")
      val future = Future.successful(Some(1))


      When("I manipulate time to cause a failure if there is a Some")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future onSome exception


      Then("my prediction shall come to pass")
      val caughtException = intercept[RuntimeException] {

        Await.result(manipulatedFuture, Duration.Zero)
        fail("...")
      }

      assert(caughtException.getMessage === exceptionMessage)
    }


    scenario("I prophesy the Future of an Option containing a None to result in no failure") {

      Given("I predict the future")
      val future = Future.successful(None)


      When("I manipulate time to not cause a failure if there is a None")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future onSome exception


      Then("my prediction shall come to pass")
      val result = Await.result(manipulatedFuture, Duration.Zero)

      assert(result === None)
    }
  }



  feature("A Future of an Option: ?") {

    scenario("I prophesy the Future of an Option containing a Some to result in a failure") {

      Given("I predict the future")
      val future = Future.successful(Some(1))


      When("I manipulate time to cause a failure if there is a Some")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ? exception


      Then("my prediction shall come to pass")
      val caughtException = intercept[RuntimeException] {

        Await.result(manipulatedFuture, Duration.Zero)
        fail("...")
      }

      assert(caughtException.getMessage === exceptionMessage)
    }


    scenario("I prophesy the Future of an Option containing a None to result in no failure") {

      Given("I predict the future")
      val future = Future.successful(None)


      When("I manipulate time to not cause a failure if there is a None")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ? exception


      Then("my prediction shall come to pass")
      val result = Await.result(manipulatedFuture, Duration.Zero)

      assert(result === None)
    }
  }



  feature("A Future of an Option: onNone") {

    scenario("I prophesy the Future of an Option containing a None to result in a failure") {

      Given("I predict the future")
      val future = Future.successful(None)


      When("I manipulate time to cause a failure if there is a None")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future onNone exception


      Then("my prediction shall come to pass")
      val caughtException = intercept[RuntimeException] {

        Await.result(manipulatedFuture, Duration.Zero)
        fail("...")
      }

      assert(caughtException.getMessage === exceptionMessage)
    }


    scenario("I prophesy the Future of an Option containing a Some to result in no failure") {

      Given("I predict the future")
      val future = Future.successful(Some(1))


      When("I manipulate time to not cause a failure if there is a Some")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future onNone exception


      Then("my prediction shall come to pass")
      val result = Await.result(manipulatedFuture, Duration.Zero)

      assert(result === Some(1))
    }
  }



  feature("A Future of an Option: !") {

    scenario("I prophesy the Future of an Option containing a None to result in a failure") {

      Given("I predict the future")
      val future = Future.successful(None)


      When("I manipulate time to cause a failure if there is a None")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ! exception


      Then("my prediction shall come to pass")
      val caughtException = intercept[RuntimeException] {

        Await.result(manipulatedFuture, Duration.Zero)
        fail("...")
      }

      assert(caughtException.getMessage === exceptionMessage)
    }


    scenario("I prophesy the Future of an Option containing a Some to result in no failure") {

      Given("I predict the future")
      val future = Future.successful(Some(1))


      When("I manipulate time to not cause a failure if there is a Some")
      val exception = new RuntimeException(exceptionMessage)
      val manipulatedFuture = future ! exception


      Then("my prediction shall come to pass")
      val result = Await.result(manipulatedFuture, Duration.Zero)

      assert(result === Some(1))
    }
  }
}
