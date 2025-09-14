package com.project.java.app;

import com.project.java.util.Util;
import com.project.java.article.Article;
import com.project.java.member.Member;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {

	private List<Member> member;
	private List<Article> article;
	private int id;

	public App() {
		this.member = new ArrayList<>();
		this.article = new ArrayList<>();
		this.id = 0;
	}

	public void run() {
		System.out.println("== 게시판 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		this.testData();

		while (true) {
			System.out.print("명령어) ");
			String cmd = sc.nextLine();

			// 회원가입
			if (cmd.equals("member join")) {
				System.out.println("== 회원가입 ==");
				
				String ID;
				while (true) {
					System.out.print("아이디: ");
					ID = sc.nextLine();
					
					boolean exist = false;
					for (Member m : member) {
						if (ID.equals(m.getMember_id())) {
							System.out.println("이미 존재하는 아이디 입니다.");
							exist = true;
							break;
						}
						
					}
					if (!exist) {
						System.out.println("사용 가능한 아이디 입니다.");
						break;
					}
				}
				
				String PW;
				while (true) {
					System.out.print("비밀번호: ");
					PW = sc.nextLine();
					
					System.out.print("비밀번호 확인:");
					String PW_check = sc.nextLine();
					
					if (PW.equals(PW_check)) {
						System.out.println("비밀번호가 일치합니다.");
						break;
					} else {
						System.out.println("비밀번호가 일치하지 않습니다.");
					}
				}
				
				System.out.print("이름: ");
				String Name = sc.nextLine();
				
				member.add(new Member(ID, PW, Name));
				System.out.println("회원가입이 완료되었습니다.");
			}
			
			// 게시글 쓰기
			else if (cmd.equals("article write")) {
				System.out.print("제목: ");
				String title = sc.nextLine();

				System.out.print("내용: ");
				String content = sc.nextLine();

				this.article.add(new Article(title, content, this.id, Util.getDate()));
				this.id++;

				System.out.printf("%d번 게시글이 저장되었습니다.\n", this.id);
				// 게시글 수정
			} else if (cmd.startsWith("article modify ")) {

				int cmd2 = cmdSplit(cmd);

				if (this.id == -1) {
					System.out.println("게시글의 번호를 정확하게 입력해 주세요.");
					continue;
				}

				Article foundArticle = getFoundArticle(cmd2);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 없습니다.\n", cmd2);
					continue;
				}

				System.out.print("수정할 제목: ");
				String title1 = sc.nextLine();
				foundArticle.setTitle(title1);

				System.out.print("수정할 내용: ");
				String content1 = sc.nextLine();
				foundArticle.setContent(content1);

				System.out.printf("%d번 게시글이 수정되었습니다.\n", foundArticle.getId() + 1);

				// 게시글 삭제
			} else if (cmd.startsWith("article delete ")) {

				int cmd2 = cmdSplit(cmd);

				if (this.id == -1) {
					System.out.println("게시글의 번호를 정확하게 입력해 주세요.");
					continue;
				}
				Article foundArticle = getFoundArticle(cmd2);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 없습니다.\n", cmd2);
					continue;
				}

				this.article.remove(foundArticle);
				System.out.printf("%d번 게시글이 삭제되었습니다.\n", cmd2);
				// 게시물 목록 / 검색어가 제목에 포함된 글 목록
			} else if (cmd.startsWith("article list")) {

				if (this.article.isEmpty()) {
					System.out.println("작성된 글이 없습니다.");
					continue;

				}

				String[] cmdBits = cmd.split(" ");

				String search = cmd.substring("article list".length()).trim();

				List<Article> printArticle = this.article;

				if (search.length() > 0) {
					System.out.println("입력한 검색어: " + search);
					printArticle = new ArrayList<>();

					for (Article a : this.article) {
						if (a.getTitle().contains(search)) {
							printArticle.add(a);
						}
					}

					if (printArticle.size() == 0) {
						System.out.println("검색결과가 없습니다.");
						continue;
					}
				}

				System.out.println("== 게시물 목록 ==");
				System.out.println("번호   ㅣ    제목     ㅣ     작성일");

				for (int i = printArticle.size() - 1; i >= 0; i--) {
					Article a = printArticle.get(i);
					System.out.printf("%d      ㅣ    %s   ㅣ  %s  \n", a.getId() + 1, a.getTitle(), a.getNow());
				}
			}
			// 게시글 상세 목록
			else if (cmd.startsWith("article detail ")) {

				int cmd2 = cmdSplit(cmd);

				if (this.id == -1) {
					System.out.println("게시글의 번호를 정확하게 입력해 주세요.");
					continue;
				}

				Article foundArticle = getFoundArticle(cmd2);

				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 없습니다.\n", cmd2);
					continue;
				}

				System.out.printf("== %d번 게시글 상세보기 ==\n", cmd2);
				System.out.printf("번호: %d\n작성일: %s\n제목: %s\n내용: %s\n", foundArticle.getId() + 1, foundArticle.getNow(),
						foundArticle.getTitle(), foundArticle.getContent());
				// 종료
			} else if (cmd.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				sc.close();
				break;
				// 명령어 공백
			} else if (cmd.trim().isEmpty()) {
				System.out.println("명령어를 입력해 주세요.");
				// 명령어 오류
			} else {
				System.out.println("해당 명령어는 존재하지 않습니다. 다시 입력해 주세요.");
			}
		}
	}

	private int cmdSplit(String cmd) {
		String[] cmdBits = cmd.split(" ");

		try {
			return Integer.parseInt(cmdBits[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
	}

	private Article getFoundArticle(int cmd2) {
		Article foundArticle = null;

		for (Article a : this.article) {
			if (cmd2 - 1 == a.getId()) {
				return a;
			}
		}
		return null;
	}

	private void testData() {
		for (int i = 1; i <= 3; i++) {
			this.article.add(new Article(i + "번", i + "번째 게시물 입니다.", this.id, Util.getDate()));
			this.id++;
		}
	}
}