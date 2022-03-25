import java.util.Random;

import enigma.core.Enigma;

public class Main {
	public static void add(int count,char ary[][],CircularQueue q) {
		Random rnd= new Random();
		while(count!=0) {
			if(count>=8) {
				while(!(q.isEmpty())) {
					int x = rnd.nextInt(10);
					int y = rnd.nextInt(10);
					while((ary[x][y]!='.')) {
						x = rnd.nextInt(10);
						y = rnd.nextInt(10);
					}
					char qw = (char)q.dequeue();
					ary[x][y]=(char)qw;
				}
				count = count-8;
				for (int i = 0; i < 8; i++) {
					int randomnumber = rnd.nextInt(13)+1;
					if (randomnumber<=9) {
						char c=(char)(randomnumber+'0');
						q.enqueue(c);
					}
					else if (randomnumber == 10) q.enqueue('*');
					else if (randomnumber == 11) q.enqueue('+');
					else if (randomnumber == 12) q.enqueue('/');
					else if (randomnumber == 13) q.enqueue('-');
				}
			}
			if(count>0 && count<8) {
				int temp =count;
				while(count!=0) {
					int x = rnd.nextInt(10);
					int y = rnd.nextInt(10);
					while((ary[x][y]!='.')) {
						x = rnd.nextInt(10);
						y = rnd.nextInt(10);
					}
					char qw = (char)q.dequeue();
					ary[x][y]=(char)qw;
					count--;
				}
				for (int i = 0; i < temp; i++) {
					int randomnumber = rnd.nextInt(13)+1;
					if (randomnumber<=9) {
						char c=(char)(randomnumber+'0');
						q.enqueue(c);
					}
					else if (randomnumber == 10) q.enqueue('*');
					else if (randomnumber == 11) q.enqueue('+');
					else if (randomnumber == 12) q.enqueue('/');
					else if (randomnumber == 13) q.enqueue('-');
				}
			}
		}
	}
	public static void printCircularQueue(CircularQueue q) {
		CircularQueue tempq = new CircularQueue(8);
		while(!(q.isEmpty())) {
			System.out.print(q.peek());
			tempq.enqueue(q.dequeue());	
		}
		while(!(tempq.isEmpty())) q.enqueue(tempq.dequeue());
	}
	
	public static void Queuelist(CircularQueue q) {
		Random rnd = new Random();
		for (int i = 0; i < 8; i++) {
			int randomnumber = rnd.nextInt(13)+1;
			if (randomnumber<=9) {
				char c=(char)(randomnumber+'0');
				q.enqueue(c);
			}
			else if (randomnumber == 10) q.enqueue('*');
			else if (randomnumber == 11) q.enqueue('+');
			else if (randomnumber == 12) q.enqueue('/');
			else if (randomnumber == 13) q.enqueue('-');
		}
	}
	public static void Display2(int x,int y,char[][]array) {
		for (int i = 0; i < 10; i++) {
			System.out.print(array[x][y]);
			y++;
		}
		System.out.print("#");
		System.out.println();
	}
	public static void Display(char[][]array) {
		System.out.print("  ");
		for (int i = 1; i <=9; i++) {
			System.out.print(i);
		}
		System.out.print("0");
		System.out.println();
		System.out.print(" ");
		for (int i = 0; i < 12; i++) {
			System.out.print("#");
		}
		System.out.println();
		System.out.print("1#");
		Display2(0,0,array);
		System.out.print("2#");
		Display2(1,0,array);
		System.out.print("3#");
		Display2(2,0,array);
		System.out.print("4#");
		Display2(3,0,array);
		System.out.print("5#");
		Display2(4,0,array);
		System.out.print("6#");
		Display2(5,0,array);
		System.out.print("7#");
		Display2(6,0,array);
		System.out.print("8#");
		Display2(7,0,array);
		System.out.print("9#");
		Display2(8,0,array);
		System.out.print("0#");
		Display2(9,0,array);
		System.out.print(" ");
		for (int i = 0; i < array.length+2; i++) System.out.print("#");
		
	}
public static void ary(char [][] array) {
	Random rnd = new Random();
	for (int i = 0; i < array.length; i++) {
		for (int j = 0; j < array.length; j++) {
			array[i][j] = '.';
		}
	}
	CircularQueue q = new CircularQueue(8);
	int a =0;
	while(a!=5) {
		for (int i = 0; i < 8; i++) {
			int randomnumber = rnd.nextInt(13)+1;
			if (randomnumber<=9) {
				char c=(char)(randomnumber+'0');
				q.enqueue(c);
			}
			else if (randomnumber == 10) q.enqueue('*');
			else if (randomnumber == 11) q.enqueue('+');
			else if (randomnumber == 12) q.enqueue('/');
			else if (randomnumber == 13) q.enqueue('-');
		}
		while(!(q.isEmpty())) {
			int x = rnd.nextInt(10);
			int y = rnd.nextInt(10);
			if(array[x][y]=='.') {
				array[x][y]=(char)q.dequeue();
			}
		}
		a++;
	}
}

public static boolean isInteger(Object object) {
    if(object instanceof Integer) {
        return true;
    } else {
        String string = object.toString();

        try {
            Integer.parseInt(string);
        } catch(Exception e) {
            return false;
        }
    }

    return true;
}
	public static void main(String[] args) throws Exception {
		char [][]array= new char[10][10];
		CircularQueue q = new CircularQueue(8);
		Queuelist(q);
		ary(array);
		Game game= new Game(array,q);
		
	}

}
