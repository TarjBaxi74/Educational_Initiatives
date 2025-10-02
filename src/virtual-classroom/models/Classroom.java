package virtualclassroom.model;


import java.util.*;


public class Classroom {
private final String name;
private final Map<String, virtualclassroom.model.Student> students = new LinkedHashMap<>();
private final Map<String, virtualclassroom.model.Assignment> assignments = new LinkedHashMap<>();


public Classroom(String name){ this.name = Objects.requireNonNull(name).trim(); }
public String getName(){ return name; }


public void enrollStudent(virtualclassroom.model.Student s){ students.put(s.getId(), s); }
public Optional<virtualclassroom.model.Student> getStudent(String id){ return Optional.ofNullable(students.get(id)); }
public Collection<virtualclassroom.model.Student> listStudents(){ return students.values(); }


public void addAssignment(virtualclassroom.model.Assignment a){ assignments.put(a.getId(), a); }
public Optional<virtualclassroom.model.Assignment> getAssignment(String id){ return Optional.ofNullable(assignments.get(id)); }
public Collection<virtualclassroom.model.Assignment> listAssignments(){ return assignments.values(); }
}