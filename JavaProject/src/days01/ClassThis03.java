package days01;

import java.util.Iterator;

class Student{
	private int bunho;
	private String name;
	private int[] scores;
	private int total;
	private double average;
	
	Student(){
		scores = new int[3];
		name = "홍길남";
		scores[0] = 90;
		scores[1] = 90;
		scores[2] = 90;
		
	}
	
	Student(String n){
		this();
		this.name = n;
	}
	Student(String n, int s1, int s2, int s3){
		this(n);
		scores[0] = s1;
		scores[1] = s2;
		scores[2] = s3;
	}
}
// 생성자 안에서 호출되는 this()는 다른 명령보다 항상 위에 있어야한다.
// 순서가 맞지 않으면 에러를 발생시킨다
// 에러 메시지 : Constructor call must be the first statement in a constructor

public class ClassThis03 {
	
	public static void main(String[] args) {
		Student std1 = new Student();
		Student std2 = new Student("홍길동");
		Student std3 = new Student("홍길남", 98, 87, 89);
	}
}
