package example.util

import org.apache.spark.internal.Logging
import org.slf4j.Logger

/**
  * A proxy to [[org.slf4j.Logger]].
  */
trait LogsProxy extends Serializable {

  def loggerName: String

  def logger: Logger

}

/**
  * A default implementation of [[LogsProxy]].
  */
final class DefaultLogsProxy(val loggerName: String)
    extends LogsProxy
    with Logging {

  override protected def logName: String = {
    loggerName
  }

  override def logger: Logger = {
    log
  }

}
