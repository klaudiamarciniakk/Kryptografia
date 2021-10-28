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

//        val p = b(7)
//        val b =b(2)
//        val result = 4
//        println("expected: " + result + " provided: " + pierwiastekKwadratowyPhi(p, b))
}

private fun pierwiastekKwadratowyPhi(p: BigInteger, b: BigInteger): BigInteger {
  val rk = p.divide(b(2))
  if (efektywnePotegowanie(p, rk, b) != b(1)) {
    return b(0)
  }
  val k = p.divide(b(4))
  val r = efektywnePotegowanie(p, k, b)
  return odwrotnoscWGrupie(r.toInt(), p)
}

fun b(x: Int): BigInteger = BigInteger.valueOf(x.toLong())

private fun rEuklides(x: Int, n: Int): IntArray {
  var a: Int
  var b: Int
  var u: Int
  var v: Int
  a = n
  b = x
  u = 0
  v = 1
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
  } while (b != 0)
  val d = a
  val x = u
  v = (d - x * u) / n
  return intArrayOf(u, v, d)

}

private fun odwrotnoscWGrupie(n: Int, b: BigInteger): BigInteger {
  var value = 0
  var found = false
  for (i in 1..n) {
    val p = b.multiply(b(i))
    value = p.mod(b(n)).toInt()
    if (value == 1) {
      if (rEuklides(n, value)[2] == 1) {
        found = true
        value = i
        break
      }
    }
  }
  return if (found) {
    b(value)
  } else {
    b(0)
  }
}

private fun efektywnePotegowanie(n: BigInteger, k: BigInteger, b: BigInteger): BigInteger {
  var k = k
  var b = b
  var temp = BigInteger("1")
  while (k.compareTo(b(0)) > 0) {
    if (k.mod(b(2)).compareTo(b(0)) != 0) {
      temp = temp.multiply(b).mod(n)
    }
    b = b.multiply(b).mod(n)
    k = k.divide(b(2))
  }
  return temp.mod(n)
}

private fun liczbaPierwsza(n: BigInteger): Boolean {
  if (n.compareTo(b(1)) == 0) {
    return false
  }
  if (n.compareTo(b(2)) == 0 || n.compareTo(b(3)) == 0) {
    return true
  }
  var counter = generator(n.divide(b(2)), n)
  while (counter != b(0)) {
    val b = generator(b(2), n.subtract(b(2)))
    if (efektywnePotegowanie(n, n.subtract(b(1)), b) != b(1)) {
      println("false")
      return false
    }
    counter = counter.subtract(b(1))
  }
  return true
}

private fun generator(start: BigInteger, end: BigInteger): BigInteger {
  val margin = end - start
  return (BigInteger(margin.bitLength(), java.util.Random()) % margin) + start
}

fun liczbaKwadratowa(p: BigInteger, b: BigInteger): BigInteger {
  if (p < b(2)) {
    throw ArithmeticException()
  }

  return efektywnePotegowanie(p, (p - b(1)) / b(2), b)
}

