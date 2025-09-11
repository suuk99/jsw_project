package com.koreaIT.exam;

import java.util.Scanner;
import com.koreaIT.exam.util.Util;
import com.koreaIT.exam.dto.Article;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Article> article = new ArrayList<>();

		int id = 0;

		System.out.println("== 게시판 프로그램 시작 ==");

		while (true) {
			System.out.print("명령어) ");
			String cmd = sc.nextLine();

			// 게시글 쓰기
			if (cmd.equals("article write")) {
				System.out.print("제목: ");
				String title = sc.nextLine();

				System.out.print("내용: ");
				String content = sc.nextLine();

				article.add(new Article(title, content, id, Util.getDate()));
				id++;

				System.out.printf("%d번 게시글이 저장되었습니다.\n", id);
				// 게시글 수정
			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");
				int cmd2 = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (Article a : article) {
					if (cmd2 - 1 == a.getId()) {
						foundArticle = a;
						break;
					}
				}

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
				String[] cmdBits = cmd.split(" ");
				int cmd3 = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (Article a : article) {
					if (cmd3 - 1 == a.getId()) {
						foundArticle = a;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 없습니다.\n", cmd3);
					continue;
				}

				article.remove(foundArticle.getId());
				System.out.printf("%d번 게시글이 삭제되었습니다.\n", cmd3);
				// 게시물 목록 / 검색어가 제목에 포함된 글 목록
			} else if (cmd.startsWith("article list")) {

				if (article.isEmpty()) {
					System.out.println("작성된 글이 없습니다.");
					continue;
				}
				
				String[] cmdBits = cmd.split(" ");
				String search = cmdBits[2].trim();
				
				System.out.println("== 게시물 목록 ==");
				System.out.println("번호   ㅣ    제목     ㅣ     작성일");
				
				for (int i = article.size() - 1; i >= 0; i--) {
					Article a = article.get(i);
					if (a.getTitle().contains(search)) {
						System.out.printf("%d      ㅣ    %s   ㅣ  %s  \n", a.getId() + 1, a.getTitle(), a.getNow());
					}
					if (cmd.equals("article list")) {
						System.out.printf("%d      ㅣ    %s   ㅣ  %s  \n", a.getId() + 1, a.getTitle(), a.getNow());
					}
				}
					
				}
//				if (cmd.equals("article list")) {
//					System.out.println("== 게시물 목록 ==");
//					System.out.println("번호   ㅣ    제목     ㅣ     작성일");
//
//					for (int i = article.size() - 1; i >= 0; i--) {
//						Article a = article.get(i);
//						System.out.printf("%d      ㅣ    %s   ㅣ  %s  \n", a.getId() + 1, a.getTitle(), a.getNow());
//					}
//				}
//					else {
//					String[] cmdBits = cmd.split(" ");
//					String search = cmdBits[2];
//
//					System.out.println("== 검색 결과 ==");
//					
//					for (int i = article.size() - 1; i >= 0; i--) {
//						Article a = article.get(i);
//						if (a.getTitle().contains(search)) {
//							System.out.printf("%d      ㅣ    %s   ㅣ  %s  \n", a.getId() + 1, a.getTitle(), a.getNow());
//						}
//						else {
//							System.out.println("검색결과가 없습니다.");
//						}
//					}
//				}
				// 게시글 상세 목록
			 else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int cmd1 = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (Article a : article) {
					if (cmd1 - 1 == a.getId()) {
						foundArticle = a;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시글이 없습니다.\n", cmd1);
					continue;
				}

				System.out.printf("== %d번 게시글 상세보기 ==\n", cmd1);
				System.out.printf("번호: %d\n작성일: %s\n제목: %s\n내용: %s\n", foundArticle.getId() + 1, foundArticle.getNow(),
						foundArticle.getTitle(), foundArticle.getContent());
				// 종료
			} else if (cmd.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
				// 명령어 공백
			} else if (cmd.trim().isEmpty()) {
				System.out.println("명령어를 입력해 주세요.");
				// 명령어 오류
			} else {
				System.out.println("해당 명령어는 존재하지 않습니다. 다시 입력해 주세요.");
			}
		}
		sc.close();
	}
}