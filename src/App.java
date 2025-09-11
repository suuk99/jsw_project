import java.util.Scanner;
import java.util.ArrayList;
class App {
  Scanner sc = new Scanner(System.in);
  ArrayList<Wisesaying> list = new ArrayList<>();

  int id = 1;

  void run () {
    System.out.println("== 명언 앱 ==");

    auto_create();
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
      } else if (cmd.startsWith("삭제")) {
        remove_(cmd);
      } else if (cmd.startsWith("수정")) {
        reset_(cmd);
      } else {
        System.out.println("해당 기능은 없습니다. 다시 입력해 주세요.");
      }
    }
  }
  void auto_create () {
    list.add(new Wisesaying("1", "1", id++));
    list.add(new Wisesaying("2", "2", id++));
    list.add(new Wisesaying("3", "3", id++));
    list.add(new Wisesaying("4", "4", id++));
    list.add(new Wisesaying("5", "5", id++));
    list.add(new Wisesaying("6", "6", id++));
    list.add(new Wisesaying("7", "7", id++));
    list.add(new Wisesaying("8", "8", id++));
    list.add(new Wisesaying("9", "9", id++));
    list.add(new Wisesaying("10", "10", id++));
  }
  void register() {
    System.out.print("명언을 입력해 주세요 : ");
    String wisesaying = sc.nextLine();

    System.out.print("작가를 입력해 주세요 : ");
    String writer = sc.nextLine();

    Wisesaying w = new Wisesaying(wisesaying, writer, id);
    list.add(w);

    System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    id ++;
  }
  void list_ () {
    for (Wisesaying w : list) {
      System.out.printf("%d / %s / %s\n", w.id, w.wisesaying, w.writer);
    }
  }
  void remove_ (String cmd) {
    if (list.isEmpty()) {
      System.out.println("등록된 명언이 없습니다.");
      return;
    }
    if (!cmd.startsWith("삭제?id=")) {
      System.out.println("다시 입력해 주세요.");
      return;
    }

    String idStr = cmd.replace("삭제?id=", "");
    int r_id = -1;

    try {
      r_id = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
      System.out.println("삭제할 명언의 id를 입력해 주세요.");
      return;
    }

    Wisesaying found = null;
    for(Wisesaying w : list) {
      if (w.id == r_id) {
        found = w;
        break;
      }
    }
      if (found != null) {
        list.remove(found);
        System.out.printf("%d번 명언이 삭제 되었습니다.\n", r_id);
      }
      else {
        System.out.printf("%d번 명언이 존재하지 않습니다.\n",r_id);
      }
    }
  void reset_ (String cmd) {
    if (list.isEmpty()) {
      System.out.println("등록된 명언이 없습니다.");
      return;
    }
    if (!cmd.startsWith("수정?id=")) {
      System.out.println("다시 입력해 주세요.");
      return;
    }
    String idStr = cmd.replace("수정?id=", "");
    int r_id = -1;

    try {
      r_id = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
      System.out.println("숫자를 입력해 주세요.");
      return;
    }

    Wisesaying found = null;
    for (Wisesaying w : list) {
      if (w.id == r_id) {
        found = w;
        break;
      }
    }
    if (found != null) {
      System.out.print("새로운 명언을 입력해 주세요 : ");
      String new_wisesaying = sc.nextLine();
      System.out.print("작가를 입력해 주세요 : ");
      String new_writer = sc.nextLine();

      Wisesaying new_ws = new Wisesaying(new_wisesaying, new_writer, r_id);
      int index = list.indexOf(found);
      list.set(index ,new_ws);

      System.out.printf("%d번 명언이 수정되었습니다.\n", r_id);
    }
    else {
      System.out.printf("%d번 명언이 존재하지 않습니다\n",r_id);
    }
  }
}