public class Constants {
        // app dimensions
        public static final double APP_WIDTH = 350;
        public static final double APP_HEIGHT = 700;

        // width of each square
        public static final int SQUARE_WIDTH = 25;

        // coordinates for squares in each tetris piece
        public static final int[][] I_PIECE_COORDS = { { 0, 0 }, { 0, SQUARE_WIDTH }, { 0, 2 * SQUARE_WIDTH },
                        { 0, 3 * SQUARE_WIDTH } };
        public static final int[][] T_PIECE_COORDS = { { -1 * SQUARE_WIDTH, 0 }, { -1 * SQUARE_WIDTH, SQUARE_WIDTH },
                        { -1 * SQUARE_WIDTH, 2 * SQUARE_WIDTH }, { 0, SQUARE_WIDTH } };
}