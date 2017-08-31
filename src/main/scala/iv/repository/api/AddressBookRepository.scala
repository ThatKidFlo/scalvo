package iv.repository.api

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
trait AddressBookRepository[Representation] {
  def fetch: Iterator[Representation]
}

