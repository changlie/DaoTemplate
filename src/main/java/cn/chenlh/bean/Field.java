package cn.chenlh.bean;

public class Field {
	private String name;
	private String NAME;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Field [name=" + name + ", NAME=" + NAME + ", type=" + type + "]";
	}
	
}
