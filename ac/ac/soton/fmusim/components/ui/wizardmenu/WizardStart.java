package ac.soton.fmusim.components.ui.wizardmenu;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class WizardStart extends Wizard implements INewWizard{

	private SelectFilesWizardPage selectFilesPage;
	private DisplayFMUInfo infoFMU;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}


	public void addPages(){
		selectFilesPage = new SelectFilesWizardPage();
		addPage(selectFilesPage);
		infoFMU = new DisplayFMUInfo();
		addPage(infoFMU);	
	}

	@Override
	public boolean performFinish() {
		return false;
	}

	public IPath getSourceLocation() {
		return null;
	}
}
