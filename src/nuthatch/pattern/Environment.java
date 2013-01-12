package nuthatch.pattern;

import java.util.Iterator;

import nuthatch.tree.Tree;

public interface Environment extends Iterable<String> {
	/**
	 * Enter a new scope.
	 * 
	 * May return the same or a different object.
	 * 
	 * @return An environment with the empty scope at the top.
	 */
	Environment enterScope();


	/**
	 * Exit a scope, abandoning all bindings in the current scope.
	 * 
	 * May return the same or a different object.
	 * 
	 * @return An environment with tall the top-level bindings discarded
	 */
	Environment exitScope();


	/**
	 * Begin a transaction.
	 * 
	 */
	void begin();


	/**
	 * Rollback all bindings entered since the last begin().
	 */
	void rollback();


	/**
	 * Commit all bindings entered since the last begin().
	 */
	void commit();


	/**
	 * Lookup a variable.
	 * 
	 * @param var
	 *            Name of the variable
	 * @return The value, or null if not found
	 */
	Tree get(String var);


	/**
	 * Bind a new variable, overriding any previous binding.
	 * 
	 * @param var
	 *            Name of the variable
	 * @param val
	 *            The value
	 */
	void put(String var, Tree val);


	/**
	 * Iterate over all the variable names in the environment.
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	Iterator<String> iterator();


	/**
	 * @return True if no variables are bound in any scope
	 */
	boolean isEmpty();


	/**
	 * @return True if no variables are bound in current scope
	 */
	boolean isEmptyScope();
}