package days01;

// this 키워드의 용도 #1

// this 변수는 레퍼런스(참조) 변수입니다.
// 멤버변수는 new 생성자()에 의해 새로운공간 객체별로 각각 만들어집니다.
// 반면에 멤버 메서드는 한개만 만들어지며, 각 객체들이 공유&호출해서 사용합니다.
// 이때 메서드내에는 "누가 자신을 호출했는지를 구분"하기 위해 this라는 참조변수를 준비합니다.
// 호출객체의 주소(참조값)이 this에 저장됩니다

class Cclass{
	private int num;
	public void init(int n /* , Cclass this */) {
		num = n;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void copy(Cclass c) {
		// TODO Auto-generated method stub
		this.num = c.num;
	}
	
	
}

public class ClassThis01 {

	public static void main(String[] args) {
		Cclass c1 = new Cclass();
		Cclass c2 = new Cclass();
		Cclass c3 = new Cclass();

		c1.init(10);	// 10 -> init 메서드의 매개변수 n, c1은 init 메서드의 this로 전달
		c2.init(20);	// 10 -> init 메서드의 매개변수 n, c2은 init 메서드의 this로 전달
		c3.init(30);	// 10 -> init 메서드의 매개변수 n, c3은 init 메서드의 this로 전달
		
//		Cclass c4 = c3;
//		c3.setNum(100);
//		
//		System.out.println(c4.getNum());
//		System.out.println(c3.getNum());
		
		Cclass c4 = new Cclass();
		c4.copy(c3);
		c3.setNum(100);
		System.out.println(c3.getNum());
		System.out.println(c4.getNum());
	}

}
