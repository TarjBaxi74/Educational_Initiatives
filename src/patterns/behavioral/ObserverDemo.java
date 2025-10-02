package patterns.behavioral;


import java.util.ArrayList;
import java.util.List;


// Simple Observer pattern demo: Subject -> Observers
interface ObserverB { void update(String event); }
class LoggerObserver implements ObserverB { public void update(String event){ System.out.println("[Logger] " + event); } }
class EmailObserver implements ObserverB { public void update(String event){ System.out.println("[Email] notify: " + event); } }


class SubjectB {
private final List<ObserverB> observers = new ArrayList<>();
public void attach(ObserverB o){ observers.add(o); }
public void detach(ObserverB o){ observers.remove(o); }
public void notifyAllObservers(String event){ for(var o: observers) o.update(event); }
}


public class ObserverDemo {
public static void demo(){
SubjectB subject = new SubjectB();
subject.attach(new LoggerObserver());
subject.attach(new EmailObserver());
subject.notifyAllObservers("New assignment scheduled in Math 101");
}


public static void main(String[] args){ demo(); }
}