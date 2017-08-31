package iv

import iv.config.constants.Constants
import iv.config.constants.Constants._
import iv.marshalling.api.AddressBookConverter
import iv.marshalling.impl.FileAddressBookConverter
import iv.model.AddressEntry
import iv.repository.api.AddressBookRepository
import iv.repository.impl.FileAddressBookRepository
import iv.service.{BillOlderThanPaulInDays, CountMales, OldestPerson}

import scala.util.Properties

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
object AddressBookApplication {
  val questions: List[(Long, Function[List[AddressEntry], _])] = List(
    1L -> CountMales.answer,
    2L -> OldestPerson.answer,
    3L -> BillOlderThanPaulInDays.answer
  )

  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      println(s"[WARNING] >>>> Usage: executable <path_to_address_book>${Properties.lineSeparator}[WARNING] >>>> Will fallback to <${Constants.DEFAULT_FILE_PATH}>")
    } else if (args.length > 1) {
      println(s"[WARNING] >>>> Usage: executable <path_to_address_book>${Properties.lineSeparator}[WARNING] >>>> Will consider <${args(0)}> as the path argument.")
    }

    val filePath = args
      .headOption
      .getOrElse(DEFAULT_FILE_PATH)

    val dataSource: AddressBookRepository[String] = FileAddressBookRepository(filePath)
    val converter: AddressBookConverter[String] = FileAddressBookConverter()

    val contacts: List[AddressEntry] = dataSource
      .fetch
      .flatMap(converter.deserialize)
      .toList

    questions map {
      case (id, question) => id -> question(contacts)
    } foreach println
  }
}

