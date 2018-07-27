package testutil

import org.apache.log4j.spi.LoggingEvent
import org.apache.log4j.{AppenderSkeleton, Level, LogManager}

import scala.collection.mutable

/**
  * A testing utility that allows the [[org.apache.log4j.spi.LoggingEvent]] to be captured.
  */
trait LoggingCapture {

  final class TestAppenderSkeleton extends AppenderSkeleton {

    val buffer: mutable.Buffer[LoggingEvent] = mutable.Buffer.empty[LoggingEvent]

    override def append(event: LoggingEvent): Unit = {
      buffer.append(event)
    }

    override def close(): Unit = {}

    override def requiresLayout(): Boolean = false

  }

  def withLog[T](level: Level)(f: => T): List[LoggingEvent] = {
    val rootLogger = LogManager.getRootLogger
    val originalLevel = rootLogger.getLevel
    val testAppenderSkeleton = new TestAppenderSkeleton
    rootLogger.addAppender(testAppenderSkeleton)
    try {
      f
      testAppenderSkeleton.buffer.toList
    } finally {
      rootLogger.removeAppender(testAppenderSkeleton)
      rootLogger.setLevel(originalLevel)
    }
  }

}
