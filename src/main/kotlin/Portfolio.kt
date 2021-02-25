import java.time.LocalDate
import java.util.function.BiFunction

class Portfolio {
    private val currentCompanies: MutableMap<String, Int> = mutableMapOf()

    fun print(): String
    {
        val companiesStrings = currentCompanies.map {
            val name = it.key
            val amount = it.value
            val company = CompaniesRepository().findByName(name)
            if (company != null) {
                val total = amount * company.price
                return "${company.name} | $amount | $${company.price} | $$total"
            }
        }
        return companiesStrings.toString()
    }

    fun buy(company: String, amount: Int, date: LocalDate?) {
        currentCompanies.merge(company, amount, BiFunction { prev, one -> prev + one })
    }

    fun sell(company: String, amount: Int, date: LocalDate?) {
        currentCompanies.merge(company, -amount, BiFunction { prev, one -> prev + one })
    }
}

class CompaniesRepository {
    private val list: List<Company> = listOf(
        Company("Old School Waterfall Software LTD", 5.75),
        Company("Crafter Masters Limited", 17.25),
        Company("XP Practitioners Incorporated", 25.55)
    )

    fun findByName(name: String): Company? {
        return try {
            val found = list.filter { it.name === name }
            found.first()
        } catch (e: NoSuchElementException) {
            null
        }
    }
}

data class Company(val name: String, val price: Double)
