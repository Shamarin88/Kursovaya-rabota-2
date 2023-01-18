import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repatable {
    public WeeklyTask(String title, String descripion, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, descripion, taskType, date);
    }

    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }

    @Override
    public LocalDateTime getFirstDate() {
        return null;
    }
}
