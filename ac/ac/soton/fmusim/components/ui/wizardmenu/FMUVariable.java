package ac.soton.fmusim.components.ui.wizardmenu;

class FMUVariable {

	private String VarName;
	private String VarDesc;
	private String VarType;
	private String VarVariability;

	public FMUVariable(){
	}

	public FMUVariable(String name){
		this.VarName = name;
	}

	public FMUVariable(String name, String type, String variability, String desc){
		this.VarName = name;
		this.VarDesc = desc;
		this.VarType = type;
		this.VarVariability = variability;
	}

	public String getName(){
		return this.VarName;
	}

	public String getType(){
		return this.VarType;
	}

	public String getVariability(){
		return this.VarVariability;
	}

	public String getDesc(){
		return this.VarDesc;
	}

	public void setName(String name){
		this.VarName = name;
	}

	public void setType(String type){
		this.VarType = type;
	}

	public void setDescription(String description){
		this.VarDesc = description;
	}

	public void setVariability(String variability){
		this.VarVariability = variability;
	}

}
