public class Test {
	
	public static void main (String[] args) {
		handler example = new handler();
		example.test();
	}
}

class handler {

	testObject x;
	testObject1 y;

	public void test() {
		x = new testObject();x.num = 1;x.text = "what";
		y = new testObject1();y.val = 2;y.word = "who";y.ans = false;y.obj = x;
		if (x == y.obj) {System.out.println("success");}
		System.out.println(y.obj.text);
		testObject thing = y.obj;
		System.out.println(thing.text);
	}
}

class testObject {
	int num;
	String text;
}

class testObject1 {
	int val;
	String word;
	boolean ans;
	testObject obj;
}