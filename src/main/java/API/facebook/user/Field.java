package API.facebook.user;

public class Field {
	private String name;
	private String description;
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name: "+ name+ " description: "+description+" type: "+type;

//		return super.toString();
	}
	
	
	
}
