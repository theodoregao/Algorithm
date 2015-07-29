import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Solver {
    private Board initial;
    private BoardWraper solution;
    
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if (initial == null) throw new NullPointerException();
        this.initial = initial;
        solve();
    }
    
    public boolean isSolvable()            // is the initial board solvable?
    {
        MinPQ<BoardWraper> minPQ = new MinPQ<BoardWraper>(new BoardComparator());
        MinPQ<BoardWraper> minPQTwin = new MinPQ<BoardWraper>(new BoardComparator());
        List<Board> bag = new ArrayList<Board>();
        List<Board> bagTwin = new ArrayList<Board>();
        
        minPQ.insert(new BoardWraper(initial));
        bag.add(initial);
        
        minPQTwin.insert(new BoardWraper(initial.twin()));
        bagTwin.add(initial.twin());
        
        while (!minPQ.isEmpty() && !minPQTwin.isEmpty()) {
            BoardWraper boardWraper = minPQ.delMin();
            BoardWraper boardWraperTwin = minPQTwin.delMin();
            
            for (Board board: boardWraper.board.neighbors()) {
                
                if (board.isGoal()) {
                    solution = new BoardWraper(board, boardWraper.move + 1, boardWraper);
                    return true;
                }
                
                if (!bag.contains(board)) {
                    bag.add(board);
                    minPQ.insert(new BoardWraper(board, boardWraper.move + 1, boardWraper));
                }
            }
            
            for (Board board: boardWraperTwin.board.neighbors()) {
                
                if (board.isGoal()) {
                    solution = null;
                    return false;
                }
                
                if (!bagTwin.contains(board)) {
                    bagTwin.add(board);
                    minPQTwin.insert(new BoardWraper(board, boardWraper.move + 1, boardWraper));
                }
            }
        }
        
        return false;
    }
    
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if (!isSolvable()) return -1;
        int size = 0;
        BoardWraper boardWraper = solution;
        while (boardWraper.parent != null) {
            size++;
            boardWraper = boardWraper.parent;
        }
        return size;
    }
    
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (!isSolvable()) return null;
        return new SolutionIterable(solution);
    }
    
    private static class SolutionIterable implements Iterable<Board> {
        
        private BoardWraper boardWraper;
        
        public SolutionIterable(BoardWraper boardWraper) {
            this.boardWraper = boardWraper;
        }

        @Override
        public Iterator<Board> iterator() {
            return new SolutionIterator(boardWraper);
        }
        
    }
    
    private static class SolutionIterator implements Iterator<Board> {
        private Stack<Board> solution;
        
        public SolutionIterator(BoardWraper boardWraper) {
            solution = new Stack<Board>();
            BoardWraper tail = boardWraper;
            while (tail != null) {
                solution.push(tail.board);
                tail = tail.parent;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public Board next() {
            if (!hasNext()) throw new NoSuchElementException();
            return solution.pop();
        }
        
        @Override
        public boolean hasNext() {
            return !solution.isEmpty();
        }
        
    }
    
    private void solve() {
        MinPQ<BoardWraper> minPQ = new MinPQ<BoardWraper>(new BoardComparator());
        List<Board> bag = new ArrayList<Board>();
        
        minPQ.insert(new BoardWraper(initial));
        bag.add(initial);
        
        while (!minPQ.isEmpty()) {
            BoardWraper boardWraper = minPQ.delMin();
            
            for (Board board: boardWraper.board.neighbors()) {
                
                if (board.isGoal()) {
                    solution = new BoardWraper(board, boardWraper.move + 1, boardWraper);

                    return;
                }
                
                if (!bag.contains(board)) {
                    bag.add(board);
                    minPQ.insert(new BoardWraper(board, boardWraper.move + 1, boardWraper));
                }
            }
        }
        
        solution = null;
    }
    
    private static class BoardWraper {
        private Board board;
        private int move;
        private BoardWraper parent;
        
        public BoardWraper(Board board) {
            this.board = board;
            move = 0;
            parent = null;
        }
        
        public BoardWraper(Board board, int move, BoardWraper parent) {
            this.board = board;
            this.move = move;
            this.parent = parent;
        }
    }
    
    private static class BoardComparator implements Comparator<BoardWraper> {

        @Override
        public int compare(BoardWraper x, BoardWraper y) {
            return x.board.manhattan() * 100 + x.move - y.board.manhattan() * 100 - y.move;
        }
        
    }
    
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        int[][] data = new int[][] {{2,  9,  3,  5}, 
            {8, 11, 12,  7}, 
            {15,  4,  0, 13}, 
             {6,  1, 10, 14 
 
}};
        Board board = new Board(data);
        Solver solver = new Solver(board);
        
        if (solver.isSolvable())
            System.out.println(solver.moves());
            for (Board b: solver.solution()) {
                System.out.println(b);
            }
    }
}