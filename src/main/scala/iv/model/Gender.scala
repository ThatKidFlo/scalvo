package iv.model

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
sealed abstract class Gender {
  final def representation: String = this.getClass.getSimpleName.dropRight(1)
}
case object Male extends Gender
case object Female extends Gender
case object Other extends Gender

object Gender {
  val values: List[Gender] = List(Male, Female, Other)

  final def find(name: String): Option[Gender] = values.find(_.representation == name)
}

