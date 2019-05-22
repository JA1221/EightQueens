import java.util.Scanner;

class eightQueens{
	static final int boardNum = 100;
	static int board[] = new int[boardNum];//8�� �̭��s�O�b�ĴX�C
	static char Icon[] = {'��', '�D'};
	static Scanner scanner= new Scanner(System.in);
	static int changeCount = 0;

//�ѽL�ü��\��
	static void random(){
		// System.out.println("==================");
		for(int i = 0; i < boardNum; i++)
			board[i] = (int)(Math.random()*8);
		// showBoard();
	}
//��ܴѽL
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
//*�^�� ����Ĭ��(��, �C) 
	static int conflict(int x, int y){
		int n = 0;

		for(int i = 0; i < boardNum; i ++){//��C�氻�� ���F�ۤv
			if(i == x) continue;

			if(y == board[i])//�
				n++;
			else if(Math.abs(x-i) == Math.abs(y - board[i]))//�׼�
				n++;
		}
		return n;
	}
//�����ֽ̤ܳĬ�ƱƦC *�^�ǬO�_����
	static boolean choseGood(int x){
		boolean changeFlag = false;

		for(int i = 0; i < boardNum; i++){
			if(conflict(x, i) < conflict(x, board[x])){//��e�� �Ĭ���쥻��
				board[x] = i;
				changeFlag = true;
				changeCount++;
			}
		}
		return changeFlag;
	}
//���s(�ѽL) *�^�Ǧ��X�����
	static int climb(){
		int n = 0;

		for(int i = 0; i < boardNum; i++)
			if(choseGood(i))
				n++;
		// System.out.println("change:" + n);

		return n;
	}
//���Ĭ𥴶� *�^�ǬO�_����
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
			int n = climb();//���s�t�� �������X�����
			// showBoard();

			if(n == 0){//�������
				if(randomRestart()){//�٦��Ĭ�N����
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