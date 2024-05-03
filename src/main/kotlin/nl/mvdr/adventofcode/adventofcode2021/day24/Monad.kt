package nl.mvdr.adventofcode.adventofcode2021.day24

data class Monad(val program: Program) {
    fun isValid(serialNumber: Long): Boolean {
        val input = toDigits(serialNumber)
        return !input.contains(0) && isValid(input)
    }

    /**
     * Checks whether the given [input] represents a valid serial number.
     * Note that the given input must not contain any zero values.
     */
    private fun isValid(input: List<Int>): Boolean {
        val endState = program.execute(input)
        return endState.variables[Variable.Z] == 0
    }

    private fun toDigits(serialNumber: Long) = serialNumber.toString().map { it.toString().toInt() }
}
