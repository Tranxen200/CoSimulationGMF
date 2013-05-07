package ac.soton.fmusim.components.ui.wizardmenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.text.TableView.TableRow;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


public class DisplayFMUInfo extends WizardPage
{
	private CheckboxTableViewer checkboxTableViewer;
	private CheckboxTableViewer checkboxTableViewer2;
	private CheckboxTableViewer checkboxTableViewer3;
	private String sourceLocation = "";
	private Composite container;
	private List<FMUVariable[]> checker;

	public DisplayFMUInfo() {
		super("selectStrings");
		setTitle("Extract");
		setDescription("Select the variables to be imported");
	}

	public DisplayFMUInfo(String source) {
		super("selectStrings");
		this.sourceLocation = source;
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

		final Table table = checkboxTableViewer.getTable();
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

		/*
		Listener sortListener = new Listener() {
	        public void handleEvent(Event e) {
	            TableItem[] items = table.getItems();
	            Collator collator = Collator.getInstance(Locale.getDefault());
	            TableColumn column = (TableColumn)e.widget;
	            int index = column == tableColumn ? 0 : 3;
	            for (int i = 1; i < items.length; i++) {
	                String value1 = items[i].getText(index);
	                for (int j = 0; j < i; j++){
	                    String value2 = items[j].getText(index);
	                    if (collator.compare(value1, value2) < 0) {
	                        String[] values = {items[i].getText(0), items[i].getText(1)};
	                        items[i].dispose();
	                        TableItem item = new TableItem(table, SWT.NONE, j);
	                        item.setText(values);
	                        items = table.getItems();
	                        break;
	                    }
	                }
	            }
	            table.setSortColumn(column);
	        }
	    };
	    tableColumn.addListener(SWT.Selection, sortListener);
	    tableColumn_1.addListener(SWT.Selection, sortListener);
	    tableColumn_2.addListener(SWT.Selection, sortListener);
	    tableColumn_3.addListener(SWT.Selection, sortListener);
		 */

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
		table2Column_3.setText("Description");

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
			if(this.sourceLocation == ""){
				String location = ((SelectFilesWizardPage) this.getPreviousPage()).getSourceLocation().toString();
				sourceLocation = location;
			}
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
			
			if(this.checker != null){
				for(int i =0;i<checker.get(0).length;i++){
					for(int j=0;j<checkboxTableViewer.getTable().getItems().length;j++){
						if(checkboxTableViewer.getTable().getItem(j).getText().contains(checker.get(0)[i].getName())){
							checkboxTableViewer.setChecked(checkboxTableViewer.getTable().getItem(j).getData(), true);
						}
					}
				}
				for(int i =0;i<checker.get(1).length;i++){
					for(int j=0;j<checkboxTableViewer2.getTable().getItems().length;j++){
						if(checkboxTableViewer2.getTable().getItem(j).getText().contains(checker.get(1)[i].getName())){
							checkboxTableViewer2.setChecked(checkboxTableViewer2.getTable().getItem(j).getData(), true);
						}
					}
				}
				for(int i =0;i<checker.get(2).length;i++){
					for(int j=0;j<checkboxTableViewer3.getTable().getItems().length;j++){
						if(checkboxTableViewer3.getTable().getItem(j).getText().contains(checker.get(2)[i].getName())){
							checkboxTableViewer3.setChecked(checkboxTableViewer3.getTable().getItem(j).getData(), true);
						}
					}
				}
			}
			
			
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
	
	//Check boxes
	public void setSelection(List<FMUVariable[]> checklist){
		checkboxTableViewer.setCheckedElements(checklist.get(0));
		checkboxTableViewer2.setCheckedElements(checklist.get(1));
		checkboxTableViewer3.setCheckedElements(checklist.get(2));
	}
	
	public void setChecker(List<FMUVariable[]> checklist){
		this.checker = checklist;
	}
	
	
	
}

