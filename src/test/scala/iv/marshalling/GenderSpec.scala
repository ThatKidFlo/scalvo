package iv.marshalling

import iv.model._
import org.scalatest.FlatSpec

/**
  * Created by Florin-Gabriel Barbuceanu on 01/09/2017.
  */
class GenderSpec extends FlatSpec {

  "Gender" should "be represented as the name of the case object" in {
    assert("Male" == Male.representation)
    assert("Female" == Female.representation)
    assert("Other" == Other.representation)
  }
}
