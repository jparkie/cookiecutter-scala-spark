package suites

import com.dimafeng.testcontainers.ForAllTestContainer
import com.holdenkarau.spark.testing.SharedMiniCluster
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.scalatest.GivenWhenThen

/**
  * A test class to extend when writing acceptance tests with Spark.
  * - Provides embedded HDFS and YARN clusters tied with the [[org.apache.spark.SparkContext]].
  * - Provides Docker containers through the use of testcontainer-scala.
  *
  * @see [[https://github.com/testcontainers/testcontainers-scala]].
  */
abstract class SparkAcceptanceSuite extends BaseSuiteLike
  with SharedMiniCluster
  with ForAllTestContainer
  with GivenWhenThen {

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
