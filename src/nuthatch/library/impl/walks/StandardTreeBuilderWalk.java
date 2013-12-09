package nuthatch.library.impl.walks;

import nuthatch.library.Action;
import nuthatch.library.Walk;
import nuthatch.walker.Walker;

public class StandardTreeBuilderWalk<W extends Walker<?, ?>> implements Walk<W> {

	private Action<W> action;


	public StandardTreeBuilderWalk(Action<W> action) {
		this.action = action; //Might want to specify another walk to use with action, to ensure that you get the right behaviour
	}


	@Override
	public void init(W walker) {
		action.init(walker);
	}


	@Override
	public int step(W walker) {
		int nextToVisit = action.step(walker);

		if(nextToVisit != PROCEED) {
			return nextToVisit;
		}
		else {
			return NEXT;
		}
	}

}

//walk copyall {
//state result = new TreeBuilder();
//if down then { result.add(current); result.moveDown(); }
//if up then result.moveUp();
//}