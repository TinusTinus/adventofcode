package nl.mvdr.adventofcode.adventofcode2025.day02

fun solve(lines: Sequence<String>, isInvalid: (Long) -> Boolean) =
    lines.first()
        .split(",")
        .asSequence()
        .map { it.split("-") }
        .map { it.first().toLong() .. it.last().toLong() }
        .flatMap { it.asSequence() }
        .filter(isInvalid)
        .sum()
