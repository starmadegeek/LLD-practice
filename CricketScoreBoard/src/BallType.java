public enum BallType {
    DOT("0", false), RUN("R", false),
    FOUR("F", false), SIX("S", false),
    WIDE("Wd", true), WICKET("W", false),
    NOBALL("N", true), LEGBYES("L", false),
    BYES("B", false);

    public final String code;
    public final boolean special;

    BallType(String code, boolean special) {
        this.code = code;
        this.special = special;
    }

    static boolean isSpecial(String code){
        return getByCode(code).special;
    }
    static BallType getByCode(String code){
        for (BallType ballType : BallType.values()) {
            if (ballType.code.equals(code)) {
                return ballType;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
