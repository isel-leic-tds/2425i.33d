package isel.tds

import org.example.isel.tds.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep
import kotlin.test.assertFailsWith

class DateTest{
    @Test
    fun testDateCreation(){
        val sut = Date(2024, 9, 18)

        assertEquals(2024, sut.year, "Error in year")
        assertEquals(9, sut.month)
        assertEquals( 18, sut.day)
    }

    @Test
    fun `test Date Creation With Partial Year and Month Values`(){
        val sut = Date(2024, 9)

        assertEquals(2024, sut.year, "Error in year")
        assertEquals(9, sut.month)
        assertEquals(1, sut.day)
    }

    @Test
    fun `test Date Creation With Partial Year Value`(){
        val sut = Date(2024)

        assertEquals(2024, sut.year, "Error in year")
        assertEquals(1, sut.month)
        assertEquals(1, sut.day)
    }

    @Test
    fun `test Date Creation With Partial Year and Day Value`(){
        val sut = Date(2024, day=18)

        assertEquals(2024, sut.year, "Error in year")
        assertEquals(1, sut.month)
        assertEquals(18, sut.day)
    }

    @Test
    fun `test leapYear`(){
        val sut = Date(2024)
//        assertTrue(sut.isLeapYear)
        assertTrue(sut.isLeapYear())
    }
    @Test
    fun `test isn't leapYear`(){
        val sut = Date(2023)
//        assertTrue(sut.isLeapYear)
        assertFalse(sut.isLeapYear())
    }

    @Test
    fun `test last day of month`(){
        val sut = Date(2023, 12)
//        assertTrue(sut.isLeapYear)
        assertEquals(31, sut.lastDayOfMonth)
    }

    @Test
    fun `test last day of february month`(){
        val sut = Date(2023, 2)
        assertEquals(28, sut.lastDayOfMonth)

        val sutLeapYear = Date(2024, 2)
        assertEquals(29, sutLeapYear.lastDayOfMonth)

    }

    @Test
    fun `test invalid year`(){
        assertFailsWith<IllegalArgumentException> { Date(1) }
    }
    @Test
    fun `test invalid month`(){
        assertFailsWith<IllegalArgumentException> { Date(2020, 0) }
    }
    @Test
    fun `test invalid day`(){
        assertFailsWith<IllegalArgumentException> { Date(2023, 2, 29) }
        assertFailsWith<IllegalArgumentException> { Date(2020, 1, 32) }
        assertFailsWith<IllegalArgumentException> { Date(2020, 12, -1) }
        assertFailsWith<IllegalArgumentException> { Date(2020, 12, 0) }
    }

    @Test
    fun addDaysToDateTest(){
        val sut = Date(2024, 9, 20) + 5
        assertEquals(2024, sut.year)
        assertEquals(9, sut.month)
        assertEquals(25, sut.day)
    }
    @Test
    fun addDateToDaysTest(){
        val sut = 5 + Date(2024, 9, 20)
        assertEquals(2024, sut.year)
        assertEquals(9, sut.month)
        assertEquals(25, sut.day)
    }

    @Test
    fun addDaysToDatePassingTheMonthTest(){
        val sut = Date(2024, 9, 20) + 15
        assertEquals(2024, sut.year)
        assertEquals(10, sut.month)
        assertEquals(5, sut.day)
    }

    @Test
    fun addDaysToDatePassingTheYearTest(){
        val sut = Date(2024, 9, 20) + 105
        assertEquals(2025, sut.year)
        assertEquals(1, sut.month)
        assertEquals(3, sut.day)
    }

    @Test fun testEqualityOfDates(){
        val date1 = Date(2024, 9, 20)
        val date2 = date1//Date(2024, 9, 20)
        assertEquals(date1, date1)
        assertEquals(date1, date2)

        assertTrue( date1 == date2)
//        assertTrue( date1 === date2)
    }

    @Test fun `Tests inequality between dates and dates with another type`() {
        val sut = Date(2023, 3, 2)
        assertNotEquals(sut, Date(2023, 4, 1))
        val any :Int = 2023
        assertNotEquals(sut, any)  // Compare with Int
        val dn :Date? = null
        assertNotEquals(sut, dn)   // Compare with null
        assertNotEquals(dn, sut)   // Compare null with Date
    }

    @Test fun `Coherence between equals and hashCode`() {
        val sut = Date(2023, 3, 2)
        assertEquals(sut.hashCode(), sut.hashCode())
        val d2 = Date(2023, 3, 2)
        assertEquals(sut, d2)
        assertEquals(sut.hashCode(), d2.hashCode()) // Must be the same
        val d3 = Date(2023, 4, 2)
        assertNotEquals(sut, d3)
        assertNotEquals(sut.hashCode(), d3.hashCode()) // Should be different
    }


    @Test fun `Compare dates`() {
        val sut = Date(2023, 3, 2)
        assertTrue(sut < Date(2023, 3, 4))
        assertTrue(Date(2023, 4, 2) >= sut)
        assertTrue(sut <= Date(2024, 3, 2))
        assertTrue(sut > Date(2023, 3, 1))
    }

    @Test fun `String representation of a date`() {
        val sut = Date(2023, 3, 2)
        assertEquals("2023-03-02", sut.toString())
    }

    @Test
    fun testLotOfDates(){
        println("start")
        val dates = (1..1000).map { Date(2000, 1, 1) + it }
        //dates.forEach { println(it) }
        println("sleep")
        sleep(60000)
        println(dates.size)
        println("end")
    }


}

