import java.time.LocalDateTime;

public class DailyTask extends Task implements Repatable {
       public DailyTask(String title, String descripion, TaskType taskType, LocalDateTime date) throws WrongInputException {
           super(title, descripion, taskType, date);
       }

       public boolean checkOccurance(LocalDateTime requestedDate) {
           return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
       }
}

