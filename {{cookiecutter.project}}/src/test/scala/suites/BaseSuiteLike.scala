package suites

import java.io.InputStream

import org.apache.spark.util.PublicClosureCleaner
import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest._
import org.scalatest.prop.Checkers

import scala.io.Source

/**
  * A base trait that features many batteries to aid writing tests.
  */
trait BaseSuiteLike extends FunSuiteLike
  with Matchers
  with Checkers
  with Inspectors
  with Inside
  with OptionValues
  with EitherValues
  with PrivateMethodTester
  with MockitoSugar
  with ArgumentMatchersSugar {

  def getResourceAsInputStream(resourcePath: String): InputStream = {
    val currentClassLoader = Thread.currentThread().getContextClassLoader
    currentClassLoader.getResourceAsStream(resourcePath)
  }

  def getResourceAsString(resourcePath: String): String = {
    val inputStream = getResourceAsInputStream(resourcePath)
    try {
      Source.fromInputStream(inputStream).mkString
    } finally {
      inputStream.close()
    }
  }

  def assertSparkSerializable(closure: AnyRef): Unit = {
    noException should be thrownBy PublicClosureCleaner.clean(closure, cleanTransitively = false)
  }

}
