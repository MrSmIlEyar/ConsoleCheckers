import java.util.Scanner;


public class ConsoleCheckers {
	public static void main (String [] args) {
		String [][] field = new String [10][10];
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (i <= 8 && i >= 6) {
					if (i % 2 == 0 && j % 2 != 0) {
						field[i][j] = "w";
					} else if (i % 2 != 0 && j % 2 == 0) {
						field[i][j] = "w";
					} else {
						field[i][j] = "";
					}
				} else if (i <= 3) {
					if (i % 2 == 0 && j % 2 != 0) {
						field[i][j] = "b";
					} else if (i % 2 != 0 && j % 2 == 0) {
						field[i][j] = "b";
					} else {
						field[i][j] = "";
					}
				} else {
					field[i][j] = "";
				}
			}
		}
		print_field(field);
		boolean game = true;
		int start_x = 0;
		int start_y = 0;
		int end_x = 0;
		int end_y = 0;
		int number_of_move = 1;
		int move = 0;
		int number_of_black = 8;
		int number_of_white = 8;
		String [] for_split_move = new String [5];
		Scanner sc = new Scanner(System.in);
		while (game) {
			if (number_of_move == 1) {
				System.out.println("--White's move--");
				System.out.print("Enter move (x1y1x2y2, for examle 1122): ");
				move = sc.nextInt();
				start_x = move / 1000;
				start_y = 9 - (move / 100 % 10);
				end_x = move / 10 % 10;
				end_y = 9 - (move % 10);
				if (field[end_y][end_x] == "") {
					field[end_y][end_x] = "w";
					field[start_y][start_x] = "";
				}
			} else {
				System.out.println("--Black's move--");
				System.out.print("Enter move (x1y1x2y2, for examle 1122): ");
				move = sc.nextInt();
				start_x = move / 1000;
				start_y = 9 - (move / 100 % 10);
				end_x = move / 10 % 10;
				end_y = 9 - (move % 10);
				if (field[end_y][end_x] == "") {
					field[end_y][end_x] = "b";
					field[start_y][start_x] = "";
				}
			}
			print_field(field);
			number_of_move = Math.abs(number_of_move - 1);
		} 
	}

	public static void print_field (String [][] field) {
		System.out.println();
		System.out.println("    -------------------------------");
		System.out.println("   / 1   2   3   4   5   6   7   8 \\");
		int k = 8;
		for (int i = 1; i <= 8; i++) {
			System.out.println("  ||---|---|---|---|---|---|---|---||");
			System.out.print(k + " ||" );
			for (int j = 1; j <= 8; j++) {
				if (field[i][j] != "") {
					System.out.print(" " + field[i][j] + " |");
				} else {
					System.out.print("   |");
				}
				
			}
			System.out.println("| " + k);
			k -= 1;
		}
		System.out.println("  ||---|---|---|---|---|---|---|---||");
		System.out.println("   \\ 1   2   3   4   5   6   7   8 /");
		System.out.println("    -------------------------------");
	}
}