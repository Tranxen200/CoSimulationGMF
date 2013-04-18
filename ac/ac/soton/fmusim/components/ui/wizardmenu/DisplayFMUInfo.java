package ac.soton.fmusim.components.ui.wizardmenu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


public class DisplayFMUInfo extends WizardPage
{
	private CheckboxTableViewer checkboxTableViewer;
	private CheckboxTableViewer checkboxTableViewer2;
	private CheckboxTableViewer checkboxTableViewer3;
	private IPath sourceLocation;
	private Composite container;

	public DisplayFMUInfo() {
		super("selectStrings");
		setTitle("Extract");
		setDescription("Select the variables to be imported");
	}

	public void createControl(Composite parent) {
		//Create the layout
		this.container = new Composite(parent, SWT.NULL);
		TableWrapLayout containerwrap = new TableWrapLayout();
		container.setLayout(containerwrap);
		setControl(container);

		//Get Checkbox lists 
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Internals");
		checkboxTableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER);
		checkboxTableViewer.setContentProvider(new ArrayContentProvider());
		checkboxTableViewer.setLabelProvider(new FMULabelProvider());
		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Inputs");
		checkboxTableViewer2 = CheckboxTableViewer.newCheckList(container, SWT.BORDER);
		checkboxTableViewer2.setContentProvider(new ArrayContentProvider());
		checkboxTableViewer2.setLabelProvider(new FMULabelProvider());
		Label label3 = new Label(container, SWT.NONE);
		label3.setText("Outputs");
		checkboxTableViewer3 = CheckboxTableViewer.newCheckList(container, SWT.BORDER);
		checkboxTableViewer3.setContentProvider(new ArrayContentProvider());
		checkboxTableViewer3.setLabelProvider(new FMULabelProvider());

		Table table = checkboxTableViewer.getTable();
		Table table2 = checkboxTableViewer2.getTable();
		Table table3 = checkboxTableViewer3.getTable();
		table.setHeaderVisible(true);
		table2.setHeaderVisible(true);
		table3.setHeaderVisible(true);

		//Setting internal variables' table columns
		final TableColumn tableColumn =
				new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(150);
		tableColumn.setText("Name");
		final TableColumn tableColumn_1 =
				new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("Type");
		final TableColumn tableColumn_2 =
				new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(150);
		tableColumn_2.setText("Variability");
		final TableColumn tableColumn_3 =
				new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(350);
		tableColumn_3.setText("Description");

		//Setting inports variables' table columns
		final TableColumn table2Column =
				new TableColumn(table2, SWT.NONE);
		table2Column.setWidth(150);
		table2Column.setText("Name");
		final TableColumn table2Column_1 =
				new TableColumn(table2, SWT.NONE);
		table2Column_1.setWidth(150);
		table2Column_1.setText("Type");
		final TableColumn table2Column_2 =
				new TableColumn(table2, SWT.NONE);
		table2Column_2.setWidth(150);
		table2Column_2.setText("Variability");
		final TableColumn table2Column_3 =
				new TableColumn(table2, SWT.NONE);
		table2Column_3.setWidth(350);
		table2Column_3.setText("Desciption");

		//Setting outports variables' table columns
		final TableColumn table3Column =
				new TableColumn(table3, SWT.NONE);
		table3Column.setWidth(150);
		table3Column.setText("Name");
		final TableColumn table3Column_1 =
				new TableColumn(table3, SWT.NONE);
		table3Column_1.setWidth(150);
		table3Column_1.setText("Type");
		final TableColumn table3Column_2 =
				new TableColumn(table3, SWT.NONE);
		table3Column_2.setWidth(150);
		table3Column_2.setText("Variability");
		final TableColumn table3Column_3 =
				new TableColumn(table3, SWT.NONE);
		table3Column_3.setWidth(350);
		table3Column_3.setText("Description");
	}

	//Displays lists
	public void setVisible(boolean visible) {
		if (visible){
			IPath location = ((SelectFilesWizardPage) this.getPreviousPage()).getSourceLocation();
			sourceLocation = location;
			FMUContentProvider fmuCP = new FMUContentProvider();
			fmuCP.setLists(sourceLocation.toString());
			checkboxTableViewer.setInput(fmuCP.getInternals());
			checkboxTableViewer2.setInput(fmuCP.getInputs());
			checkboxTableViewer3.setInput(fmuCP.getOutputs());		
			TableWrapData data1 = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
			data1.maxHeight = 220;
			TableWrapData data2 = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
			data2.maxHeight = 220;
			TableWrapData data3 = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
			data3.maxHeight = 220;
			checkboxTableViewer.getTable().setLayoutData(data1);
			checkboxTableViewer2.getTable().setLayoutData(data2);
			checkboxTableViewer3.getTable().setLayoutData(data3);
			this.getShell().setSize(900, 700);
			container.layout();
		}
		super.setVisible(visible);
	}

	//Retrieve checked boxes
	public List<FMUVariable[]> getSelection() {
		Object[] checked = checkboxTableViewer.getCheckedElements();
		Object[] checked2 = checkboxTableViewer2.getCheckedElements();
		Object[] checked3 = checkboxTableViewer3.getCheckedElements();
		int count = checked.length;
		int count2 = checked2.length;
		int count3 = checked3.length;
		FMUVariable[] extracted = new FMUVariable[count];
		System.arraycopy(checked, 0, extracted, 0, count);
		FMUVariable[] extracted2 = new FMUVariable[count2];
		System.arraycopy(checked2, 0, extracted2, 0, count2);
		FMUVariable[] extracted3 = new FMUVariable[count3];
		System.arraycopy(checked3, 0, extracted3, 0, count3);
		List<FMUVariable[]> checkedLists = new ArrayList<FMUVariable[]>();
		checkedLists.add(extracted);
		checkedLists.add(extracted2);
		checkedLists.add(extracted3);
		return checkedLists;
	}

}

