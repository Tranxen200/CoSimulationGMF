package ac.soton.fmusim.components.ui.wizardmenu;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

class FMULabelProvider implements ITableLabelProvider {
	
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	
	//Set up table content
	public String getColumnText(Object element, int columnIndex) {
		FMUVariable fmuVar = (FMUVariable) element;
		switch (columnIndex) {
		case 0:
			return fmuVar.getName();
		case 1:
			return fmuVar.getType();
		case 2:
			return fmuVar.getVariability();
		case 3:
			return fmuVar.getDesc();
		}
		return "";
	}

	public void dispose() {
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
	}
}
