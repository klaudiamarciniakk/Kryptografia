import java.math.BigInteger

class Project3 {

  fun main(args: Array<String>) {
    val p = BigInteger("20769187434139310514121985316880223")
    println(generowanieKluczaElGamala(p))
  }

  private fun generowanieKluczaElGamala(p: BigInteger): List<BigInteger> {
    val result = mutableListOf<BigInteger>()
    val punktP = randomEllipticCurve(p)
    var x = bi(0)
    val punktQ = randomPointOnEllipticCurve(p, punktP[0], punktP[1])
    while (liczbaPierwsza(x)) {
      x = generator(bi(0), p.subtract(bi(1)))
    }
    val wynik = wielokrotnoscPunktu(punktP[0], punktP[1], p, x, punktQ[0], punktQ[1])
    result.add(wynik[0])
    result.add(wynik[1])
    return result
  }
}