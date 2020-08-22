package solution;

import java.util.Deque;
import java.util.ArrayDeque;

public class DequeBy3Stacks {

	Deque<Integer> left;
	Deque<Integer> right;
	Deque<Integer> buffer;

	public DequeBy3Stacks() {
		left = new ArrayDeque<>();
		right = new ArrayDeque<>();
		buffer = new ArrayDeque<>();
	}

	public void offerFirst(int element) {
		left.offerFirst(element);
	}

	public void offerLast(int element) {
		right.offerFirst(element);
	}

	public Integer pollFirst() {
		if (!left.isEmpty()) {
			return left.pollFirst();
		} else if (left.isEmpty() && right.isEmpty()) {
			return null;
		} else {
			shuffle();
			return left.isEmpty() ? null : left.pollFirst();
		}
	}

	public Integer pollLast() {
		if (!right.isEmpty()) {
			return right.pollFirst();
		} else if (left.isEmpty() && right.isEmpty()) {
			return null;
		} else {
			shuffle();
			return right.isEmpty() ? null : right.pollFirst();
		}
	}

	public Integer peekFirst() {
		if (!left.isEmpty()) {
			return left.peekFirst();
		} else if (left.isEmpty() && right.isEmpty()) {
			return null;
		} else {
			shuffle();
			return left.isEmpty() ? null : left.peekFirst();
		}
	}

	public Integer peekLast() {
		if (!right.isEmpty()) {
			return right.peekFirst();
		} else if (left.isEmpty() && right.isEmpty()) {
			return null;
		} else {
			shuffle();
			return right.isEmpty() ? null : right.peekFirst();
		}
	}

	public void shuffle() {
		Deque<Integer> source = left.isEmpty() ? right : left;
		Deque<Integer> target = left.isEmpty() ? left : right;
		int halfSize = source.size() / 2;

		while (halfSize > 0) {
			buffer.offerFirst(source.pollFirst());
			halfSize--;
		}

		while (!source.isEmpty()) {
			target.offerFirst(source.pollFirst());
		}

		while (!buffer.isEmpty()) {
			source.offerFirst(buffer.pollFirst());
		}
	}

	public int size() {
		return left.size() + right.size();
	}

	public boolean isEmpty() {
		return left.isEmpty() && right.isEmpty();
	}
}
