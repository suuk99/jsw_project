import java.util.Scanner;
import java.util.ArrayList;
class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<WiseSaying> list = new ArrayList<> ();

    int wise_saying_id = 0;

    while (true) {
      System.out.println("== 명언 앱 ==");
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 삭제");
      System.out.println("4. 수정");
      System.out.println("5. 종료");
      System.out.print("입력 : ");
      int choice = sc.nextInt();
      sc.nextLine();

      // 등록
      if (choice == 1) {
        System.out.print("명언 : ");
        String wise_saying = sc.nextLine();

        System.out.print("작가 : ");
        String writer = sc.nextLine();

        wise_saying_id ++;
        WiseSaying w = new WiseSaying(wise_saying, writer, wise_saying_id);

        list.add(w);
        System.out.printf("%d번째 명언이 등록되었습니다.\n", wise_saying_id);
      }
      // 목록
      else if (choice == 2) {
        if (list.isEmpty()) {
          System.out.println("등록된 명언이 없습니다.");
          continue;
        }

        System.out.println("== 등록된 명언 == ");

        for (WiseSaying w : list) {
          System.out.printf("%d : %s / %s \n", w.wise_saying_id, w.writer, w.wise_saying);
        }
        System.out.println("\n등록된 명언의 갯수 : " + list.size());
      }
      // 삭제
      else if (choice == 3) {
        if (list.isEmpty()) {
          System.out.println("등록된 명언이 없습니다.");
          continue;
        }

        System.out.print("삭제할 명언 번호를 입력해 주세요 : ");
        int ws_num = sc.nextInt();
        sc.nextLine();

        boolean found = false;
        for (int i = 0; i < list.size(); i ++) {
          if (list.get(i).wise_saying_id == ws_num) {
            list.remove(i);
            System.out.printf("%d번 명언이 삭제되었습니다.\n",ws_num);
            found = true;
            break;
          }
        }

        if (!found) {
          System.out.println("해당 명언이 없습니다. 다시 입력해 주세요.");
        }
      }
      // 수정
      else if (choice == 4) {
        if (list.isEmpty()) {
          System.out.println("등록된 명언이 없습니다.");
          continue;
        }

        System.out.print("수정할 명언의 번호를 입력해 주세요 : ");
        int ws_num = sc.nextInt();
        sc.nextLine();

        boolean found = false;
        for (int i = 0; i < list.size(); i ++) {
          if (list.get(i).wise_saying_id == ws_num) {
            System.out.print("새로운 명언을 입력해 주세요 : ");
            String new_wise_saying = sc.nextLine();

            System.out.println("새로운 작가명을 입력해 주세요 : ");
            String new_writer = sc.nextLine();

            WiseSaying new_ = new WiseSaying(new_wise_saying, new_writer, ws_num);
            list.set(i, new_);

            System.out.printf("%d번 명언이 수정되었습니다.\n", ws_num);

            found = true;
            break;
          }
        }
        if (!found) {
          System.out.println("해당 명언이 없습니다. 다시 입력해 주세요.");
        }
      }

      // 종료
      else if (choice == 5) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
      // 그 외
      else {
        System.out.println("다시 입력하세요.");
      }
    }
    sc.close();
  }
}
class WiseSaying {
  String wise_saying;
  String writer;
  int wise_saying_id;

  public WiseSaying(String wise_saying, String writer, int wise_saying_id) {
    this.wise_saying = wise_saying;
    this.writer = writer;
    this.wise_saying_id = wise_saying_id;
  }
}