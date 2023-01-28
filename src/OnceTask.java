import java.time.LocalDateTime;

public class OnceTask extends Task implements Repatable {
    public OnceTask(String title, String descripion, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, descripion, taskType, date);
    }

    public boolean checkOccurance(LocalDateTime requestedDate) {
        return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
    }
}
