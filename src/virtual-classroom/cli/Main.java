package virtualclassroom.cli;

import virtualclassroom.core.VCManager;
import virtualclassroom.model.Student;
import virtualclassroom.core.exceptions.VCException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        VCManager mgr = VCManager.getInstance();
        // Register a simple console listener
        mgr.getNotifier().register(msg -> System.out.println("[NOTIFY] " + msg));

        System.out.println("Virtual Classroom Manager - Type 'help' for commands");
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("> ");
            if(!sc.hasNextLine()) { System.out.println(); System.out.println("Bye"); break; }
            String line = sc.nextLine();
            if(line == null) break;
            line = line.trim();
            if(line.isEmpty()) continue;
            if(line.equalsIgnoreCase("exit")) { System.out.println("Bye"); break; }
            if(line.equalsIgnoreCase("help")) { printHelp(); continue; }

            String[] parts = line.split("\\s+", 4);
            try{
                switch(parts[0].toLowerCase()){
                    case "add_classroom" -> {
                        if(parts.length < 2) { System.out.println("Usage: add_classroom <name>"); }
                        else { mgr.addClassroom(parts[1]); }
                    }
                    case "remove_classroom" -> {
                        if(parts.length < 2) { System.out.println("Usage: remove_classroom <name>"); }
                        else { mgr.removeClassroom(parts[1]); }
                    }
                    case "list_classrooms" -> {
                        List<String> cs = mgr.listClassrooms();
                        if(cs.isEmpty()) System.out.println("No classrooms");
                        else cs.forEach(System.out::println);
                    }
                    case "add_student" -> {
                        if(parts.length < 3) { System.out.println("Usage: add_student <studentId> <className>"); }
                        else { mgr.addStudent(parts[1], parts[1], parts[2]); }
                    }
                    case "list_students" -> {
                        if(parts.length < 2) { System.out.println("Usage: list_students <className>"); }
                        else { var list = mgr.listStudents(parts[1]); list.forEach(System.out::println); }
                    }
                    case "schedule_assignment" -> {
                        if(parts.length < 4) { System.out.println("Usage: schedule_assignment <className> <assignmentId> <title>"); }
                        else { mgr.scheduleAssignment(parts[1], parts[2], parts[3]); }
                    }
                    case "submit_assignment" -> {
                        if(parts.length < 4) { System.out.println("Usage: submit_assignment <studentId> <className> <assignmentId>"); }
                        else { mgr.submitAssignment(parts[1], parts[2], parts[3]); }
                    }
                    default -> System.out.println("Unknown command. Type 'help'.");
                }
            } catch(VCException ex){ System.out.println("ERROR: " + ex.getMessage()); }
            catch(Exception ex){ System.out.println("Unhandled error: " + ex.getMessage()); }
        }
        sc.close();
    }

    private static void printHelp(){
        System.out.println("Commands:");
        System.out.println("  add_classroom <name>");
        System.out.println("  remove_classroom <name>");
        System.out.println("  list_classrooms");
        System.out.println("  add_student <studentId> <className>");
        System.out.println("  list_students <className>");
        System.out.println("  schedule_assignment <className> <assignmentId> <title>");
        System.out.println("  submit_assignment <studentId> <className> <assignmentId>");
        System.out.println("  help");
        System.out.println("  exit");
    }
}
