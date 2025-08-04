enum FooBar {
    FOO,
    BAR;
    
    static String toString(FooBar value) {
        return switch (value) {
            case FOO -> "foo";
            case BAR -> "bar";
            default -> "unexpected";
        };
    }
}
