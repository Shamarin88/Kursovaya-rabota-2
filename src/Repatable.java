import java.time.LocalDateTime;

public interface Repatable {

    boolean checkOccurance(LocalDateTime localDateTime);
    void setTitle(String title);
    LocalDateTime getFirstDate();
}
