import java.util.ArrayList;
import java.util.List;

public class Splitwise {
    private List<Person> persons;
    private List<Transaction> ledger;

    public Splitwise() {
        this.persons = new ArrayList<>();
        this.ledger = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Transaction> getLedger() {
        return ledger;
    }

    public void setLedger(List<Transaction> ledger) {
        this.ledger = ledger;
    }

    public void addPerson(Person person){
        this.persons.add(person);
    }

    public void addTransaction(Transaction transaction){
        this.ledger.add(transaction);
    }

    public void makeGroupTransaction(float amount, Person lender, List<Person> debtors){
        for(Person debtor: debtors){
            if(!persons.contains(debtor)) throw new IllegalArgumentException("Invalid person");
            debtor.addDebt(amount / debtors.size(), lender);
            debtor.addExpenditure(amount/debtors.size());
            if(!debtor.equals(lender)) lender.addDebt(-1*(amount / debtors.size()), debtor);
        }
        addTransaction(new GroupTransaction(amount, lender, debtors));
    }

    public void settleTransaction(Person debtor, Person lender){
        debtor.settleDebt(lender);
        addTransaction(new BinaryTransaction(0, lender, debtor));
    }

    public String printLedger(){
        StringBuilder res = new StringBuilder();
        res.append("Ledger: \n");
        for(Transaction transaction: ledger) res.append(transaction.toString() + "\n");
        return res.toString();
    }

    public String printPersons(){
        StringBuilder res = new StringBuilder();
        res.append("Persons: \n");
        for(Person person: persons) res.append(person.toString() + "\n");
        return res.toString();
    }
    
}
