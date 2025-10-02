package virtualclassroom.core;

import virtualclassroom.model.*;
import virtualclassroom.core.exceptions.VCException;
import java.util.*;
import java.util.logging.*;

// Singleton manager for the virtual classroom system
public final class VCManager {
    private static volatile VCManager instance;
    private final Map<String, Classroom> classrooms = new LinkedHashMap<>();
    private final NotificationCenter notifier = new NotificationCenter();
    private final Logger logger = Logger.getLogger(VCManager.class.getName());

    private VCManager(){
        logger.setLevel(Level.INFO);
        for(Handler h : logger.getHandlers()) h.setLevel(Level.ALL);
    }

    public static VCManager getInstance(){
        if(instance == null){
            synchronized(VCManager.class){
                if(instance == null) instance = new VCManager();
            }
        }
        return instance;
    }

    public NotificationCenter getNotifier(){ return notifier; }

    // Classroom operations
    public void addClassroom(String name) throws VCException{
        validateName(name);
        if(classrooms.containsKey(name)) throw new VCException("Classroom already exists: " + name);
        classrooms.put(name, EntityFactory.createClassroom(name));
        String msg = "Classroom ["+name+"] has been created.";
        logger.info(msg);
        notifier.notifyAll(msg);
    }

    public void removeClassroom(String name) throws VCException{
        validateName(name);
        Classroom removed = classrooms.remove(name);
        if(removed == null) throw new VCException("Classroom not found: " + name);
        String msg = "Classroom ["+name+"] removed.";
        logger.info(msg);
        notifier.notifyAll(msg);
    }

    public List<String> listClassrooms(){ return new ArrayList<>(classrooms.keySet()); }

    // Student operations
    public void addStudent(String studentId, String studentName, String className) throws VCException{
        validateName(studentId);
        Classroom c = classrooms.get(className);
        if(c == null) throw new VCException("Classroom not found: " + className);
        Student s = EntityFactory.createStudent(studentId, studentName);
        c.enrollStudent(s);
        String msg = "Student ["+studentId+"] enrolled in ["+className+"].";
        logger.info(msg);
        notifier.notifyAll(msg);
    }

    public List<Student> listStudents(String className) throws VCException{
        Classroom c = classrooms.get(className);
        if(c == null) throw new VCException("Classroom not found: " + className);
        return new ArrayList<>(c.listStudents());
    }

    // Assignment operations
    public void scheduleAssignment(String className, String assignmentId, String title) throws VCException{
        validateName(assignmentId);
        Classroom c = classrooms.get(className);
        if(c == null) throw new VCException("Classroom not found: " + className);
        Assignment a = EntityFactory.createAssignment(assignmentId, title);
        c.addAssignment(a);
        String msg = "Assignment for ["+className+"] scheduled: " + a.getId();
        logger.info(msg);
        notifier.notifyAll(msg);
    }

    public void submitAssignment(String studentId, String className, String assignmentId) throws VCException{
        Classroom c = classrooms.get(className);
        if(c == null) throw new VCException("Classroom not found: " + className);
        Student s = c.getStudent(studentId).orElseThrow(() -> new VCException("Student not enrolled: " + studentId));
        Assignment a = c.getAssignment(assignmentId).orElseThrow(() -> new VCException("Assignment not found: " + assignmentId));
        a.submit(studentId);
        String msg = "Assignment submitted by Student ["+studentId+"] in ["+className+"].";
        logger.info(msg);
        notifier.notifyAll(msg);
    }

    private void validateName(String s) throws VCException{
        if(s == null || s.trim().isEmpty()) throw new VCException("Identifier cannot be empty");
    }
}
