import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/*
    In order to use this all you need to do it to put the response file that you got from the forms and press download the csv file.
    From there you just need to put it in the where the current lunch duty.csv file is.  After ran it will output everything into the lunchDuty.csv file
    If assistance is needed contact Jacob Walter or anyone who has taken at least CSCE 155.
 */
public class Main {

    private static int currentDay=0;
    // Change this date to whatever is the starting date.  Month is from 0-11 so use Calander.Month
    private static Date day = new Date(2023,Calendar.AUGUST,21);

    public static void main(String[] args) throws FileNotFoundException {
        /*
            If Pledges and actives don't matter and all are to be lumped in together delete what is below and uncomment this part out and delete this line

        List<Person> people = new ArrayList<>();
        Scanner s = new Scanner(new File("src/lunch duty.csv"));
        String temp = s.nextLine();
        while(s.hasNextLine()){
           temp = s.nextLine();
           String tokens[] = temp.split(",");
            Person person = new Person(tokens[1], tokens[2]);
           people.add(person);
        }
        s.close();
        PrintWriter f = new PrintWriter("lunchDuty.csv");
        populate(people,f)
        populate(people,f)
        f.close();
    }
         */
        List<Person> pledges = new ArrayList<>();
        List<Person> actives = new ArrayList<>();


        Scanner s = new Scanner(new File("src/lunch duty.csv"));
        String temp = s.nextLine();
        while(s.hasNextLine()){
           temp = s.nextLine();
           String tokens[] = temp.split(",");
            Person person = new Person(tokens[1], tokens[2]);
           if (tokens[3].equals("Pledge")){
               pledges.add(person);
           }
           else{
               actives.add(person);
           }
        }
        s.close();
        PrintWriter f = new PrintWriter("lunchDuty.csv");
        populate(pledges, f);
        populate(pledges, f);
        populate(actives, f);
        f.close();
    }

    private static void populate(List<Person> people, PrintWriter f) throws FileNotFoundException {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        List<Person> tempList = new ArrayList<>();
        for(int a=0; a<people.size(); a++){
            tempList.add(people.get(a));
        }
        List<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        for(int i = tempList.size(); i>0; i--){
            Person temp = setDay(tempList,days.get(currentDay));
            if (temp!=null){
                System.out.println(day.getMonth()+1+"/"+day.getDate() + " " + days.get(currentDay) + " = " + temp.getName());
                f.println(day.getMonth()+1+"/"+day.getDate() + " " + days.get(currentDay) + " = " + temp.getName());
                tempList.remove(temp);
            } else{
                System.out.println(day.getMonth()+1+"/"+day.getDate() + " " +days.get(currentDay) + " = TBD");
                f.println(day.getMonth()+1+"/"+day.getDate() + " " +days.get(currentDay) + " = TBD");
            }
            if(currentDay!=4){
                currentDay++;
                c.add(Calendar.DATE, 1);
                day = c.getTime();
            }else{
                currentDay=0;
                c.add(Calendar.DATE, 3);
                day = c.getTime();
            }

        }
    }

    private static Person setDay(List<Person> people, String day) {
        List<Person> tempList = new ArrayList<>();
        for(int a=0; a<people.size(); a++){
            tempList.add(people.get(a));
        }
        for(int i = tempList.size(); i>0; i--){
            int j = (int) (Math.random()*(tempList.size()-1));
            String temp[] = tempList.get(j).getDays();
            for(int z=0; z<temp.length; z++) {
                if (temp[z].contains(day)) {
                    return tempList.get(j);
                }
            }
            tempList.remove(j);
        }
        return null;
    }
}