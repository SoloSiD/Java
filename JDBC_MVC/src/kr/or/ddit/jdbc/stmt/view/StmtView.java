package kr.or.ddit.jdbc.stmt.view;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.jdbc.stmt.service.impl.StmtServiceImpl;
import kr.or.ddit.jdbc.stmt.vo.BoardVO;

public class StmtView {
	public static void main(String[] args) {
		StmtServiceImpl service = StmtServiceImpl.getInstance();

		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("┌──────────────────────────────────────────┐");
			System.out.println("│                성우의 게시판                          │");
			System.out.println("└──────────────────────────────────────────┘");
			System.out.println();			
			System.out.println("1. 게시판 글 작성");
			System.out.println("2. 게시판 글 수정");
			System.out.println("3. 게시판 글 삭제");
			System.out.println("4. 게시판 글 열기");
			System.out.println("5. 게시판 목록 보기");
			System.out.println("Q - 프로그램 종료\n");
			System.out.print("번호를 입력하세요 : ");
			
			// git test
			String inputNum = sc.nextLine();
			System.out.println();
			switch (inputNum) {			
			case "1":
				System.out.print("작성자의 이름을 입력하세요 : ");
				String inputWriter = sc.nextLine();
				System.out.print("글 제목을 입력하세요 : ");
				String inputTitle = sc.nextLine();
				System.out.print("내용을 입력하세요 : ");
				String inputContent = sc.nextLine();
				
				service.insert(inputWriter, inputTitle, inputContent);
				System.out.println("글 작성이 완료되었습니다. ");
				break;
				
			case "2":
				System.out.print("수정할 글 번호를 입력하세요 : ");
				String inputNo = sc.nextLine();
				System.out.print("바꿀 글 제목을 입력하세요 : ");
				inputTitle = sc.nextLine();
				System.out.print("바꿀 글 내용을 입력하세요 : ");
				inputContent = sc.nextLine();
				
				service.update(inputNo, inputTitle, inputContent);
				System.out.println("업데이트가 완료되었습니다.\n");
				break;
				
			case "3":
				System.out.print("삭제할 글 번호를 입력하세요 : ");
				inputNo = sc.nextLine();
				
				service.delete(inputNo);
				System.out.println("\n");
				break;
			case "4":
				System.out.print("읽을 글 번호를 입력하세요 : ");
				inputNo = sc.nextLine();
				System.out.println();
				System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
				try {
					System.out.println(service.select(inputNo).toString(true));					
				} catch (NullPointerException e) {
					System.out.println("없는 글 번호 입니다. 다시 입력하세요!!");					
				}
				System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
				System.out.println("\n");
				break;
			case "5":
				boolean isUpper = true;
				while(isUpper) {
					System.out.println("1. 조건 검색");
					System.out.println("2. 전체 목록");
					System.out.println("3. 이전 화면 돌아가기\n");
					System.out.print("번호를 입력하세요 : ");
					inputNum = sc.nextLine();
					List<BoardVO> viewList = null;
					switch (inputNum) {
					case "1":
						System.out.print("찾을 글 번호를 입력하세요(생략가능) : ");
						inputNo = sc.nextLine();
						System.out.print("찾을 글 제목을 입력하세요(생략가능) : ");
						inputTitle = sc.nextLine();
						System.out.print("찾을 글 작성자을 입력하세요(생략가능) : ");
						inputWriter = sc.nextLine();
						System.out.print("찾을 글 내용 일부를 입력하세요(생략가능) : ");
						inputContent = sc.nextLine();
						System.out.println("\n");
						System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println("글번호\t글제목\t\t\t\t작성자\t\t작성날짜\t\t\t글내용");
						System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
						viewList = service.selectList(inputNo, inputTitle, inputWriter, inputContent);
						
						for (int i = 0; i < viewList.size(); i++) {
							System.out.println(viewList.get(i).toString());
						}
						System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println("\n");
						break;
					case "2":
						System.out.println("\n");
						System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println("글번호\t글제목\t\t\t\t작성자\t\t작성날짜\t\t\t글내용");
						System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
						viewList = service.selectList("", "", "", "");
						for (int i = 0; i < viewList.size(); i++) {
							System.out.println(viewList.get(i).toString());							
						}
						System.out.println("────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.println("\n");
						break;
					case "3":
						isUpper = false;
						System.out.println("\n");
						break;
					}					
				}
				break;
			case "Q": 
			case "q" :
				System.out.println("프로그램을 종료합니다.");
				return;	
			default:
				System.out.println("다시 입력해주세요!!");
				break;
			}			
		}
	}
}
