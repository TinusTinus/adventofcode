package nl.mvdr.adventofcode.adventofcode2025.day10

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger{}

interface State<S : State<S>> {
    fun update(button: Button): S
    fun getInitialState(): S
    fun canReach(targetState: S): Boolean
}

fun <S: State<S>> computeFewestButtonPressesToReach(targetState: S, buttons: List<Button>): Int {
    var current = setOf(targetState.getInitialState())
    var buttonPresses = 0

    while(!current.contains(targetState)) {
        current = current.flatMap { state -> buttons.map { state.update(it) } }
            .filter { it.canReach(targetState) }
            .toSet()
        buttonPresses++
    }

    logger.debug { "$buttonPresses button presses for target state $targetState" }

    return buttonPresses
}
