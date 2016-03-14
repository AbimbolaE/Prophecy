package com.godis.async.prophecy

import scala.concurrent.{ExecutionContext, Future}

object Prophecy {

  implicit class PropheticOption[A](f: Future[Option[A]]) {

    def onNone(ex: Exception)(implicit ec: ExecutionContext) = f flatMap { o =>
      if (o.isEmpty) Future.failed(ex)
      else Future.successful(o)
    }

    def !(ex: Exception)(implicit ec: ExecutionContext) = onNone(ex)


    def onSome(ex: Exception)(implicit ec: ExecutionContext) = f flatMap { o =>
      if (o.nonEmpty) Future.failed(ex)
      else Future.successful(o)
    }

    def ?(ex: Exception)(implicit ec: ExecutionContext) = onSome(ex)
  }

  implicit class PropheticBoolean(f: Future[Boolean]) {

    def ifTrue(ex: Exception)(implicit ec: ExecutionContext) = f flatMap { b =>
      if (b) Future.failed(ex)
      else Future.successful(b)
    }

    def ?(ex: Exception)(implicit ec: ExecutionContext) = ifTrue(ex)


    def ifFalse(ex: Exception)(implicit ec: ExecutionContext) = f flatMap { b =>
      if (!b) Future.failed(ex)
      else Future.successful(b)
    }

    def !(ex: Exception)(implicit ec: ExecutionContext) = ifFalse(ex)
  }
}
