package iv.marshalling.impl

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import iv.config.constants.Constants._
import iv.marshalling.api.AddressBookConverter
import iv.model.{AddressEntry, Gender}

import scala.util.Try

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
final class FileAddressBookConverter private() extends AddressBookConverter[String] {
  private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)

  override def deserialize(line: String): Option[AddressEntry] = line.split(ENTRY_VALUES_SEPARATOR) match {
    case Array(name, gender, dateOfBirth) => Try {
      AddressEntry(name, Gender.find(gender).get, LocalDate.parse(dateOfBirth, dateFormatter))
    } toOption
    case _ => Option.empty
  }
}

object FileAddressBookConverter {
  def apply(): AddressBookConverter[String] = new FileAddressBookConverter
}
