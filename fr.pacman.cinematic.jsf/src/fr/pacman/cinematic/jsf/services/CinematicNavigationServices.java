/**
 * 
 */
package fr.pacman.cinematic.jsf.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.dsl.cinematic.CinematicRoot;
import org.obeonetwork.dsl.cinematic.Event;
import org.obeonetwork.dsl.cinematic.flow.ActionState;
import org.obeonetwork.dsl.cinematic.flow.DecisionState;
import org.obeonetwork.dsl.cinematic.flow.FinalState;
import org.obeonetwork.dsl.cinematic.flow.Flow;
import org.obeonetwork.dsl.cinematic.flow.FlowState;
import org.obeonetwork.dsl.cinematic.flow.InitialState;
import org.obeonetwork.dsl.cinematic.flow.SubflowState;
import org.obeonetwork.dsl.cinematic.flow.Transition;
import org.obeonetwork.dsl.cinematic.flow.ViewState;
import org.obeonetwork.dsl.cinematic.view.AbstractViewElement;
import org.obeonetwork.dsl.cinematic.view.ViewContainer;
import org.obeonetwork.dsl.cinematic.view.ViewEvent;

import fr.pacman.cinematic.api.services.CinematicModelServices;

/**
 * @author arichard
 * 
 */
public class CinematicNavigationServices {
	
	static private int counter = 0;
	synchronized static public int getId() {
		return counter++;
	}

	synchronized static public int getIdJavaService(Object object){return getId();}

	/**
	 * Return a collection of {@link ActionState} and/or {@link ViewState}
	 * associated with the given {@link FlowState}.
	 * 
	 * @param flowState
	 *            the given {@link FlowState}.
	 * @param event
	 *            the given {@link Event}.
	 * @return a collection of {@link FlowState}.
	 */
	public Set<FlowState> getNextStates(FlowState flowState, Event event) {
		Set<FlowState> nexts = new HashSet<FlowState>();
		if (flowState instanceof SubflowState) {
			Flow flow = ((SubflowState) flowState).getSubflow();
			if (flow != null) {
				InitialState initialState = getInitialState(flow);
				nexts.addAll(getNextStates(initialState, null));
			}

		} else if (flowState instanceof ViewState
				|| flowState instanceof ActionState
				|| flowState instanceof InitialState
				|| flowState instanceof DecisionState) {
			Flow flow = getFlow(flowState);
			if (flow != null) {
				for (Transition transition : flow.getTransitions()) {
					FlowState from = transition.getFrom();
					FlowState to = transition.getTo();
					Collection<Event> on = transition.getOn();
					if (from != null && to != null && from.equals(flowState)
							&& (on.contains(event) || event == null)) {
						if (to instanceof ViewState) {
							nexts.add((ViewState) to);
						} else if (to instanceof ActionState) {
							nexts.add((ActionState) to);
						} else if (to instanceof SubflowState) {
							nexts.addAll(getNextStates((SubflowState) to, null));
						} else if (to instanceof DecisionState) {
							nexts.addAll(getNextStates((DecisionState) to, null));
						} else if (to instanceof FinalState) {
							Set<SubflowState> subflows = getAllSubFlowStates(flow);
							Set<Transition> transitions = getAllTransitions(flow);
							for (SubflowState subflowState : subflows) {
								if (flow.equals(subflowState.getSubflow())) {
									for (Transition transitionTmp : transitions) {
										FlowState fromTmp = transitionTmp.getFrom();
										FlowState toTmp = transitionTmp.getTo();
										if (from != null && to != null && fromTmp.equals(subflowState)) {
											nexts.add(toTmp);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return nexts;
	}

	public Set<FlowState> getNextStatesJavaService(FlowState flowState,Event event){return getNextStates(flowState,event);}

	/**
	 * 
	 * @param flow
	 * @return
	 */
	private InitialState getInitialState(Flow flow) {
		for (FlowState flowState : flow.getStates()) {
			if (flowState instanceof InitialState) {
				return (InitialState) flowState;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param object
	 * @return
	 */
	private Flow getFlow(EObject object) {
		EObject container = object.eContainer();
		if (container instanceof Flow) {
			return (Flow) container;
		}
		return null;
	}
	/**
	 * 
	 * @param object
	 * @return
	 */
	private CinematicRoot getCinematicRoot(EObject object) {
		EObject container = EcoreUtil.getRootContainer(object);
		if (container instanceof CinematicRoot) {
			return (CinematicRoot) container;
		}
		return null;
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	private Set<SubflowState> getAllSubFlowStates(EObject object) {
		Set<SubflowState> subflows = new HashSet<SubflowState>();
		CinematicRoot root = getCinematicRoot(object);
		for (TreeIterator<EObject> iterAssoc = root.eAllContents(); iterAssoc.hasNext();) { 
			Object obj = iterAssoc.next(); 
			if (obj instanceof SubflowState) {
				subflows.add((SubflowState) obj);
			}
		}
		return subflows;
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	private Set<Transition> getAllTransitions(EObject object) {
		Set<Transition> transitions = new HashSet<Transition>();
		CinematicRoot root = getCinematicRoot(object);
		for (TreeIterator<EObject> iterAssoc = root.eAllContents(); iterAssoc.hasNext();) { 
			Object obj = iterAssoc.next(); 
			if (obj instanceof Transition) {
				transitions.add((Transition) obj);
			}
		}
		return transitions;
	}
	
	/**
	 * Return a collection of {@link ActionViewEvent}
	 * associated with the given {@link ViewContainer}.
	 *
	 * @param flowState
	 *            the given {@link ViewContainer}.
	 * 
	 * @return a collection of {@link ViewEvent}.
	 */
	public Set<ViewEvent> getUnBindedViewEvent(ViewContainer viewContainer) {
		
		Set<ViewEvent> events = new HashSet<ViewEvent>();
		
			for (AbstractViewElement anAVE :  viewContainer.getOwnedElements()){			
						for (ViewEvent  eventElement : anAVE.getEvents()){
							events.add(CinematicModelServices.getUnbindedViewEvent(eventElement));							
						}					
			}				
		return events;
		
	}

	public Set<ViewEvent> getUnBindedViewEventJavaService(ViewContainer viewContainer){return getUnBindedViewEvent(viewContainer);}
	
	/**
	 * Return le nombre de bouton à ajouter dans la page jsf * 2 qui correspond au nombre de colonnes
	 * associated with the given {@link ViewContainer}.
	 * 
	 * @param flowState
	 *            the given {@link ViewContainer}.
	 * 
	 * @return a collection of {@link ViewEvent}.
	 */
	public Integer getNbButtons(ViewContainer viewContainer) {
		
		Integer nbBt = 0;				 			
			for (AbstractViewElement anAVE :  viewContainer.getOwnedElements()){				
				if ("button".equalsIgnoreCase(anAVE.getWidget().getName())){
					nbBt++;	
				}		
			 
			}	
			// Multiplier x2 pour aligner les boutons 
		return (nbBt*2);
		
	}

	public Integer getNbButtonsJavaService(ViewContainer viewContainer){return getNbButtons(viewContainer);}
	
	
	   /**
	    * Cherche un objet parent de type donné pour un objet.
	    * 
	    * @param p_obj
	    *           l'objet source
	    * @param p_type
	    *           le type du parent recherché
	    * @param <T>
	    *           le type du parent recherché
	    * @return le parent trouvé avec le type p_type, ou null si aucun parent de
	    *         ce type n'a été trouvé
	    */
	   @SuppressWarnings({ "unchecked", "unused" })
	   private static <T> T getParent (final EObject p_obj, final Class<T> p_type)
	   {
	      EObject v_parent = p_obj.eContainer();
	      while (v_parent != null && !(p_type.isAssignableFrom(v_parent.getClass())))
	      {
	         v_parent = v_parent.eContainer();
	      }
	      if (v_parent != null)
	      {
	         return (T) v_parent;
	      }
	      return null;
	   }

}
