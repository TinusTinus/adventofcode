class Numbers {
    
    static String toString(Number number) {
        return switch (number) {
            case Integer integer -> "int";
            case Long longValue -> "long";
            default -> "other";
        };
    }
}
