import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, I'm Splitwise!");
        
        List<Person> persons = new ArrayList<>();
        for(int i=1; i<=5; i++) {
            persons.add(new Person("p" + i));
        }

        Splitwise splitwise = new Splitwise();
        splitwise.setPersons(persons);
        splitwise.makeGroupTransaction(100, persons.get(0), persons);
        splitwise.makeGroupTransaction(200, persons.get(3), persons);
        splitwise.makeGroupTransaction(1180, persons.get(2), persons);

        List<Person> persons2 = new ArrayList<>();
        for(int i=2; i<=5; i++) {
            persons2.add(persons.get(i-1));
        }

        splitwise.makeGroupTransaction(560, persons.get(4), persons2);

        System.out.println(splitwise.printLedger());
        System.out.println(splitwise.printPersons());
        
    }
}
