package fr.pacman.start.ui.maven;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MavenTable {

	public MavenTable(Composite p_container) {
		this._container = p_container;
	}

	TableItem _selectedItem;
	private Composite _container;
	private Table _table;
	private TableViewer _viewer;
	private int _selectedItemIndex;
	private Button _deleteButton;
	private Button _addButton;

	public void addTable() {
		GridData v_gd =  new GridData(GridData.FILL, GridData.CENTER, true, false);
		v_gd.horizontalSpan = 2;
		Label v_label = new Label(this._container, SWT.NONE);
		v_label.setText("Dépot maven : ");
		v_label.setLayoutData(v_gd);
		_viewer = new TableViewer(this._container, SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION);
		GridData v_gd2 =  new GridData(GridData.FILL, GridData.FILL, true, true);
		v_gd2.horizontalSpan = 2;
		_viewer.getControl().setLayoutData(v_gd2);

		String[] v_columns = new String[] {"Nom", "Url", "Login", "Mot de passe"};
		for( String v_element : v_columns ) {
			TableColumn v_col = new TableColumn( _viewer.getTable(), SWT.CENTER );
			v_col.setText( v_element);
		}

		_table = _viewer.getTable();
		_table.setLayoutData(v_gd);
		_table.setHeaderVisible(true);
		_table.setLinesVisible(true);
		GridData v_gdTable =  new GridData(GridData.FILL, GridData.FILL, true, true);
		v_gdTable.horizontalSpan = 2;
		v_gdTable.minimumHeight = 200;
		_table.setLayoutData(v_gdTable);

		_table.addSelectionListener(new SelectionListener() {


			@Override
			public void widgetSelected(SelectionEvent p_e) {
				Table v_table = (Table) p_e.getSource();
				_selectedItemIndex = v_table.getSelectionIndex();
				_selectedItem = v_table.getItem(_selectedItemIndex);
				_deleteButton.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent p_e) {
				widgetSelected(p_e);
			}
		});



		// Define the layout of the table
		TableLayout v_tlayout = new TableLayout();
		v_tlayout.addColumnData( new ColumnWeightData( 20, 80, true ));
		v_tlayout.addColumnData( new ColumnWeightData( 40, 150, true ));
		v_tlayout.addColumnData( new ColumnWeightData( 20, 80, true ));
		v_tlayout.addColumnData( new ColumnWeightData( 20, 80, true ));
		_viewer.getTable().setLayout( v_tlayout );

		_viewer.setContentProvider(ArrayContentProvider.getInstance());


		_viewer.setLabelProvider(new MavenLabelProvider());
		MavenDepot[] v_depots = new MavenDepot[1];
		v_depots[0] = new MavenDepot("Safran", "https://safran-forge-cdn.intradef.gouv.fr/nexus-default/nexus/content/repositories/central/", "", "");
		_viewer.setInput(v_depots);
		_viewer.refresh();
		addControls();
	}

	/**
	 * Ajoute les boutons de contrôle ajout et suppression à la table.
	 */
	private void addControls() {
		addDeleteButton();
		addAddButton();
	}

	private void addDeleteButton() {
		_deleteButton = new Button(this._container, SWT.PUSH);
		_deleteButton.setText("Supprimer dépot");
		_deleteButton.setEnabled(false);
		_deleteButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent p_e) {
				getMavenrepositories();
				_viewer.remove(_viewer.getElementAt(_selectedItemIndex));
			}

			@Override
			public void mouseDown(MouseEvent p_e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDoubleClick(MouseEvent p_e) {
				mouseUp(p_e);
			}
		});
	}

	private void addAddButton() {
		_addButton = new Button(this._container, SWT.PUSH);
		_addButton.setText("Ajouter dépot");
		_addButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent p_e)
			{ 
				new MavenAddShell(_container.getDisplay(), _viewer);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent p_e)
			{
				widgetSelected(p_e);
			} 
		});
	}
	
	public List<MavenDepot> getMavenrepositories() {
		List<MavenDepot> v_mavenRepos = new ArrayList<MavenDepot>();
		for (TableItem v_item :  _viewer.getTable().getItems()) {
			v_mavenRepos.add((MavenDepot)v_item.getData());
		};
		return v_mavenRepos;
	}
}
