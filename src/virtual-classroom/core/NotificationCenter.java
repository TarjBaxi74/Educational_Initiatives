package virtualclassroom.core;

import java.util.ArrayList;
import java.util.List;

public class NotificationCenter {
    public interface Listener{ void onNotify(String msg); }
    private final List<Listener> listeners = new ArrayList<>();

    public void register(Listener l){ listeners.add(l); }
    public void unregister(Listener l){ listeners.remove(l); }

    public void notifyAll(String msg){
        for(var l: listeners) {
            try{ l.onNotify(msg); }
            catch(Exception e) { /* prevent single listener crash */ }
        }
    }
}
