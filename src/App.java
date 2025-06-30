import java.util.Scanner;
import java.util.ArrayList;
class App {
  Scanner sc = new Scanner(System.in);
  ArrayList<Wisesaying> list = new ArrayList<>();

  int id = 1;

  void run () {
    System.out.println("== 명언 앱 ==");
    while (true) {
      System.out.print("명령 : ");
      String cmd = sc.nextLine();

      if (cmd.equals("종료")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      } else if (cmd.equals("등록")) {
        register();
      } else if (cmd.equals("목록")) {
        list_();
      } else if (cmd.equals("삭제")) {

      } else if (cmd.equals("수정")) {

      } else {
        System.out.println("해당 기능은 없습니다. 다시 입력해 주세요.");
      }
    }
  }
  void register() {
    System.out.print("명언을 입력해 주세요 : ");
    String wisesaying = sc.nextLine();

    System.out.println("작가를 입력해 주세요 : ");
    String writer = sc.nextLine();

    Wisesaying w = new Wisesaying(wisesaying, writer, id);
    list.add(w);

    System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    id ++;
  }
  void list_ () {
    if (list.isEmpty()) {
      System.out.println("등록된 명언이 없습니다.");
    }
    for (Wisesaying w : list) {
      System.out.println(w);
    }
  }
}
