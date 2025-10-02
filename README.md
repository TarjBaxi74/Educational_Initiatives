# Virtual Classroom + Design Patterns (Java)


## Overview


Contains two parts:


1. **Exercise 1**: Demonstrations of 6 design patterns (Observer, Strategy, Singleton, Factory, Adapter, Decorator).
2. **Exercise 2**: A terminal-based **Virtual Classroom Manager** implementing Singleton (manager), Factory (entities), Observer (notifications), logging, validation, exception handling and a console CLI.


## How to build & run


From project root:


```bash
# compile all .java files
javac -d out $(find src -name "*.java")


# run CLI
java -cp out virtualclassroom.cli.Main
```


Or use the included `build-and-run.sh`.


## Notes
- Logging uses `java.util.logging` configured in the manager.
- The CLI supports commands: `add_classroom <name>`, `remove_classroom <name>`, `list_classrooms`, `add_student <studentId> <className>`, `list_students <className>`, `schedule_assignment <className> <assignmentId> <title>`, `submit_assignment <studentId> <className> <assignmentId>`, `help`, `exit`.