import java.util.*;

public class SaleBook {
	public static void main(String [] args) {
		System.out.println("��å�����Դϴ�.\nID�� password�� �Է��ϼ���  (ȸ�������� 'Signin'�� �����ּ���.): ");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();  String pw = sc.next();

		log login = new log();
		String[][] user = users.getUser();
		String[][] book = books.getBook();
		String[][] mybook = mybooks.getMybook();
		login.logIn(id, pw, user);  //�α��� 
		
		//test
		user[0][0] = "1"; user[0][1] = "2"; user[0][2] = "3"; user[0][3] = "4"; user[0][4] = "5";
		user[1][0] = "11"; user[1][1] = "22"; user[1][2] = "33"; user[1][3] = "44"; user[1][4] = "55";
		book[0][0] = "a"; book[0][1] = "b"; book[0][2] = "c"; book[0][3] = "d"; book[0][4] = "e"; book[0][5] = "11"; book[0][6] = "f"; book[0][7] = "g";
		book[1][0] = "aa"; book[1][1] = "bb"; book[1][2] = "cc"; book[1][3] = "dd"; book[1][4] = "ee"; book[1][5] = "1"; book[1][6] = "ff"; book[1][7] = "gg";
	
		//����
		if (login.logIn(id, pw, user) == 1) {
			System.out.println("�α��ο� �����߽��ϴ�.");
			user us = new user();
			System.out.println("å �˻��� ���ϸ� 1��, å ����� ���ϸ� 2��, å�� ��ġ�ų� ������ ���ϸ� 3���� �����ּ���");
			int num = sc.nextInt();
			if(num == 1)
				us.searchBook(id, book, user);
			
			if(num == 2)
				books.makeBook();
			
			if(num == 3) {
				mybooks.getMybook();
				us.mybookList(id, book , mybook );
			}
		}
			
		//ȸ������
		if (login.logIn(id, pw, user) == 2) {
			users.makeUser();
			System.out.println("ȸ�����Կ� �����߽��ϴ�");
		}
		if (login.logIn(id, pw, user) == 3) {
			System.out.println("ȸ�����Կ� �����߽��ϴ�");
		}
	
		//������	
		if (login.logIn(id, pw, user) == 0) {
			System.out.println("�����ڷ� �α����մϴ�.");
			admin ad = new admin();
			System.out.println("������� ������ �����ϱ� ���ϸ� 1, å ������ ���ϸ� 2���� �����ּ���");
			int num = sc.nextInt();
			if(num == 1)
				ad.controlUser(user);
			
			if(num == 2)
				ad.deleteBook(book, mybook, user ); 
		}
	
		sc.close(); 
	}

	static class books{
		static String book[][] = new String[30][8];  //book list ����
		
		static String[][] getBook() {
			return book;
		}
		
		static String[][] makeBook(){
			System.out.println("���ʴ�� å����, ISBN��ȣ, ����, ���ǻ�, ���ǿ���, �Ǹ���, ��ϰ���, ����(Excellent, Good, Fair)�� ���ÿ�");	
			Scanner sc2 = new Scanner(System.in);
			
			String bookName = sc2.next();
			String ISBN = sc2.next();
			String author = sc2.next();
			String publisher = sc2.next();
			String year = sc2.next();
			String sellerId = sc2.next();
			String price = sc2.next();
			String Statement = sc2.next();
			
			for (int i = 0; i<30; i++) {  //�� 30���� ����� �� ���� ����� �κп�  �ִ´�. 
				if (book[i][0] == null) {
					book[i][0] = bookName;
					book[i][1] = ISBN;
					book[i][2] = author;
					book[i][3] = publisher;
					book[i][4] = year;
					book[i][5] = sellerId;
					book[i][6] = price;
					book[i][7] = Statement;
					
					System.out.println((i+1)+"��°�� å�� ����Ǿ����ϴ�.");	
					break;
				}
				
			}	
			
			sc2.close();	
		return book;	
			}
		
		}

	static class users{
		static String user[][] = new String[30][5];
		
		static String[][] getUser() {
			return user;
		}
		
		static String[][] makeUser(){
			System.out.println("���ʴ�� id, password, name, number, e-mail�� ���ÿ�");	
			Scanner sc1 = new Scanner(System.in);
			
			String signId = sc1.next();
			String signPw = sc1.next();
			String signName = sc1.next();
			String signNumber = sc1.next();
			String signEmail = sc1.next();

				
			for (int i = 0; i<30; i++) {  //�� 30���� ����� �� ���� ����� �κп�  �ִ´�. 
				if (user[i][0] == null) {
					user[i][0] = signId;
					user[i][1] = signPw;
					user[i][2] = signName;
					user[i][3] = signNumber;
					user[i][4] = signEmail;
					break;
				}
			}
			sc1.close();
			return user;
		}
	}
	
	static class mybooks{
		static String mybook[][] = new String[30][8];

		static String[][] getMybook() {
			return mybook;
		}
		static String[][] getMybook(String id, String[][]book ){
			System.out.println("����� å��");
			for(int j=0; j<30; j++) {
				for(int k=0; k<30; k++) {
					for(int l=0; l<30; l++) {
						if(id.equals(book[j][5])) { //seller id�� ����� id�� ���� ��� 
							if(mybook[k][0] != null) {
								mybook[k][l] =  book[j][l];
								System.out.println(k +"�� : "+ mybook[k][0]);	}
						}
					}
				}	
			}
			return mybook;
		}
	}
}


class admin{
	void deleteBook(String[][]book,String[][]mybook,String[][]user ) {
		Scanner sc = new Scanner(System.in);
		String seller = sc.next();
		for (int i = 0; i<30; i++) 
			if (book[i][0] != null)
				System.out.print(book[i][0] + "  ");
		System.out.println("������ å�� �̸��� �����ÿ� : ");
		String  bookname = sc.next();
		//Ư���� å ����
		for (int j = 0; j<30; j++) {   
			if (bookname.equals(book[j][0])){
				book[j][0] = null;
				seller = book[j][5];
			}
		}
		//å�� �Ǹ��� ������� book list ����	
		for (int k = 0; k<30; k++) {  
			for (int l = 0; l<30; l++) {
				if (seller.equals(user[k][0] )) {
					mybook[l][0] = null;
				}
			}
		}
		sc.close();
	} 
	
	//�α��� ����, ��� ���� ���� 
	void controlUser(String[][]user ) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i<30; i++) 
			if (user[i][0] != null)
				System.out.print(user[i][0] + "  ");
		
		System.out.println("� ����ڸ� deactivate�Ͻðڽ��ϱ�?: ");
		String id = sc.next();
		System.out.println("�α����� ���� �Ͻðڽ��ϱ�? no�� ���ý� user�� ��� ������ �����˴ϴ�.: ");
		String control = sc.next();
		
		//�α��� ����
		if(control.equals("yes")) {
			for (int j = 0; j<30; j++) {   
				if (id.equals(user[j][0])){
					//���.. �α��� ����.. ������
				}
			}
		}
		//��� ���� ����
		if(control.equals("no")) {
		for (int k = 0; k<30; k++) {   
			if (id.equals(user[k][0]))
				user[k] =null; 
			}
		}
		sc.close(); 
	}
}


class user{
//å �˻� ��� 
	String []a = {"å����","ISBN","����","���ǻ�","���ǿ���","�Ǹ���"};
	void searchBook(String id, String[][]book, String[][]user) {
		
		System.out.println("å����, ISBN, ����, ���ǻ�, ���ǿ���, �Ǹ��� �� �˻��ϰ� ���� ���� ��������");	
		Scanner sc4 = new Scanner(System.in);
		String search1 = sc4.next();
		
		for(int l=0; l<a.length; l++) {
			if (search1.equals(a[l])) {
				System.out.println(a[l] + "��������: ");	
				String search2 = sc4.next();
				for(int i=0; i<30; i++) {
					if (search2.equals(book[i][0])) {
						System.out.println(book[i][0]);
					}	
				}
			}
		}	
		
		//�˻��� å ����  
		System.out.println("�����Ͻðڽ��ϱ�? ");	
		String buy = sc4.next();
		if(buy.equals("yes")) { //�����ڿ� �������� �̸��� ǥ�� 
			for(int j=0; j<30; j++) {
				for (int k=0; k<30; k++) {//book�� ����� sellerId�� user�� id�� ������  user���� �̸��� ������
					if(book[j][5].equals(user[k][0])&& user[k][5] != null) {  //�Ǹ��� id email����
							System.out.println("�Ǹ��� email: "+user[k][4]);	
					}
				}
			}
			for(int p=0; p<30; p++) {
				if(id.equals(user[p][0])&& user[p][0] != null) 
					System.out.println("������ email: "+user[p][4]); //������ id email����
			}	
		}
		sc4.close();
	}  	
	
//�� ����
	void mybookList(String id, String[][]book , String[][]mybook ) {	
		System.out.println("å�� ��ġ�ų� �����ϰڽ��ϱ�?:  ");
		Scanner sc3 = new Scanner(System.in);
		String yes = sc3.next();
		if (yes.equals("yes")) {
			//å ���� ���
			String fr = sc3.next();
			if (fr.equals("remove")) {
				System.out.println("���° å�� �����ϰڽ��ϱ�?: ");
				int num1 = sc3.nextInt();
				for(int j=0; j<30; j++) {
					if (mybook[num1][0].equals(book[j][0])) {
						book[j] = null;
						System.out.println(book[j]+"�� �����Ǿ����ϴ�.");
					}
				}
			}
			//å ��ġ�� ��� 
			if (fr.equals("fix")) {
				System.out.println("���° å�� ��ġ�ڽ��ϱ�?: ");
				int num2 = sc3.nextInt();
				System.out.println("å����, ISBN, ����, ���ǻ�, ���ǿ���, �Ǹ��� �� ��ġ�� ���� �κ��� ���ÿ�");
				String search1 = sc3.next();
				System.out.println("��ġ�� ���� ���� �����ÿ�");
				String fix = sc3.next();

				for(int j=0; j<30; j++) {
					if (mybook[num2][0].equals(book[j][0])) {  //�� ���翡 �ִ� å�� ��ü ��ϵ� å ��
						for (int i =0; i<a.length; i++) { 
							if(search1.equals(a[i])) {	//��ġ�� ���� �κ��� ���� �˾Ƴ���
								book[j][i] = fix;
								System.out.println(book[j][i]+"�� ���������ϴ�.");
							}
						}	
					}	
				}
			}
		}	
		sc3.close();
	}
}

class log{
	public int logIn(String id, String pw, String[][]user ) {
		//������ �α���
		if (id.equals("admin") && pw.equals("nayana")) {
			return 0;
		}
		//����� �α��� 	
		for (int i = 0; i<30;) { 
			if (id.equals(user[i][0])&& pw.equals(user[i][1])) return 1;
			else return 3;
		}
		//ȸ������  -> user �迭 ����� 
		if (id.equals("Sign") &&pw.contentEquals("in")) {
			System.out.println("ȸ������ �մϴ�.");
			return 2;
		}
		return 5;
	}
}


