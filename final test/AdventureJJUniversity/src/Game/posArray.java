package Game;

import java.util.HashMap;
// ���� �÷��� ��ǥ Ŭ����
public class posArray {
	// tele : �ڷ���Ʈ ��� �Ķ��� ��ġ�� ����� �� �ش��ϴ� �ʰ� ������ ��ġ�� X,Y ���� ����� �迭 (x, y, map) ������ ����Ǿ�����
	protected static int tele[][][];
	// escape Ŭ�������� �ִ� �󺧹迭 �缳������ ���� ������ ������ �ش��ϴ� �ǹ��̸����� �ٲ��ִ� �ؽ���
	protected static HashMap<Integer, String> numToPlace;
	// �ʿ��� ��ȣ�ۿ� �� �� �ִ� ��ġ�� ������ ��ȣ�ۿ� �ǹ� ��ȣ�� �˷��ִ� �ؽ���
	protected static HashMap<Integer, Integer> placeToNum;
	
	public posArray() {
		int a [][][] =
		{ 
			//0~9
			{ {250,450,30} ,{450,200,1} , {240,450,27} },
			{ {250,450,23} ,{50,200,0} ,{450,200,2} },		
			{ {230,450,37} ,{50,200,1} ,{450,200,3} ,{0,450,37} }, 
			{ {230,450,33} ,{50,200,2} ,{450,200,4} ,{470,450,38} }, 
			{ {230,450,38} ,{50,200,3} ,{450,200,5} ,{0,450,38} }, 
			{ {350,450,6} ,{50,200,4} ,{450,50,17} }, 
			{ {330,450,7} ,{20,250,35} ,{30,60,38} ,{350,20,5} ,{450,430,15} ,{450,180,15} }, 
			{ {30,30,20} ,{30,300,20} ,{30,30,40} ,{330,30,6} ,{450,100,8} },
			{ {50,100,7} ,{400,30,19} ,{450,100,9} },
			{ {50,100,8} ,{400,30,13} ,{450,100,10} },
			//10~19
			{ {50,100,9} ,{300,30,11} },
			{ {300,450,10} ,{30,180,13} ,{280,30,12} },
			{ {280,450,11} ,{30,280,13} },
			{ {250,450,9} ,{150,30,14} ,{450,30,12} ,{400,450,11}  },
			{ {400,450,13} },
			{ {30,180,6} ,{30,450,6} ,{30,30,16} },
			{ {20,450,15} ,{30,20,17} },
			{ {340,450,5} ,{450,400,18} ,{450,80,18} ,{450,470,16} },
			{ {30,130,17} ,{30,450,17} },
			{ {450,450,8} },
			//20~29
			{ {30,340,21} ,{80,50,31} ,{450,50,7} ,{300,50,7} },
			{ {50,30,22} ,{450,350,20} },
			{ {30,50,28} ,{230,30,24} ,{330,30,32} ,{380,450,21} },
			{ {230,450,24} ,{30,450,25} ,{30,430,26} ,{230,30,1} ,{450,430,37}, {450,60,37} ,{450,380,36} },
			{ {90,450,22} ,{30,100,25} ,{230,30,23} },
			{ {230,450,39} ,{30,330,29} ,{50,140,30} ,{240,40,27} ,{450,50,23} ,{470,150,24}},
			{ {450,420,23} ,{30,410,27} },
			{ {300,450,25} ,{40,30,0} ,{450,430,26} },
			{ {300,30,29} ,{450,80,22} },
			{ {240,450,28} ,{290,30,30} ,{450,80,25} },
			//30~39
			{ {290,450,29} ,{450,30,0} ,{350,430,25} },
			{ {80,450,20} ,{30,230,32} ,{30,350,32} ,{400,30,34} ,{430,30,40} },
			{ {30,380,22} ,{450,50,33} ,{450,450,31} ,{450,300,31} },
			{ {330,450,32} ,{30,230,36} ,{30,80,37} ,{330,30,3} ,{450,60,38} ,{400,450,34} },
			{ {100,450,31} ,{200,30,33} ,{450,200,35} },
			{ {30,250,34} ,{450,150,6} },
			{ {30,50,23} ,{200,40,37} ,{450,210,33} },
			{ {210,450,36} ,{30,170,23} ,{30,430,23} ,{380,30,2} ,{70,30,2} ,{450,330,33} },
			{ {30,330,33} ,{130,30,3} ,{380,30,4} ,{80,30,4} ,{450,450,6} },
			{ {250,30,25} },
			//40
			{ {30,130,31} ,{430,230,7}  }
		};
		this.tele = a;
		
		HashMap<Integer, String> h = new HashMap<Integer,String>();
		h.put(0, "����1��"); h.put(1, "����2��"); h.put(2, "���нǽ���"); h.put(3, "������"); h.put(4, "������");
		h.put(5, "������(ü����)"); h.put(6, "����������"); h.put(7, "�����Ľ�"); h.put(8, "���к���"); h.put(9, "���������");
		h.put(10, "������"); h.put(11, "���б�ȸ"); h.put(12, "������"); h.put(13, "�������ǽ���"); h.put(14, "�л�ȸ��");
		h.put(15, "��ȭ��"); h.put(16, "õ���"); h.put(17, "������"); h.put(18, "�̻�����"); h.put(19, "â����");
		h.put(20, "EM������"); h.put(21, "EM��Ȱ��ȭ��"); h.put(22, "����������"); h.put(23, "��óâ����"); h.put(24, "���Ȧ");
		h.put(25, "�����Ǽ���"); h.put(26, "�������Ű�");
		// ex. ������ ������ 0�� ������ �̼Ƕ󺧿� ����1���̶�� ������ -> �� 27���� ������ ������ 0��(����1��)�� �ִٰ� �˷��ش�
		this.numToPlace = h;
		
		
		
		HashMap<Integer, Integer> h1 = new HashMap<Integer, Integer>();
		h1.put(27, 0); h1.put(23, 1); h1.put(25, 2); h1.put(37, 3); h1.put(38, 4);
		h1.put(1, 5); h1.put(3, 6); h1.put(3, 7); h1.put(18, 8); h1.put(15, 9);
		h1.put(15, 10); h1.put(6, 11); h1.put(8, 12); h1.put(19, 13); h1.put(0, 14);
		h1.put(21, 15); h1.put(11, 16); h1.put(12, 17); h1.put(12, 18); h1.put(13, 19);
		h1.put(14, 20); h1.put(14, 21); h1.put(31, 22); h1.put(28, 23); h1.put(15, 24); 
		h1.put(31, 25); h1.put(15, 26); 
		this.placeToNum = h1;
		
	}

}