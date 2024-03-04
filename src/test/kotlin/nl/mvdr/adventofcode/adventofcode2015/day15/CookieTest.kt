package nl.mvdr.adventofcode.adventofcode2015.day15

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CookieTest {
    @Test
    fun testScore() {
        val butterscotch = parseIngredient("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8")
        val cinnamon = parseIngredient("Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3")
        val cookie = Cookie(mapOf(Pair(butterscotch, 44), Pair(cinnamon, 56)))

        assertEquals(68, cookie.score(Property.CAPACITY))
        assertEquals(80, cookie.score(Property.DURABILITY))
        assertEquals(152, cookie.score(Property.FLAVOR))
        assertEquals(76, cookie.score(Property.TEXTURE))
        assertEquals(62842880, cookie.score())
    }
}