package testutil

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

/**
  * A testing utility that allows the [[Console]] standard out and error to be captured.
  */
trait ConsoleCapture {

  def withConsoleOutErr[T](f: => T): (String, String) = {
    val outStream = new ByteArrayOutputStream()
    val errStream = new ByteArrayOutputStream()
    Console.withOut(outStream) {
      Console.withErr(errStream) {
        f
      }
    }
    val outString = outStream.toString(StandardCharsets.UTF_8.name())
    val errString = errStream.toString(StandardCharsets.UTF_8.name())
    (outString, errString)
  }

  def withConsoleOut[T](f: => T): String = {
    val outputStream = new ByteArrayOutputStream()
    Console.withOut(outputStream)(f)
    outputStream.toString(StandardCharsets.UTF_8.name())
  }

  def withConsoleErr[T](f: => T): String = {
    val outputStream = new ByteArrayOutputStream()
    Console.withErr(outputStream)(f)
    outputStream.toString(StandardCharsets.UTF_8.name())
  }

}
