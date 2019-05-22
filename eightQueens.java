import java.util.Scanner;

class eightQueens{
	static final int boardNum = 100;
	static int board[] = new int[boardNum];//8行 裡面存是在第幾列
	static char Icon[] = {'●', '．'};
	static Scanner scanner= new Scanner(System.in);
	static int changeCount = 0;

//棋盤亂數擺放
	static void random(){
		// System.out.println("==================");
		for(int i = 0; i < boardNum; i++)
			board[i] = (int)(Math.random()*8);
		// showBoard();
	}
//顯示棋盤
	static void showBoard(){
		for(int i = 0; i < boardNum; i++){
			for(int j = 0; j < boardNum; j++){
				if(board[j] == i)
					System.out.print(Icon[0]);
				else
					System.out.print(Icon[1]);
			}
			System.out.println();
		}
		for(int i = 0; i < boardNum; i++)
			System.out.print(conflict(i, board[i]) + " ");
		System.out.println("\n");
	}
//*回傳 此行衝突數(行, 列) 
	static int conflict(int x, int y){
		int n = 0;

		for(int i = 0; i < boardNum; i ++){//跟每行偵測 除了自己
			if(i == x) continue;

			if(y == board[i])//橫撞
				n++;
			else if(Math.abs(x-i) == Math.abs(y - board[i]))//斜撞
				n++;
		}
		return n;
	}
//此行選擇最少衝突數排列 *回傳是否改變
	static boolean choseGood(int x){
		boolean changeFlag = false;

		for(int i = 0; i < boardNum; i++){
			if(conflict(x, i) < conflict(x, board[x])){//當前行 衝突較原本少
				board[x] = i;
				changeFlag = true;
				changeCount++;
			}
		}
		return changeFlag;
	}
//爬山(棋盤) *回傳有幾行改變
	static int climb(){
		int n = 0;

		for(int i = 0; i < boardNum; i++)
			if(choseGood(i))
				n++;
		// System.out.println("change:" + n);

		return n;
	}
//有衝突打亂 *回傳是否打亂
	static boolean randomRestart(){
		int n = 0;
		for(int i = 0; i < boardNum; i++)
			n += conflict(i, board[i]);

		if(n != 0){
			random();
			return true;
		}else
			return false;
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int rsCount = 0;
		random();

		while(true){			
			int n = climb();//爬山演算 紀錄有幾行改變
			// showBoard();

			if(n == 0){//不能改變
				if(randomRestart()){//還有衝突就打亂
					rsCount++;
					// System.out.println("*** Restarted! Now is round " + (rsCount+1) + " ***\n");
				}else
					break;
			}				
		}
		showBoard();
		System.out.println("restart count:" + rsCount + ", change:" + changeCount);
		System.out.println(boardNum + "-Queens Using Time:" + (System.currentTimeMillis() - startTime) + " ms");
	}
}