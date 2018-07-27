package example.util

import com.codahale.metrics.MetricRegistry
import org.apache.spark.SparkEnv
import org.apache.spark.metrics.source.PublicSource

/**
  * A proxy to [[com.codahale.metrics.MetricRegistry]].
  */
trait MetricsProxy extends Serializable {

  def metricRegistryName: String

  def metricRegistry: MetricRegistry

}

/**
  * A default implementation of [[MetricsProxy]].
  */
final class DefaultMetricsProxy(val metricRegistryName: String) extends MetricsProxy {

  @transient
  override lazy val metricRegistry: MetricRegistry = {
    val metricsSource = new DefaultMetricsProxySource()
    val metricsSystem = SparkEnv.get.metricsSystem
    try {
      metricsSystem.registerSource(metricsSource)
    } catch {
      case _: Exception =>
        // Do Nothing.
    }
    metricsSystem.getSourcesByName(metricRegistryName)
      .headOption
      .map(actualMetricsSource => actualMetricsSource.metricRegistry)
      .getOrElse(throw new IllegalStateException(s"Unable to get MetricRegistry for name: $metricRegistryName"))
  }

  private class DefaultMetricsProxySource extends PublicSource {

    override val sourceName: String = {
      metricRegistryName
    }

    override val metricRegistry: MetricRegistry = {
      new MetricRegistry()
    }

  }

}
