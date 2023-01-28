import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repatable {
    public YearlyTask(String title, String descripion, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, descripion, taskType, date);
    }

    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().getYear() == requestedDate.getYear();
    }
}
