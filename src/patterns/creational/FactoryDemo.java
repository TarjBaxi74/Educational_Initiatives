package patterns.creational;


// Factory pattern: create different notification channels
interface Channel { void send(String msg); }
class SMSChannel implements Channel{ public void send(String msg){ System.out.println("SMS: " + msg); } }
class EmailChannel implements Channel{ public void send(String msg){ System.out.println("Email: " + msg); } }


class ChannelFactory {
public static Channel create(String type){
return switch(type.toLowerCase()){
case "sms" -> new SMSChannel();
case "email" -> new EmailChannel();
default -> throw new IllegalArgumentException("Unknown channel: " + type);
};
}
}


public class FactoryDemo{
public static void demo(){
Channel c = ChannelFactory.create("sms"); c.send("Assignment due");
Channel e = ChannelFactory.create("email"); e.send("Assignment due");
}
public static void main(String[] args){ demo(); }
}