public class Snake extends Entity{

    public Snake(int start, int end) {
        super(start, end);
    }

    @Override
    public String printEntity() {
        return("S: " + start + "->" + end);
    }
    
}
