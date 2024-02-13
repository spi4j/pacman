package fr.pacman.cinematic.jsp.services;

import org.obeonetwork.dsl.cinematic.CinematicRoot;
import org.obeonetwork.dsl.cinematic.flow.Flow;
import org.obeonetwork.dsl.cinematic.flow.FlowState;
import org.obeonetwork.dsl.cinematic.flow.ViewState;
import org.obeonetwork.dsl.cinematic.view.Layout;
import org.obeonetwork.dsl.cinematic.view.ViewContainer;

import fr.pacman.commons.services.StringUtils;

public class CinematicJspModelServices {

	private static ViewState _header;
	private static ViewState _footer;
	private static ViewState _root;
	private static ViewState _error;
	private static ViewState _referential;

	private static Layout _formLayout;
	private static String _virtualDiv;
	private static String _oldPageName;

	/**
	 * Conserve le temps de la génération les controleurs pour l'en-tete, le
	 * pied-de-page, le referentiel et l'index s'ils existent (sauf pour l'index qui
	 * est obligatoire).
	 * 
	 * @param p_root : le diagramme de cinématique root.
	 */
	public static void init(final CinematicRoot p_root) {

		_header = null;
		_footer = null;
		_oldPageName = "";

		for (Flow v_flow : p_root.getFlows()) {
			for (FlowState v_viewState : v_flow.getStates()) {
				if (v_viewState instanceof ViewState) {
					for (ViewContainer v_viewContainer : ((ViewState) v_viewState).getViewContainers()) {
						if ("HeaderPanel".equalsIgnoreCase(v_viewContainer.getWidget().getName())) {
							_header = (ViewState) v_viewState;
						}
						if ("FooterPanel".equalsIgnoreCase(v_viewContainer.getWidget().getName())) {
							_footer = (ViewState) v_viewState;
						}
						if ("ReferentialPanel".equalsIgnoreCase(v_viewContainer.getWidget().getName())) {
							_referential = (ViewState) v_viewState;
						}
						if ("MainPanel".equalsIgnoreCase(v_viewContainer.getWidget().getName())) {
							_root = (ViewState) v_viewState;
						}
						if ("ErrorPanel".equalsIgnoreCase(v_viewContainer.getWidget().getName())) {
							_error = (ViewState) v_viewState;
						}
					}
					if (_footer != null && _header != null && _referential != null && _root != null && _error != null)
						break;
				}
			}
		}
	}

	public static ViewState get_headerState() {
		return _header;
	}

	public static ViewState get_footerState() {
		return _footer;
	}

	public static ViewState get_referentialState() {
		return _referential;
	}
	
	public static ViewState get_errorState () {
		return _error;
	}

	public static ViewState get_rootState() {
		return _root;
	}

	public static boolean hasReferentialContainer() {
		return _referential != null;
	}

	public static boolean hasHeaderContainer() {
		return _header != null;
	}

	public static boolean hasFooterContainer() {
		return _footer != null;
	}
	
	public static boolean hasErrorContainer() {
		return _error != null;
	}

	public static ViewContainer get_referentialContainer() {

		if (null == _referential)
			return null;

		if (null == _referential.getViewContainers() || _referential.getViewContainers().isEmpty())
			return null;

		return _referential.getViewContainers().get(0);
	}

	public static ViewContainer get_headerContainer() {

		if (null == _header)
			return null;

		if (null == _header.getViewContainers() || _header.getViewContainers().isEmpty())
			return null;

		return _header.getViewContainers().get(0);
	}

	public static ViewContainer get_footerContainer() {

		if (null == _footer)
			return null;

		if (null == _footer.getViewContainers() || _footer.getViewContainers().isEmpty())
			return null;

		return _footer.getViewContainers().get(0);
	}
	
	public static ViewContainer get_errorContainer() {

		if (null == _error)
			return null;

		if (null == _error.getViewContainers() || _error.getViewContainers().isEmpty())
			return null;

		return _error.getViewContainers().get(0);
	}

	public static String set_currentFormLayout(final Layout p_currentFormLayout) {
		_formLayout = p_currentFormLayout;
		return null;
	}

	public static Layout get_currentFormLayout() {
		return _formLayout;
	}

	/**
	 * Simple "compteur" pour l'identifiant unique des divs 'virtuels'. On se
	 * contente à chaque div que l'on trouve, de rajouter dans le nom "_div". La
	 * chane s'allonge ainsi de div en div et on effectue un hash de la chaine.
	 * Ainsi tant que la structure de la pacge n'est pas modifiée, l'identifiant des
	 * divs virtuels de devrait pas changer. A chaque changement de page, on
	 * reinitialise la chaine.
	 * 
	 * @param pageName : Le nom de la page en cours de generation.
	 * @return un id unique pour le div.
	 */
	public static String get_divId(String pageName) {

		if (!pageName.equals(_oldPageName)) {
			_oldPageName = pageName;
			_virtualDiv = "virtualDiv";
		}
		_virtualDiv += "_div";
		System.out.println(_virtualDiv);
		System.out.println(StringUtils.createFixedUserCodeId(_virtualDiv));
		return StringUtils.createFixedUserCodeId(_virtualDiv);
	}
}
