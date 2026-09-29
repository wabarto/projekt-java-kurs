import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2026, 5, 15);
        LocalTime time = LocalTime.of(14, 30);
        LocalDateTime dateTime = LocalDateTime.of(2026, 5, 15, 14, 30);
        ZonedDateTime zoned = ZonedDateTime.of(dateTime, ZoneId.of("Europe/Warsaw"));
        OffsetDateTime withOffset = OffsetDateTime.of(dateTime, ZoneOffset.of("+02:00"));
        Instant instant = Instant.now();
        Duration duration = Duration.ofHours(2);
        Period period = Period.ofMonths(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        System.out.println(date);
        System.out.println(dateTime);
        System.out.println(zoned);
        System.out.println(instant);


        System.out.println(date.plus(period));

        System.out.println(dateTime.plus(duration));


        LocalDate withEnum = LocalDate.of(2026, Month.JUNE, 15);
        LocalDate today = LocalDate.now();
        LocalDate parsed = LocalDate.parse("2026-02-15");

        parsed.getYear();
        parsed.getMonth(); // enum FEBRUARY
        parsed.getMonthValue(); // 2
        parsed.getDayOfMonth(); // 15
        parsed.getDayOfWeek(); // SUNDAY
        parsed.getDayOfYear(); // 46
        System.out.println(parsed.lengthOfMonth());
        System.out.println(parsed.lengthOfYear()); // 366
        System.out.println(parsed.isLeapYear()); //


        parsed.plusDays(1);
        parsed.plusWeeks(1);
        parsed.plusMonths(1);
        parsed.plusYears(1);
        parsed.minusMonths(1);


        LocalDate newDate = parsed.withDayOfMonth(1); // 2026-02-01
        parsed.withMonth(1);
        parsed.withYear(1);

        LocalDate d = LocalDate.of(2026, 6, 15);

        d.with(TemporalAdjusters.firstDayOfMonth()); // 2026-06-01
        d.with(TemporalAdjusters.lastDayOfMonth()); // 2026-06-30
        d.with(TemporalAdjusters.firstDayOfNextMonth()); // 2026-07-01
        d.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)); // 2026-06-22
        d.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        d.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        d.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));


        System.out.println("comparing");

        LocalDate d1 = LocalDate.of(2024, 6, 15);

        System.out.println(d.isBefore(d1));
        System.out.println(d.isAfter(d1));
        System.out.println(d.isEqual(d1));
        System.out.println(d.compareTo(d1));

        List<LocalDate> dates = new ArrayList<>(List.of(d, d1));
        Collections.sort(dates);

        System.out.println(dates);


        LocalDate birthDate = LocalDate.of(1930, 10, 10);


        Period age = Period.between(birthDate, today);

        System.out.println(age.getYears());
        System.out.println(age.getMonths());
        System.out.println(age.getDays());

        // ChronoUnit

        long days = ChronoUnit.DAYS.between(birthDate, today);
        long weeks = ChronoUnit.WEEKS.between(birthDate, today);
        long months = ChronoUnit.MONTHS.between(birthDate, today);
        long years = ChronoUnit.YEARS.between(birthDate, today);



        LocalTime now = LocalTime.now();
        LocalTime.of(14, 30);
        LocalTime.parse("14:30:45");

        now.getHour();
        now.getMinute();
        now.getSecond();
        now.getNano();

        now.plusHours(2);
        now.plusMinutes(2);

        long minutes = ChronoUnit.MINUTES.between(now, LocalTime.of(14, 30));

        // LocalDateTime now, of, parse
        LocalDateTime dateTime1 = LocalDateTime.parse("2026-06-05T14:30:45");
        LocalDateTime dateTimeNow = LocalDateTime.now();

        dateTime1.toLocalDate();
        dateTime1.toLocalTime();

        dateTime1.plusWeeks(1);
        dateTime1.withHour(9).withMinute(10);

        Duration difference = Duration.between(dateTime1, dateTimeNow);

        difference.toHours();
        difference.toMinutes();
        difference.toSeconds();
        difference.toMinutesPart();


        Duration twoHours = Duration.ofHours(2);
        Duration halfHour = Duration.ofMinutes(30);
        Duration total = twoHours.plus(halfHour);

        LocalDateTime later = dateTimeNow.plus(total);


        Clock fixedClock = Clock.fixed(Instant.parse("2025-06-05T10:00:00Z"), ZoneId.of("Europe/Warsaw"));

        System.out.println(LocalDateTime.now(fixedClock));
        System.out.println(LocalDate.now(fixedClock));



























    }
}
