package fr.pacman.start.ui.maven;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * New shell used to add Maven repository.
 * @author r.le-baro
 *
 */
public class MavenAddShell {
    private Shell _shell = null;
    private TableViewer _viewer;
    private String _name = "";
	private String _url = "";
	private String _login = "";
	private String _password = "";
	private Button _confirmButton;
	public MavenAddShell(final Display p_display, TableViewer p_viewer) {
		final Shell v_parent = p_display.getActiveShell();
		this._viewer = p_viewer;
		_shell = new Shell(v_parent, SWT.CLOSE); // Subclassing not allowed, so can't extends from shell. 
		_shell.setLayout(new GridLayout(2, false));
		_shell.setSize(400, 400);
		
		final Rectangle v_bounds = v_parent.getBounds();
		final Rectangle v_rect = _shell.getBounds();
		
		int v_x = v_bounds.x + (v_bounds.width - v_rect.width) / 2;
	    int v_y = v_bounds.y + (v_bounds.height - v_rect.height) / 2;
		_shell.setLocation(v_x, v_y);
		addNameInput();
		addUrlInput();
		addLoginInput();
		addPasswordInput();
		addControl();
		
		_shell.addListener(SWT.Close, new Listener()
        {
           @Override
           public void handleEvent (final Event p_event)
           {
              System.out.println("Child Shell handling Close event, about to dispose this Shell");
              _shell.dispose();
           }
        });
		_shell.open();
	}
	
	private void addNameInput () {
		Label v_label = new Label(_shell, SWT.NONE);
		v_label.setText("Nom du dépot");

		final Text v_element = new Text(_shell, SWT.BORDER | SWT.SINGLE);
		GridData v_gd = new GridData(GridData.FILL_HORIZONTAL);
		v_element.setLayoutData(v_gd);
		v_element.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent p_e) {
				_name = v_element.getText().toString();
				computeValidity();
			}

			@Override
			public void keyPressed(KeyEvent p_e) {

			}
		});
	}
	
	private void addUrlInput() {
		Label v_label = new Label(_shell, SWT.NONE);
		v_label.setText("Urlt");

		final Text v_element = new Text(_shell, SWT.BORDER | SWT.SINGLE);
		GridData v_gd = new GridData(GridData.FILL_HORIZONTAL);
		v_element.setLayoutData(v_gd);
		v_element.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent p_e) {
				_url = v_element.getText().toString();
				computeValidity();
			}

			@Override
			public void keyPressed(KeyEvent p_e) {

			}
		});
	}
	
	private void addLoginInput() {
		Label v_label = new Label(_shell, SWT.NONE);
		v_label.setText("Login du dépot");

		final Text v_element = new Text(_shell, SWT.BORDER | SWT.SINGLE);
		GridData v_gd = new GridData(GridData.FILL_HORIZONTAL);
		v_element.setLayoutData(v_gd);
		v_element.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent p_e) {
				_login = v_element.getText().toString();
			}

			@Override
			public void keyPressed(KeyEvent p_e) {

			}
		});
	}
	
	private void addPasswordInput() {
		Label v_label = new Label(_shell, SWT.NONE | SWT.PASSWORD);
		v_label.setText("Password du dépot");

		final Text v_element = new Text(_shell, SWT.BORDER | SWT.SINGLE);
		GridData v_gd = new GridData(GridData.FILL_HORIZONTAL);
		v_element.setLayoutData(v_gd);
		v_element.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent p_e) {
				_password = v_element.getText().toString();
			}

			@Override
			public void keyPressed(KeyEvent p_e) {

			}
		});
	}
	
	private void addControl() {
		addCancelButton();
		addConfirmButton();
	}
	
	private void addCancelButton() {
		Button v_confirmButton = new Button(_shell, SWT.PUSH);
		v_confirmButton.setText("Annuler");
		v_confirmButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent p_e)
			{ 
				_shell.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent p_e)
			{
				widgetSelected(p_e);
			} 
		});
	}
	
	private void addConfirmButton() {
		_confirmButton = new Button(_shell, SWT.PUSH);
		_confirmButton.setText("Confirmer");
		_confirmButton.setEnabled(false);
		_confirmButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent p_e)
			{
				_viewer.add(new MavenDepot(_name, _url, _login, _password));
				_shell.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent p_e)
			{
				widgetSelected(p_e);
			} 
		});
	}

	private void computeValidity() {
		if(!_name.isEmpty() && !_url.isEmpty()) {
			_confirmButton.setEnabled(true);
		} else {
			_confirmButton.setEnabled(false);
		}
		
	}
}
