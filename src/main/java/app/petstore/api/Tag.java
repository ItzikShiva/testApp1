package app.petstore.api;

public class Tag {
	private float id;
	private String name;

	public Tag(float id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public float getId() {
		return id;
	}
	public void setId(float id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
