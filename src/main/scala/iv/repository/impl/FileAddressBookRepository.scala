package iv.repository.impl

import iv.repository.api.AddressBookRepository

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
final class FileAddressBookRepository private(path: String) extends AddressBookRepository[String] {
  override def fetch: Iterator[String] = scala
    .io
    .Source
    .fromFile(path)
    .getLines
}

object FileAddressBookRepository {
  def apply(path: String): AddressBookRepository[String] = new FileAddressBookRepository(path)
}