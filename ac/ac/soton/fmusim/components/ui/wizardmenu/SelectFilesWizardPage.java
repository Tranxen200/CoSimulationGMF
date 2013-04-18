package ac.soton.fmusim.components.ui.wizardmenu;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SelectFilesWizardPage extends WizardPage
{
	private Text sourceFileField;

	public SelectFilesWizardPage() {
		super("selectFiles");
		setTitle("Select file");
		setDescription(
				"Select the FMU file");
	}

	public void createControl(Composite parent) {
		//Create the layout
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);
		setControl(container);
		setPageComplete(false);
		final Label label = new Label(container, SWT.NONE);
		final GridData gridData = new GridData();
		gridData.horizontalSpan = 3;
		label.setLayoutData(gridData);
		label.setText(
				"Select the FMU file");
		final Label label_1 = new Label(container, SWT.NONE);
		final GridData gridData_1 =
				new GridData(GridData.HORIZONTAL_ALIGN_END);
		label_1.setLayoutData(gridData_1);
		label_1.setText("Source File:");
		sourceFileField = new Text(container, SWT.BORDER);
		sourceFileField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});
		sourceFileField.setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));
		final Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				browseForSourceFile();
			}
		});
		button.setText("Browse...");
		final Label label_2 = new Label(container, SWT.NONE);
		final GridData gridData_2 = new GridData();
		gridData_2.horizontalSpan = 3;
		label_2.setLayoutData(gridData_2);
	}

	//Verify the selected file
	private void updatePageComplete() {
		IPath sourceLoc = getSourceLocation();
		if (sourceLoc == null || !sourceLoc.toFile().exists() || !sourceLoc.toString().endsWith(".fmu")) {
			setMessage(null);
			setErrorMessage("Please select an existing FMU file");
			return;
		}
		setPageComplete(true);
		setMessage(null);
		setErrorMessage(null);
	}

	protected void browseForSourceFile() {
		IPath path = browse(getSourceLocation(), false);
		if (path == null)
			return;
		IPath rootLoc = ResourcesPlugin.getWorkspace()
				.getRoot().getLocation();
		if (rootLoc.isPrefixOf(path))
			path = path
			.setDevice(null)
			.removeFirstSegments(rootLoc.segmentCount());
		sourceFileField.setText(path.toString());
	}

	//Browse for FMU file
	private IPath browse(IPath path, boolean mustExist) {
		FileDialog dialog = new FileDialog(getShell(),
				mustExist ? SWT.OPEN : SWT.SAVE);
		String[] filter = {"*.fmu"};
		dialog.setFilterExtensions(filter);
		if (path != null) {
			if (path.segmentCount() > 1)
				dialog.setFilterPath(path.removeLastSegments(1)
						.toOSString());
			if (path.segmentCount() > 0)
				dialog.setFileName(path.lastSegment());
		}
		String result = dialog.open();
		if (result == null)
			return null;
		return new Path(result);
	}

	//Retrieve FMU file's location
	public IPath getSourceLocation() {
		String text = sourceFileField.getText().trim();
		if (text.length() == 0)
			return null;
		IPath path = new Path(text);
		if (!path.isAbsolute())
			path = ResourcesPlugin.getWorkspace().getRoot().getLocation()
			.append(path);
		return path;
	}


}	
