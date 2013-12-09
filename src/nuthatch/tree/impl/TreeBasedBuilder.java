package nuthatch.tree.impl;

import static nuthatch.library.JoinPoints.down;
import static nuthatch.library.JoinPoints.root;
import static nuthatch.library.JoinPoints.up;
import nuthatch.library.Action;
import nuthatch.library.BaseWalk;
import nuthatch.library.Walk;
import nuthatch.library.impl.walks.StandardTreeBuilderWalk;
import nuthatch.tree.Tree;
import nuthatch.walker.Walker;
import nuthatch.walker.impl.SimpleWalker;

/**
 * @author John-Petter Indroey
 * 
 *         This class is supposed to be the de facto class users of Nuthatch
 *         turns to when they want to build a new tree based upon an original.
 * 
 *         The first step is to support a copy of the original tree.
 * 
 *         A natural next step will be to allow the user to insert subtrees into
 *         the tree beeing buildt at certain joinPoints. These jointPoints are
 *         to be defined using paths.
 * 
 *         A third step may be to allow the user to hand this class a set of
 *         rules that determines how the new tree shall be buildt, based upon
 *         the inputTree. One can look upon it as the inputTree as the domain,
 *         the outputTree (the one to be buildt), as the range, and the set of
 *         rules as the function to map the domain to the range.
 */
public class TreeBasedBuilder<Value, Type, T extends Tree<Value, Type>> {

	/**
	 * This method
	 * 
	 * @param inputTree
	 * @param outTree
	 * @param action
	 * @return a shallow copy of the input tree.
	 */
	public Tree<Value, Type> CopyTree(T inputTree, T outTree, final Action<Walker<Value, Type>> action) {

		final StandardTreeBuildingCursor<Value, Type> buildCursor = new StandardTreeBuildingCursor<Value, Type>(outTree); // builds new nodes
		final StandardTreeBuilderWalk<Walker<Value, Type>> buildWalk = new StandardTreeBuilderWalk<>(action);
		final StandardTreeCursor<Value, Type> stdCursor = new StandardTreeCursor<Value, Type>(inputTree);

		Walk<SimpleWalker<Value, Type>> newTree = new BaseWalk<SimpleWalker<Value, Type>>() {
			@Override
			public int step(SimpleWalker<Value, Type> w) {
				int nextToVisit = action.step(w);
				if(root(w) && down(w)) {
					buildCursor.createNode(stdCursor.getName(), stdCursor.getType(), stdCursor.getData(), stdCursor.getNumChildren());
				}
				if(root(w) && down(w)) {
					buildWalk.step(w);
				}
				else if(down(w)) {
					buildWalk.step(w);
					assert (buildCursor.isAtPlaceholder()) : "Cursor not at placeholder, we don't want it to overwrite a legitimate child!";
					buildCursor.createNode(stdCursor.getName(), stdCursor.getType(), stdCursor.getData(), stdCursor.getNumChildren());
				}
				else if(up(w)) {
					buildWalk.step(w); //Keep the cursors in sync!
				}

				if(nextToVisit != PROCEED) {
					return nextToVisit;
				}
				else {
					return NEXT;
				}
			}
		};
		SimpleWalker<Value, Type> treeWalker = new SimpleWalker<Value, Type>(stdCursor, newTree);
		treeWalker.start();

		return outTree;
	}
}
