package pl.edu.pw.elka.prm2t22l.battleships.generator;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
	private final T value;
	private final Tree<T> parent;
	private final List<Tree<T>> children = new ArrayList<>();

	public Tree(Tree<T> parent, T value) {
		this.parent = parent;
		this.value = value;
	}

	public Tree(Tree<T> parent) {
		this(parent, null);
	}

	public Tree() {
		this(null);
	}

	public Tree<T> add(T value) {
		Tree<T> tree = new Tree<>(this, value);
		children.add(tree);
		return tree;
	}

	public boolean has(T value) {
		return children.stream()
				.map(Tree::getValue)
				.anyMatch(value::equals);
	}

	public T getValue() {
		return value;
	}

	public Tree<T> getParent() {
		return parent;
	}

	public boolean isRoot() {
		return parent == null;
	}
}
