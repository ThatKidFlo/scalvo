package iv.marshalling

import iv.config.constants.Constants
import iv.repository.impl.FileAddressBookRepository
import org.scalatest.FlatSpec

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
class FileHandlerSpec extends FlatSpec {

  "FileAddressBookRepository" should "return an empty Iterator if file cannot be read" in {
    val fileHandler = FileAddressBookRepository("")
    assert(fileHandler.fetch.isEmpty)
  }

  it should "successfully read a file if it exists" in {
    assert(!FileAddressBookRepository(Constants.DEFAULT_FILE_PATH).fetch.contains(""))
  }
}
