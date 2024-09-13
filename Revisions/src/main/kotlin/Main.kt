package org.example

fun main() {
//    _01_printHelloWorld()

//    _2_testValAndVar()

//    _3_testBasicTypes()

//    _4_testRanges()

//    _5_testIfsAndWhens()

//    _6_testCollections()

//    _7_testLoops()

//    _8_testFunctions()

//    _9_testLambdas()

//    _10_testClasses()

    _11_testHirerarchy()
}

fun _11_testHirerarchy() {
    // Base class
    open class Animal(val name: String) {
        open fun sound(): String {
            return "Unknown sound"
        }
    }

    // Derived classes
    class Dog(name: String) : Animal(name) {
        override fun sound(): String {
            return "Bark"
        }
    }

    class Cat(name: String) : Animal(name) {
        override fun sound(): String {
            return "Meow"
        }
    }

    class Cow(name: String) : Animal(name) {
        override fun sound(): String {
            return "Moo"
        }
    }

    val cow = Cow("Bessie")
    val anyCow :Any = cow
    println(cow.name)
    println(cow.sound())
    val cowAnimal = cow is Animal
//    val cowCat = cow is Cat   // Error: Inconvertible types and Compiler already knows its a cow
    val cowCat = anyCow is Cat
    val cowCow = cow is Cow
    println("cowAnimal=$cowAnimal, cowCat=$cowCat, cowCow=$cowCow")
}

fun _10_testClasses() {
    class Thing

    val oneThing = Thing()
    println(oneThing)      // --> Thing@5b480cf9

    val otherThing = Thing()
    println(otherThing)      // --> Thing@6f496d9f
    println(oneThing == otherThing) // --> false

    //Properties in classes
    class Circle(val radius: Double = 0.0) {
        val pi = 3.14159         // Should this value belong to each object?
        val area = pi * radius * radius
    }

    val c1 = Circle(5.0)
    println("radius=${c1.radius}, area=${c1.area}") // --> radius=5.0, area=78.53975

    //Moving circle with mutable state
    //Check declaration First
    val c2 = MovingCircle(5.0)
    println("radius=${c2.radius}, area=${c2.area}") // --> radius=5.0, area=78.53975
    println("center=(${c2.center.x},${c2.center.y})") // --> center=(0.0,0.0)
    c2.center = Point(10.5, 20.0)
    println("center=(${c2.center.x},${c2.center.y})") // --> center=(10.5,20.0)
}

class Point(val x: Double, val y: Double)
val origin = Point(0.0, 0.0)
const val PI = 3.14159
class MovingCircle(val radius: Double){
    var center: Point = origin        // Mutable property
    val area = PI * radius * radius   // Computed in object creation and stored
}
fun _9_testLambdas() {
    fun upperOf(c: Char): Char = if (c in 'a'..'z') c - ('a'-'A') else c
    fun lowerOf(c: Char): Char = if (c in 'A'..'Z') c + ('a'-'A') else c

    var fx = ::upperOf  // Variable that stores a reference to one function.
    println(fx('h')) // --> H
    fx = ::lowerOf
    println(fx('M')) // --> m

    //function type
    var fx2: (Char)->Char = ::upperOf

    //Functions as parameters
    fun printlnIn(text: String, charMapper: (Char)->Char) {
        for (c in text)
            print(charMapper(c))
        println()
    }

    printlnIn("Hello, World!", ::upperOf) // --> HELLO, WORLD!
    printlnIn("Hello, World!", ::lowerOf) // --> hello, world!

    //Functions that return functions
    fun charMapper(upper: Boolean): (Char)->Char =
        if (upper) ::upperOf
        else ::lowerOf

    printlnIn("Hello, World!", charMapper(upper = true)) // --> HELLO, WORLD!

    //Lambda expressions
    printlnIn(
        "Hello, World!",
        { c -> if (c in 'A'..'Z') c + ('a'-'A') else c }
    ) // --> hello, world!

    //Trailling function
    printlnIn("Hello, World trailing function!") {
        if (it in 'a'..'z') it - ('a'-'A') else it
    } // --> HELLO, WORLD!

    //collection.filter
    val grades = listOf(7, 13, 5, 18, 3, 14, 11, 15)
    val passing = grades.filter { it >= 10 }
    println(passing) // --> [13, 18, 14, 11, 15]

    //Possible implementation of filter calling the function argument
    fun List<Int>.filter(selector: (Int)->Boolean): List<Int> {
        val result = mutableListOf<Int>()
        for (item in this)
            if (selector(item)) result.add(item)
        return result  // Implicit conversion to List<Int>
    }
}

fun _8_testFunctions() {
    //Calling functions that return Unit
    hello1()    // --> Hello, World!
    hello2()    // --> Hello, World!
    hello3()    // --> Hello, World!

    //Expression functions
    println(max1(1,2))
    println(max2(1,2))
    println(max3(1,2))

    //Extension functions
    println(hasInString("Hello, World!",'o')) // --> true
    println("Hello, World!".has('l')) // --> true

    //Better implementation
    println(hasInString2("Hello, World!",'o')) // --> true
    println("Hello, World!".has2('l')) // --> true
}


fun hasInString(text: String, symbol: Char): Boolean {
    for (c in text)
        if (c == symbol) return true
    return false
}

fun String.has(symbol: Char): Boolean {
    for (c in this)   // this refers to the string itself.
        if (c == symbol) return true
    return false
}

fun hasInString2(text: String, symbol: Char): Boolean  = text.contains(symbol)
fun String.has2(symbol: Char): Boolean = this.contains(symbol)


private fun _8_testFunctions_readName() {
    val person = "Maria"
    val age = readAge(person, 18)  // Call the function and save the value.
    println("$person are $age years old.")

    //named arguments
//    val age = readAge(18, person)                // Error: Arguments in wrong order.
    val age1 = readAge(person, default = 18)       // First argument by order.
    println("$person are $age1 years old.")
    val age2 = readAge(default = 18, name = person) // All arguments by name.
    println("$person are $age2 years old.")

    //Calling after setting default function parameter defaults
    val age3 = readAge(person)        // First by order and second omitted.
    println("$person are $age3 years old.")
    val age4 = readAge(name = person) // First by name and second omitted.
    println("$person are $age4 years old.")
}

fun max1(a: Int, b: Int): Int {
    return if (a >= b) a else b
}
fun max2(a: Int, b: Int): Int =
    if (a >= b) a else b
fun max3(a: Int, b: Int) = if (a >= b) a else b

fun hello1(name: String = ""):Unit { //Unit is not needed
    if (name == "") {
        println("Hello, World!")
        return
    }
    println("Hello, $name!")
}

fun hello2(name: String = "") {
    val who = if (name == "") "World" else name
    println("Hello, $who!")
}

fun hello3(name: String = "World") {
    println("Hello, $name!")
}


//fun readAge(name: String, default: Int): Int {
fun readAge(name: String, default: Int = 18): Int {
    print("How old is $name? ")      // Displays the question with the name.
    val input = readln().trim()      // Reads the input ignoring the spaces.
    if (input.length==0 || input[0] !in '0'..'9') // If no input or invalid.
        return default               // Returns the default value.
    return input.toInt()             // Converts the input to Int and returns it.
}

fun _7_testLoops() {
    //for
    for(i in 0 ..< 5) print("$i ")            // --> 0 1 2 3 4
    println()

    for(n in listOf("Pedro","Ana","Maria")) print("$n ") // --> Pedro Ana Maria
    println()

    for(letter in "Kotlin") print("$letter ")  // --> K o t l i n
    println()

    //while
    var remaining = 3
    while(remaining > 0) {
        print("Falta $remaining ") // --> Falta 3 Falta 2 Falta 1
        remaining--   // Same as: remaining = remaining - 1 or remaining -= 1
    }
    while (remaining > 0)
        println("Acabou.") // Nunca será executado

    println()

    //do while
    var count = 0
    do {
        count++
        print("$count ")
    } while (count <= 3) // --> 1 2 3 4
    println()

    do println("Ok") while (count < 1) // --> Ok
    println()
}

fun _6_testCollections() {
    //-----------------List-----------------
    val names: List<String> = listOf("Pedro", "Ana", "João", "Maria", "Ana")
    println(names)                 // --> [Pedro, Ana, João, Maria, Ana]
    //[0] and [size-1]
    println("First name: ${names[0]}")            // --> First name: Pedro
    println("Last name: ${names[names.size-1]}")  // --> Last name: Ana
    // first and last
    println("First name: ${names.first()}")  // --> First name: Pedro
    println("Last name: ${names.last()}")    // --> Last name: Ana
    //in
    println("Maria" in names)    // --> true

    //Immutable List operations
    var numbers = listOf(5, 1, 3, 4, 3, 6, 7)
    numbers = numbers + listOf(2, 1) - 4  // A new List<Int> is created
    println(numbers)

    //Mutable List operations
    val mutableNames: MutableList<String> = mutableListOf("Ana", "Pedro", "Maria")
    mutableNames.add("Ana")        // Append at the end
    mutableNames.remove("Pedro")   // Remove the first found
    mutableNames[1] = "Isabel"     // Replace the element at index 1
    println(mutableNames)          // --> [Ana, Isabel, Ana]
    val readOnlyNames: List<String> = mutableNames
}

fun _5_testIfsAndWhens() {
    println("if")
    val value: Int = (-10..10).random()
    if (value >= 0)                    // if to control the flow of the program
        println("$value is positive.")
    else
        println("$value is negative.")

    //If as an expression
    val value2: Int = (-10..10).random()
    val text = if (value2 >= 0) "positive" else "negative" // if as an expression
    println("$value2 is $text.")

    //When
    println("When")
    val value3: Int = (-20..20).random()
    when {
        value3 > 0 -> println("$value3 is positive.")
        value3 < 0 -> println("$value3 is negative.")
        else -> println("$value3 is zero.")
    }


    when {
        value3 !in -10..10 -> {
            println("Error.")
            println("$value3 is invalid.")
        }
        value3 > 0 -> println("$value3 is positive.")
        value3 < 0 -> println("$value3 is negative.")
    }
    //When with an expression
    when (val value5 = (-10..10).random()) {
        0 -> println("The value is zero.")
        2, 4, 6, 8, 10 -> println("$value5 is even.")
        in 1..10 -> println("$value5 is positive.")
        else -> println("$value5 is negative.")
    }

    //When as an expression
    val value5 = (-10..10).random()
    val result = when {
        value5 > 0 -> "positive"
        value5 < 0 -> "negative"
        else -> "zero"
    }
    println("$value5 is $result.")
}

fun _4_testRanges() {
    //Ranges
    val max = 5
    val r1 = 1..max             // Inclusive range, Type IntRange is inferred
    println("range1 = $r1")     // --> range1 = 1..5
    val r2 =  1..<(max+1)       // Exclusive range
    println("$r1 == $r2 = ${r1==r2}")  // --> 1..5 == 1..5 = true

    //range operations
    val letters = 'A'..'Z'      // Range of chars, Type CharRange is inferred
    println('H' in letters)     // --> true, same as println('H'>='A' && 'H'<='Z')
    val total = letters.count()
    println("${letters.first}..${letters.last} has $total") // --> A..Z has 26

    // step
    val evens = 2..10 step 2     // Progression, Type IntProgression is inferred
    println(evens.count())       // --> 5
    println(3 in evens)          // --> false
    val odds = 9 downTo 0 step 2 // Decreasing progression
    println(odds.last)           // --> 1

    //Show downTo infix function

    //String ranges support "in" but not "count"
    val prices = 3.5..6.75     // Double range
    println(4.5 in prices)     // --> true
    val names = "ana".."pedro" // String range
    println("joão" in names)   // --> true
    println("rui" in names)    // --> false


    //Random numbers in range
    val dice = (1..6).random() // Random number in the range
    println("Dice = $dice")    // --> Dice = ...


}

fun _3_testBasicTypes() {
    _31_testNumericTypes()

    _32_testTextTypes()
}

fun _32_testTextTypes() {

    val name = "Kotlin"
    println("Hello, $name!")

    fun getName(name: String): String {
        return name
    }
    println("Hello, ${getName(name)}!")

    val letter: Char = 'A'         // Single character, : Char can be omittedomited
    val tab = '\t'                 // Tab character
    val euro = '\u20AC'            // Unicode for symbol €
    println("|$tab|$letter|$euro") // --> |   |A|€


    val cA: Int = 'A'.code          // Unicode code of 'A'
    val cB = 'B'.toInt()            // Unicode code of 'B', Char.toInt() deprecated
    println("A code = $cA, B code = $cB")   // --> A code = 65, B code = 66

    val d: Char = 'A' + 3             // Char + Int = Char
    val y = 'Z' - 1                   // Char - Int = Char
    val dif: Int = 'Y' - 'D'          // Char - Char = Int
    println("'$y'-'$d' = $dif")       // --> 'Y'-'D' = 21

    val ln = '\n'               // Newline character
    val str = "Euro-\u20AC.$ln" // String terminated by a newline
    print(str)                  // --> Euro-€.    (like a println)

    val raw =
        """
A-\u20AC //not a comment
-B \n \t

--Useful for SQL, regex, HTML, XML, JSON, etc.
select * 
from my_table
where column = 'value'
"""
    println(raw)
//Indexação nas Strings
    val s = "Kotlin"
    val first = s[0]                        // Char at first index
    val last = s[s.length-1]                // Char at last index
    println("$s: first=$first, last=$last") // --> Kotlin: first=K, last=n

    //String concatenation
    val final = '!'
    val txt1 = s + " é fixe" + final  // Concatenation: "Kotlin é fixe!"
    println(txt1)
    val txt2 = "$s é fixe$final"      // String template: "Kotlin é fixe!"
    println(txt2)

    //String to numeric and vice versa
    println(126.toString() + " = 0x"+126.toString(16))    // --> 126 = 0x7e
    println("011".toInt().toString() + " + " +  "011".toByte(2).toString())              // --> 14
    println("011".toInt() + "011".toByte(2))              // --> 14
    println("3.4e4".toDouble() + 1)                       // --> 34001.0

    //Logical Values
    val ok: Boolean = true              // Explicit type can be omitted
    val notOk = false
    println("ok = $ok, notOk = $notOk") // --> ok = true, notOk = false

    val logic: Boolean = "TRUE".toBoolean()     // Convert a String to a Boolean
    val text = logic.toString()                 // Convert a Boolean to a String
    println("logic = $text")

    // Logical Evaluation
    val max = 36
    val b1: Boolean = 5 < max // b1 = true
    val res = firstBooleanExpression() || b1 || 30 >= max || lastBooleanExpression()// res = true, The right part of || is not evaluated
    println("b1 = $b1, res = $res")


}


fun firstBooleanExpression(): Boolean {
    println("firstBooleanExpression")
    return false
}

fun lastBooleanExpression(): Boolean {
    println("lastBooleanExpression")
    return true
}

@OptIn(ExperimentalStdlibApi::class)
fun _31_testNumericTypes() {

    val a: Int = 1
    val b: Long = 1234234
    val b2 = 12342L
    val c: Float = 1.0f
    val d: Double = 1.0
    val e: Char = 'a'
    val f: String = "a"
    val g: Boolean = true

    println("a: $a, b: $b, b2: $b2, c: $c, d: $d, e: $e, f: $f, g: $g")

    val h: Int? = null
    val i: Long? = null
    val j: Float? = null
    val k: Double? = null
    val l: Char? = null
    val m: String? = null
    val n: Boolean? = null

    println("h: $h, i: $i, j: $j, k: $k, l: $l, m: $m, n: $n")

    val o: Int? = 1
    val p: Long? = 1L
    val q: Float? = 1.0f
    val r: Double? = 1.0
    val s: Char? = 'a'
    val t: String? = "a"
    val u: Boolean? = true

    println("o: $o, p: $p, q: $q, r: $r, s: $s, t: $t, u: $u")

    //Hexadecimal format
    val x: Int               // Declaration without initialization
    x = 0x0F                 // Initialize with hexadecimal value 3F unsigned
    println("x Hex in Dec = $x, in hex: ${x.toHexString()}")

    val y: Byte               // Declaration without initialization
    y = 0x0F                 // Initialize with hexadecimal value 3F unsigned
    println("y Hex in Dec = $y, in hex: ${y.toHexString()}, , in Long hex: ${y.toLong().toHexString()}")

    //Real values
    val realValue = 2.3e4        // Inferred as Double
    println("realValue = $realValue")
}

fun _2_testValAndVar() {
    val a = 1
//    a= 2 not possibel
    var b = 2
    b=3
    println("a: $a, b: $b")

    class MyComplexA {
        val a = 1
        var b = 2
    }
    val myComplexA = MyComplexA()
    myComplexA.b = 3
    println("myComplexA.a: ${myComplexA.a}, myComplexA.b: ${myComplexA.b}")
}


private fun _01_printHelloWorld() {
    println("Hello World!")
}