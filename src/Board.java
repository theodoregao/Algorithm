import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Board {
    
    private static final int[][] DIR = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    
    private int[][] board;
    private int dimension;
    private int x, y;
    
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    {
        dimension = blocks.length;
        board = new int[dimension][];
        for (int i = 0; i < dimension; i++) {
            board[i] = new int[dimension];
            for (int j = 0; j < dimension; j++) {
                board[i][j] = blocks[i][j];
                
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }
    }
    
    public int dimension()                 // board dimension N
    {
        return dimension;
    }
    
    public int hamming()                   // number of blocks out of place
    {
        int cell;
        int value = 0;
        int i = 0, j = 0;
        for (i = 0; i < dimension; i++) {
            for (j = 0; j < dimension; j++) {
                cell = board[i][j];
                if (cell != 0 && ((cell - 1) / dimension != i || (cell - 1) % dimension != j)) {
                    value++;
                }
            }
        }
        return value;
    }
    
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int value = 0;
        int i = 0, j = 0;
        int cell;
        for (i = 0; i < dimension; i++) {
            for (j = 0; j < dimension; j++) {
                cell = board[i][j];
                if (cell != 0) {
                    value += Math.abs((cell - 1) / dimension - i) + Math.abs((cell - 1) % dimension - j);
                }
            }
        }
        return value;
    }
    
    public boolean isGoal()                // is this board the goal board?
    {
        int i = 0, j = 0;
        for (i = 0; i < dimension; i++) {
            for (j = 0; j < dimension; j++) {
                if (board[i][j] != i * dimension + j + 1)
                    return (i == dimension - 1) && (j == dimension - 1) && (board[i][j] == 0);
            }
        }
        return false;
    }
    
    public Board twin()                    // a board that is obtained by exchanging two adjacent blocks in the same row
    {
        Board newBoard = new Board(board);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                for (int k = 0; k < 2; k++) {
                    if (board[i][j] == 0) continue;
                    
                    int dx = i + DIR[k][0];
                    int dy = j + DIR[k][1];
                    if (dx >= 0 && dx < dimension && dy >= 0 && dy < dimension && board[dx][dy] != 0) {
                        newBoard.board[dx][dy] = board[i][j];
                        newBoard.board[i][j] = board[dx][dy];
                        return newBoard;
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public boolean equals(Object obj)        // does this board equal y?
    {
        if (obj instanceof Board && ((Board) obj).dimension == dimension) {
            Board other = (Board) obj;
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (board[i][j] != other.board[i][j])
                        return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        return new BoardIterable(this);
    }
    
    private static class BoardIterable implements Iterable<Board> {
        
        private BoardIterator boardIterator;
        
        public BoardIterable(Board board) {
            boardIterator = new BoardIterator(board);
        }

        @Override
        public Iterator<Board> iterator() {
            return boardIterator;
        }
        
    }
    
    private static class BoardIterator implements Iterator<Board> {
        
        private List<Board> boards;
        
        public BoardIterator(Board board) {
            boards = new ArrayList<Board>();
            
            int dimension = board.dimension;
            
            int x, y;
            
            for (int i = 0; i < DIR.length; i++) {
                x = board.x + DIR[i][0];
                y = board.y + DIR[i][1];
                
                if (x >= 0 && x < dimension && y >= 0 && y < dimension) {
                    Board newBoard = new Board(board.board);
                    newBoard.board[board.x][board.y] = newBoard.board[x][y];
                    newBoard.board[x][y] = 0;
                    newBoard.x = x;
                    newBoard.y = y;
                    boards.add(newBoard);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !boards.isEmpty();
        }

        @Override
        public Board next() {
            if (!hasNext()) throw new NoSuchElementException();
            return boards.remove(0);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder stringBuilder = new StringBuilder(dimension + "");
        for (int i = 0; i < dimension; i++) {
            stringBuilder.append("\n");
            for (int j = 0; j < dimension; j++)
                stringBuilder.append(" " + board[i][j]);
        }
        return stringBuilder.toString();
    }
    
    public static void main(String[] args) // unit tests (not graded)
    {
        int[][] data = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] data2 = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(data);
        Board board2 = new Board(data2);
        ArrayList<Board> list = new ArrayList<Board>();
        list.add(board);
        System.out.println(list.contains(board2));
    }
}