
import java.util.Arrays;
import java.util.Date;

public class Person {
    private String Name;
    String days[];
    private int numTimes;


    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", days=" + Arrays.toString(days) +
                ", numTimes=" + numTimes +
                '}';
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    public Person(String name, String days) {
        Name=name;
        this.days = days.split(";");
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void addNum(){
        numTimes++;
    }
}
