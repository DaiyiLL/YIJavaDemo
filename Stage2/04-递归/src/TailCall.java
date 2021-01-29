
public class TailCall {

	void test1() {
		int a = 10;
		int b = a + 10;
		test2(b);
	}
	
	void test2(int b) {
		
	}
}
