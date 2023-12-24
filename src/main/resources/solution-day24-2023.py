#!/usr/bin/env python3

from z3 import *

rx, ry, rz = Ints('rx ry rz')
rvx, rvy, rvz = Ints('rvx rvy rvz')
t0, t1, t2 = Ints('t0 t1 t2')
answer = Int('answer')

solve(
    rx + t0 * rvx == 277903024391745 + t0 * -118,
    ry + t0 * rvy == 368934106615824 + t0 * -107,
    rz + t0 * rvz == 298537551311799 + t0 * 62,

    rx + t1 * rvx == 183412557215942 + t1 * 72,
    ry + t1 * rvy == 418647008376557 + t1 * -215,
    rz + t1 * rvz == 219970939624628 + t1 * 133,

    rx + t2 * rvx == 378231151165434 + t2 * -118,
    ry + t2 * rvy == 338743238211338 + t2 * -260,
    rz + t2 * rvz == 302779683441821 + t2 * -344,

    answer == rx + ry + rz,
)