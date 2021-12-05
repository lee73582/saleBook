import java.util.*;

public class SaleBook {
	public static void main(String [] args) {
		System.out.println("헌책장터입니다.\nID와 password를 입력하세요  (회원가입은 'Signin'을 적어주세요.): ");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();  String pw = sc.next();

		log login = new log();
		String[][] user = users.getUser();
		String[][] book = books.getBook();
		String[][] mybook = mybooks.getMybook();
		login.logIn(id, pw, user);  //로그인 
		
		//test
		user[0][0] = "1"; user[0][1] = "2"; user[0][2] = "3"; user[0][3] = "4"; user[0][4] = "5";
		user[1][0] = "11"; user[1][1] = "22"; user[1][2] = "33"; user[1][3] = "44"; user[1][4] = "55";
		book[0][0] = "a"; book[0][1] = "b"; book[0][2] = "c"; book[0][3] = "d"; book[0][4] = "e"; book[0][5] = "11"; book[0][6] = "f"; book[0][7] = "g";
		book[1][0] = "aa"; book[1][1] = "bb"; book[1][2] = "cc"; book[1][3] = "dd"; book[1][4] = "ee"; book[1][5] = "1"; book[1][6] = "ff"; book[1][7] = "gg";
	
		//유저
		if (login.logIn(id, pw, user) == 1) {
			System.out.println("로그인에 성공했습니다.");
			user us = new user();
			System.out.println("책 검색을 원하면 1번, 책 등록을 원하면 2번, 책을 고치거나 삭제를 원하면 3번을 눌러주세요");
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
			
		//회원가입
		if (login.logIn(id, pw, user) == 2) {
			users.makeUser();
			System.out.println("회원가입에 성공했습니다");
		}
		if (login.logIn(id, pw, user) == 3) {
			System.out.println("회원가입에 실패했습니다");
		}
	
		//관리자	
		if (login.logIn(id, pw, user) == 0) {
			System.out.println("관리자로 로그인합니다.");
			admin ad = new admin();
			System.out.println("사용자의 정보를 제어하길 원하면 1, 책 삭제를 원하면 2번을 눌러주세요");
			int num = sc.nextInt();
			if(num == 1)
				ad.controlUser(user);
			
			if(num == 2)
				ad.deleteBook(book, mybook, user ); 
		}
	
		sc.close(); 
	}

	static class books{
		static String book[][] = new String[30][8];  //book list 생성
		
		static String[][] getBook() {
			return book;
		}
		
		static String[][] makeBook(){
			System.out.println("차례대로 책제목, ISBN번호, 저자, 출판사, 출판연도, 판매자, 등록가격, 상태(Excellent, Good, Fair)를 쓰시오");	
			Scanner sc2 = new Scanner(System.in);
			
			String bookName = sc2.next();
			String ISBN = sc2.next();
			String author = sc2.next();
			String publisher = sc2.next();
			String year = sc2.next();
			String sellerId = sc2.next();
			String price = sc2.next();
			String Statement = sc2.next();
			
			for (int i = 0; i<30; i++) {  //총 30개의 사용자 중 에서 비어진 부분에  넣는다. 
				if (book[i][0] == null) {
					book[i][0] = bookName;
					book[i][1] = ISBN;
					book[i][2] = author;
					book[i][3] = publisher;
					book[i][4] = year;
					book[i][5] = sellerId;
					book[i][6] = price;
					book[i][7] = Statement;
					
					System.out.println((i+1)+"번째로 책이 저장되었습니다.");	
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
			System.out.println("차례대로 id, password, name, number, e-mail을 쓰시오");	
			Scanner sc1 = new Scanner(System.in);
			
			String signId = sc1.next();
			String signPw = sc1.next();
			String signName = sc1.next();
			String signNumber = sc1.next();
			String signEmail = sc1.next();

				
			for (int i = 0; i<30; i++) {  //총 30개의 사용자 중 에서 비어진 부분에  넣는다. 
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
			System.out.println("등록한 책은");
			for(int j=0; j<30; j++) {
				for(int k=0; k<30; k++) {
					for(int l=0; l<30; l++) {
						if(id.equals(book[j][5])) { //seller id와 사용자 id가 같은 경우 
							if(mybook[k][0] != null) {
								mybook[k][l] =  book[j][l];
								System.out.println(k +"번 : "+ mybook[k][0]);	}
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
		System.out.println("삭제할 책의 이름을 적으시오 : ");
		String  bookname = sc.next();
		//특정한 책 삭제
		for (int j = 0; j<30; j++) {   
			if (bookname.equals(book[j][0])){
				book[j][0] = null;
				seller = book[j][5];
			}
		}
		//책을 판매한 사용자의 book list 삭재	
		for (int k = 0; k<30; k++) {  
			for (int l = 0; l<30; l++) {
				if (seller.equals(user[k][0] )) {
					mybook[l][0] = null;
				}
			}
		}
		sc.close();
	} 
	
	//로그인 제어, 모든 정보 삭제 
	void controlUser(String[][]user ) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i<30; i++) 
			if (user[i][0] != null)
				System.out.print(user[i][0] + "  ");
		
		System.out.println("어떤 사용자를 deactivate하시겠습니까?: ");
		String id = sc.next();
		System.out.println("로그인을 제어 하시겠습니까? no를 선택시 user의 모든 정보는 삭제됩니다.: ");
		String control = sc.next();
		
		//로그인 제어
		if(control.equals("yes")) {
			for (int j = 0; j<30; j++) {   
				if (id.equals(user[j][0])){
					//어떻게.. 로그인 제어.. 생각해
				}
			}
		}
		//모든 정보 삭제
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
//책 검색 기능 
	String []a = {"책제목","ISBN","저자","출판사","출판연도","판매자"};
	void searchBook(String id, String[][]book, String[][]user) {
		
		System.out.println("책제목, ISBN, 저자, 출판사, 출판연도, 판매자 중 검색하고 싶은 것을 적으세요");	
		Scanner sc4 = new Scanner(System.in);
		String search1 = sc4.next();
		
		for(int l=0; l<a.length; l++) {
			if (search1.equals(a[l])) {
				System.out.println(a[l] + "적으세요: ");	
				String search2 = sc4.next();
				for(int i=0; i<30; i++) {
					if (search2.equals(book[i][0])) {
						System.out.println(book[i][0]);
					}	
				}
			}
		}	
		
		//검색된 책 구입  
		System.out.println("구매하시겠습니까? ");	
		String buy = sc4.next();
		if(buy.equals("yes")) { //구매자와 소유자의 이메일 표시 
			for(int j=0; j<30; j++) {
				for (int k=0; k<30; k++) {//book에 저장된 sellerId와 user의 id가 같으면  user에게 이메일 보내기
					if(book[j][5].equals(user[k][0])&& user[k][5] != null) {  //판매자 id email전송
							System.out.println("판매자 email: "+user[k][4]);	
					}
				}
			}
			for(int p=0; p<30; p++) {
				if(id.equals(user[p][0])&& user[p][0] != null) 
					System.out.println("구매자 email: "+user[p][4]); //구매자 id email전송
			}	
		}
		sc4.close();
	}  	
	
//내 서재
	void mybookList(String id, String[][]book , String[][]mybook ) {	
		System.out.println("책을 고치거나 제거하겠습니까?:  ");
		Scanner sc3 = new Scanner(System.in);
		String yes = sc3.next();
		if (yes.equals("yes")) {
			//책 삭제 기능
			String fr = sc3.next();
			if (fr.equals("remove")) {
				System.out.println("몇번째 책을 삭제하겠습니까?: ");
				int num1 = sc3.nextInt();
				for(int j=0; j<30; j++) {
					if (mybook[num1][0].equals(book[j][0])) {
						book[j] = null;
						System.out.println(book[j]+"가 삭제되었습니다.");
					}
				}
			}
			//책 고치기 기능 
			if (fr.equals("fix")) {
				System.out.println("몇번째 책을 고치겠습니까?: ");
				int num2 = sc3.nextInt();
				System.out.println("책제목, ISBN, 저자, 출판사, 출판연도, 판매자 중 고치고 싶은 부분을 고르시오");
				String search1 = sc3.next();
				System.out.println("고치고 싶은 것을 적으시오");
				String fix = sc3.next();

				for(int j=0; j<30; j++) {
					if (mybook[num2][0].equals(book[j][0])) {  //내 서재에 있는 책과 전체 등록된 책 비교
						for (int i =0; i<a.length; i++) { 
							if(search1.equals(a[i])) {	//고치고 싶은 부분의 순서 알아내기
								book[j][i] = fix;
								System.out.println(book[j][i]+"로 고쳐졌습니다.");
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
		//관리자 로그인
		if (id.equals("admin") && pw.equals("nayana")) {
			return 0;
		}
		//사용자 로그인 	
		for (int i = 0; i<30;) { 
			if (id.equals(user[i][0])&& pw.equals(user[i][1])) return 1;
			else return 3;
		}
		//회원가입  -> user 배열 만든다 
		if (id.equals("Sign") &&pw.contentEquals("in")) {
			System.out.println("회원가입 합니다.");
			return 2;
		}
		return 5;
	}
}


