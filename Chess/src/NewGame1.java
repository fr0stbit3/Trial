
import java.util.Scanner;

public class NewGame1 {

	public int [][] board = new int[8][9];
	public int [][] vboard = new int[8][9];
	String str = new String();
	char [] c;
	int temp;
	int moveFlag;
	Board bd = new Board();
	int [] arr = new int[4];
	boolean wcheck=false,bcheck=false,gover=false,gover1=false;
	public int bx=0,by=5,wx=7,wy=5;	// Initial Position of black and white kings!!!
	Scanner input = new Scanner(System.in);

	NewGame1()
	{
		board = bd.returnBoard();
		vboard = bd.returnBoard();
	}
	
	
	public boolean input()
	{
		int flag=0;
		boolean b=false;
		//int f=0;
		do
		{	
			bd.display(board);
			if(flag==0)
			{
				setFlag(0);
				System.out.println("White's move : ");
				str = input.nextLine();
				b=validate(str);
				//System.out.println(b);
				if(b==true)
				{
					virtualPiece(arr[0],arr[1],arr[2],arr[3]);
					bcheck=isCheck(bx,by,0); // black king under check by white move
					wcheck=isCheck(wx,wy,1);  // white king under check!
					if(bcheck==true || wcheck==true)
						System.out.println("Check!");
					//System.out.println(bcheck + " " + wcheck + wx + " " + wy);
					while(wcheck==true)
						{
							resetPiece(arr[0],arr[1],arr[2],arr[3]);
							System.out.println("Invalid Move! Please try again!");
							str = input.nextLine();
							b=validate(str);
							virtualPiece(arr[0],arr[1],arr[2],arr[3]);
							wcheck=isCheck(wx,wy,1);	
						}
						if(wcheck==false && (bcheck==true || bcheck==false))
						{
							movePiece(arr[0],arr[1],arr[2],arr[3]);
						}			
				}
				else
				{
					while(b==false)
					{
						System.out.println("Invalid Move! Please try again!");
						str = input.nextLine();
						b=validate(str);
					}
					if(b==true)
					{	
						virtualPiece(arr[0],arr[1],arr[2],arr[3]);
						bcheck=isCheck(bx,by,0);
						wcheck=isCheck(wx,wy,1);
						if(bcheck==true || wcheck==true)
							System.out.println("Check!");
						
						while(wcheck==true)
						{
							resetPiece(arr[0],arr[1],arr[2],arr[3]);
							System.out.println("Invalid Move! Please try again!");
							str = input.nextLine();
							b=validate(str);
							virtualPiece(arr[0],arr[1],arr[2],arr[3]);
							wcheck=isCheck(wx,wy,1);
						}
					}
						if(wcheck==false)
						{
							movePiece(arr[0],arr[1],arr[2],arr[3]);
						}
				}
				
				if(bcheck==true)
					gover=isCheckMate(0); // black king under check mate by white move!
				else
					gover1=isStalemate(0);
			}
			else
			{
				setFlag(1);
				System.out.println("Black's move : ");
				str = input.nextLine();
				b=validate(str);
				if(b==true)
				{
					virtualPiece(arr[0],arr[1],arr[2],arr[3]);
					wcheck=isCheck(wx,wy,1); // white king under check by black move!
					bcheck=isCheck(bx,by,0); // Black king under check!
					if(bcheck==true || wcheck==true)
						System.out.println("Check!");
					
					while(bcheck==true)
					{
						resetPiece(arr[0],arr[1],arr[2],arr[3]);
						System.out.println("Invalid Move! Please try again!");
						str = input.nextLine();
						b=validate(str);
						virtualPiece(arr[0],arr[1],arr[2],arr[3]);
						bcheck=isCheck(bx,by,0);
					}
					if(bcheck==false)
					{
						movePiece(arr[0],arr[1],arr[2],arr[3]);
					}		
				}
				else
				{	
					while(b==false)
					{
						System.out.println("Invalid Move! Please try again!");
						str = input.nextLine();
						b=validate(str);
					}
					if(b==true)
					{
						virtualPiece(arr[0],arr[1],arr[2],arr[3]);
						wcheck=isCheck(wx,wy,1);
						bcheck=isCheck(bx,by,0);
						if(bcheck==true || wcheck==true)
							System.out.println("Check!");
						
						while(bcheck==true)
						{
							resetPiece(arr[0],arr[1],arr[2],arr[3]);
							System.out.println("Invalid Move! Please try again!");
							str = input.nextLine();
							b=validate(str);
							virtualPiece(arr[0],arr[1],arr[2],arr[3]);
							bcheck=isCheck(bx,by,0);
						}
						if(bcheck==false)
						{
							movePiece(arr[0],arr[1],arr[2],arr[3]);
						}
					}
				}
				
				if(wcheck==true)
					gover=isCheckMate(1);  // White king under check mate by black move!
				else
					gover1=isStalemate(1);
			}
	
			if(flag==0)
				flag=1;
			else
				flag=0;

			
		}while(gover==false && gover1==false);	// while not check mate!
		
		bd.display(board);
		if(gover==true)
			System.out.println("Check Mate!");
		
		return gover; // true if game over!
	}
	
	public boolean validate(String s)
	{
		int j,y;
		int i,x;
		
		boolean b=false;
		c = s.toCharArray();
		try
		{
		switch(c[0])
		{
		case 'a' : j=1;break;
		case 'b' : j=2;break;
		case 'c' : j=3;break;
		case 'd' : j=4;break;
		case 'e' : j=5;break;
		case 'f' : j=6;break;
		case 'g' : j=7;break;
		case 'h' : j=8;break;
		default  : return false;
		}
		
		switch(c[3])
		{
		case 'a' : y=1;break;
		case 'b' : y=2;break;
		case 'c' : y=3;break;
		case 'd' : y=4;break;
		case 'e' : y=5;break;
		case 'f' : y=6;break;
		case 'g' : y=7;break;
		case 'h' : y=8;break;
		default  : return false; 
		}

		i=c[1]-48;
		i=8-i;	//	System.out.println(i+ " " +j);
		x=c[4]-48;
		x=8-x;	//	System.out.println(x + " " +y + " " + board[i][j] + " " + board[x][y]);
		
		if(vboard[i][j]<0 && getFlag()==0)
			return false;
		else if(vboard[i][j]>0 && getFlag()==1)
			return false;
		else if(i<=8 && x<=8)
			switch(vboard[i][j])
			{
			case 1: b=pawn(i,j,x,y,0);break;
			case -1: b=pawn(i,j,x,y,1);break;
			case 2:	b=knight(i,j,x,y,0);break;
			case -2: b=knight(i,j,x,y,1);break;
			case 3: b=bishop(i,j,x,y,0);break;
			case -3: b=bishop(i,j,x,y,1);break;
			case 5: b=rook(i,j,x,y,0);break;
			case -5: b=rook(i,j,x,y,1);break;
			case 7:b=queen(i,j,x,y,0);break;
			case -7: b=queen(i,j,x,y,1);break;
			case 9: b=king(i,j,x,y,0);break;
			case -9: b=king(i,j,x,y,1);break;
			default : return false;
			}
		else
			return false;
		
		if(b==true)
		{
			arr[0]=i;
			arr[1]=j;
			arr[2]=x;
			arr[3]=y;
		}
		}
		catch(Exception e)
		{
			return false;
		}
		
		return b;		
	}
	
	public void setFlag(int f)
	{
		moveFlag=f;
	}
	
	public int getFlag()
	{
		return moveFlag;
	}
	
	public boolean pawn(int i,int j,int x,int y,int f)
	{
		Scanner in = new Scanner(System.in);
		
		if(j==y)
			if(i==6)
			{
				if(x==i-2)
				{
					//int next=i-2;
					if(vboard[x+1][j]==0)
						if(vboard[x][j]==0)
						{
							return true;
						}
						else
							return false;
					else
						return false;
				}
				else if(x==i-1)
				{
					//int next=i--;
					if(vboard[x][j]==0)
					{
						return true;
					}
					else
						return false;
				}
				else if(x==7 && f==1)
				{
					int ch;
					System.out.println("Exchange pawn for?... 1.Queen 2.Rook 3.Bishop 4.Knight : ");
					ch = in.nextInt();
					switch(ch)
					{
					case 1 : vboard[x][j]=-7;break;
					case 2 : vboard[x][j]=-5;break;
					case 3 : vboard[x][j]=-3;break;
					case 4 : vboard[x][j]=-2;break;
					default : return false;
					}
					return true;
				}
				else
					return false;
			}
			else if(i==1)
			{	
				if(x==i+2)
				{
					//int next=x;
					if(vboard[x-1][j]==0)
						if(vboard[x][j]==0)
						{
							return true;
						}
						else
							return false;
					else
						return false;
				}
				else if(x==i+1)
				{
					//int next=i++;
					if(vboard[x][j]==0)
					{
						return true;
					}
					else
						return false;
				}
				else if(x==0 && f==0)
				{
					int ch;
					System.out.println("Exchange pawn for?... 1.Queen 2.Rook 3.Bishop 4.Knight : ");
					ch = in.nextInt();
					switch(ch)
					{
					case 1 : vboard[x][j]=7;break;
					case 2 : vboard[x][j]=5;break;
					case 3 : vboard[x][j]=3;break;
					case 4 : vboard[x][j]=2;break;
					default : return false;
					}
					return true;
				}
				else
					return false;
			}
			else
			{
				if(x==i+1)
				{
					if(f==0)
						return false;
					
					//int next=i++;
					if(vboard[x][j]==0)
					{
						return true;
					}
					else
						return false;
				}
				else if(x==i-1)
				{
					if(f==1)
						return false;
					
					if(vboard[x][j]==0)
					{
						return true;
					}
				}
				else
					return false;
			}
		else
			if(x==i-1)
				if(y==j+1 || y==j-1)
				{
					if(f==1)
						return false;
					else if(vboard[x][y]!=0)
					{
						return true;
					}
					else
						return false;
				}
				else
					return false;
			else if(x==i+1)
				if(y==j+1 || y==j-1)
				{
					if(f==0)
						return false;
					else if(vboard[x][y]!=0)
					{
						return true;
					}
					else
						return false;
				}
				else
					return false;
			else
				return false;
		
		return true;			
	}
	
	public boolean rook(int i,int j,int x,int y,int f)
	{
		int flag=0;
		int a;
		if(vboard[x][y]>0 && f==0)
			return false;
		
		if(vboard[x][y]<0 && f==1)
			return false;
		//System.out.println(i+ " " + j + " " + x + " " + y);
		if(j==y)
		{
			if(i>x) //up
			{
				for(a=i-1;a>x;a--)
				{
					if(vboard[a][j]!=0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][j]>0 && f==0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][j]<0 && f==1)
					{
						flag=1;
						return false;
					}
				}
				
				if(vboard[x][y]>0 && f==0)
					return false;
				else if(vboard[x][y]<0 && f==1)
					return false;
				
			}
			else if(i<x) //down
			{
				for(a=i+1;a<x;a++)
				{
					if(vboard[a][j]!=0)
					{
						flag=1;
						return false;	
					}
					else if(vboard[a][j]>0 && f==0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][j]<0 && f==1)
					{
						flag=1;
						return false;
					}
				}
				
				if(vboard[x][y]>0 && f==0)
					return false;
				else if(vboard[x][y]<0 && f==1)
					return false;
			}
			else if(i==x)
				return false;
		}
		else if(i==x)
		{
			if(j>y) //left
			{
				for(a=j-1;a>y;a--)
				{
					if(vboard[i][a]!=0)
					{
						flag=1;
						return false;
					}
					else if(vboard[i][a]>0 && f==0)
					{
						flag=1;
						return false;
					}
					else if(vboard[i][a]<0 && f==1)
					{
						flag=1;
						return false;
					}
				}
				
				if(vboard[x][y]>0 && f==0)
					return false;
				else if(vboard[x][y]<0 && f==1)
					return false;
			}
			else if(j<y) //right
			{
				for(a=j+1;a<y;a++)
				{
					if(vboard[i][a]!=0)
					{
						flag=1;
						return false;
					}
					else if(vboard[i][a]>0 && f==0)
					{
						flag=1;
						return false;
					}
					else if(vboard[i][a]<0 && f==1)
					{
						flag=1;
						return false;
					}
				}
				
				if(vboard[x][y]>0 && f==0)
					return false;
				else if(vboard[x][y]<0 && f==1)
					return false;
			}
			else if(j==y)
				return false;
		}
		else
			return false;	
		
		if(flag==0)
		{
			return true;
		}
		else
			return false;
	}
	
	public boolean bishop(int i,int j,int x,int y,int f)
	{
		int flag=0;
		int a,b;
		if(vboard[x][y]>0 && f==0)
			return false;
		
		if(vboard[x][y]<0 && f==1)
			return false;
		//System.out.println(i+ " " + j + " " + x + " " + y);
		if(i>x && j>y)	//left up
			{
				for(a=i-1,b=j-1;a>x && b>y;a--,b--)
					{if(vboard[a][b]!=0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][b]>0 && f==0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][b]<0 && f==1)
					{
						flag=1;
						return false;
					}}
				
				if(x!=a || y!=b)
					return false;
				
				if(vboard[x][y]>0 && f==0)
					return false;
				else if(vboard[x][y]<0 && f==1)
					return false;
			}
		else if(i>x && j<y) // right up
			{
				for(a=i-1,b=j+1;a>x && b<y;a--,b++)
					{if(vboard[a][b]!=0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][b]>0 && f==0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][b]<0 && f==1)
					{
						flag=1;
						return false;
					}}
				
				if(x!=a || y!=b)
					return false;
				
				if(vboard[x][y]>0 && f==0)
					return false;
				else if(vboard[x][y]<0 && f==1)
					return false;
					
			}
		else if(i<x && j>y) // left down
			{
				for(a=i+1,b=j-1;a<x && b>y;a++,b--)
					{if(vboard[a][b]!=0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][b]>0 && f==0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][b]<0 && f==1)
					{
						flag=1;
						return false;
					}}
				
				if(x!=a || y!=b)
					return false;
				
				if(vboard[x][y]>0 && f==0)
					return false;
				else if(vboard[x][y]<0 && f==1)
					return false;
			}
		else if(i<x && j<y) // right down
			{
				for(a=i+1,b=j+1;a<x && b<y;a++,b++)
					{if(vboard[a][b]!=0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][b]>0 && f==0)
					{
						flag=1;
						return false;
					}
					else if(vboard[a][b]<0 && f==1)
					{
						flag=1;
						return false;
					}}
				
				if(x!=a || y!=b)
					return false;
				
				if(vboard[x][y]>0 && f==0)
					return false;
				else if(vboard[x][y]<0 && f==1)
					return false;
			}
		else
			return false;
		
		
		if(flag==0)
		{
			return true;
		}
		else 
			return false;
			
	}
	
	public boolean queen(int i,int j,int x,int y,int f)
	{
		if(j==y || i==x)
			return rook(i,j,x,y,f);
		else if((i>x && j>y) || (i>x && j<y) || (i<x && j>y) || (i<x && j<y))
			return bishop(i,j,x,y,f);
		else
			return false;
	}
	
	public boolean knight(int i,int j,int x,int y,int f)
	{
		//System.out.println(i+ " " + j + " " + x + " " + y);
		if(vboard[x][y]>0 && f==0)
			return false;
		else if(vboard[x][y]<0 && f==1)
			return false;
		else if((x==i-2 && (y==j+1 || y==j-1)) || (x==i-1 && (y==j+2 || y==j-2)) || (x==i+2 && (y==j+1 || y==j-1)) || (x==i+1 && (y==j+2 || y==j-2)))
		{
			return true;
		}
		else
			return false;
	}
	
	public boolean king(int i,int j,int x,int y,int flag)
	{
		boolean b;
		//Checking for nearby king in final pos!!
		if(flag==0) //white kings move! search for -9!
		{
			if(x-1>=0 && y-1>0 && y+1<9)
			{	if(vboard[x-1][y]==-9 || vboard[x][y-1]==-9 || vboard[x][y+1]==-9 || vboard[x-1][y-1]==-9 || vboard[x-1][y+1]==-9)
				{	return false;}}
			else if(x+1<8 && y-1>0 && y+1<9)
			{	if(vboard[x+1][y]==-9  || vboard[x+1][y-1]==-9 || vboard[x+1][y+1]==-9)
				{	return false;}}
		}
		
		else if(flag==1) // black kings move! search for +9!
		{
			if(x-1>=0 && y-1>0 && y+1<9)
				{if(vboard[x-1][y]==9 || vboard[x][y-1]==9 || vboard[x][y+1]==9 || vboard[x-1][y-1]==9 || vboard[x-1][y+1]==9)
				{return false;}}
			else if(x+1<8 && y-1>0 && y+1<9)
				{if(vboard[x+1][y]==9 || vboard[x+1][y-1]==9 || vboard[x+1][y+1]==9)
					return false;}
		}
		
		if((j==y && (i==x+1 || i==x-1)) || (i==x && (j==y+1 || j==y-1)))
			{	
				b=rook(i,j,x,y,flag);
				if(b==true)
					{
						if(flag==0)
						{
							wx=x;wy=y;
						}
						else
						{
							bx=x;by=y;
						}
					}	
			}
		else if(i==x+1 || i==x-1 || j==y+1 || j==y-1)
			{
				b=bishop(i,j,x,y,flag);
				if(b==true)
				{
					if(flag==0)
					{
						wx=x;wy=y;
					}
					else
					{
						bx=x;by=y;
					}
				}
			}
		else
			return false;
		
		if(b==true)
			return true;
		else 
			return false;
	}
	
	public void movePiece(int i,int j,int x,int y)
	{
		board = vboard;
	}
	
	public void virtualPiece(int i,int j,int x,int y)
	{
		if(x==0 && vboard[i][j]==1)
		{
			vboard[i][j]=0;
		}
		else if(x==7 && board[i][j]==-1)
		{
			vboard[i][j]=0;
		}
		else
		{
			temp=vboard[x][y];
			vboard[x][y]=vboard[i][j];
			vboard[i][j]=0;
		}
		//System.out.println(i+ " " + j + " " + x + " " + y);
	}
	
	public void resetPiece(int i,int j,int x,int y)
	{
		vboard[i][j]=vboard[x][y];
		vboard[x][y]=temp;	
	}
	
	public boolean isCheck(int x,int y,int f)  // x & y are king pos!
	{
		boolean checku=false,checkd=false,checkl=false,checkr=false,checkur=false,checkul=false,checkdr=false,checkdl=false;
		//up
			for(int i=x-1;i>=0;i--)
			{
				if(i<0)
					break;
				else
					switch(vboard[i][y])
					{
					case 1 : checku=wPawnCheck(i,y);break;
					case -1 : checku=bPawnCheck(i,y);break;
					case 2 : break; 	// 12 is temp var! used coz of overloading!
					case -2 : break;
					case 3: break;
					case -3 : break;
					case 5 : if(f==0)checku=true;break;
					case -5 : if(f==1)checku=true;break;
					case 7 : if(f==0)checku=true;break;
					case -7 : if(f==1)checku=true;break;
					default : continue;
					}
					break;
				
			}
		
				//down
					for(int i=x+1;i<8;i++)
					{
							switch(board[i][y])
							{
							case 1 : checkd=wPawnCheck(i,y);break;
							case -1 : checkd=bPawnCheck(i,y);break;
							case 2 : break;
							case -2 : break;
							case 3 : break;
							case -3 : break;
							case 5 : if(f==0)checkd=true;break;
							case -5 : if(f==1)checkd=true;break;
							case 7 : if(f==0)checkd=true;break;
							case -7 : if(f==1)checkd=true;break;
							default : continue;
							}
							break;	
					}

					//left
						for(int j=y-1;j>0;j--)
						{
								switch(vboard[x][j])
								{
								case 1 : checkl=wPawnCheck(x,j);break;
								case -1 : checkl=bPawnCheck(x,j);break;
								case 2 : break;
								case -2 : break;
								case 3: break;
								case -3 : break;
								case 5 : if(f==0)checkl=true;break;
								case -5 : if(f==1)checkl=true;break;
								case 7 : if(f==0)checkl=true;break;
								case -7 : if(f==1)checkl=true;break;
								default : continue;
								}
								break;
							//}
						}
		
						// rihgt
							for(int j=y+1;j<=8;j++)
							{
									switch(vboard[x][j])
									{
									case 1 : checkr=wPawnCheck(x,j);break;
									case -1 : checkr=bPawnCheck(x,j);break;
									case 2 : break;
									case -2 : break;
									case 3 : break;
									case -3 : break;
									case 5 : if(f==0)checkr=true;break;
									case -5 : if(f==1)checkr=true;break;
									case 7 : if(f==0)checkr=true;break;
									case -7 : if(f==1)checkr=true;break;
									default : continue;
									}
									break;
								//}
							}
	
							//upleft
								for(int i=x-1,j=y-1;i>=0 && j>0;i--,j--)
								{
									//System.out.println(board[i][j]);
										switch(vboard[i][j])
										{
										case 1 : checkul=wPawnCheck(i,j);break;
										case -1 : checkul=bPawnCheck(i,j);break;
										case 2 : break;
										case -2 : break;
										case 3 : if(f==0)checkul=true;break;
										case -3 : if(f==1)checkul=true;break;
										case 5 : break;
										case -5 : break;
										case 7 : if(f==0)checkul=true;break;
										case -7 : if(f==1)checkul=true;break;
										default : continue;
										}
										break;
									//}
								}
				
								//upright
									for(int i=x-1,j=y+1;i>=0 && j<=8;i--,j++)
									{
											switch(vboard[i][j])
											{
											case 1 : checkur=wPawnCheck(i,j);break;
											case -1 : checkur=bPawnCheck(i,j);break;
											case 2 : break;
											case -2 : break;
											case 3 : if(f==0)checkur=true;break;
											case -3 : if(f==1)checkur=true;break;
											case 5 : break;
											case -5 : break;
											case 7 : if(f==0)checkur=true;break;
											case -7 : if(f==1)checkur=true;break;
											default : continue;
											}
											break;
										//}
									}
				
									//downlwft
											for(int i=x+1,j=y-1;i<8 && j>0;i++,j--)
											{
					
													switch(vboard[i][j])
													{
													case 1 : checkdl=wPawnCheck(i,j);break;
													case -1 : checkdl=bPawnCheck(i,j);break;
													case 2 : break;
													case -2 : break;
													case 3 : if(f==0)checkdl=true;break;
													case -3 : if(f==1)checkdl=true;break;
													case 5 : break;
													case -5 : break;
													case 7 : if(f==0)checkdl=true;break;
													case -7 : if(f==1)checkdl=true;break;
													default : continue;
													}
													break;
												//}
											}
	
										// downright	
												for(int i=x+1,j=y+1;i<8 && j<=8;i++,j++)
												{
														switch(vboard[i][j])
														{
														case 1 : checkdr=wPawnCheck(i,j);break;
														case -1 : checkdr=bPawnCheck(i,j);break;
														case 2 : break;
														case -2 : break;
														case 3 : if(f==0)checkdr=true;break;
														case -3 : if(f==1)checkdr=true;break;
														case 5 : break;
														case -5 : break;
														case 7 : if(f==0)checkdr=true;break;
														case -7 : if(f==1)checkdr=true;break;
														default : continue;
														}
														break;
												//	}
												}
												
				boolean check1=false,check2=false;
				
				
							check1=wKnightCheck(bx,by);
							
							check2=bKnightCheck(wx,wy);
						//	System.out.println(check1 + " " + check2);
						boolean checkc=false;
							if(f==1)
								checkc=check2;
							else if(f==0)
								checkc=check1;
					
																				
				//System.out.println(checku + "" + checkd + checkl + checkr + checkul + checkur + checkdr + checkdl);
			//	System.out.println(c1+ " " +c2);
				boolean check = checku || checkd || checkr || checkl || checkul || checkur || checkdr || checkdl ||checkc;
				if(check==true)
					return true;
				else
					return false;
				
	}
	
	public boolean wPawnCheck(int x,int y)
	{
		//check by white pawn
			if(y==8)
			{
				if(vboard[x-1][y-1]==-9)
					{
				//		System.out.println("Check!");
						return true;
					}
			}
			else if(y==1)
			{
				if(vboard[x-1][y+1]==-9)
					{
				//		System.out.println("Check!");
						return true;
					}
			}
			else if(vboard[x-1][y-1]==-9 || vboard[x-1][y+1]==-9)
					{
				//		System.out.println("Check!");
						return true;
					}
			
					return false;
	}
	
	public boolean bPawnCheck(int x,int y)
	{
		// check by black pawn
			if(y==8)
			{
				if(vboard[x+1][y-1]==9)
					{
				//		System.out.println("Check!");
						return true;
					}
			}
			else if(y==1)
			{
				if(vboard[x+1][y+1]==9)
					{
				//		System.out.println("Check!");
						return true;
					}
			}
			else if(vboard[x+1][y+1]==9 || vboard[x+1][y-1]==9)
					{
				//		System.out.println("Check!");
						return true;
					}
			
				return false;
	}
	
	public boolean wKnightCheck(int x,int y)  // x, y are black kings! pos!
	{
		// Need to check if [][] goes out f bounds!!!!
		for(int i=0;i<8;i++)
			for(int j=1;j<=8;j++)
			{
				if(vboard[i][j]==2)
				{
					if(j+2<=8 && j-2>0 && i+2<8)
						if(vboard[i+2][j+1]==-9 || vboard[i+2][j-1]==-9)
						{
						//	System.out.println("Check!");
							return true;
						}
						else if(vboard[i+1][j+2]==-9 || vboard[i+1][j-2]==-9)
						{
						//	System.out.println("Check!");
							return true;
						}
					
					if(i-2>=0 && j+2<=8 && j-2>0)
						if(vboard[i-2][j+1]==-9 || vboard[i-2][j-1]==-9)
						{
						//	System.out.println("Check!");
							return true;
						}
						else if(vboard[i-1][j+2]==-9 || vboard[i-1][j-2]==-9)
						{
						//	System.out.println("Check!");
							return true;
						}
				}
			}
		return false;
	}
	
	public boolean bKnightCheck(int x,int y) // white kings pos!
	{
		for(int i=0;i<8;i++)
			for(int j=1;j<=8;j++)
			{
				if(vboard[i][j]==-2)
				{
					if(j+2<=8 && j-2>0 && i+2<8)
						if(vboard[i+2][j+1]==9 || vboard[i+2][j-1]==9)
						{
						//	System.out.println("Check!");
							return true;
						}
						else if(vboard[i+1][j+2]==9 || vboard[i+1][j-2]==9)
						{
						//	System.out.println("Check!");
							return true;
						}
					
					if(i-2>=0 && j+2<=8 && j-2>0)
						if(vboard[i-2][j+1]==9 || vboard[i-2][j-1]==9)
						{
						//	System.out.println("Check!");
							return true;
						}
						else if(vboard[i-1][j+2]==9 || vboard[i-1][j-2]==9)
						{
							//System.out.println("Check!");
							return true;
						}
				}
			}
		return false;
	}

	
	public boolean isCheckMate(int flag) // fn called only when under check! final pos of piece that caused check!
	{
		int a,b;
		int k,l;
		boolean c1=true;
		boolean cu=true,cd=true,cl=true,cr=true,cul=true,cur=true,cdl=true,cdr=true;
		if(flag==0)
		{
			a=bx;
			b=by;
			
			//	System.out.println(a + " " + b);
				if(a!=0)
				{ 
					//up
					if(vboard[a-1][b]>=0)
					{
						k=a-1;
						virtualPiece(a,b,a-1,b);
						cu=isCheck(a-1,b,0);				// check caused by white move!
						resetPiece(a,b,a-1,b);
					//	System.out.println(vboard[a-1][b]);
					}
						
					if(vboard[a-1][b-1]>=0)
					{
						k=a-1;l=b-1;
						virtualPiece(a,b,a-1,b-1);
						cul=isCheck(a-1,b-1,0);
						resetPiece(a,b,a-1,b-1);
					}
					
					if(vboard[a-1][b+1]>=0)
					{
						k=a-1;l=b+1;
						virtualPiece(a,b,a-1,b+1);
						cur=isCheck(a-1,b+1,0);
						resetPiece(a,b,a-1,b+1);
					}
				}
				
				if(vboard[a+1][b]>=0)
				{
					k=a+1;
					virtualPiece(a,b,a+1,b);
					cd=isCheck(a+1,b,0);
					resetPiece(a,b,a+1,b);
				}
				
				if(vboard[a][b-1]>=0)
				{
					l=b-1;
					virtualPiece(a,b,a,b-1);
					cl=isCheck(a,b-1,0);
					resetPiece(a,b,a,b-1);
				}
				
				if(vboard[a][b+1]>=0)
				{
					l=b+1;
					virtualPiece(a,b,a,b+1);
					cr=isCheck(a,b+1,0);
					resetPiece(a,b,a,b+1);
				}

				if(vboard[a+1][b-1]>=0)
				{
					k=a+1;l=b-1;
					virtualPiece(a,b,a+1,b-1);
					cdl=isCheck(a+1,b-1,0);
					resetPiece(a,b,a+1,b-1);
				}
				
				if(vboard[a+1][b+1]>=0)
				{
					k=a+1;l=b+1;
					virtualPiece(a,b,a+1,b+1);
					cdr=isCheck(a+1,b+1,0);
					resetPiece(a,b,a+1,b+1);
					//System.out.println(cdr);
				}
			int m,n;	
				for(int i=0;i<8;i++)
				{	for(int j=1;j<=8;j++)
					
						//if(i==a && j==b)
						//	continue;
						
						if(vboard[i][j]<0)
						{
							//System.out.println(i + " " + j);
										for(m=0;m<8;m++)
										{	for(n=1;n<=8;n++)
										//System.out.println( m + " " + n);
													if(vboard[i][j]==-1)
													{if(pawn(i,j,m,n,1)==true)
													{	virtualPiece(i,j,m,n);
														c1=isCheck(a,b,0);
														resetPiece(i,j,m,n);
														}}
										
													else if(vboard[i][j]==-2)
													{if(knight(i,j,m,n,1)==true)
														{virtualPiece(i,j,m,n);
														c1=isCheck(a,b,0);
														resetPiece(i,j,m,n);
														}}
										
													else if(vboard[i][j]==-3)
													{if(bishop(i,j,m,n,1)==true)
														{virtualPiece(i,j,m,n);
														c1=isCheck(a,b,0);
														resetPiece(i,j,m,n);
														}}
										
													else if(vboard[i][j]==-5)
													{if(rook(i,j,m,n,1)==true)
														{virtualPiece(i,j,m,n);
														c1=isCheck(a,b,0);
														resetPiece(i,j,m,n);
														}}
										
													else if(vboard[i][j]==-7)
													{if(queen(i,j,m,n,1)==true)
														{virtualPiece(i,j,m,n);
														c1=isCheck(a,b,0);
														resetPiece(i,j,m,n);
														}}
						
													if(c1==false)
														break;
											}			
						}
						if(c1==false)
							break;	
					}
				
				boolean ci = c1 ;
			//	System.out.println(c1); 
				boolean cf = cu && cd && cl && cr && cul && cur && cdl && cdr && ci;
			//	System.out.println(c1 + "" + cu+""+cd+""+cl+""+cr+""+cul+""+cur+""+cdr +""+cdl);
				if(cf==true)
					return true;
				else
					return false;
			
		}
		else
		{
			a=wx;
			b=wy;
		//	System.out.println(a+ " " + b);
				//System.out.println(a + " " + b);
				if(vboard[a-1][b]<=0)
				{
					k=a-1;
					virtualPiece(a,b,a-1,b);
					cu=isCheck(a-1,b,1);
					resetPiece(a,b,a-1,b);
				}
				
				if(vboard[a][b-1]<=0)
				{
					l=b-1;
					virtualPiece(a,b,a,b-1);
					cl=isCheck(a,b-1,1);
					resetPiece(a,b,a,b-1);
				}
				if(vboard[a][b+1]<=0)
				{
					l=b+1;
					virtualPiece(a,b,a,b+1);
					cd=isCheck(a,b+1,1);
					resetPiece(a,b,a,b+1);
				}
				if(vboard[a-1][b-1]<=0)
				{
					k=a-1;l=b-1;
					virtualPiece(a,b,a-1,b-1);
					cul=isCheck(a-1,b-1,1);
					resetPiece(a,b,a-1,b-1);
				}
				if(vboard[a-1][b+1]<=0)
				{
					k=a-1;l=b+1;
					virtualPiece(a,b,a-1,b+1);
					cur=isCheck(a-1,b+1,1);
					resetPiece(a,b,a-1,b+1);
				}
				if(a!=7)
				{
					if(vboard[a+1][b]<=0)
					{
						k=a+1;
						virtualPiece(a,b,a+1,b);
						cd=isCheck(a+1,b,1);
						resetPiece(a,b,a+1,b);
					}
					if(vboard[a+1][b-1]<=0)
					{
						k=a+1;l=b-1;
						virtualPiece(a,b,a+1,b-1);
						cdl=isCheck(a+1,b-1,1);
						resetPiece(a,b,a+1,b-1);
					}
					if(vboard[a+1][b+1]<=0)
					{
						k=a+1;l=b+1;
						virtualPiece(a,b,a+1,b+1);
						cdr=isCheck(a+1,b+1,1);
						resetPiece(a,b,a+1,b+1);
					}
				}
				
				for(int i=0;i<8;i++)
				{	for(int j=1;j<=8;j++)
					
					//	if(i==a && j==b)
					//		continue;
						
						if(vboard[i][j]>0)
						{
							//System.out.println(i+ " " + j);
										for(int m=0;m<8;m++)
											for(int n=1;n<=8;n++)
											{
												if(vboard[i][j]==1)
												{if(pawn(i,j,m,n,0)==true)
													{	virtualPiece(i,j,m,n);
														c1=isCheck(a,b,1);
														resetPiece(i,j,m,n);
														if(c1==false)
														{break;}}}
										
												else if(vboard[i][j]==2)
												{if(knight(i,j,m,n,0)==true)
														{virtualPiece(i,j,m,n);
														c1=isCheck(a,b,1);
														resetPiece(i,j,m,n);
														if(c1==false)
														{break;}}}
										
												else if(vboard[i][j]==3)
												{if(bishop(i,j,m,n,0)==true)
														{virtualPiece(i,j,m,n);
														c1=isCheck(a,b,1);
														resetPiece(i,j,m,n);
														if(c1==false)
														{break;}}}
										
												else if(vboard[i][j]==5)
												{if(rook(i,j,m,n,0)==true)
														{virtualPiece(i,j,m,n);
														c1=isCheck(a,b,1);
														resetPiece(i,j,m,n);
														if(c1==false)
														{break;}}}
										
												else if(vboard[i][j]==7)
												{if(queen(i,j,m,n,0)==true)
														{virtualPiece(i,j,m,n);
														c1=isCheck(a,b,1);
														resetPiece(i,j,m,n);
														if(c1==false)
														{break;}}}
						
												if(c1==false)
													break;	
										}
						}
						if(c1==false)
							break;
					}
					
				//System.out.println(c1);
				boolean ci = c1 ;
			//	System.out.println(c1 + "" + cu+""+cd+""+cl+""+cr+""+cul+""+cur+""+cdr +""+cdl);
				boolean cf = cu && cd && cl && cr && cul && cur && cdl && cdr && ci;
				if(cf==true)
					return true;
				else
					return false;
		}
	}
	
	public boolean isStalemate(int f)
	{
		int flag=0;int k,l,a,b;
		boolean cu=true,cd=true,cl=true,cr=true,cul=true,cur=true,cdl=true,cdr=true;
		boolean c1=false;
		if(f==0)
		{
			a=bx;
			b=by;
			
				if(a!=0)
				{ 
					//up
					if(vboard[a-1][b]>=0)
					{
						k=a-1;
						virtualPiece(a,b,a-1,b);
						cu=isCheck(a-1,b,0);				// check caused by white move!
						resetPiece(a,b,a-1,b);
					}
						
					if(vboard[a-1][b-1]>=0)
					{
						k=a-1;l=b-1;
						virtualPiece(a,b,a-1,b-1);
						cul=isCheck(a-1,b-1,0);
						resetPiece(a,b,a-1,b-1);
					}
					
					if(vboard[a-1][b+1]>=0)
					{
						k=a-1;l=b+1;
						virtualPiece(a,b,a-1,b+1);
						cur=isCheck(a-1,b+1,0);
						resetPiece(a,b,a-1,b+1);
					}
				}
				
				if(vboard[a+1][b]>=0)
				{
					k=a+1;
					virtualPiece(a,b,a+1,b);
					cd=isCheck(a+1,b,0);
					resetPiece(a,b,a+1,b);
				}
				
				if(vboard[a][b-1]>=0)
				{
					l=b-1;
					virtualPiece(a,b,a,b-1);
					cl=isCheck(a,b-1,0);
					resetPiece(a,b,a,b-1);
				}
				
				if(vboard[a][b+1]>=0)
				{
					l=b+1;
					virtualPiece(a,b,a,b+1);
					cr=isCheck(a,b+1,0);
					resetPiece(a,b,a,b+1);
				}

				if(vboard[a+1][b-1]>=0)
				{
					k=a+1;l=b-1;
					virtualPiece(a,b,a+1,b-1);
					cdl=isCheck(a+1,b-1,0);
					resetPiece(a,b,a+1,b-1);
				}
				
				if(vboard[a+1][b+1]>=0)
				{
					k=a+1;l=b+1;
					virtualPiece(a,b,a+1,b+1);
					cdr=isCheck(a+1,b+1,0);
					resetPiece(a,b,a+1,b+1);
				}
				
			for(int i=0;i<8;i++)
			{	for(int j=1;j<=8;j++)
				{	if(vboard[i][j]<0)
					{	
						for(int x=0;x<8;x++)
						{	for(int y=1;y<=8;y++)
							{	if(vboard[i][j]==-1)
								{	if(pawn(i,j,x,y,1)==true)
									{	virtualPiece(i,j,x,y);
										c1=isCheck(bx,by,0);
										resetPiece(i,j,x,y);
										if(c1==true)
										flag=1;
										else flag=0;}
									else flag=1;}
								else if(vboard[i][j]==-2)
								{	if(knight(i,j,x,y,1)==true)
									{	virtualPiece(i,j,x,y);
										c1=isCheck(bx,by,0);
										resetPiece(i,j,x,y);
										if(c1==true)
											flag=1;
										else flag=0;}
									else flag=1;}
								else if(vboard[i][j]==-3)
								{	if(bishop(i,j,x,y,1)==true)
									{	virtualPiece(i,j,x,y);
										c1=isCheck(bx,by,0);
										resetPiece(i,j,x,y);
										if(c1==true)
											flag=1;
										else flag=0;}
									else flag=1;}
								else if(vboard[i][j]==-5)
								{	if(rook(i,j,x,y,1)==true)
									{	virtualPiece(i,j,x,y);
										c1=isCheck(bx,by,0);
										resetPiece(i,j,x,y);
										if(c1==true)
											flag=1;
										else flag=0;}
									else flag=1;}
								else if(vboard[i][j]==-7)
								{	if(queen(i,j,x,y,1)==true)
									{	virtualPiece(i,j,x,y);
										c1=isCheck(bx,by,0);
										resetPiece(i,j,x,y);
										if(c1==true)
											flag=1;
										else flag=0;}
									else flag=1;}
							}
							if(flag==0)
								break;
						}
					}
				}
			if(flag==0)
				break;
			}
			if(flag==1)
				c1=true;
			boolean cf = cu && cd && cl && cr && cul && cur && cdl && cdr && c1;
			//System.out.println(c1 + "" + cu+""+cd+""+cl+""+cr+""+cul+""+cur+""+cdr +""+cdl);
			if(cf==true)
				{
					System.out.println("Stalemate!");
					return true;
				}
		}
		else if(f==1)
		{
			a=wx;
			b=wy;
		
				if(vboard[a-1][b]<=0)
				{
					k=a-1;
					virtualPiece(a,b,a-1,b);
					cu=isCheck(a-1,b,1);
					resetPiece(a,b,a-1,b);
				}
				
				if(vboard[a][b-1]<=0)
				{
					l=b-1;
					virtualPiece(a,b,a,b-1);
					cl=isCheck(a,b-1,1);
					resetPiece(a,b,a,b-1);
				}
				if(vboard[a][b+1]<=0)
				{
					l=b+1;
					virtualPiece(a,b,a,b+1);
					cd=isCheck(a,b+1,1);
					resetPiece(a,b,a,b+1);
				}
				if(vboard[a-1][b-1]<=0)
				{
					k=a-1;l=b-1;
					virtualPiece(a,b,a-1,b-1);
					cul=isCheck(a-1,b-1,1);
					resetPiece(a,b,a-1,b-1);
				}
				if(vboard[a-1][b+1]<=0)
				{
					k=a-1;l=b+1;
					virtualPiece(a,b,a-1,b+1);
					cur=isCheck(a-1,b+1,1);
					resetPiece(a,b,a-1,b+1);
				}
				if(a!=7)
				{
					if(vboard[a+1][b]<=0)
					{
						k=a+1;
						virtualPiece(a,b,a+1,b);
						cd=isCheck(a+1,b,1);
						resetPiece(a,b,a+1,b);
					}
					if(vboard[a+1][b-1]<=0)
					{
						k=a+1;l=b-1;
						virtualPiece(a,b,a+1,b-1);
						cdl=isCheck(a+1,b-1,1);
						resetPiece(a,b,a+1,b-1);
					}
					if(vboard[a+1][b+1]<=0)
					{
						k=a+1;l=b+1;
						virtualPiece(a,b,a+1,b+1);
						cdr=isCheck(a+1,b+1,1);
						resetPiece(a,b,a+1,b+1);
					}
				}
			
			for(int i=0;i<8;i++)
			{	for(int j=1;j<=8;j++)
				{	if(vboard[i][j]>0)
					{	for(int x=0;x<8;x++)
						{	for(int y=1;y<=8;y++)
							{	if(vboard[i][j]==1)
								{	if(pawn(i,j,x,y,0)==false)
										flag=1;
									else flag=0;}
								else if(vboard[i][j]==2)
								{	if(knight(i,j,x,y,0)==false)
									flag=1;
									else flag=0;}
								else if(vboard[i][j]==3)
								{	if(bishop(i,j,x,y,0)==false)
									flag=1;
									else flag=0;}
								else if(vboard[i][j]==5)
								{	if(rook(i,j,x,y,0)==false)
									flag=1;
									else flag=0;}
								else if(vboard[i][j]==7)
								{	if(queen(i,j,x,y,0)==false)
									flag=1;
									else flag=0;}
								else if(vboard[i][j]==9)
								{	if(king(i,j,x,y,0)==false)
									flag=1;
									else flag=0;}
							}
							if(flag==0)
								break;
						}
					}
				}
			if(flag==0)
				break;
			}
			
			if(flag==1)
				c1=true;
			boolean cf = cu && cd && cl && cr && cul && cur && cdl && cdr && c1;
			if(cf==true)
				{
					System.out.println("Stalemate!");
					return true;
				}
		}	
			return false;
	}
	
}