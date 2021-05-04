package sec04.p539;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateTimeCompareExample {
   public static void main(String[] args) {
      LocalDateTime startDateTime = LocalDateTime.of(2023, 4, 30, 9, 30, 0, 0);
      System.out.println("시작일: " + startDateTime);
      
      LocalDateTime endDateTime = LocalDateTime.of(2024, 2, 1, 18, 10, 0);
      System.out.println("종료일: " + endDateTime + "\n");
      //--------------------------------------------------------------
      if(startDateTime.isBefore(endDateTime)) {
         System.out.println("진행 중입니다." + "\n");
      } else if(startDateTime.isEqual(endDateTime)) {
         System.out.println("종료합니다." + "\n");
      } else if(startDateTime.isAfter(endDateTime)) {
         System.out.println("종료했습니다." + "\n");
      }
      //--------------------------------------------------------------
      System.out.println("[종료까지 남은 시간]");
      long remainYear = startDateTime.until(endDateTime, ChronoUnit.YEARS);
      long remainMonth = startDateTime.until(endDateTime, ChronoUnit.MONTHS);
      long remainDay = startDateTime.until(endDateTime, ChronoUnit.DAYS);
      long remainHour = startDateTime.until(endDateTime, ChronoUnit.HOURS);
      long remainMinute = startDateTime.until(endDateTime, ChronoUnit.MINUTES);
      long remainSecond = startDateTime.until(endDateTime, ChronoUnit.SECONDS);
      
      System.out.println("=========1st===================");
      
      System.out.println("남은 해: " + remainYear);
      System.out.println("남은 달: " + remainMonth);
      System.out.println("남은 일: " + remainDay);
      System.out.println("남은 시간: " + remainHour);
      System.out.println("남은 분: " + remainMinute);
      System.out.println("남은 초: " + remainSecond + "\n");
      
      
      

      remainYear = ChronoUnit.YEARS.between(startDateTime, endDateTime);
      remainMonth = ChronoUnit.MONTHS.between(startDateTime, endDateTime);
      remainDay = ChronoUnit.DAYS.between(startDateTime, endDateTime);
      remainHour = ChronoUnit.HOURS.between(startDateTime, endDateTime);
      remainMinute = ChronoUnit.MINUTES.between(startDateTime, endDateTime);
      remainSecond = ChronoUnit.SECONDS.between(startDateTime, endDateTime);
      
      System.out.println("=========2nd===================");
      System.out.println("남은 해: " + remainYear);
      System.out.println("남은 달: " + remainMonth);
      System.out.println("남은 일: " + remainDay);
      System.out.println("남은 시간: " + remainHour);
      System.out.println("남은 분: " + remainMinute);
      System.out.println("남은 초: " + remainSecond + "\n");
      
      //--------------------------------------------------------------
      LocalDateTime startDateTime2 = LocalDateTime.of(2023, 4, 30, 9, 30, 0);
      System.out.println("시작일: " + startDateTime2);
      
      LocalDateTime endDateTime2 = LocalDateTime.of(2025, 7, 1, 18, 10, 0);
      //LocalDateTime endDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
      
      System.out.println("종료일: " + endDateTime2 + "\n");

      System.out.println("[종료까지 남은 기간]");
      
      Period period = Period.between(startDateTime2.toLocalDate(), endDateTime2.toLocalDate());
      //Period period = Period.between(endDateTime2.toLocalDate(), startDateTime2.toLocalDate());
      System.out.println(period);
      
      System.out.println("년차이: " + period.getYears() + "년 ");
      System.out.println("월차이: " + period.getMonths() + "달 ");
      System.out.println("일차이: " + period.getDays() + "일\n");
      //--------------------------------------------------------------
      Duration duration = 
            Duration.between(startDateTime2.toLocalTime(), 
                         endDateTime2.toLocalTime());
      
      System.out.println(duration);
      
      System.out.println("남은 초: " + duration.getSeconds());
      System.out.println("초차이: " + duration.getSeconds());
      
   }
}
