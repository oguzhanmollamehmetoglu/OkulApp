
public class Bolum {
private int id;
private String ad;
public Bolum(int id, String ad) {
	super();
	this.id = id;
	this.ad = ad;
}
public int getId() {
	return id;
}
public String getAd() {
	return ad;
}
@Override
	public String toString() {
		return this.ad;
	}
}
