import java.util.List;

public class GroupTransaction implements Transaction {
    private final float amount;
    private final Person lender;
    private final List<Person> debters;

    public GroupTransaction(float amount, Person lender, List<Person> debters) {
        this.amount = amount;
        this.lender = lender;
        this.debters = debters;
    }

    @Override
    public float getAmount() {
        return this.amount;
    }

    @Override
    public Person getLender() {
        return this.lender;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("GroupTransaction [amount=" + amount + ", lender=" + lender.getName() + ", debters=");
        for(Person debtor: debters) res.append(debtor.getName() + ", ");
        return res.toString() +  "]";
    }
    
    
}
