package nl.mvdr.adventofcode.adventofcode2015.day11

/**
 * Finds Santa's next password, given his [currentPassword].
 */
fun findNextPassword(currentPassword: String): String {
    var result = increment(currentPassword)
    while (!isValidPassword(result)) {
        result = increment(result)
    }
    return result
}

/**
 * Increments the given [password].
 *
 * Incrementing is just like counting with numbers: xx, xy, xz, ya, yb, and so on.
 *
 * Increase the rightmost letter one step; if it was z, it wraps around to a,
 * and repeat with the next letter to the left until one doesn't wrap around.
 */
fun increment(password: String): String = when(password.last()) {
    'z' -> increment(password.dropLast(1)) + "a" // wrap around
    else -> password.dropLast(1) + (password.last() + 1)
}

/**
 * Checks whether the given [password] meets all password requirements.
 */
fun isValidPassword(password: String) = meetsFirstRequirement(password)
        && meetsSecondRequirement(password)
        && meetsThirdRequirement(password)

/**
 * Checks whether the given [password] meets the following requirement.
 *
 * Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz.
 * They cannot skip letters; abd doesn't count.
 */
fun meetsFirstRequirement(password: String): Boolean = (0 until password.length - 2)
    .any { password[it] + 1 == password[it + 1] && password[it + 1] + 1 == password[it + 2] }

/**
 * Checks whether the given [password] meets the following requirement.
 *
 * Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
 */
fun meetsSecondRequirement(password: String): Boolean = !password.contains("i")
        && !password.contains("o")
        && !password.contains("l")

/**
 * Checks whether the given [password] meets the following requirement.
 *
 * Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
 */
fun meetsThirdRequirement(password: String): Boolean {
    val pairs = (0 until password.length - 1)
        .count { password[it] == password[it + 1] && (it == 0 || password[it - 1] != password[it]) }
    return 2 <= pairs
}