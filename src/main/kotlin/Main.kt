import java.lang.ArithmeticException
import java.math.BigInteger
import kotlin.math.pow

fun main(args: Array<String>) {

//        ZAD.1
//        val b = 3
//        val n = (2.0.pow(30.0) - 5).toInt()
//        val result = 357913940
//  val b = b(2)
//  val n = 7
//  val result = 4
//  println(
//    "Zad1. \n n=" + n + "\n b=" + b + "\n Result:\n provided: " + odwrotnoscWGrupie(n, b).toString() + "\n " +
//            "expected: " + result
//  )

//        ZAD.2
//        var n = b(2)
//        n = n.pow(100)
//        n = n.subtract(b(1))
//        var k = b(2)
//        k = k.pow(30)
//        val b = b(3)
//        val result = BigInteger("404294742055422617191248672231")
//        println("Zad2. \n n: " + n + " \n k: " + k + "\n b: " + b + "\n Result:\n provided: " + efektywnePotegowanie(n, k, b)
//            + "\n expected: " + result)

//        ZAD.3

//        val n = b(2)
//        n.pow(201)
//        n.subtract(b(313))
//        val result = true
//
//        println("Zad3. \n n: "+n + "\n Liczba pierwsza: " + liczbaPierwsza(n) + "\n Expected result: " + result)

//        Zad 4
//        val n = b(2).pow(201)- b(313)
//        val b = b(2)
//        val result = true

//        val n = b(2).pow(201)- b(313)
//        val b = b(3)
//        val result = false

//        val n = b(2).pow(33) - b(9)
//        val b = b(11)
//        val result = true

//        val n = b(2).pow(33) - b(9)
//        val b = b(10)
//        val result = false

//        println("expected: " + result + " provided: " + (liczbaKwadratowa(n,b) == b(1)))

//        ZAD5
//        var p = b(2)
//        p = p.pow(201)
//        p = p.subtract(b(313))
//        val b = b(2)
//        val result=BigInteger("3101948484103482126970424634806781703628882321291710380824411")

//        var p = b(2)
//        p = p.pow(33)
//        p = p.subtract(b(9))
//        val b = b(11)
//        val result = BigInteger("1578746474")

//        val p = bi(7)
//        val b =bi(2)
//        val result = 4
//        println("expected: " + result + " provided: " + pierwiastekKwadratowyPhi(p, b))
}

private fun pierwiastekKwadratowyPhi(p: BigInteger, b: BigInteger): BigInteger {
  val rk = p.divide(bi(2))
//  if (efektywnePotegowanie(p, rk+b, b) != b(1)) {
//    return b(0)
//  }
  val k = p.divide(bi(4))
  val r = efektywnePotegowanie(p, k+bi(1), b)
  return odwrotnoscWGrupie(r, p)
}

fun bi(x: Int): BigInteger = BigInteger.valueOf(x.toLong())
fun bl(x: Long): BigInteger = BigInteger.valueOf(x)

fun rEuklides(x: BigInteger, n: BigInteger): List<BigInteger> {
  val result = mutableListOf<BigInteger>()
  var a: BigInteger
  var b: BigInteger
  var u: BigInteger
  var v: BigInteger
  a = n
  b = x
  u = bi(0)
  v = bi(1)
  do {
    val q = a / b
    var temp1 = b
    var temp2 = a + -q * b
    a = temp1
    b = temp2
    temp1 = v
    temp2 = u + -q * v
    u = temp1
    v = temp2
  } while (b != bi(0))
  val d = a
  val x = u
  v = (d - x * u) / n
  result.add(0, u)
  result.add(1, v)
  result.add(2, d)
  return result

}

private fun odwrotnoscWGrupie(n: BigInteger, b: BigInteger): BigInteger {
  var value = bi(0)
  var found = false
  for (i in 1..n.toInt()) {
    val p = b.multiply(bi(i))
    value = p.mod(n)
    if (value == bi(1)) {
      if (rEuklides(n, value)[2] == bi(1)) {
        found = true
        value = bi(i)
        break
      }
    }
  }
  return if (found) {
    value
  } else {
    bi(0)
  }
}

fun efektywnePotegowanie(n: BigInteger, k: BigInteger, b: BigInteger): BigInteger {
  var k = k
  var b = b
  var temp = BigInteger("1")
  while (k.compareTo(bi(0)) > 0) {
    if (k.mod(bi(2)).compareTo(bi(0)) != 0) {
      temp = temp.multiply(b).mod(n)
    }
    b = b.multiply(b).mod(n)
    k = k.divide(bi(2))
  }
  return temp.mod(n)
}

fun liczbaPierwsza(n: BigInteger): Boolean {
  if (n.compareTo(bi(1)) == 0) {
    return false
  }
  if (n.compareTo(bi(2)) == 0 || n.compareTo(bi(3)) == 0) {
    return true
  }
  var counter = generator(n.divide(bi(2)), n)
  while (counter != bi(0)) {
    val b = generator(bi(2), n.subtract(bi(2)))
    if (efektywnePotegowanie(n, n.subtract(bi(1)), b) != bi(1)) {
      println("false")
      return false
    }
    counter = counter.subtract(bi(1))
  }
  return true
}

fun generator(start: BigInteger, end: BigInteger): BigInteger {
  val margin = end - start
  return (BigInteger(margin.bitLength(), java.util.Random()) % margin) + start
}

fun primality(num: BigInteger): Boolean{
  var flag = false
  for (i in 2..num.toInt() / 2) {
    // condition for nonprime number
    if (num.mod(bi(i)).equals(0)) {
      flag = true
      break
    }
  }
  return !flag
}

fun liczbaKwadratowa(p: BigInteger, b: BigInteger): BigInteger {
  if (p < bi(2)) {
    throw ArithmeticException()
  }

  return efektywnePotegowanie(p, (p - bi(1)) / bi(2), b)
}
fun result():List<BigInteger>{
  val result = mutableListOf<BigInteger>()
  result.add(BigInteger("59980568326"))
  result.add(BigInteger("67335054959"))
  return result
}
fun punktR(): List<BigInteger>{
  val result = mutableListOf<BigInteger>()
  result.add(BigInteger("10"))
  result.add(BigInteger("1"))
  return result
}

