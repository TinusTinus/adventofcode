package nl.mvdr.adventofcode.adventofcode2015.day23

import java.util.function.IntPredicate
import java.util.function.IntUnaryOperator

data class State(val registers: Map<Register, Int> = Register.entries.associateWith { 0 }, val instructionPointer: Int = 0) {

    /**
     * Sets [register] to half its current value, then continues with the next instruction.
     */
    fun half(register: Register) = updateRegister(register) { it / 2 }

    /**
     * Sets [register] to triple its current value, then continues with the next instruction.
     */
    fun triple(register: Register) = updateRegister(register) { it * 3 }

    /**
     * Increments [register], adding 1 to it, then continues with the next instruction.
     */
    fun increment(register: Register) = updateRegister(register) { it + 1 }

    /**
     * Jump
     * Continues with the instruction [offset] away relative to itself.
     */
    fun jump(offset: Int) = State(registers, instructionPointer + offset)

    /**
     * Jump if even.
     * Like jmp, but only jumps if [register] is even.
     */
    fun jumpIfEven(register: Register, offset: Int) = conditionalJump(register, offset) { it % 2 == 0}

    /**
     * Jump if one.
     * (Not jump if odd!)
     * Like jmp, but only jumps if register r is 1.
     */
    fun jumpIfOne(register: Register, offset: Int) = conditionalJump(register, offset) { it == 1 }

    /**
     * Updated the value of [register] with the given [operation], then continues with the next instruction.
     */
    private fun updateRegister(register: Register, operation: IntUnaryOperator): State {
        val updatedRegisters = registers.toMutableMap()
        updatedRegisters[register] = operation.applyAsInt(updatedRegisters[register]!!)
        return State(updatedRegisters, instructionPointer + 1)
    }

    /**
     * Continues with the instruction [offset] away relative to itself, if the given [condition] applies to the given [register].
     */
    private fun conditionalJump(register: Register, offset: Int, condition: IntPredicate) = when {
        condition.test(registers[register]!!) -> jump(offset)
        else -> State(registers, instructionPointer + 1)
    }
}