package cn.chenlh.bean;
import java.util.List;

public class Entity {

	private String _package;
	private String name;
	private List<String> imports;
	private List<Field> fields;
	
	
	public List<String> getImports() {
		return imports;
	}
	public void setImports(List<String> imports) {
		this.imports = imports;
	}
	public String get_package() {
		return _package;
	}
	public void set_package(String _package) {
		this._package = _package;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	
}

