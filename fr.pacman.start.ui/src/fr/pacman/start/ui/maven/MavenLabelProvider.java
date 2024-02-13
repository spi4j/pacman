package fr.pacman.start.ui.maven;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class MavenLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener p_listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object p_element, String p_property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener p_listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getColumnImage(Object p_element, int p_columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object p_element, int p_columnIndex) {
		String v_result = null;
        switch( p_columnIndex ) {
	        case 0:
	            v_result = ((MavenDepot) p_element).getName();
	            break;
	
	        case 1:
	            v_result = ((MavenDepot) p_element).getUrl();
	            break;
	
	        case 2:
	            v_result = ((MavenDepot) p_element).getLogin();
	            break;
			case 3:
				v_result = ((MavenDepot) p_element).getPasswordHidden();
				break;
	    }
        return v_result;
	}

}
