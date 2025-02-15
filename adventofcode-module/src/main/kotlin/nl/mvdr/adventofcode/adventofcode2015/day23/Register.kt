package nl.mvdr.adventofcode.adventofcode2015.day23

enum class Register(val stringRepresentation: String) {
    A("a"),
    B("b")
}

fun parseRegister(text: String) = Register.entries.first { it.stringRepresentation == text }