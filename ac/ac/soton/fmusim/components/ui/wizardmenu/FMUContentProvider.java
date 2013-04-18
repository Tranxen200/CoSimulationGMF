package ac.soton.fmusim.components.ui.wizardmenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ptolemy.fmi.FMIModelDescription;
import com.sun.jna.Function;

public class FMUContentProvider {

	private List<FMUVariable> internalList;
	private List<FMUVariable> inputList;
	private List<FMUVariable> outputList;
	private FMIModelDescription modelDesc;

	//Retrieve FMU file's variables
	public void setLists(String FMUPath){
		internalList = new ArrayList<FMUVariable>();
		inputList = new ArrayList<FMUVariable>();
		outputList = new ArrayList<FMUVariable>();

		try {
			FMU fmuFile = new FMU(FMUPath);
			String causality = "";
			String description = "";
			String variability = "";
			Function type;
			String typestr = "";
			modelDesc = fmuFile.getModelDescription();
			for(int i=0;i<modelDesc.modelVariables.size();i++){
				if(modelDesc.modelVariables.get(i).causality != null ){
					causality = modelDesc.modelVariables.get(i).causality.toString();
				}
				if(modelDesc.modelVariables.get(i).description != null ){
					description = modelDesc.modelVariables.get(i).description.toString();
				}
				if(modelDesc.modelVariables.get(i).variability != null ){
					variability = modelDesc.modelVariables.get(i).variability.toString();
				}
				if(modelDesc.modelVariables.get(i).type != null ){
					type = modelDesc.modelVariables.get(i).fmiGetFunction;
					if(type.getName().endsWith("Real")){typestr = "Real";}
					else if(type.getName().endsWith("Integer")){typestr = "Integer";}
					else if(type.getName().endsWith("Boolean")){typestr = "Boolean";}
					else if(type.getName().endsWith("String")){typestr = "String";}
				}				
				switch (causality) {
				case "input":
					inputList.add(new FMUVariable(modelDesc.modelVariables.get(i).name.toString(),
							typestr,
							variability,
							description));
					break;
				case "output":
					outputList.add(new FMUVariable(modelDesc.modelVariables.get(i).name.toString(),
							typestr,
							variability,
							description));
					break;
				default:
					internalList.add(new FMUVariable(modelDesc.modelVariables.get(i).name.toString(),
							typestr,
							variability,
							description));
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<FMUVariable> getInputs(){
		return this.inputList;
	}

	public List<FMUVariable> getOutputs(){
		return this.outputList;
	}

	public List<FMUVariable> getInternals(){
		return this.internalList;
	}
}
