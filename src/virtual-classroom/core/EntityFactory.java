package virtualclassroom.core;

import virtualclassroom.model.*;

public class EntityFactory {
    public static Classroom createClassroom(String name){ return new Classroom(name); }
    public static Student createStudent(String id, String name){ return new Student(id, name); }
    public static Assignment createAssignment(String id, String title){ return new Assignment(id, title); }
}
