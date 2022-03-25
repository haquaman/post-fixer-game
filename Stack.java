
public class Stack {
	private int top;
	private Object[] elements;
	
	Stack(int capacity){
		elements = new Object[capacity];
		top =-1;
	}
	void push(Object data) {
		if (isFull()) System.out.println("Stack owerflow");
		else {
			top++;
			elements[top]=data;
		}
	}
	Object pop() {
		if (isEmpty()) {
			System.out.println("Stack is Empty");
			return null;
		}
		else {
			Object retdata = elements[top];
			top--;
			return retdata;
		}
	}
	Object peek() {
		if (isEmpty()) {
			System.out.println("Stack is Empty");
			return null;
		}
		else return elements[top];
	}
	boolean isEmpty() {
		return (top ==-1);
	}
	boolean isFull() {
		return (top+1 == elements.length);
	}
	int size() {
		return top+1;
	}
}
