public class Ladder extends Entity{

    public Ladder(int start, int end) {
        super(start, end);
    }

    @Override
    public String printEntity() {
        return("L: " + start + "->" + end);
    }
    
}
