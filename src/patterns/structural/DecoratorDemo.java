package patterns.structural;


// Decorator: extend a base assignment with features (e.g., plagiarism check)
interface AssignmentComponent { String details(); }
class BasicAssignment implements AssignmentComponent{ private final String title; public BasicAssignment(String t){ title=t;} public String details(){ return "Assignment: " + title; } }
class AssignmentDecorator implements AssignmentComponent{ protected final AssignmentComponent inner; public AssignmentDecorator(AssignmentComponent c){ inner=c; } public String details(){ return inner.details(); } }
class PlagiarismDecorator extends AssignmentDecorator{ public PlagiarismDecorator(AssignmentComponent c){ super(c);} public String details(){ return super.details() + " [PlagiarismChecked]"; } }


public class DecoratorDemo{ public static void demo(){ AssignmentComponent a = new PlagiarismDecorator(new BasicAssignment("Math HW")); System.out.println(a.details()); } public static void main(String[] args){ demo(); } } 
