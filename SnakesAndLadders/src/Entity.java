public abstract class Entity {
    final int start;
    final int end;
    
    public Entity(int start, int end){
        this.start = start;
        this.end = end;
    }
    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public abstract String printEntity();
    
}
