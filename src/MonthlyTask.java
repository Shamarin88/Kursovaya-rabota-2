import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repatable {
    public MonthlyTask(String title, String descripion, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, descripion, taskType, date);
    }

    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().getMonth().equals(requestedDate.getMonth());
    }
}
