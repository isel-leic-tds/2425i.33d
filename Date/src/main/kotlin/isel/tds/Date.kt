package org.example.isel.tds

//class Date(val year: Int, val month:Int, val day:Int) {
//
//    constructor(y:Int, m:Int): this(y, m, 1)
//    constructor(y:Int): this(y, 1)
//}

//class Date(val year: Int, val month:Int=1, val day:Int=1) {
//    init{
//        require(year in 1582..2200) { "Invalid year=$year" }
//        require(month in 1..12) { "Invalid month=$month" }
//        require(day in 1..lastDayOfMonth) { "Invalid day=$day" }
//    }
//
////    val isLeapYear: Boolean = year%4 == 0 && year%100 != 0 || year%400 == 0
////    val isLeapYear: Boolean
////        get() = year%4 == 0 && year%100 != 0 || year%400 == 0
//
//    override operator fun equals(other: Any?) =
//        other is Date
//                && year == other.year && month == other.month && day == other.day
//
//    override fun hashCode() = (year * 12 + month) * 31 + day
//
//    override fun toString() = "$year-%02d-%02d".format(month, day)
//
//    val lastDayOfMonth: Int
//        get() =
//            if (month==2 && year.isLeapYear()) 29
//            else daysOfMonth[month-1]
//}

fun Date.isLeapYear() = year.isLeapYear()
fun Int.isLeapYear() = this%4 == 0 && this%100 != 0 || this%400 == 0


private val daysOfMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

val Date.lastDayOfMonth: Int
    get() =
        if (month==2 && year.isLeapYear()) 29
        else daysOfMonth[month-1]

operator fun Date.plus(daysToAdd: Int): Date = this.addDays(daysToAdd)
operator fun Int.plus(date: Date): Date = date.addDays(this)

private fun Date.addDays(days: Int): Date = when {
//    day == 20 -> x() //Force an StackOverflowError
    days + day <= lastDayOfMonth -> Date(year, month, day + days)
    month < 12 -> Date(year, month + 1, 1).addDays(days - (lastDayOfMonth - day +1))
    else -> Date(year + 1, 1, 1).addDays(days - ((lastDayOfMonth - day +1)))
}
tailrec private fun Date.addDaysTailRec(days: Int): Date = when {
    days + day <= lastDayOfMonth -> Date(year, month, day + days)
    month < 12 -> Date(year, month + 1, 1).addDaysTailRec(days - (lastDayOfMonth - day +1))
    else -> Date(year + 1, 1, 1).addDaysTailRec(days - ((lastDayOfMonth - day +1)))
}

//fun x(): Date = x() //Force an StackOverflowError

operator fun Date.compareTo(other: Date): Int = when {
    year != other.year    -> year - other.year
    month != other.month  -> month - other.month
    else                  -> day - other.day
}

private const val DAY_BITS = 5   // 0..31
private const val MONTH_BITS = 4 // 0..15
private const val YEAR_BITS = 12 // 0..4095
//private const val DAY_MASK = 0x1F
// We could implement using masks

//class Date (year: Int, month:Int = 1, day:Int = 1){
//    private val bits: Int =
//        (year shl (DAY_BITS + MONTH_BITS)) or (month shl DAY_BITS) or day
//
//    init {
//        require(year in 1582..2200) { "Invalid year=$year" }
//        require(month in 1..12) { "Invalid month=$month" }
//        require(day in 1..lastDayOfMonth) { "Invalid day=$day" }
//    }
//    val year: Int get() = bits shr (DAY_BITS + MONTH_BITS)
//    val month: Int get() = (bits shr DAY_BITS) and ((1 shl MONTH_BITS) - 1)
//    val day: Int get() = bits and ((1 shl DAY_BITS) - 1)
//
//    override operator fun equals(other: Any?) =
//        other is Date
//                && year == other.year && month == other.month && day == other.day
//
//    override fun hashCode() = (year * 12 + month) * 31 + day
//
//    override fun toString() = "$year-%02d-%02d".format(month, day)
//}

@JvmInline
value class Date private constructor(private val bits: Int){

    val year: Int get() = bits shr (DAY_BITS + MONTH_BITS)
    val month: Int get() = (bits shr DAY_BITS) and ((1 shl MONTH_BITS) - 1)
    val day: Int get() = bits and ((1 shl DAY_BITS) - 1)

    constructor(year: Int, month:Int = 1, day:Int = 1): this(
        (year shl (DAY_BITS + MONTH_BITS)) or (month shl DAY_BITS) or day
    ){
        require(year in 1582..2200) { "Invalid year=$year" }
        require(month in 1..12) { "Invalid month=$month" }
        require(day in 1..lastDayOfMonth) { "Invalid day=$day" }
    }

    override fun toString() = "$year-%02d-%02d".format(month, day)
}

