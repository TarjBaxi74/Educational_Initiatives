package virtualclassroom.model;


import java.util.Objects;


public class Student {
private final String id;
private final String name;
public Student(String id, String name){ this.id = Objects.requireNonNull(id).trim(); this.name = (name==null? this.id : name.trim()); }
public String getId(){ return id; }
public String getName(){ return name; }
@Override public String toString(){ return id + " (" + name + ")"; }
}