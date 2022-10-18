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
	public static void main(String[] args) {
		String[][] field = new String[10][10]; // создание поля

		// заполнение поля
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (i >= 6) {
					if (i % 2 == 0 && j % 2 != 0) {
						field[i][j] = "w";
					} else if (i % 2 != 0 && j % 2 == 0) {
						field[i][j] = "w";
					} else {
						field[i][j] = " ";
					}
				} else if (i <= 3) {
					if (i % 2 == 0 && j % 2 != 0) {
						field[i][j] = "b";
					} else if (i % 2 != 0 && j % 2 == 0) {
						field[i][j] = "b";
					} else {
						field[i][j] = " ";
					}
				} else {
					field[i][j] = " ";
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
	public static void game_with_person(String[][] field) {
		while (game) {
			if (number_of_move == 1) {
				print_field(field);
				System.out.println("--White's move--");
				System.out.print("Enter move (x1y1x2y2, for example 1122): ");
				move = sc.nextInt();
				start_x = move / 1000;
				start_y = 9 - (move / 100 % 10);
				end_x = move / 10 % 10;
				end_y = 9 - (move % 10);
				if (field[end_y][end_x] == " ") { // если ходится на пустую клетку, то
					if (check_kill_on_all_board(number_of_move, field)) { // проверка того, что необходимо ли съедать шашку в этом ходу
						if (check_kill(start_x, start_y, field) & isJump(start_x, start_y, end_x, end_y)) { // проверка того, что этой шашкой что-то съедается и это прыжок
							Kill(start_x, start_y, end_x, end_y, field); // void функция - уничтожает шашку, перемещает текущую шашку, меняет цвет хода
						} else {
							System.out.println("YOU MUST KILL");
						}
					} else if (check(number_of_move, start_x, start_y, end_x, end_y)) { // если не надо съедать, то проверяется правильность хода (для шашки)
						field[end_y][end_x] = "w";
						field[start_y][start_x] = " ";
						number_of_move -= 1;

					} else {
						System.out.println("WRONG MOVE!");
					}
				} else {
					System.out.println("WRONG MOVE!");
				}
			} else {
				print_field(field);
				System.out.println("--Black's move--");
				System.out.print("Enter move (x1y1x2y2, for example 1122): ");
				move = sc.nextInt();
				start_x = move / 1000;
				start_y = 9 - (move / 100 % 10);
				end_x = move / 10 % 10;
				end_y = 9 - (move % 10);
				if (field[end_y][end_x] == " ") { // если ходится на пустую клетку, то
					if (check_kill_on_all_board(number_of_move, field)) { // проверка того, что необходимо ли съедать шашку в этом ходу
						if (check_kill(start_x, start_y, field) & isJump(start_x, start_y, end_x, end_y)) { // проверка того, что этой шашкой что-то съедается и это прыжок
							Kill(start_x, start_y, end_x, end_y, field); // void функция - уничтожает шашку, перемещает текущую шашку, меняет цвет хода
						} else {
							System.out.println("YOU MUST KILL");
						}
					} else if (check(number_of_move, start_x, start_y, end_x, end_y)) { // если не надо съедать, то проверяется правильность хода (для шашки)
						field[end_y][end_x] = "b";
						field[start_y][start_x] = " ";
						number_of_move += 1;

					} else {
						System.out.println("WRONG MOVE!");
					}
				} else {
					System.out.println("WRONG MOVE!");
				}
			}
		}
	}


	// функция игры с ботом
	public static void game_with_bot(String[][] field) {
		System.out.println("Bot in process of development");
	}


	// функция выводит игровое поле
	public static void print_field(String[][] field) {
		System.out.println();
		System.out.println("    -------------------------------");
		System.out.println("   / 1   2   3   4   5   6   7   8 \\");
		int k = 8;
		for (int i = 1; i <= 8; i++) {
			System.out.println("  ||---|---|---|---|---|---|---|---||");
			System.out.print(k + " ||");
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


	public static boolean check(int color, int startx, int starty, int endx, int endy) { // проверка правильности хода (не для дамки)
		if (check_pos(endx, endy)) {
			if (color == 1) {
				return Math.abs(startx - endx) == 1 & starty - endy == 1;
			} else if (color == 0) {
				return Math.abs(startx - endx) == 1 & endy - starty == 1;
			}
		}
		return false;
	}


	public static boolean check_pos(int x, int y) { // простая проверка координат
		return 1 <= x & x <= 8 & 1 <= y & y <= 8;
	}


	public static boolean check_kill(int startx, int starty, String[][] field) {
        /*
        проверяется можно ли съесть текущей шашкой какую-нибудь шашку противника, возвращает булевое значение
         */
		int x = 0, y = 0;
		int have_x = 0, have_y = 0;
		for (int i = 1; i <= 4; i++) {
			switch (i) {
				case 1 -> {
					x = startx - 2;
					y = starty - 2;
					have_x = x + 1;
					have_y = y + 1;
				}
				case 2 -> {
					x = startx + 2;
					y = starty - 2;
					have_x = x - 1;
					have_y = y + 1;
				}
				case 3 -> {
					x = startx + 2;
					y = starty + 2;
					have_x = x - 1;
					have_y = y - 1;
				}
				case 4 -> {
					x = startx - 2;
					y = starty + 2;
					have_x = x + 1;
					have_y = y - 1;
				}
			}
			if (check_pos(x, y)) {
				if (field[have_y][have_x] != field[starty][startx] & field[y][x] == " " & (field[have_y][have_x] == "w" || field[have_y][have_x] == "b")) {
					return true;
				}
			}
		}
		return false;
	}


	public static boolean check_kill_on_all_board(int color, String[][] field) {
        /*
        проверка всех шашек текущего цвета можно ли съесть какую-нибудь шашку противоположного цвета
         */
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (field[i][j] == "w" & color == 1) {
					if (check_kill(j, i, field)) {
						return true;
					}
				}
				else if (field[i][j] == "b" & color == 0) {
					if (check_kill(j, i, field)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isJump(int start_x, int start_y, int end_x, int end_y) {
        /*
        проверка того, что ход является прыжком
         */
		return Math.abs(end_x - start_x) == 2 && Math.abs(end_y - start_y) == 2;
	}

	public static void Kill(int start_x, int start_y, int end_x, int end_y, String[][] field) {
        /*
        процедура выполнения съедения шашки
         */
		int have_x = 0, have_y = 0;
		int x = 0, y = 0;
		for (int i = 1; i <= 4; i++) {
			switch (i) {
				case 1 -> {
					x = start_x - 2;
					y = start_y - 2;
					have_x = x + 1;
					have_y = y + 1;
				}
				case 2 -> {
					x = start_x + 2;
					y = start_y - 2;
					have_x = x - 1;
					have_y = y + 1;
				}
				case 3 -> {
					x = start_x + 2;
					y = start_y + 2;
					have_x = x - 1;
					have_y = y - 1;
				}
				case 4 -> {
					x = start_x - 2;
					y = start_y + 2;
					have_x = x + 1;
					have_y = y - 1;
				}
			}
			if (check_pos(x, y)) {
				if (x == end_x & y == end_y & field[have_y][have_x] != field[start_y][start_x] & field[y][x] == " " & (field[have_y][have_x] == "w" || field[have_y][have_x] == "b")) {
					if (field[have_y][have_x] == "b") {
						number_of_black -= 1;
						field[end_y][end_x] = "w";
						if (!check_kill(end_x, end_y, field)) {
							number_of_move -= 1;
						}
					} else {
						number_of_white -= 1;
						field[end_y][end_x] = "b";
						if (!check_kill(end_x, end_y, field)) {
							number_of_move += 1;
						}
					}
					field[have_y][have_x] = " ";
					field[start_y][start_x] = " ";
					break;
				}
			}
		}
	}
}