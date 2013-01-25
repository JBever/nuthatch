package nuthatch.strategy;

import nuthatch.engine.Engine;
import nuthatch.tree.TreeCursor;

public interface Transform {
	/**
	 * Apply the transformation.
	 * 
	 * @param engine
	 *            The engine; use {@link Engine#currentTree()} to obtain the
	 *            current tree node
	 * @return A replacement subtree, or null if no change
	 */
	TreeCursor apply(Engine engine);
}
