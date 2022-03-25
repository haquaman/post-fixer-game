import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;
import java.awt.Color;

public class Game {
	private TextAttributes foreground=new TextAttributes(Color.white,Color.blue);
	private TextAttributes foreground2=new TextAttributes(Color.white,Color.green);
	public enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
	public KeyListener klis;

   // ------ Standard variables for mouse and keyboard ------
   public int mousepr;          // mouse pressed?
   public int mousex, mousey;   // mouse text coords.
   public int keypr;   // key pressed?
   public int rkey;    // key   (for press/release)
   // ----------------------------------------------------
   
   
   Game(char ary[][],CircularQueue q) throws Exception {   // --- Contructor
      klis=new KeyListener() {
         public void keyTyped(KeyEvent e) {}
         public void keyPressed(KeyEvent e) {
            if(keypr==0) {
               keypr=1;
               rkey=e.getKeyCode();
            }
         }
         public void keyReleased(KeyEvent e) {}
      };
      cn.getTextWindow().addKeyListener(klis);
      // ----------------------------------------------------
      int px=5,py=5;
      int time =60;
      int score =0;
      int timebreak=0;
      char score1[]= new char[1000];
      for (int i = 0; i < score1.length; i++) score1[i]='0';
      int number =0, count=0,spacecount=0;
      Stack tempexpression = new Stack(1000);
      Stack temp1 = new Stack(1000);
      Stack temp2 = new Stack(1000);
      Stack temp3 = new Stack(1000);
      Stack expression = new Stack(1000);
      Stack empty=new Stack(1000);
      Stack right=new Stack(1000);
      Stack rightemp=new Stack(1000);
      String mode ="Free";
      cn.getTextWindow().setCursorType(1);
      cn.getTextWindow().setCursorPosition(0,0);
	  Main.Display(ary);
	  boolean Evaluationflag= true;
	  boolean gameflag = true;
      while(gameflag==true) {
    	  Thread.sleep(20);
    	  if(time==0) {
    		  gameflag=false;
    		  cn.getTextWindow().setCursorPosition(16,12);
    		  System.out.println("Game Over !!!");
    	  }
    	  if(mode.equalsIgnoreCase("Take")) {
    		  timebreak++;
    		  if(timebreak == 50) {
    			  time--;
    			  timebreak=0;
    			  cn.getTextWindow().setCursorPosition(31,0);
    	    	  cn.getTextWindow().output("Time :   ");
    		  }
    	  }
    	  cn.getTextWindow().setCursorPosition(16,0);
    	  cn.getTextWindow().output("Input");
    	  cn.getTextWindow().setCursorPosition(16,1);
    	  cn.getTextWindow().output("<<<<<<<<");
    	  cn.getTextWindow().setCursorPosition(16,2);
    	  Main.printCircularQueue(q);
    	  cn.getTextWindow().setCursorPosition(16,3);
    	  cn.getTextWindow().output("<<<<<<<<");
    	  cn.getTextWindow().setCursorPosition(16,8);
    	  cn.getTextWindow().output("Expression: ");
    	  cn.getTextWindow().setCursorPosition(28,8);
    	  while(!(expression.isEmpty())) {
    		  tempexpression.push(expression.pop());
    	  }
    	  while(!(tempexpression.isEmpty())) {
    		  System.out.print(tempexpression.peek()+" ");
    		  expression.push(tempexpression.pop());
    	  }
    	  if(mode.equalsIgnoreCase("Evaluation")) {
    		  cn.getTextWindow().setCursorPosition(16,9);
        	  cn.getTextWindow().output("Evaluation: ");   
    	  }   	  
    	  cn.getTextWindow().setCursorPosition(31,0);
    	  cn.getTextWindow().output("Time : "+time);
    	  cn.getTextWindow().setCursorPosition(31,1);
    	  cn.getTextWindow().output("Score:        ");
    	  cn.getTextWindow().setCursorPosition(31,1);
    	  cn.getTextWindow().output("Score: "+score);
    	  cn.getTextWindow().setCursorPosition(31,2);
    	  cn.getTextWindow().output("Mode : "+mode);
    	  int ax=1;
    	  for (int i = 0; i < 7; i++) {
    		  cn.getTextWindow().setCursorPosition(60,ax);
  			System.out.println("|");
  			cn.getTextWindow().setCursorPosition(66,ax);
  			System.out.println("|");
  			ax++;
  		} 
    	  cn.getTextWindow().setCursorPosition(60,8);
    	  System.out.println("+-----+");
    	  /*cn.getTextWindow().setCursorPosition(0,0);
    	  a.Display(ary);*/
         if(keypr==1) {    // if keyboard button pressed
            if((rkey==KeyEvent.VK_LEFT || rkey==KeyEvent.VK_A) && px!=2) {
            	if(mode.equalsIgnoreCase("Take") && px!=2) while(px!=2 &&ary[py-2][px-3] == '.')px--;
            	if(px!=2)px--;   
            }
            if((rkey==KeyEvent.VK_RIGHT || rkey==KeyEvent.VK_D) && px!=11) {
            	if(mode.equalsIgnoreCase("Take") && px!=11) while(px!=11 &&ary[py-2][px-1] == '.')px++;
            	if(px!=11)px++;
            }
            if((rkey==KeyEvent.VK_UP|| rkey==KeyEvent.VK_W) && py!=2) {
            	if(mode.equalsIgnoreCase("Take") && py!=2) while(py!=2 && ary[py-3][px-2] == '.') py--;
            	if(py!=2)py--;
            }
            if((rkey==KeyEvent.VK_DOWN|| rkey==KeyEvent.VK_S) && py!=11) {
            	if(mode.equalsIgnoreCase("Take")&& py!=11) while(py!=11 && ary[py-1][px-2] == '.') py++;
            	if(py!=11)py++;
            }
            
            char rckey=(char)rkey;
            if(rkey==KeyEvent.VK_T) {
            	mode ="Take";
            	cn.getTextWindow().setCursorPosition(31,2);
            	cn.getTextWindow().output("Mode : "+mode);
            	/*
            	long start=System.currentTimeMillis();
            	boolean timeflag = true;
            	while(timeflag==true) {
                	try {
                		Thread.sleep(1000);
                	}catch (InterruptedException e) {
                		e.printStackTrace();
                	}
                	long end = System.currentTimeMillis();
                	int time1 = time+(int)((start-end)/1000);
                	cn.getTextWindow().setCursorPosition(31,0);
                	cn.getTextWindow().output("Time : "+time1);
            	}*/
            }
            if(mode.equalsIgnoreCase("Take")) {
            	if((rkey==KeyEvent.VK_LEFT || rkey==KeyEvent.VK_A) && ary[py-2][px-2]!='.') {
            		int intary[] = new int[10];
            		int sum=0;
            		for (int i = 0; i < intary.length; i++) intary[i]=0;
            		if(px!=2 && ary[py-2][px-3]!='.' && (ary[py-2][px-2]!='+' && ary[py-2][px-2]!='-' && ary[py-2][px-2]!='/' && ary[py-2][px-2]!='*') && 
            				(ary[py-2][px-3]!='+' && ary[py-2][px-3]!='-' && ary[py-2][px-3]!='/' && ary[py-2][px-3]!='*' && ary[py-2][px-3]!='#')) {
            			int tempx=px;
            			int ondalik=0;
            			int power=1;
            			intary[0] = Integer.parseInt(String.valueOf(ary[py-2][tempx-2]));
            			ary[py-2][tempx-2]='.';
            			while(tempx!=2 &&(ary[py-2][tempx-3]=='1' ||ary[py-2][tempx-3]=='2' ||ary[py-2][tempx-3]=='3' ||ary[py-2][tempx-3]=='4' ||ary[py-2][tempx-3]=='5' ||ary[py-2][tempx-3]=='6' ||ary[py-2][tempx-3]=='7' ||ary[py-2][tempx-3]=='8' ||ary[py-2][tempx-3]=='9')) {
            				tempx=tempx-1;
            				intary[power]=Integer.parseInt(String.valueOf(ary[py-2][tempx-2]));
            				power++;
            				ary[py-2][tempx-2]='.';
            				ondalik++;
            				px=tempx;
            			}
            			int tempcount = ondalik+1;
            			for (int i = 0; i < intary.length; i++) {
            				if(intary[i]!=0) {
            					int katsayi = (int)Math.pow(10, ondalik);
            					sum = sum + (intary[i]*katsayi);
            					ondalik--;
            				}
            				
						}
            			expression.push(sum);
            			temp1.push(sum);
            			temp3.push(sum);
            			count=count + tempcount;
            			/*
            			int n2 = Integer.parseInt(String.valueOf(ary[py-2][px-3]));
                		int n1 = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
                		ary[py-2][px-3]='.';
                		ary[py-2][px-2]='.';
                		count++;
                		number = (n1*10)+n2;
                		expression.push(number);
                		*/
            		}
            		else {
            			if((ary[py-2][px-2]!='+' && ary[py-2][px-2]!='-' && ary[py-2][px-2]!='/' && ary[py-2][px-2]!='*')) {
            				number = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
                			ary[py-2][px-2]='.';
                			count++;
                			expression.push(number);
                			temp1.push(number);
                			temp3.push(number);
            			}
            			else if((ary[py-2][px-2]=='+' || ary[py-2][px-2]=='-' || ary[py-2][px-2]=='/' || ary[py-2][px-2]=='*')) {
            				char tempchar = (char)ary[py-2][px-2];
            				ary[py-2][px-2]='.';
            				count++;
            				expression.push(tempchar);
            				temp1.push(tempchar);
            				temp3.push(tempchar);
            			}
            		}
            	}
            	if((rkey==KeyEvent.VK_RIGHT || rkey==KeyEvent.VK_D) && ary[py-2][px-2]!='.') {
            		int intary[] = new int[10];
            		int sum=0;
            		for (int i = 0; i < intary.length; i++) intary[i]=0;
            		if(px!=11 && ary[py-2][px-1]!='.' && (ary[py-2][px-2]!='+' && ary[py-2][px-2]!='-' && ary[py-2][px-2]!='/' && ary[py-2][px-2]!='*') && 
            				(ary[py-2][px-1]!='+' && ary[py-2][px-1]!='-' && ary[py-2][px-1]!='/' && ary[py-2][px-1]!='*' && ary[py-2][px-1]!='#')) {
            			int tempx=px;
            			int power=1;
            			int ondalik=0;
            			intary[0] = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
            			ary[py-2][px-2]='.';
            			while(tempx!=11 &&(ary[py-2][tempx-1]=='1' ||ary[py-2][tempx-1]=='2' ||ary[py-2][tempx-1]=='3' ||ary[py-2][tempx-1]=='4' ||ary[py-2][tempx-1]=='5' ||ary[py-2][tempx-1]=='6' ||ary[py-2][tempx-1]=='7' ||ary[py-2][tempx-1]=='8' ||ary[py-2][tempx-1]=='9')) {
            				tempx=tempx+1;
            				intary[power]=Integer.parseInt(String.valueOf(ary[py-2][tempx-2]));
            				power++;
            				ary[py-2][tempx-2]='.';
            				ondalik++;
            				px=tempx;
            			}
            			int tempcount = ondalik+1;
            			for (int i = 0; i < intary.length; i++) {
            				if(intary[i]!=0) {
            					int katsayi = (int)Math.pow(10, ondalik);
            					sum = sum + (intary[i]*katsayi);
            					ondalik--;
            				}
            				
						}
            			expression.push(sum);
            			temp1.push(sum);
            			temp3.push(sum);
            			count = count + tempcount;
            			/*
            			int n2 = Integer.parseInt(String.valueOf(ary[py-2][px-3]));
                		int n1 = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
                		ary[py-2][px-3]='.';
                		ary[py-2][px-2]='.';
                		count++;
                		number = (n1*10)+n2;
                		expression.push(number);
                		*/
            		}
            		else {
            			if((ary[py-2][px-2]!='+' && ary[py-2][px-2]!='-' && ary[py-2][px-2]!='/' && ary[py-2][px-2]!='*')) {
            				number = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
                			ary[py-2][px-2]='.';
                			count++;
                			expression.push(number);
                			temp1.push(number);
                			temp3.push(number);
            			}
            			else if((ary[py-2][px-2]=='+' || ary[py-2][px-2]=='-' || ary[py-2][px-2]=='/' || ary[py-2][px-2]=='*')) {
            				char tempchar = (char)ary[py-2][px-2];
            				ary[py-2][px-2]='.';
            				count++;
            				expression.push(tempchar);
            				temp1.push(tempchar);
            				temp3.push(tempchar);
            			}
            		}
            	}
            	if((rkey==KeyEvent.VK_DOWN || rkey==KeyEvent.VK_S) && ary[py-2][px-2]!='.') {
            		int intary[] = new int[10];
            		int sum=0;
            		for (int i = 0; i < intary.length; i++) intary[i]=0;
            		if(py!=11 && ary[py-1][px-2]!='.' && (ary[py-2][px-2]!='+' && ary[py-2][px-2]!='-' && ary[py-2][px-2]!='/' && ary[py-2][px-2]!='*') && 
            				(ary[py-1][px-2]!='+' && ary[py-1][px-2]!='-' && ary[py-1][px-2]!='/' && ary[py-1][px-2]!='*' && ary[py-1][px-2]!='#')) {
            			int tempy=py;
            			int power=0;
            			int ondalik=0;
            			intary[power] = Integer.parseInt(String.valueOf(ary[tempy-2][px-2]));
            			ary[tempy-2][px-2]='.';
            			while(tempy!=11 &&(ary[tempy-1][px-2]=='1' ||ary[tempy-1][px-2]=='2' ||ary[tempy-1][px-2]=='3' ||ary[tempy-1][px-2]=='4' ||ary[tempy-1][px-2]=='5' ||ary[tempy-1][px-2]=='6' ||ary[tempy-1][px-2]=='7' ||ary[tempy-1][px-2]=='8' ||ary[tempy-1][px-2]=='9')) {
            				tempy=tempy+1;
            				power++;
            				intary[power]=Integer.parseInt(String.valueOf(ary[tempy-2][px-2]));
            				ary[tempy-2][px-2]='.';
            				ondalik++;
            				py=tempy;
            			}
            			int tempcount = ondalik+1;
            			for (int i = 0; i < intary.length; i++) {
            				if(intary[i]!=0) {
            					int katsayi = (int)Math.pow(10, ondalik);
            					sum = sum + (intary[i]*katsayi);
            					ondalik--;
            				}
            				
						}
            			expression.push(sum);
            			temp1.push(sum);
            			temp3.push(sum);
            			count = count + tempcount;
            			/*
            			int n2 = Integer.parseInt(String.valueOf(ary[py-2][px-3]));
                		int n1 = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
                		ary[py-2][px-3]='.';
                		ary[py-2][px-2]='.';
                		count++;
                		number = (n1*10)+n2;
                		expression.push(number);
                		*/
            		}
            		else {
            			if((ary[py-2][px-2]!='+' && ary[py-2][px-2]!='-' && ary[py-2][px-2]!='/' && ary[py-2][px-2]!='*')) {
            				number = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
                			ary[py-2][px-2]='.';
                			count++;
                			expression.push(number);
                			temp1.push(number);
                			temp3.push(number);
            			}
            			else if((ary[py-2][px-2]=='+' || ary[py-2][px-2]=='-' || ary[py-2][px-2]=='/' || ary[py-2][px-2]=='*')) {
            				char tempchar = (char)ary[py-2][px-2];
            				ary[py-2][px-2]='.';
            				count++;
            				expression.push(tempchar);
            				temp1.push(tempchar);
            				temp3.push(tempchar);
            			}
            		}
            	}
            	if((rkey==KeyEvent.VK_UP || rkey==KeyEvent.VK_W) && ary[py-2][px-2]!='.') {
            		int intary[] = new int[10];
            		int sum=0;
            		for (int i = 0; i < intary.length; i++) intary[i]=0;
            		if(py!=2 && ary[py-3][px-2]!='.' && (ary[py-2][px-2]!='+' && ary[py-2][px-2]!='-' && ary[py-2][px-2]!='/' && ary[py-2][px-2]!='*') && 
            				(ary[py-3][px-2]!='+' && ary[py-3][px-2]!='-' && ary[py-3][px-2]!='/' && ary[py-3][px-2]!='*' && ary[py-3][px-2]!='#')) {
            			int tempy=py;
            			int power=0;
            			int ondalik=0;
            			intary[power] = Integer.parseInt(String.valueOf(ary[tempy-2][px-2]));
            			ary[tempy-2][px-2]='.';
            			while(tempy!=2 &&(ary[tempy-3][px-2]=='1' ||ary[tempy-3][px-2]=='2' ||ary[tempy-3][px-2]=='3' ||ary[tempy-3][px-2]=='4' ||ary[tempy-3][px-2]=='5' ||ary[tempy-3][px-2]=='6' ||ary[tempy-3][px-2]=='7' ||ary[tempy-3][px-2]=='8' ||ary[tempy-3][px-2]=='9')) {
            				tempy=tempy-1;
            				power++;
            				intary[power]=Integer.parseInt(String.valueOf(ary[tempy-2][px-2]));
            				ary[tempy-2][px-2]='.';
            				ondalik++;
            				py=tempy;
            			}
            			int tempcount = ondalik+1;
            			for (int i = 0; i < intary.length; i++) {
            				if(intary[i]!=0) {
            					int katsayi = (int)Math.pow(10, ondalik);
            					sum = sum + (intary[i]*katsayi);
            					ondalik--;
            				}
            				
						}
            			expression.push(sum);
            			temp1.push(sum);
            			temp3.push(sum);
            			count= count + tempcount;
            			/*
            			int n2 = Integer.parseInt(String.valueOf(ary[py-2][px-3]));
                		int n1 = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
                		ary[py-2][px-3]='.';
                		ary[py-2][px-2]='.';
                		count++;
                		number = (n1*10)+n2;
                		expression.push(number);
                		*/
            		}
            		else {
            			if((ary[py-2][px-2]!='+' && ary[py-2][px-2]!='-' && ary[py-2][px-2]!='/' && ary[py-2][px-2]!='*')) {
            				number = Integer.parseInt(String.valueOf(ary[py-2][px-2]));
                			ary[py-2][px-2]='.';
                			count++;
                			expression.push(number);
                			temp1.push(number);
                			temp3.push(number);
            			}
            			else if((ary[py-2][px-2]=='+' || ary[py-2][px-2]=='-' || ary[py-2][px-2]=='/' || ary[py-2][px-2]=='*')) {
            				char tempchar = (char)ary[py-2][px-2];
            				ary[py-2][px-2]='.';
            				count++;
            				expression.push(tempchar);
            				temp1.push(tempchar);
            				temp3.push(tempchar);
            				
            			}
            		}
            	}
            	if(rkey==KeyEvent.VK_F) {
            		int counta=0;
            		boolean flagls=true;
            		while (!expression.isEmpty()) {
            			empty.push(expression.pop());
            		}
            		while (!empty.isEmpty()) {
            			if(Main.isInteger(empty.peek()))
            				counta++;
            			else {
            				counta--;
            				if(counta<1)
            					flagls=false;
            					
            			}
            			expression.push(empty.pop());
            			
            		}
            		if(counta!=1)
            			flagls=false;
            		if(!flagls) {
            			Main.add(count, ary, q);
                    	for (int i = 2; i < 12; i++) {
    						for (int j = 2; j < 12; j++) {
    							cn.getTextWindow().setCursorPosition(i,j);
    							System.out.print(ary[j-2][i-2]);
    						}
    					}
            			count=0;
                		spacecount=0;
                		mode="Free";
                		score=score-20;
                		while (!expression.isEmpty()) 
							expression.pop();
                		while (!temp1.isEmpty()) 
							temp1.pop();
                		while (!temp3.isEmpty()) 
							temp3.pop();
							
						
                		cn.getTextWindow().setCursorPosition(28,8);
                		System.out.println("                         ");
            		}
            		else {
            			mode="Evaluation";
					}
            		
            		
            	}
       
            }
//          left          right          up            down
            if(rckey=='%' || rkey==KeyEvent.VK_A) {
            	for (int i = 2; i <=11; i++) cn.getTextWindow().output(i,py,ary[py-2][i-2]);
            	cn.getTextWindow().output(px,py,ary[py-2][px-2],foreground);
            }
            if (rckey=='\'' || rkey==KeyEvent.VK_D) {
            	for (int i = 2; i <=11; i++) cn.getTextWindow().output(i,py,ary[py-2][i-2]);
            	cn.getTextWindow().output(px,py,ary[py-2][px-2],foreground);
			}
            if (rckey=='&' || rkey==KeyEvent.VK_W) {
            	for (int i = 2; i <=11; i++) cn.getTextWindow().output(px,i,ary[i-2][px-2]);
            	cn.getTextWindow().output(px,py,ary[py-2][px-2],foreground);
			}
            if (rckey=='(' || rkey==KeyEvent.VK_S) {
            	for (int i = 2; i <=11; i++) cn.getTextWindow().output(px,i,ary[i-2][px-2]);
            	cn.getTextWindow().output(px,py,ary[py-2][px-2],foreground);
			}
            if(mode.equalsIgnoreCase("Free")) Evaluationflag=true;
            if(mode.equalsIgnoreCase("Evaluation") && Evaluationflag==true) {
            	cn.getTextWindow().setCursorPosition(28,9);
          	  	while(!(temp1.isEmpty())) {
          	  		temp2.push(temp1.pop());
          	  	}
          	  	while(!(temp2.isEmpty())) {
          	  		System.out.print(temp2.peek()+" ");
          	  		temp1.push(temp2.pop());
          	  	}
            	Evaluationflag=false;
            }
            if(rkey==KeyEvent.VK_SPACE && mode.equalsIgnoreCase("Evaluation")) {
            	if(temp3.isEmpty()) {
            		int tempscore=0;
                	int control =0; // tekrar i�lemini kontrol etmesi i�in (1=int,2=char)
                	while(!(temp1.isEmpty())) {
                		String tempstr = String.valueOf (temp1.peek());
                		temp2.push(temp1.pop());
                    	int basamak =tempstr.length();
                    	if((tempstr.equals("/") || tempstr.equals("+") || tempstr.equals("-")  || tempstr.equals("*")) && control==1) tempscore = tempscore+2;
                    	if((tempstr.equals("/") || tempstr.equals("+") || tempstr.equals("-")  || tempstr.equals("*")) && (control!=1)) tempscore = tempscore+1;
                    	if(!(tempstr.equals("/") || tempstr.equals("+") || tempstr.equals("-")  || tempstr.equals("*")) && basamak==1 && control !=2) tempscore = tempscore+1;
                    	if(!(tempstr.equals("/") || tempstr.equals("+") || tempstr.equals("-")  || tempstr.equals("*")) && basamak==1 && control ==2) tempscore = tempscore+2;
                    	if(!(tempstr.equals("/") || tempstr.equals("+") || tempstr.equals("-")  || tempstr.equals("*")) && basamak>1) tempscore = tempscore +(2*basamak);
                    	if((tempstr.equals("/") || tempstr.equals("+") || tempstr.equals("-")  || tempstr.equals("*"))) control=2;
                    	if(!(tempstr.equals("/") || tempstr.equals("+") || tempstr.equals("-")  || tempstr.equals("*"))) control=1;
                	}
                	Main.add(count, ary, q);
                	for (int i = 2; i < 12; i++) {
						for (int j = 2; j < 12; j++) {
							cn.getTextWindow().setCursorPosition(i,j);
							System.out.print(ary[j-2][i-2]);
						}
					}
                	score = score +(tempscore*tempscore);
                	tempscore=0;
                	while(!(temp2.isEmpty())) temp1.push(temp2.pop());
            		while(!(expression.isEmpty())) expression.pop();
            		while(!(tempexpression.isEmpty())) tempexpression.pop();
            		while(!(temp1.isEmpty())) temp1.pop();
            		while(!(temp2.isEmpty())) temp2.pop();
            		while(!(temp3.isEmpty())) temp3.pop();
            		cn.getTextWindow().setCursorPosition(28,8);
            		System.out.print("                    ");
            		cn.getTextWindow().setCursorPosition(16,9);
            		System.out.print("                                        ");
            		cn.getTextWindow().setCursorPosition(38,2);
            		System.out.print("          ");
            		count=0;
            		spacecount=0;
            		mode="Free";
            		while(!right.isEmpty())
                		right.pop();
            		cn.getTextWindow().setCursorPosition(61,7);
            		System.out.println("    ");
            	}
            	else {
            		cn.getTextWindow().setCursorPosition(28,9);
                	System.out.print("                                      ");
                	while(!(temp3.isEmpty())) temp2.push(temp3.pop());
                	if(Main.isInteger(temp2.peek())) {
                		right.push(temp2.pop());
                	}
                	else {
                		char c=(char)temp2.pop();
        				int val1 = (int) right.pop();
        				int val2 = (int) right.pop();

        				switch (c) {
        				case '+':
        					right.push(val2 + val1);
        					break;

        				case '-':
        					right.push(val2 - val1);
        					break;
 
        				case '/':
        					right.push(val2 / val1);
        					break;

        				case '*':
        					right.push(val2 * val1);
        					break;
        				}

        			}
                	spacecount++;
                	cn.getTextWindow().setCursorPosition(28,9);
                	//for (int i = 0; i < spacecount; i++) System.out.print(" ");
                	while(!(temp2.isEmpty())) {
                		System.out.print(temp2.peek()+" ");
              	  		temp3.push(temp2.pop());
                	}
                	int axy=1;
              	  
              	  for (int i = 0; i < 7; i++) {
              		  cn.getTextWindow().setCursorPosition(61,axy);
            			System.out.println("    ");
            			axy++;
            		}
                	int anc=7;
                	while(!right.isEmpty()) {
                		cn.getTextWindow().setCursorPosition(61,anc);
                		System.out.println(right.peek());
                		rightemp.push(right.pop());
                		anc--;
                	}
                	while(!rightemp.isEmpty())
                		right.push(rightemp.pop());
            	}
            	rkey=KeyEvent.VK_X;
            	
            }
            keypr=0;    // last action  
         }
         
      }
   }
}

