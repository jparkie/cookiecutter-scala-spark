package example.util

/**
  * A reference to a lazily initialized resource per JVM.
  *
  * @see Joshua Bloch. 2008. Effective Java (2nd Edition) (The Java Series) (2 ed.). Prentice Hall PTR,
  *      Upper Saddle River, NJ, USA.
  */
// scalastyle:off allowNullChecks
trait PerJvmReference[T] extends Serializable with AutoCloseable {

  @transient
  @volatile
  private var instance: T = _

  def apply(f: => T): T = {
    // Item 71: Use lazy initialization judiciously
    var result = instance
    if (result == null) {
      this.synchronized {
        result = instance
        if (result == null) {
          result = f
          instance = result
        }
      }
    }
    result
  }

  sys.addShutdownHook {
    close()
  }

}
// scalastyle:on allowNullChecks
