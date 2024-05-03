package nl.mvdr.adventofcode.adventofcode2021.day24

data class Monad(private val program: Program) {

    constructor(lines: Sequence<String>) : this(Program(lines))

    fun findMaxModelNumber(): Long {
        var modelNumber = 99999999999999L
        while (!isValid(modelNumber)) {
            modelNumber--
        }
        return modelNumber
    }

    private fun isValid(modelNumber: Long): Boolean {
        val input = toDigits(modelNumber)
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
}

private fun toDigits(serialNumber: Long) = serialNumber.toString().map { it.toString().toInt() }
