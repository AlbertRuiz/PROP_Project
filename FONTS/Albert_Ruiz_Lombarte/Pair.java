import java.io.Serializable;


public class Pair<F, S> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private F first;
	private S second;
	
	public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
