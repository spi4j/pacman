package fr.pacman.cinematic.api.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.cinematic.CinematicRoot;
import org.obeonetwork.dsl.cinematic.Event;
import org.obeonetwork.dsl.cinematic.NamedElement;
import org.obeonetwork.dsl.cinematic.flow.ActionState;
import org.obeonetwork.dsl.cinematic.flow.Flow;
import org.obeonetwork.dsl.cinematic.flow.FlowAction;
import org.obeonetwork.dsl.cinematic.flow.FlowEvent;
import org.obeonetwork.dsl.cinematic.flow.FlowState;
import org.obeonetwork.dsl.cinematic.flow.InitialState;
import org.obeonetwork.dsl.cinematic.flow.SubflowState;
import org.obeonetwork.dsl.cinematic.flow.Transition;
import org.obeonetwork.dsl.cinematic.flow.ViewState;
import org.obeonetwork.dsl.cinematic.view.AbstractViewElement;
import org.obeonetwork.dsl.cinematic.view.ViewContainer;
import org.obeonetwork.dsl.cinematic.view.ViewEvent;
import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.entity.impl.EntityImpl;
import org.obeonetwork.dsl.environment.Action;
import org.obeonetwork.dsl.environment.BindingInfo;
import org.obeonetwork.dsl.environment.BindingRegistry;
import org.obeonetwork.dsl.environment.BoundableElement;
import org.obeonetwork.dsl.soa.Operation;

/**
 * Services Java pour requêter sur le métamodèle Cinematic.
 * 
 * @author MINARM
 */
public final class CinematicModelServices {

	/**
	 * Constructeur privé pour classe utilitaire.
	 */
	private CinematicModelServices() {
		// RAS
	}

	/**
	 * Retourne l'initial d'un flow.
	 * 
	 * @param p_flow le flow dont on cherche l'état initial
	 * @return l'état initial trouvé ou lance une {@link IllegalStateException} si
	 *         le flow ne contient pas d'état initial
	 */
	// [query public getInitialState(flow : Flow) : InitialState =
	// flow.states->filter(InitialState)->first()
	// /]
	public static InitialState getInitialState(final Flow p_flow) {
		for (final FlowState v_state : p_flow.getStates()) {
			if (v_state instanceof InitialState) {
				return (InitialState) v_state;
			}
		}
		throw new IllegalStateException("Aucun état initial trouvé pour le flow " + p_flow.getName());
	}

	/**
	 * Retourne les ViewStates débutant un Flow, sans passer par une transition avec
	 * garde ou liée à un événement.
	 * 
	 * @param p_flow le flow
	 * @return les ViewStates débutant un Flow, sans passer par une transition avec
	 *         garde ou liée à un événement
	 */
	// [query public getFirstViewStates(flow : Flow) : Sequence(ViewState) =
	// if
	// (flow.getInitialState().getAllFollowingViewStatesWithoutGardOrEvent(false)
	// <> null) then
	// flow.getInitialState().getAllFollowingViewStatesWithoutGardOrEvent(false)->filter(ViewState)
	// else
	// Sequence{}
	// endif
	// /]
	public static List<ViewState> getFirstViewStates(final Flow p_flow) {
		final InitialState v_initialState = getInitialState(p_flow);
		final List<ViewState> v_followingViewStates = getAllFollowingViewStatesWithoutGardOrEvent(v_initialState,
				false);
		if (v_followingViewStates != null) {
			return v_followingViewStates;
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Cherche les états suivants un état source
	 * 
	 * @param p_state          l'état source des états recherchés
	 * @param p_followSubFlows booléen indiquant si l'on doit suivre les sous flows
	 *                         ou non
	 * @return les états suivant directement un état
	 */
	// [query public getNextStates(state : FlowState, followSubFlows : Boolean) :
	// Sequence(FlowState) =
	// state.getTransitionsFromState(followSubFlows).to->asSequence()
	// /]
	/*
	 * private static List<FlowState> getNextStates (final FlowState p_state, final
	 * boolean p_followSubFlows) { final List<FlowState> v_states = new
	 * ArrayList<FlowState>(); for (final Transition v_transition :
	 * getTransitionsFromState(p_state, p_followSubFlows)) { if
	 * (v_transition.getTo() != null) { v_states.add(v_transition.getTo()); } }
	 * return v_states; }
	 */

	/**
	 * Cherche les états suivants un état source, en suivant des transitions sans
	 * garde et sans événement spécifié, en remplacant les subflowstates par l'état
	 * initial du flow sur lequel ils pointent
	 * 
	 * @param p_state          l'état source des états recherchés
	 * @param p_followSubFlows booléen indiquant si l'on doit suivre les sous flows
	 *                         ou non
	 * @return les états suivants un état source, en suivant des transitions sans
	 *         garde et sans événement spécifié, en remplacant les subflowstates par
	 *         l'état initial du flow sur lequel ils pointent
	 */
	// [query public getNextStatesWithoutGardOrEvent(state : FlowState,
	// followSubFlows : Boolean) : Sequence(FlowState) =
	// state.getTransitionsFromStateWithoutGardOrEvent(followSubFlows)->select(transition
	// : Transition | transition.to <> null)->collect(transition : Transition |
	// transition.to)->asSequence()
	// /]
	private static List<FlowState> getNextStatesWithoutGardOrEvent(final FlowState p_state,
			final boolean p_followSubFlows) {
		final List<FlowState> v_states = new ArrayList<FlowState>();
		for (final Transition v_transition : getTransitionsFromStateWithoutGardOrEvent(p_state, p_followSubFlows)) {
			if (v_transition.getTo() != null) {
				v_states.add(v_transition.getTo());
			}
		}
		return v_states;
	}

	/**
	 * Cherche les états suivants un état source RECURSIVEMENT, en suivant des
	 * transitions sans garde et sans événement spécifié
	 * 
	 * @param p_state          l'état source des états recherchés
	 * @param p_followSubFlows booléen indiquant si l'on doit suivre les sous flows
	 *                         ou non
	 * @return les états suivants un état source RECURSIVEMENT, en suivant des
	 *         transitions sans garde et sans événement spécifié
	 */
	// [query public getAllFollowingStatesWithoutGardOrEvent(state : FlowState,
	// followSubFlows : Boolean) : Sequence(FlowState) =
	// state.getNextStatesWithoutGardOrEvent(followSubFlows)->union(state.getNextStatesWithoutGardOrEvent(followSubFlows)->collect(nextState
	// : FlowState |
	// nextState.getAllFollowingStatesWithoutGardOrEvent(followSubFlows)))
	// /]
	public static List<FlowState> getAllFollowingStatesWithoutGardOrEvent(final FlowState p_state,
			final boolean p_followSubFlows) {
		final List<FlowState> v_states = new ArrayList<FlowState>();
		v_states.addAll(getNextStatesWithoutGardOrEvent(p_state, p_followSubFlows));
		for (FlowState v_nextState : getNextStatesWithoutGardOrEvent(p_state, p_followSubFlows)) {
			v_states.addAll(getAllFollowingStatesWithoutGardOrEvent(v_nextState, p_followSubFlows));
		}
		final Iterator<FlowState> v_iterator = v_states.iterator();
		while (v_iterator.hasNext()) {
			if (v_iterator.next() == null) {
				v_iterator.remove();
			}
		}
		return v_states;
	}

	/**
	 * Cherche les états suivants un état source RECURSIVEMENT, de type ViewState,
	 * en suivant des transitions sans garde et sans événement spécifié
	 * 
	 * @param p_state          l'état source des états recherchés
	 * @param p_followSubFlows booléen indiquant si l'on doit suivre les sous flows
	 *                         ou non
	 * @return es états suivants un état source RECURSIVEMENT, de type ViewState, en
	 *         suivant des transitions sans garde et sans événement spécifié
	 */
	// [query public getAllFollowingViewStatesWithoutGardOrEvent(state :
	// FlowState, followSubFlows : Boolean) : Sequence(FlowState) =
	// state.getNextStatesWithoutGardOrEvent(followSubFlows)->union(state.getNextStatesWithoutGardOrEvent(followSubFlows)->select(nextState
	// : FlowState | not nextState.oclIsKindOf(ViewState))->collect(nextState :
	// FlowState |
	// nextState.getAllFollowingViewStatesWithoutGardOrEvent(followSubFlows)))
	// /]
	private static List<ViewState> getAllFollowingViewStatesWithoutGardOrEvent(final FlowState p_state,
			final boolean p_followSubFlows) {
		final List<FlowState> v_flowStates = new ArrayList<FlowState>();
		v_flowStates.addAll(getNextStatesWithoutGardOrEvent(p_state, p_followSubFlows));
		for (FlowState v_nextState : getNextStatesWithoutGardOrEvent(p_state, p_followSubFlows)) {
			if (!(v_nextState instanceof ViewState)) {
				v_flowStates.addAll(getAllFollowingViewStatesWithoutGardOrEvent(v_nextState, p_followSubFlows));
			}
		}
		final List<ViewState> v_viewStates = new ArrayList<ViewState>();
		for (FlowState v_flowState : v_flowStates) {
			if (v_flowState instanceof ViewState) {
				v_viewStates.add((ViewState) v_flowState);
			}
		}
		return v_viewStates;
	}

	/**
	 * Cherche les transitions à partir d'un état source. Si l'état source est un
	 * SubflowState, on peut chercher à partir de l'état initial du Flow pointé si
	 * followSubFlows est positionné à true On considère qu'il n'y a pas de
	 * transitions après un SubFlowState. A tort ? Cas d'exemple ?
	 * 
	 * @param p_state          l'état source des états recherchés
	 * @param p_followSubFlows booléen indiquant si l'on doit suivre les sous flows
	 *                         ou non
	 * @return les transitions à partir d'un état source. Si l'état source est un
	 *         SubflowState, on peut chercher à partir de l'état initial du Flow
	 *         pointé si followSubFlows est positionné à true On considère qu'il n'y
	 *         a pas de transitions après un SubFlowState. A tort ? Cas d'exemple ?
	 */
	// [query public getTransitionsFromState(state : FlowState, followSubFlows :
	// Boolean) : Set(Transition) =
	// if (state.oclIsKindOf(SubflowState)) then
	// if (followSubFlows) then
	// state.oclAsType(SubflowState).subflow.transitions->select(transition :
	// Transition | transition.from =
	// state.oclAsType(SubflowState).subflow.getInitialState())
	// else
	// Set{}
	// endif
	// else
	// if (state <> null and state.eContainer(Flow) <> null and
	// state.eContainer(Flow).transitions <> null) then
	// state.eContainer(Flow).transitions->select(transition : Transition |
	// transition.from = state)
	// else
	// Set{}
	// endif
	// endif
	// /]
	public static Set<Transition> getTransitionsFromState(final FlowState p_state, final boolean p_followSubFlows) {
		final Set<Transition> v_transitions = new HashSet<Transition>();
		if (p_state instanceof SubflowState) {
			if (p_followSubFlows) {
				for (Transition v_transition : ((SubflowState) p_state).getSubflow().getTransitions()) {
					if (v_transition.getFrom() != null
							&& v_transition.getFrom().equals(getInitialState(((SubflowState) p_state).getSubflow()))) {
						v_transitions.add(v_transition);
					}
				}
			}
		} else {
			if (p_state != null && getParent(p_state, Flow.class) != null
					&& getParent(p_state, Flow.class).getTransitions() != null) {
				for (Transition v_transition : getParent(p_state, Flow.class).getTransitions()) {
					if (p_state.equals(v_transition.getFrom())) {
						v_transitions.add(v_transition);
					}
				}
			}
		}
		return v_transitions;
	}

	/**
	 * Recupere parmi l'ensemble des transitions attachees au controleur initial le
	 * controleur cible pour la transition concernees par l'evenement. Par contre on
	 * verifie bien que la cible n'est pas identique a l'appelant (cas des
	 * transitionss recursives). Cette methode est utilisee uniquement pour la
	 * cinematique jsp.
	 * 
	 * @param p_state l'état source des états recherchés
	 * @return le controleur sur lequel porte la transition concernee par
	 *         l'evenement ou null si aucune transition.
	 */
	public static ViewState getNextViewStateFromEvent(final ViewState p_state, final ViewEvent p_event) {
		for (Transition p_transition : getTransitionsFromState(p_state, false)) {
			for (Event v_event : p_transition.getOn()) {
				if (v_event.getName().equalsIgnoreCase(p_event.getName())) {
					// Cas des transitions vers les etats.
					if (p_transition.getTo() instanceof ViewState
							&& !p_state.getTechnicalid().equals(p_transition.getTo().getTechnicalid())) {
						return (ViewState) p_transition.getTo();
					}
					// Cas des debranchements vers un subflow.
					if (p_transition.getTo() instanceof SubflowState) {
						return getFirstViewState(((SubflowState) p_transition.getTo()).getSubflow(), p_event);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Recuperation d'un seul viewState a partir d'un point d'entree, de la
	 * transition et d'un evenement attache. Cette methode est utilisee uniquement
	 * pour la cinematique jsp.
	 * 
	 * @param p_flow
	 * @return le controleur sur lequel porte la transition concernee par
	 *         l'evenement ou null si aucune transition (ne pas jeter une exception
	 *         plutot ?).
	 */
	public static ViewState getFirstViewState(final Flow p_flow, final ViewEvent p_event) {
		final InitialState v_initialState = getInitialState(p_flow);
		for (Transition v_transition : getTransitionsFromState((FlowState) v_initialState, false)) {
			for (Event v_event : v_transition.getOn()) {
				if (v_event.getName().equalsIgnoreCase(p_event.getName()) && v_transition.getTo() instanceof ViewState)
					return (ViewState) v_transition.getTo();
			}
		}
		return null;
	}

	/**
	 * Cette methode est uniquement utilisee pour la cinematique JSP !!
	 * 
	 * Recupere la liste des évenements pour un controleur (a partir de l'unique vue
	 * associee au controleur). Attention ici, le parti pris est de recupererer
	 * uniquement les evenements qui sont attaches aux widgets, donc on ne recupere
	 * pas les evenements du flowEvent !
	 * 
	 * Encore une fois l'optique qui a été prise est d'essayer de faire du MVC2 dans
	 * la mesure du possible. Même si pour l'instant il n'est pas possible d'avoir
	 * plus d'une vue pour un controleur, il est cependant possible d'avoir un
	 * nombre n de boutons qui envoient vers la meme vue.. Si on s'etait base sur le
	 * parcours des transitions (comme cela semble le plus logique et comme cela a
	 * ete fait dans les autres cinematiques), il faudrait alors definir de
	 * nombreuses transitions recursives ce qui aurait complexifie la modelisation
	 * (d'autant plus que les transitions recursives sont assez difficiles a
	 * positionner sur le modele).
	 * 
	 * On prend donc uniquement les evenements des viewElements (hors conteneurs !)
	 * et seulement apres, on regarde si ces evenements sont attaches a des
	 * transitions...
	 * 
	 * @param p_viewContainer la vue associee au controleur.
	 * @return la liste des evenements pour le controleur.
	 */
	/**
	 * @param p_viewContainer
	 * @return
	 */
	public static List<ViewEvent> getEventsForViewContainer(final ViewContainer p_viewContainer) {
		List<ViewEvent> v_events = new ArrayList<>();
		for (AbstractViewElement v_element : getViewElementsForViewContainer(p_viewContainer)) {
			for (ViewEvent v_viewEvent : v_element.getEvents()) {
				v_events.add(v_viewEvent);
			}
		}
		return v_events;
	}

	/**
	 * 
	 * @param p_viewContainer
	 * @param p_elements
	 * @return
	 */
	public static List<AbstractViewElement> getViewElementsForViewContainer(final ViewContainer p_viewContainer) {
		List<AbstractViewElement> v_elements = new ArrayList<>();
		for (AbstractViewElement v_element : p_viewContainer.getOwnedElements()) {
			if (v_element instanceof ViewContainer) {
				v_elements.addAll(getViewElementsForViewContainer((ViewContainer) v_element));
			}
			v_elements.add(v_element);
		}
		return v_elements;
	}

	/**
	 * Obtenir la liste des opérations associée à l'ActionState.
	 * 
	 * @param p_actionState L'ActionState à regarder.
	 * @return La liste désirée.
	 */
	public static List<Operation> getOperationsForActionState(ActionState p_actionState) {
		List<Operation> v_return = new ArrayList<>();

		List<FlowAction> v_listAction = p_actionState.getActions();
		for (FlowAction v_flowAction : v_listAction) {
			List<Action> v_listOperation = v_flowAction.getOperations();
			for (Action v_action : v_listOperation) {
				// SI c'est une 'Operation'
				if (v_action instanceof Operation) {
					Operation v_operation = (Operation) v_action;
					v_return.add(v_operation);
				}
			}
		}
		return v_return;
	}

//	public static List<AbstractViewElement> getViewElementsForViewContainer(final ViewContainer p_viewContainer,
//			List<AbstractViewElement> p_elements) {
//
//		List<AbstractViewElement> v_elements = new ArrayList<>();
//
//		if (p_elements != null && !p_elements.isEmpty())
//			v_elements.addAll(p_elements);
//
//		for (AbstractViewElement v_element : p_viewContainer.getOwnedElements()) {
//			if (v_element instanceof ViewContainer) {
//				List<AbstractViewElement> x =	getViewElementsForViewContainer((ViewContainer)v_element, p_elements);
//				if(null != x) {
//					v_elements.addAll(x);
//				}
//			} else {
//				v_elements.add(v_element);
//				System.out.println(v_element.getName());
//			}
//		}
//		
//		return v_elements;
//	}

	/**
	 * Cherche un objet parent de type donné pour un objet.
	 * 
	 * @param p_obj  l'objet source
	 * @param p_type le type du parent recherché
	 * @param <T>    le type du parent recherché
	 * @return le parent trouvé avec le type p_type, ou null si aucun parent de ce
	 *         type n'a été trouvé
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getParent(final EObject p_obj, final Class<T> p_type) {
		EObject v_parent = p_obj.eContainer();
		while (v_parent != null && !(p_type.isAssignableFrom(v_parent.getClass()))) {
			v_parent = v_parent.eContainer();
		}
		if (v_parent != null) {
			return (T) v_parent;
		}
		return null;
	}

	/**
	 * Cherche les transitions à partir d'un état source, sans garde et sans
	 * événement spécifié
	 * 
	 * @param p_state          l'état source des états recherchés
	 * @param p_followSubFlows booléen indiquant si l'on doit suivre les sous flows
	 *                         ou non
	 * @return les transitions à partir d'un état source, sans garde et sans
	 *         événement spécifié
	 */
	// [query public getTransitionsFromStateWithoutGardOrEvent(state : FlowState,
	// followSubFlows : Boolean) : Set(Transition) =
	// state.getTransitionsFromState(followSubFlows)->select(transition :
	// Transition |
	// (transition.guard = null or transition.guard.trim().size() = 0) and
	// (transition.on = null or transition.on->isEmpty()))
	// /]
	private static Set<Transition> getTransitionsFromStateWithoutGardOrEvent(final FlowState p_state,
			final boolean p_followSubFlows) {
		final Set<Transition> v_transitions = new HashSet<Transition>();
		for (final Transition v_transition : getTransitionsFromState(p_state, p_followSubFlows)) {
			if ((v_transition.getGuard() == null || v_transition.getGuard().trim().isEmpty())
					&& (v_transition.getOn() == null || v_transition.getOn().isEmpty())) {
				v_transitions.add(v_transition);
			}
		}
		return v_transitions;
	}

	/**
	 * Cherche les transitions à partir d'un état cible
	 * 
	 * @param p_state l'état cible des transitions
	 * @return les transitions à partir d'un état cible
	 */
	// [query public getTransitionsToState(state : FlowState) : Set(Transition) =
	// state.eContainer(Flow).transitions->select(transition : Transition |
	// transition.to = state)
	// ->asSet()->sortedBy(name)
	// /]
	/*
	 * private static Set<Transition> getTransitionsToState (final FlowState
	 * p_state) { final Set<Transition> v_transitions = new HashSet<Transition>();
	 * for (final Transition v_transition : getParent(p_state,
	 * Flow.class).getTransitions()) { if (v_transition.getTo().equals(p_state)) {
	 * v_transitions.add(v_transition); } } return v_transitions; }
	 */

	/**
	 * Cherche les ViewStates qui référencent un ViewContainer
	 * 
	 * @param p_viewContainer le ViewContainer recherché
	 * @return les ViewStates qui référencent un ViewContainer
	 */
	// [query public getViewStatesForViewContainer(viewContainer : ViewContainer)
	// : Set(ViewState) =
	// viewContainer.eContainer(CinematicRoot).eAllContents(ViewState)
	// ->select(viewState : ViewState |
	// viewState.viewContainers->includes(viewContainer))
	// ->asSet()->sortedBy(name)
	// /]
	public static Set<ViewState> getViewStatesForViewContainer(final ViewContainer p_viewContainer) {
		final Set<ViewState> v_statesSet = new HashSet<ViewState>();
		final TreeIterator<EObject> v_allContents = getParent(p_viewContainer, CinematicRoot.class).eAllContents();
		while (v_allContents.hasNext()) {
			final EObject v_obj = v_allContents.next();
			if (v_obj instanceof ViewState) {
				final ViewState v_state = (ViewState) v_obj;
				if (v_state.getViewContainers().contains(p_viewContainer)) {
					v_statesSet.add(v_state);
				}
			}
		}
		final List<ViewState> v_statesList = new ArrayList<ViewState>(v_statesSet);
		Collections.sort(v_statesList, new NameComparator());
		return new LinkedHashSet<ViewState>(v_statesList);
	}

	/**
	 * Cherche les FlowEvents lancés par un ViewEvent
	 * 
	 * @param p_viewEvent le ViewEvent traité
	 * @return les FlowEvents lancés par un ViewEvent
	 */
	// [query public getFlowEventsForViewEvent(viewEvent : ViewEvent) :
	// Set(FlowEvent) =
	// viewEvent.eContainer(ViewContainer).getViewStatesForViewContainer().eContainer(Flow).events->select(flowEvent
	// : FlowEvent | flowEvent.binds->includes(viewEvent))
	// ->asSet()->sortedBy(name)
	// /]
	public static Set<FlowEvent> getFlowEventsForViewEvent(final ViewEvent p_viewEvent) {
		final Set<FlowEvent> v_flowEventsSet = new HashSet<FlowEvent>();
		for (final ViewState v_viewState : getViewStatesForViewContainer(getParent(p_viewEvent, ViewContainer.class))) {
			for (final FlowEvent v_flowEvent : getParent(v_viewState, Flow.class).getEvents()) {
				if (v_flowEvent.getBinds().contains(p_viewEvent)) {
					v_flowEventsSet.add(v_flowEvent);
				}
			}
		}
		final List<FlowEvent> v_flowEventsList = new ArrayList<FlowEvent>(v_flowEventsSet);
		Collections.sort(v_flowEventsList, new NameComparator());
		return new LinkedHashSet<FlowEvent>(v_flowEventsList);
	}

	/**
	 * Cherche les FlowEvents lancés par un ViewEvent
	 * 
	 * @param p_viewEvent le ViewEvent traité
	 * @return les FlowEvents lancés par un ViewEvent
	 */
	// [query public getFlowEventsForViewEvent(viewEvent : ViewEvent) :
	// Set(FlowEvent) =
	// viewEvent.eContainer(ViewContainer).getViewStatesForViewContainer().eContainer(Flow).events->select(flowEvent
	// : FlowEvent | flowEvent.binds->includes(viewEvent))
	// ->asSet()->sortedBy(name)
	// /]
	public static ViewEvent getUnbindedViewEvent(final ViewEvent p_viewEvent) {
		ViewEvent v_result = null;
		boolean v_binded = false;
		for (final ViewState v_viewState : getViewStatesForViewContainer(getParent(p_viewEvent, ViewContainer.class))) {
			for (final FlowEvent v_flowEvent : getParent(v_viewState, Flow.class).getEvents()) {
				if (v_flowEvent.getBinds().contains(p_viewEvent)) {
					v_binded = true;
				}
			}
		}
		if (!v_binded) {
			v_result = p_viewEvent;
		}

		return v_result;
	}

	/**
	 * Recherche le binding pour un élément
	 * 
	 * @param p_object l'élément source
	 * @return l'élément associé
	 */
	public static BoundableElement searchBindingFor(final BoundableElement p_object) {
		final CinematicRoot v_root = getParent(p_object, CinematicRoot.class);
		if (v_root == null || v_root.getBindingRegistries() == null) {
			return null;
		}

		// Récupérer l'identifiant de l'objet
		final String v_uriFragment = p_object.eResource().getURIFragment(p_object);
		for (final BindingRegistry v_binding : v_root.getBindingRegistries()) {
			for (BindingInfo v_info : v_binding.getBindingInfos()) {
				if (v_info.getLeft() != null) {
					final String v_currentUriFragment = v_info.getLeft().eResource().getURIFragment(v_info.getLeft());
					if (v_uriFragment.equals(v_currentUriFragment)) {
						return v_info.getRight();
					}
				}
				if (v_info.getRight() != null) {
					final String v_currentUriFragment2 = v_info.getRight().eResource()
							.getURIFragment(v_info.getRight());
					if (v_uriFragment.equals(v_currentUriFragment2)) {
						return v_info.getLeft();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Recherche le binding pour un élément
	 * 
	 * @param p_object l'élément source
	 * @return l'élément associé
	 */

	public static Entity searchBindingForEntity(final BoundableElement p_object) {

		final CinematicRoot v_root = getParent(p_object, CinematicRoot.class);
		if (v_root == null || v_root.getBindingRegistries() == null) {
			return null;
		}
		// Récupérer l'identifiant de l'objet
		final String v_uriFragment = p_object.eResource().getURIFragment(p_object);
		for (final BindingRegistry v_binding : v_root.getBindingRegistries()) {
			for (BindingInfo v_info : v_binding.getBindingInfos()) {
				if (v_info.getRight() != null) {
					final String v_currentUriFragment2 = v_info.getRight().eResource()
							.getURIFragment(v_info.getRight());
					final String v_currentUriFragment = v_info.getLeft().eResource().getURIFragment(v_info.getLeft());
					if (v_uriFragment.equals(v_currentUriFragment2) || (v_uriFragment.equals(v_currentUriFragment))) {
						if (v_info.getRight() instanceof org.obeonetwork.dsl.entity.Entity) {
							final EntityImpl v_ei = (org.obeonetwork.dsl.entity.impl.EntityImpl) v_info.getRight();
							return v_ei;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Comparateur d'objets Obeo suivant leur nom.
	 * 
	 * @author MINARM
	 */
	private static class NameComparator implements Comparator<NamedElement> {
		@Override
		public int compare(final NamedElement p_o1, final NamedElement p_o2) {
			if (p_o1 == null && p_o2 == null) {
				return 0;
			}
			if (p_o1 == null) {
				return -1;
			}
			if (p_o2 == null) {
				return 1;
			}
			if (p_o1.getName() == null && p_o2.getName() == null) {
				return 0;
			}
			if (p_o1.getName() == null) {
				return -1;
			}
			if (p_o2.getName() == null) {
				return 1;
			}
			return p_o1.getName().compareTo(p_o2.getName());
		}
	}

}
