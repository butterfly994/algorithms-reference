import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input
 * according to the problem statement.
 **/
class FloodFill {
	static String[][] field;
	static int[] dr = { -1, 0, 1, 0 }; // for this problem, diagonals don't
										// count! N, E, S, W
	static int[] dc = { 0, 1, 0, -1 };

	public static int floodfill(int r, int c) {
		if (r < 0 || r >= field.length || c < 0 || c >= field[0].length) { // outside
																			// of
																			// grid
			return 0;
		}
		if (field[r][c].equals("#"))
			return 0; // outside the body of water or already visited
		int ans = 1; // start with one because field[r][c] counts as part of the
						// water
		field[r][c] = "#";
		for (int i = 0; i < 4; i++) {
			ans += floodfill(r + dr[i], c + dc[i]);
		}
		return ans;
	}

	public static int forestfire(int r, int c) {
		Queue<Integer[]> upnext = new LinkedList<Integer[]>();
		Integer[] start = { r, c };
		upnext.add(start);
		int count = 0;
		while (!upnext.isEmpty()) {
			Integer[] current = upnext.poll();
			int currentr = current[0];
			int currentc = current[1];
			if (field[currentr][currentc].equals("O")) { // right color
				count++; // add in to count
				field[currentr][currentc] = "#"; // recolor
				for (int i = 0; i < 4; i++) {
					int newr = currentr + dr[i];
					int newc = currentc + dc[i];
					if (newr >= 0 && newr < field.length && newc >= 0 && newc < field[0].length) {
						Integer[] newcoord = { newr, newc };
						upnext.add(newcoord);
					}
				}
			}
		}
		return count;
	}

	public static String[][] deepClone(String[][] original) {
		String[][] copy = new String[original.length][original[0].length];
		for (int i = 0; i < original.length; i++) {
			for (int j = 0; j < original[0].length; j++) {
				copy[i][j] = original[i][j];
			}
		}
		return copy;
	}

	public static void main(String args[]) {
		 Scanner in = new Scanner(System.in);
		 int L = in.nextInt();
		 int H = in.nextInt();
		 String[][] original = new String[H][L];
		 in.nextLine();
		 for (int i = 0; i < H; i++) {
		 String row = in.nextLine();
		 for(int j = 0; j < L; j++){
		 original[i][j] = String.valueOf(row.charAt(j));
		 }
		 }
		 int N = in.nextInt();
		 int[] solutions = new int[N];
		 for (int i = 0; i < N; i++) {
		 field = deepClone(original);
		 int X = in.nextInt();
		 int Y = in.nextInt();
		 solutions[i] = floodfill(Y, X);
		 }
		
		 for (int i = 0; i < N; i++) {
		
		 // Write an action using System.out.println()
		 // To debug: System.err.println("Debug messages...");
		 System.out.println(solutions[i]);
		 }
//		field = new String[][] { { "#", "#", "#", "0" }, { "#", "#", "0", "#" }, { "#", "#", "0", "#" },
//				{ "0", "#", "#", "#" } };
//		UTest.assertTrue(forestfire(1, 2) == 2);
//		field = new String[][] { { "0", "0"}, { "0", "0"} };
//		UTest.assertTrue(forestfire(0, 0) == 4);
//		field = new String[][] { { "#", "#"}, { "#", "#"} };
//		UTest.assertTrue(forestfire(1, 0) == 0);
	}
	
	static class UTest {
		static void assertTrue(Boolean result) {
			if (!result) {
				throw new AssertionError("Assertion error occurred!");
			}
		}

		static void assertEquals(Object o1, Object o2) {
			assertTrue(o1.getClass().equals(o2.getClass()));
			assertTrue(o1.equals(o2));
		}

		static void assertEquals(Object[] o1, Object[] o2) {
			assertTrue(Arrays.deepEquals(o1, o2));
		}

		static void assertEquals(int[] o1, int[] o2) {
			assertTrue(Arrays.equals(o1, o2));
		}

		static void assertEquals(long[] o1, long[] o2) {
			assertTrue(Arrays.equals(o1, o2));
		}

		static void assertEquals(boolean[] o1, boolean[] o2) {
			assertTrue(Arrays.equals(o1, o2));
		}

		static void assertEquals(char[] o1, char[] o2) {
			assertTrue(Arrays.equals(o1, o2));
		}

		static void assertEquals(float[] o1, float[] o2) {
			assertTrue(Arrays.equals(o1, o2));
		}

		static void assertEquals(double[] o1, double[] o2) {
			assertTrue(Arrays.equals(o1, o2));
		}

		static void assertEquals(byte[] o1, byte[] o2) {
			assertTrue(Arrays.equals(o1, o2));
		}
	}
}

