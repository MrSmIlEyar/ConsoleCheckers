// шашки в консоли

public class ConsoleCheckers {


	public static void main (String [] args) {
		String [][] field = new String [10][10]; // обрамляем поле 8 на 8 рамкой
		for (int i = 1; i <= 8; i++) {
            if (i < 4) {
                for (int j = 1; j <= 8; j++) {
                    if ((i + j) % 2 == 0) {
                        field[i][j] = " ";
                    } else {
                        field[i][j] = "b"; // маленькие буквы (b, w) - шашки, большие буквы (B, W) - дамки
                    }
                }
            } else if (5 < i) {
                for (int j = 1; j <= 8; j++) {
                    if ((i + j) % 2 == 0) {
                        field[i][j] = " ";
                    } else {
                        field[i][j] = "w";
                    }
                }
            } else {
                for (int j = 1; j <= 8; j++) {
                    field[i][j] = " ";
                }
            }
        }
        print_field(field);

	}

	public static void print_field (String [][] field) {
		System.out.println();
        System.out.println("     -------------------------------");
        System.out.println("    / 1   2   3   4   5   6   7   8 \\");
        int k = 1;
        for (int i = 1; i <= 8; i++) {
            System.out.println("   ||---|---|---|---|---|---|---|---||");
            System.out.print(" " + k + " ||");
            for (int j = 1; j <= 8; j++) {
                if (field[i][j] != " ") {
                    System.out.print(" " + field[i][j] + " |");
                } else {
                    System.out.print("   |");
                }
            }
            System.out.println("| " + k);
            k += 1;
        }
        System.out.println("   ||---|---|---|---|---|---|---|---||");
        System.out.println("    \\ 1   2   3   4   5   6   7   8 /");
        System.out.println("     -------------------------------");
	}
}