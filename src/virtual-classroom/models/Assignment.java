package virtualclassroom.model;


import java.time.LocalDateTime;
import java.util.*;


public class Assignment {
private final String id;
private final String title;
private final LocalDateTime scheduledAt;
private final Set<String> submissions = new HashSet<>();


public Assignment(String id, String title){ this.id = Objects.requireNonNull(id); this.title = Objects.requireNonNull(title); this.scheduledAt = LocalDateTime.now(); }
public String getId(){ return id; }
public String getTitle(){ return title; }
public LocalDateTime getScheduledAt(){ return scheduledAt; }
public void submit(String studentId){ submissions.add(studentId); }
public boolean isSubmittedBy(String studentId){ return submissions.contains(studentId); }
public Set<String> getSubmissions(){ return Collections.unmodifiableSet(submissions); }
@Override public String toString(){ return id + ": " + title + " (scheduled: " + scheduledAt + ")"; }
}