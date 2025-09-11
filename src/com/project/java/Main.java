package com.project.java;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Article> article = new ArrayList<>();
		
		int id = 0;
		
		System.out.println("== 게시판 프로그램 시작 ==");
		
		while (true) {
			System.out.print("명령어) ");
			String cmd = sc.nextLine();
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String now1 = now.format(formatter);
			
			// 글쓰기
			if (cmd.equals("article write")) {
				System.out.print("제목: ");
				String title = sc.nextLine();
				
				System.out.print("내용: ");
				String content = sc.nextLine();
				
				article.add(new Article(title, content, id, now1));
				id ++;
				
				System.out.printf("%d번 게시물이 저장되었습니다.\n", id);
			// 게시판 목록
			} else if (cmd.equals("article list")) {
				if (article.isEmpty()) {
					System.out.println("작성된 글이 없습니다.");
				} else {
					System.out.println("== 게시물 목록 ==");
					System.out.println("번호   ㅣ    제목 ");
					
					for (int i = article.size() - 1; i >= 0; i--) {
						Article a = article.get(i);
						System.out.printf("%d      ㅣ    %s\n", a.id + 1, a.title);
					}
				}
			// 게시판 상세 목록
			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int cmd1 = Integer.parseInt(cmdBits[2]);
				
				if (cmd1 < 1 || cmd1 > article.size()) {
					System.out.printf("%d번 게시글이 존재하지 않습니다.\n", cmd1);
				} else {
					System.out.printf("== %d번 게시글 상세보기 ==\n",cmd1);
					
					Article a = article.get(cmd1 - 1);
					System.out.printf("번호: %d\n작성일: %s\n제목: %s\n내용: %s\n", a.id + 1, a.now, a.title, a.content);
				} 
			// 종료
			} else if (cmd.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("해당 명령어는 존재하지 않습니다. 다시 입력해 주세요.");
			}
		}
	}
}
