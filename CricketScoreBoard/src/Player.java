public record Player(String name) {

    @Override
    public String toString() {
        return name();
    }
}
