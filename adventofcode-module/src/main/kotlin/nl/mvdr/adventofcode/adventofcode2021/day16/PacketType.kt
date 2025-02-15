package nl.mvdr.adventofcode.adventofcode2021.day16

enum class PacketType(val id: Int) {
    LITERAL_VALUE(4),
    SUM(0),
    PRODUCT(1),
    MINIMUM(2),
    MAXIMUM(3),
    GREATER_THAN(5),
    LESS_THAN(6),
    EQUAL_TO(7)
}