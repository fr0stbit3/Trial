

public class Board {

	public int [][] board = new int[8][9];
	
	Board()
	{
		int a=8;
		for(int i=0;i<8;i++)
			{
				board[i][0]=a;
				a--;
			}
		
	/*	a=65;
		for(int j=1;j<=8;j++)
		{
			board[8][j]=a;
			a++;
		}*/
		
		
		for(int j=1;j<=8;j++)
		{
			board[1][j]=-1;
			board[6][j]=1;
		}
	
		board[0][1]=board[0][8]=-5;
		board[7][1]=board[7][8]=5;
		board[0][2]=board[0][7]=-2;
		board[7][2]=board[7][7]=2;
		board[0][3]=board[0][6]=-3;
		board[7][3]=board[7][6]=3;
		board[0][4]=-7;
		board[0][5]=-9;
		board[7][4]=7;
		board[7][5]=9;
	}
	
	public int [][] returnBoard()
	{
		return board;
	}
	
	public void display(int [][] b)
	{
		board=b;
		char c;
		for(int i=0;i<8;i++)
		{
			System.out.print(8-i);
			System.out.print("  ");
			for(int j=1;j<9;j++)
			{
				System.out.print("   ");
				if(board[i][j]!=0)
					switch(board[i][j])
					{
					case 1 : System.out.print("WP");break;
					case -1 : System.out.print("BP");break;
					case 2 : System.out.print("WN");break;
					case -2 : System.out.print("BN");break;
					case 3 : System.out.print("WB");break;
					case -3 : System.out.print("BB");break;
					case 5 : System.out.print("WR");break;
					case -5 : System.out.print("BR");break;
					case 7 : System.out.print("WQ");break;
					case -7 : System.out.print("BQ");break;
					case 9 : System.out.print("WK");break;
					case -9 : System.out.print("BK");break;
					}
				else
					System.out.print("--");
			}
			System.out.println("    ");
		}
		
		System.out.println("");
		System.out.print("      A    B    C    D    E    F    G    H"); 
		System.out.println("");
	}
}

