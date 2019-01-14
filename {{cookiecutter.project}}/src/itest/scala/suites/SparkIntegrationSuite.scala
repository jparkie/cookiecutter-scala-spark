package suites

import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.sql.{SQLContext, SparkSession}

/**
  * A test class to extend when writing integration tests with Spark.
  */
abstract class SparkIntegrationSuite
    extends BaseSuiteLike
    with SharedSparkContext {

  @transient
  private var _spark: SparkSession = _

  def spark: SparkSession = _spark

  override def beforeAll(): Unit = {
    super.beforeAll()
    _spark = new SQLContext(sc).sparkSession
  }

  override def afterAll(): Unit = {
    _spark = null // scalastyle:ignore
    super.afterAll()
  }

}
