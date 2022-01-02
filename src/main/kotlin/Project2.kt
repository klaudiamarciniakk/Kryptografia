import java.math.BigInteger

fun main(args: Array<String>) {
  //Zad1
//    val p = bl(68719476731)
//    println("Result: " + randomEllipticCurve(p))

  //Zad2
//
//    val p = BigInteger("618970019642690137449562091")
//    val a = BigInteger("128660563210764262153857048")
//    val b = BigInteger("532143667346609077651146972")
//    println("Result: " + randomPointOnEllipticCurve(p,a,b))

  //Zad3
//    val p = BigInteger("7")
//    val x = BigInteger("3")
//    val y = BigInteger("4")
//    val x1 = BigInteger("3")
//    val y1 = BigInteger("3")
//    println("Result : " + oppositePoint(p, x, y))
//    println("Expected: $x1, $y1")

//    Zad4
//  val p = BigInteger("7")
//  val a = BigInteger("2")
//  val b = BigInteger("4")
//  val x1 = BigInteger("3")
//  val y1 = BigInteger("4")
//  val x2 = BigInteger("0")
//  val y2 = BigInteger("2")
//  val x3 = BigInteger("6")
//  val y3 = BigInteger("1")
//  println("Result: " + sumOfPoints(a, b, p, x1, y1, x2, y2))
//  println("Expected: $x3,  $y3")
//  println("If result was 1111111111111111 that means it was infinite")

  //zad5

//  val p = BigInteger("11")
//  val a = BigInteger("3")
//  val b = BigInteger("5")
//  val x = BigInteger("0")
//  val y = BigInteger("4")
//  val n = BigInteger("4")
//  val xn = BigInteger("10")
//  val yn = BigInteger("1")
//  println("Result: " + wielokrotnoscPunktu(a, b, p, n, x, y))
//  println("Expected: $xn, $yn")
}

fun wielokrotnoscPunktu(
  a: BigInteger,
  b: BigInteger,
  p: BigInteger,
  n: BigInteger,
  x: BigInteger,
  y: BigInteger
): List<BigInteger> {

  var o = n

  var punktQ = listOf(x, y)
  val punktR = mutableListOf<BigInteger>()

  while (o > bi(0)) {
    if (o.mod(bi(2)) == bi(1)) {

      punktR.add(sumOfPoints(a, b, p, bi(0), bi(0), punktQ[0], punktQ[1])[0])
      punktR.add(sumOfPoints(a, b, p, bi(0), bi(0), punktQ[0], punktQ[1])[1])
      o = o.subtract(bi(1))

    }
    punktQ = sumOfPoints(a, b, p, punktQ[0], punktQ[1], punktQ[0], punktQ[1])
    o = o.divide(bi(2))

  }
  return punktR()
}


fun sumOfPoints(
  a: BigInteger,
  b: BigInteger,
  p: BigInteger,
  x1: BigInteger,
  y1: BigInteger,
  x2: BigInteger,
  y2: BigInteger
): List<BigInteger> {
  val result = mutableListOf<BigInteger>()
  if (x1 == bi(0) && y1 == bi(0)) {
    result.add(x2)
    result.add(y2)
  } else if (x2 == bi(0) && y2 == bi(0)) {
    result.add(x1)
    result.add(y1)
  }


  if (x1 != x2) {
    val lambda = ((y2.subtract(y1).mod(p)).multiply(reciprocalPhi(x2.subtract(x1), p))).mod(p)
    val x3 = (betterExponentiation(lambda, bi(2), p).subtract(x1.mod(p)).subtract(x2.mod(p))).mod(p)
    val y3 = (lambda.multiply((x1.subtract(x3))).subtract(y1)).mod(p)
    result.add(0, x3)
    result.add(1, y3)
  }

  if (x1 == x2 && y1 == y2) {
    val lambda = ((bi(3).multiply(
      betterExponentiation(
        x1,
        bi(2),
        p
      )
    ).add(a)).multiply(reciprocalPhi(y1.multiply(bi(2)), p))).mod(p)
    val x3 = (betterExponentiation(lambda, bi(2), p).subtract((x1.multiply(bi(2))).mod(p))).mod(p)
    val y3 = (lambda.multiply(x1.subtract(x3)).subtract(y1)).mod(p)
    result.add(x3)
    result.add(y3)
  }
  if (p < y2) {
    if (x1 == x2 && y1 == (y2.mod(p))) {
      result.add(BigInteger("1111111111111111"))
      result.add(BigInteger("1111111111111111"))
    }
  }

  return result()
}

fun reciprocalPhi(n: BigInteger, p: BigInteger): BigInteger? {
  val u = rEuklides(n, p)[0]
  val v = rEuklides(n, p)[1]

  if (u.multiply(n).mod(p).equals(bi(1))) {
    if (u < bi(0)) {
      return u.add(p)
    }
    return u
  } else {
    if (v < bi(0)) {
      return v.add(p)
    }
    return v
  }

}


fun oppositePoint(p: BigInteger, x: BigInteger, y: BigInteger): List<BigInteger> {
  val result = mutableListOf<BigInteger>()
  val y1 = (-y).mod(p)
  result.add(0, x)
  result.add(1, y1)
  return result
}

fun randomPointOnEllipticCurve(p: BigInteger, a: BigInteger, b: BigInteger): List<BigInteger> {
  val result = mutableListOf<BigInteger>()
  if (delta(a, b, p) != bi(0) && p.mod(bi(4)).equals(bi(3))) {

    val x = generator(bi(0), p.subtract(bi(1)))
    val fx = rownanieKrzywej(a, b, p, x)
    if (remSqEuler(fx, p)) {
      val y = betterExponentiation(fx, p.add(bi(1)).divide(bi(4)), p)
      result.add(0, x)
      result.add(1, y)
    }
  }
  return result
}

fun remSqEuler(fx: BigInteger, p: BigInteger): Boolean {
  val ans = betterExponentiation(fx, p.subtract(bi(1)).divide(bi(2)), p)

  return ans == bi(1) && primality(p)
}

fun rownanieKrzywej(a: BigInteger, b: BigInteger, p: BigInteger, x: BigInteger): BigInteger {

  return betterExponentiation(x, bi(3), p).add(((((a.multiply(x)).mod(p)).add(b.mod(p))).mod(p).mod(p)))
}


fun randomEllipticCurve(p: BigInteger): List<BigInteger> {
  val a: BigInteger
  val b: BigInteger
  val tab = mutableListOf<BigInteger>()
  if (primality(p) && p.mod(bi(4)).equals(bi(3))) {
    a = generator(bi(1), p.subtract(bi(1)))
    b = generator(bi(1), p.subtract(bi(1)))

    if (delta(a, b, p) != bi(0)) {
      tab.add(0, a)
      tab.add(1, b)
    }
  }
  return tab
}

private fun delta(a: BigInteger, b: BigInteger, p: BigInteger): BigInteger {
  return ((bi(4).multiply(betterExponentiation(a, bi(3), p).mod(p))
    .add(bi(27).multiply(betterExponentiation(b, bi(2), p).mod(p))))).mod(p)
}

private fun betterExponentiation(x: BigInteger, k: BigInteger, n: BigInteger): BigInteger {
  if (x.equals(0)) return bi(0)

  var y = bi(1)
  if (x < n && x > bi(0)) {
    val b = k.toString(2).reversed()
    val q = b.chars().filter { ch -> ch == 0 }.count()
    val w = b.chars().filter { ch -> ch == 1 }.count()
    val l = q + w
    var i = l - 1

    while (i >= 0) {
      y = y.pow(2).mod(n)
      if (b[i.toInt()] == '1') {
        y = y.multiply(x).mod(n)
        println(i)
      }
      i -= 1
    }
  }
  return y
}
