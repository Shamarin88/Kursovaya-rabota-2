import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyCalendar {

    private static final Map<Integer, Repatable> actualTask = new HashMap<>();

    public static void addTask(Scanner scanner) {

        try {
            scanner.nextLine();
            System.out.println("Введите название задачи: ");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите описание задачи: ");
            String description = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите тип задачи: 0 - Рабочая 1 - Личная");
            TaskType taskType = TaskType.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 0 - Однократная, 1 - Ежедневная, 2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная");
            int occurance = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy HH:mm ");
            scanner.nextLine();
            createEvent(scanner, title, description, taskType, occurance);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printActualTask() {
        for (Repatable task : actualTask.values()) {
            System.out.println(task);
        }
    }

    public static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occurance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Repatable task = null;
            try {
                task = createTask(occurance, title, description, taskType, eventDate);
                System.out.println("Создана задача " + task);
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат dd.MM.yyyy HH:mm и попробуйте еще раз");
            createEvent(scanner, title, description, taskType, occurance);
        }
    }

    private static Repatable createTask (int occurance, String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        return switch (occurance) {
            case 0 -> {
                OnceTask onceTask = new OnceTask(title, description, taskType, date);
                actualTask.put(onceTask.getId(), onceTask);
                yield onceTask;
            }
            case 1 -> {
                DailyTask task = new DailyTask(title, description, taskType, date);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, description, taskType, date);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, description, taskType, date);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YearlyTask task = new YearlyTask(title, description, taskType, date);
                actualTask.put(task.getId(), task);
                yield task;
            }
            default -> null;
        };
    }
    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
        printActualTask();
        System.out.println("Для удаления введите Id задачи\n");
        int id = scanner.nextInt();
        if (actualTask.containsKey(id)) {
            Repatable removedTask = actualTask.get(id);
            actualTask.remove(id);
            System.out.println("Задача " + id + " удалена\n");
        } else {
            System.out.println("Такой задачи нет\n");
        }
    }
}
