import org.junit.jupiter.api.Test

class ExamTests {


    @Test
    fun test1(){
        val b1: A = B()
        println("A interface B instance:"+b1.h())
        val b2: B = B()
        println("B interface B instance:"+b2.h())

//        val myList: List<A> = listOf(B(),A())
//        println(myList)

        println(listOf(B(),A()).map{ it.f() + it.h() } + B().h() + A().f())
    }


}

fun Any.h() = "hAny"

open class A { open fun f() = "fA" }
fun A.h() = "hA"

class B: A() { override fun f() = "fB" }
fun B.h() = "hB"

class C{ fun f() = "fC"}
fun C.h() = "hC"