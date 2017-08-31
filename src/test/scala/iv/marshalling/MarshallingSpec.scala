package iv.marshalling

import iv.marshalling.impl.FileAddressBookConverter
import iv.model.Female
import org.scalatest.FlatSpec

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
class MarshallingSpec extends FlatSpec {
  val fileAddressBookConverter = FileAddressBookConverter()

  "FileAddressBookConverter" should "return an empty Option if parsing errors are encountered" in {
    assert(fileAddressBookConverter.deserialize("Gemma Lane, Female, 20/").isEmpty)
    assert(fileAddressBookConverter.deserialize("Gemma Lane,, 20/11/91").isEmpty)
    assert(fileAddressBookConverter.deserialize(" 20/").isEmpty)
    assert(fileAddressBookConverter.deserialize("").isEmpty)
  }

  it should "build instances of AddressEntity if parsing succeeds" in {
    val maybeAddressEntry = fileAddressBookConverter.deserialize("Gemma Lane, Female, 20/11/91")
    assert(maybeAddressEntry.isDefined)
    assert(maybeAddressEntry.exists(_.name == "Gemma Lane"))
    assert(maybeAddressEntry.exists(_.gender == Female))
    assert(maybeAddressEntry.exists(_.dateOfBirth.toString == "2091-11-20"))
  }
}
