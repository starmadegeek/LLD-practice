
public class BinaryTransaction implements Transaction {
    private final float amount;
    private final Person lender;
    private final Person debter;

    public BinaryTransaction(float amount, Person lender, Person debter) {
        this.amount = amount;
        this.lender = lender;
        this.debter = debter;
    }

    @Override
    public float getAmount() {
        return amount;
    }

    @Override
    public Person getLender() {
        return lender;
    }

    public Person getDebter() {
        return debter;
    }

    @Override
    public String toString() {
        return "BinaryTransaction [amount=" + amount + ", lender=" + lender.getName() + ", debter=" + debter.getName() + "]";
    }
    
    
}
