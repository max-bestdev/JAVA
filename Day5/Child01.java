package sec07.pack;

public class Child01 extends Parent {
	   
	   int childField01 = 10 ;
	   
	   void methodChild01 () {
	      System.out.println("자식1 클래스의 메소드 1");
	   }
	   
	   void myMethod() {
	      System.out.println("부모에있는 메소드를 자식이 재정의");
	   }
}
