public class ABRTest extends ABR {
	public static void main(String[] args) {

		ABR<Integer> arbre = new ABR();
		arbre.add(23);
		arbre.add(18);
		arbre.add(42);
		arbre.add(60);
		arbre.add(38);
		arbre.add(12);
		arbre.add(2);
		System.out.println(arbre);
		arbre.remove(2);
		System.out.println(arbre);
		arbre.remove(42);
		System.out.println(arbre);
		arbre.remove(23);
		System.out.println(arbre);

	}
}