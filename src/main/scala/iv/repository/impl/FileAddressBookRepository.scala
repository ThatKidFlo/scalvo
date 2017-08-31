package iv.repository.impl

import iv.repository.api.AddressBookRepository

import scala.util.Try

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
final class FileAddressBookRepository private(path: String) extends AddressBookRepository[String] {
  override def fetch: Iterator[String] = Try {
    scala
      .io
      .Source
      .fromFile(path)
      .getLines
  } getOrElse Iterator()
}

object FileAddressBookRepository {
  def apply(path: String): AddressBookRepository[String] = new FileAddressBookRepository(path)
}