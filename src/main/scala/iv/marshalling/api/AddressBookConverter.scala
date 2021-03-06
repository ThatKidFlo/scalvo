package iv.marshalling.api

import iv.model.AddressEntry

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
trait AddressBookConverter[Representation] {
  def deserialize(representation: Representation): Option[AddressEntry]
}
