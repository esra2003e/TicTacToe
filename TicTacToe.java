import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    public enum Cell {
        X, O, EMPTY
    }

    private Cell[][] board;
    private boolean isPlayerOneTurn;
    private JButton[][] buttons;

    public TicTacToe() {
        board = new Cell[3][3];
        buttons = new JButton[3][3];
        isPlayerOneTurn = true;

        // Initialize board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Cell.EMPTY;
            }
        }

        // Set up JFrame
        JFrame frame = new JFrame("TicTacToe");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));

        // Create buttons and add to frame
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeMove(row, col);
                    }
                });
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private void makeMove(int row, int col) {
        if (board[row][col] == Cell.EMPTY) {

            if (isPlayerOneTurn) {
                board[row][col] = Cell.X;
                buttons[row][col].setText("X");
            } else {
                board[row][col] = Cell.O;
                buttons[row][col].setText("O");
            }


            if (checkWin()) {
                JOptionPane.showMessageDialog(null, "Player " + (isPlayerOneTurn ? "1" : "2") + " wins!");
                resetGame();
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(null, "It's a draw!");
                resetGame();
            } else {

                isPlayerOneTurn = !isPlayerOneTurn;
            }
        }
    }

    private boolean checkWin() {

        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != Cell.EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) ||
                    (board[0][i] != Cell.EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i])) {
                return true;
            }
        }

        return (board[0][0] != Cell.EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] != Cell.EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Cell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Cell.EMPTY;
                buttons[i][j].setText("");
            }
        }
        isPlayerOneTurn = true;
    }


}













