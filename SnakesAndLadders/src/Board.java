import java.util.HashMap;

public class Board {
    int dimension;
    HashMap<Integer, Entity> entities = new HashMap<>();

    public Board(int dimension){
        this.dimension = dimension;
    }

    public void addEntity(int position, Entity entity){
        if(position > dimension) throw new IllegalArgumentException();
        this.entities.put(position, entity);
    }

    public int getDimension() {
        return this.dimension;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Dimension: ").append(dimension).append("\n");
        for(Entity entity: entities.values()) 
            res.append(entity.printEntity()).append(" | ");
        return res.toString();
    }
}
