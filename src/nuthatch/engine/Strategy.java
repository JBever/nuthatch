package nuthatch.engine;

public interface Strategy {
	/**
	 * Visit a new node.
	 * 
	 * This strategy method is called whenever the engine enters a node.
	 * 
	 * The strategy should perform the task of visiting the current node only,
	 * and return the next node that should be visited (rather than trying to
	 * visit multiple nodes during the same visit call).
	 * 
	 * @param eng
	 *            The engine
	 * @return The direction of the next node to visit
	 */
	int visit(Engine eng);
}
