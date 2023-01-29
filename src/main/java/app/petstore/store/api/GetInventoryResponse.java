package app.petstore.store.api;

public class GetInventoryResponse {
	
	 private int sold;
	 private int placed;
	 private int string;
	 private int RUNNING;
	 private int pending;
	 private int available;
	 private int JustBorn;
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public int getPlaced() {
		return placed;
	}
	public void setPlaced(int placed) {
		this.placed = placed;
	}
	public int getString() {
		return string;
	}
	public void setString(int string) {
		this.string = string;
	}
	public int getRUNNING() {
		return RUNNING;
	}
	public void setRUNNING(int rUNNING) {
		RUNNING = rUNNING;
	}
	public int getPending() {
		return pending;
	}
	public void setPending(int pending) {
		this.pending = pending;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getJustBorn() {
		return JustBorn;
	}
	public void setJustBorn(int justBorn) {
		JustBorn = justBorn;
	}
	 

}

