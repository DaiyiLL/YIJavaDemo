import com.dsy.set.ListSet;
import com.dsy.set.Set;
import com.dsy.set.Set.Visitor;
import com.dsy.set.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Set<Integer> set = new ListSet<Integer>();
		Set<Integer> set = new TreeSet<Integer>();
		set.add(10);
		set.add(12);
		set.add(11);
		set.add(11);
		set.add(10);
		set.add(10);
		set.traversal(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
	}

}
