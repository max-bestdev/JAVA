package chap15;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//import java.util.*;

public class HashSetExample {
   public static void main(String[] args) {
      
      Set<String> set = new HashSet<String>();
      //String 클래스에 재정의된 hashCode(), equals()를 이용해서
      //객체 검사를 하여 중복값을 자는 String 입력을 막습니다.
      set.add("Java");
      //boolean addResult2 = set.add("Java");
      //System.out.println("addResult: " + addResult2);
      set.add("JDBC");
      set.add("Servlet/JSP");
      boolean addResult = set.add("Java");
      //false : 동등객체가 존재할 경우 add실행문은 실제로 추가가 안됨
      System.out.println("addResult: " + addResult);
      set.add("iBATIS");
      
      int size = set.size();
      System.out.println("총 객체수: " + size);
      
      Iterator<String> iterator = set.iterator();
      while(iterator.hasNext()) {
         String element = iterator.next();
         System.out.println("\t" + element);
      }
      
      System.out.println("=======================");
      
      for(String element2:set) {
         System.out.println("\t" + element2);
      }
      
      set.remove("JDBC");
      set.remove("iBATIS");
      
      System.out.println("총 객체수: " + set.size());
      
      for(String element : set) {
         System.out.println("\t" + element);
      }
      
      set.clear();      
      if(set.isEmpty()) { System.out.println("비어 있음"); }
   }
}