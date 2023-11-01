import java.util.HashMap;

public class Person {
    private final String name;
    private float expenditure;
    private HashMap<Person, Float> debt;

    public Person(String name) {
        this.name = name;
        this.expenditure = 0;
        this.debt = new HashMap<>();
    }
    public String getName() {
        return name;
    }

    public void addDebt(float amount, Person person){
        if(this.equals(person)) return;
        else{
            debt.put(person, debt.getOrDefault(person, (float) 0) + amount);        
        }
    }

    public void addExpenditure(float amount) {
        this.expenditure += amount;
    }

    public void settleDebt(Person person) {
        debt.remove(person);
    }

    public float totalOwed(){
        float sum = 0;
        for(float amount: debt.values()) sum += amount;
        return sum;
    }

    
    public float getExpenditure() {
        return expenditure;
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Person [name=" + getName() + ", expenditure=" + expenditure + ", debt=" + totalOwed() + ", lenders= [");
        for(Person debtor: debt.keySet()) res.append(debtor.getName() + " : "+ debt.get(debtor)+ ", ");
        return res.toString() +  "]";
    }
    
}
