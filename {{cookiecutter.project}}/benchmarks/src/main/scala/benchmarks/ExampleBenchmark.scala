package benchmarks

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

// scalastyle:off magic.number
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
// scalastyle:on magic.number
class ExampleBenchmark {

  @Benchmark
  def exampleOperation(bh: Blackhole): Unit = {
    bh.consume(1)
  }

}
