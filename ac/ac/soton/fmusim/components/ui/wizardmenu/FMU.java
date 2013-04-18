package ac.soton.fmusim.components.ui.wizardmenu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.ptolemy.fmi.FMIModelDescription;
import org.ptolemy.fmi.FMIScalarVariable;
import org.ptolemy.fmi.FMUFile;


public class FMU {

	private final FMIModelDescription modelDescription;

	public final Map<String, FMIScalarVariable> variables = new HashMap<String, FMIScalarVariable>();

	public FMU(String fmuFileName) throws IOException {
		modelDescription = FMUFile.parseFMUFile(fmuFileName);
		for (FMIScalarVariable fmiScalarVariable : modelDescription.modelVariables) {
			variables.put(fmiScalarVariable.name, fmiScalarVariable);
		}
	}

	public FMIModelDescription getModelDescription() {
		return modelDescription;
	}

	public void checkVariables()
	{
		FMIModelDescription model1 = getModelDescription();
		for(int i=0;i<model1.modelVariables.size();i++){
			System.out.println(model1.modelVariables.get(i).name);
			System.out.println(model1.modelVariables.get(i).causality);
		}
	}
}
