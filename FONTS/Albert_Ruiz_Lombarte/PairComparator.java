
import java.util.Comparator;

public class PairComparator implements Comparator<Pair<String, Double> > {
	
	public int compare(Pair<String, Double> a, Pair<String, Double> b) {
		if (a.getSecond() < b.getSecond()) {
			return 1;
		}
		if (a.getSecond() > b.getSecond()) {
			return -1;
		}
		return 0;
	}
}
