import java.util.Scanner;
import java.util.Random;


public class ConsoleCheckers {
	// объявление всех необходимых переменных
	public static Random rn = new Random(); // генератор рандомных чисел
	public static Scanner sc = new Scanner(System.in); // сканер для ввода
	public static int move = 0; // переменная, принимающая ход, введённый игроком
	public static int start_x = 0; // переменная, хранящая в себе стартовую координату x хода
	public static int start_y = 0; // переменная, хранящая в себе стартовую координату y хода
	public static int end_x = 0; // переменная, хранящая в себе конечную координату x хода
	public static int end_y = 0; // переменная, хранящая в себе конечную координату y хода
	public static int number_of_move = 1; // переменная, хранящая в себе порядок хода
	public static boolean game = true; // переменная, показывающая, что игра идёт (true) или не идёт (false)
	public static int number_of_white = 12; // количество белых шашек
	public static int number_of_black = 12; // количество чёрных шашек


	// главная функция заполняет поле изначальной расстановкой шашек и перенаправляет пользователся по режимам игры
	public static void main (String [] args) {
		String [][] field = new String [10][10]; // создание поля

		// заполнение поля
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

		// ввод режима игры
		System.out.print("Enter game mode (1 - with bot, 2 - with person): ");
		int game_mode = sc.nextInt();
		System.out.println();
		while (game_mode != 1 && game_mode != 2) {
			System.out.print("Enter correct game mode: ");
			game_mode = sc.nextInt();
			System.out.println();
		}

		// перенаправление пользователя по режиму игры
		switch (game_mode) {
			case 1:
				game_with_bot(field);
			case 2:
				game_with_person(field);
		}
	}

	
	// функция игры с человеком
	public static void game_with_person (String [][] field) {
		print_field(field);
		while (game) {
			if (number_of_move == 1) {
				System.out.println("--White's move--");
				System.out.print("Enter move (x1y1x2y2, for example 1122): ");
				move = sc.nextInt();
				start_x = move / 1000;
				start_y = 9 - (move / 100 % 10);
				end_x = move / 10 % 10;
				end_y = 9 - (move % 10);
				if (field[end_y][end_x] == "") {
					field[end_y][end_x] = "w";
					field[start_y][start_x] = "";
				}
			} 
			else {
				System.out.println("--Black's move--");
				System.out.print("Enter move (x1y1x2y2, for example 1122): ");
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


	// функция игры с ботом
	public static void game_with_bot (String [][] field) {
		System.out.println("Bot in process of development");
	}


	// функция выводит игровое поле
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