package isel.tds

import org.example.isel.tds.Date
import org.example.isel.tds.isLeapYear
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
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
        assertFailsWith<IllegalArgumentException> { Date(2020, 0) }
    }
}