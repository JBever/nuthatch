package nuthatch.tree.impl;

import nuthatch.tree.Tree;
import nuthatch.tree.TreeBuildingCursor;
import nuthatch.tree.TreeCursor;

/**
 * @author John-Petter Indroey
 * 
 *         This class is intended to keep track where in the tree we are working
 *         at the moment. This cursor can also handle that a node not yet is
 *         created, as well as create it.
 * 
 *         TODO make independent of standardTreeCursor
 */
public class StandardTreeBuildingCursor<Value, Type> extends StandardTreeCursor<Value, Type> implements TreeBuildingCursor<Value, Type> {

	Tree<Value, Type> tree;


	public StandardTreeBuildingCursor(Tree<Value, Type> tree) {
		super(tree);
		this.tree = tree;
	}


	@Override
	public TreeCursor<Value, Type> copy() {
		throw new UnsupportedOperationException("This method is yet to be implemented"); //TODO
	}


	@Override
	public TreeCursor<Value, Type> copyAndReplaceSubtree(TreeCursor<Value, Type> replacement) {
		throw new UnsupportedOperationException("This method is yet to be implemented"); //TODO
	}


	@Override
	public TreeCursor<Value, Type> copySubtree() {
		throw new UnsupportedOperationException("This method is yet to be implemented"); //TODO
	}


	@Override
	public void createNode(String name, Type type, Value data, int numChildren) {
		// TODO Auto-generated method stub
		if(isAtRoot()) {

		}
		else if(isAtPlaceholder()) {
			new StandardTree() {
			};//insert the new child
		}
		else {
//			throw new BranchNotFoundError("Somewthing went wrong in the StdTreeBuilderCursor, couldn't find branch to create node in!");
		}

	}


	@Override
	public boolean isAtPlaceholder() {
		// TODO Auto-generated method stub
		return false;
	}
}
