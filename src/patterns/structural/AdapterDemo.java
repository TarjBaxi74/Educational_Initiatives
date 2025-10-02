package patterns.structural;


// Adapter: adapt legacy student record to our Student interface
class LegacyStudentRecord { public String getFull() { return "John Doe|S123"; } }


interface IStudent { String id(); String name(); }


class StudentAdapter implements IStudent {
private final LegacyStudentRecord legacy;
public StudentAdapter(LegacyStudentRecord l){ legacy = l; }
public String id(){ return legacy.getFull().split("\\|")[1]; }
public String name(){ return legacy.getFull().split("\\|")[0]; }
}


public class AdapterDemo{ public static void demo(){ IStudent s = new StudentAdapter(new LegacyStudentRecord()); System.out.println(s.id() + " -> " + s.name()); } public static void main(String[] a){ demo(); } } 
