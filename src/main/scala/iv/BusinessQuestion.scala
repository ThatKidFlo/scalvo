package iv

import iv.Constants._

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
sealed trait BusinessQuestion[A] {
  def answer(contacts: List[AddressEntry]): A
}

case object CountMales extends BusinessQuestion[Int] {
  override def answer(contacts: List[AddressEntry]): Int = contacts.count(_.gender == Male)
}

case object OldestPerson extends BusinessQuestion[AddressEntry] {
  override def answer(contacts: List[AddressEntry]): AddressEntry = contacts.minBy(_.dateOfBirth.toEpochDay)
}

case object BillOlderThanPaulInDays extends BusinessQuestion[Option[Long]] {
  override def answer(contacts: List[AddressEntry]): Option[Long] = for {
    bill <- contacts.find(_.name contains BILL)
    paul <- contacts.find(_.name contains PAUL)
  } yield {
    paul.dateOfBirth.toEpochDay - bill.dateOfBirth.toEpochDay
  }
}