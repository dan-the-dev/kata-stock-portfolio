package acceptance

import Portfolio
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class PortfolioTest() {

    @Test
    fun testShallPass(): Unit
    {
        assertEquals(1, 1)
    }

    @Test
    fun testItShouldPrintThePortfolio(): Unit
    {
        // given
        val portfolio = Portfolio()
        portfolio.buy("Old School Waterfall Software LTD", 1000, LocalDate.of(1990, 2, 14))
        portfolio.buy("Crafter Masters Limited", 400, LocalDate.of(2016, 6, 9))
        portfolio.buy("XP Practitioners Incorporated", 700, LocalDate.of(2018, 12, 10))
        portfolio.sell("Old School Waterfall Software LTD", 500, LocalDate.of(2018, 12, 11))

        // when
        val actual = portfolio.print()

        // then
        assertEquals("""
            company | shares | current price | current value | last operation
            Old School Waterfall Software LTD | 500 | $5.75 | $2,875.00 | sold 500 on 11/12/2018
            Crafter Masters Limited | 400 | $17.25 | $6,900.00 | bought 400 on 09/06/2016
            XP Practitioners Incorporated | 700 | $25.55 | $17,885.00 | bought 700 on 10/12/2018
        """.trimIndent(), actual
        )
    }

}