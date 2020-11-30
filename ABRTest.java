import java.util.*;

public class ABRTest{
	public static void main(String[] args) {
		final Set<String> basicSet = new HashSet<String>();
		basicSet.add("One");
		basicSet.add("Two");
		basicSet.add("One");
		basicSet.add("Three");
		System.out.println(basicSet.toString());
		ABR<String> arbre = new <String>ABR();
		ABR<String> arbreCollection = new <String>ABR(basicSet);
		arbre.toString();
		arbreCollection.toString();
	}
}