package days02;

class MemberCall {
	// 인스턴스 변수
	int iv = 10;
	int iv2 = sv; // 스태틱 변수로 인스턴스 변수값 초기화 O
	
	// 스태틱 변수
	static int sv = 20;
//	static int sv2 = iv;  인스턴스 변수로 스태틱변수값 초기화X
	static int sv2 = new MemberCall().iv; // 꼭 사용해야 겠다면 이렇게.

	// 인스턴스 메서드
	void instanceMethod1() {
		// 인스턴스 메서드에서는 인스턴스 변수 사용 O
		System.out.println(iv);
		// 인스턴스 메서드에서는 스태틱 변수 사용 O
		System.out.println(sv);
		// 인스턴스 메서드에서 인스턴스 메서드 호출 O
		instanceMethod2();
		// 인스턴스 메서드에서 스태틱 메서드 호출 O
		staticMethod2();
	}
	void instanceMethod2() {

	}

	// 스태틱 메서드
	static void staticMethod1() {
		// 스태틱 메서드에서 인스턴스 변수 사용 X
//		System.out.println(iv); 
		// 스태틱 메서드에서 스태틱 변수 사용 O
		System.out.println(sv);
		// 스태틱 메서드에서 인스턴스 메서드 호출 X
//		instanceMethod2();
		// 스태틱 메서드에서 스태틱 메서드 호출 O
		staticMethod2();
	}
	static void staticMethod2() {
		
	}
}

public class ClassStatic03 {

	public static void main(String[] args) {

		// 스태틱 멤버는 객체 생성없이 바로 사용 가능
		MemberCall.sv = 300;
		MemberCall.staticMethod1();

		// 인스턴스 메서드는 객체생성 후에 사용 가능
		MemberCall mc = new MemberCall();
		mc.iv = 30;
		mc.instanceMethod1();
		
		// Integer 클래스의 메서드
//		Integer.parseInt(null);
		// Ex) Math 클래스의 메서드들
		Math.random();	// 난수 출력
		Math.sqrt(0);	// 제곱근 출력
		Math.abs(0);	// 절대값 출력
	}

}
