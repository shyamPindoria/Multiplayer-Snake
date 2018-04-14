import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class UIController extends JFrame implements ActionListener {

	/**
	 *  Default Serial Version
	 */
	private static final long serialVersionUID = 1L;


	JPanel contentPane;
	JPanel gamePane;
	JPanel startPane;
	JPanel loginPane;

	private JPanel boardPane;
	private JPanel scorePane;
	
	private CardLayout contentCardLayout;

	private JLabel labelPlayer1Score;
	private JLabel labelPlayer2Score;
	private JLabel labelPlayer3Score;
	private JLabel labelPlayer4Score;
	private JComboBox<Integer> comboBoxNumberOfPlayers;
	private JButton btnStart;

	public UIController() {
		
		setTitle("Multiplayer Snake");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentCardLayout = new CardLayout(0, 0);
		
		contentPane.setLayout(contentCardLayout);

		contentPane.add(createStartPane(), "startPane");
		
		contentPane.add(createLoginPane(), "loginPane");

		contentPane.add(createGamePane(), "gamePane");
		
		contentCardLayout.show(contentPane, "startPane");
		

		this.setVisible(true);

	}

	private JPanel createStartPane() {

		startPane = new JPanel();
		GridBagLayout gbl_startPane = new GridBagLayout();
		gbl_startPane.columnWidths = new int[]{0, 0, 0};
		gbl_startPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_startPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_startPane.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		startPane.setLayout(gbl_startPane);
		
		JLabel labelTitle = new JLabel("Multiplayer Snake");
		labelTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.gridwidth = 2;
		gbc_labelTitle.insets = new Insets(0, 0, 5, 0);
		gbc_labelTitle.gridx = 0;
		gbc_labelTitle.gridy = 0;
		startPane.add(labelTitle, gbc_labelTitle);
		
		JLabel labelNumberOfPlayers = new JLabel("Select the number of players:");
		GridBagConstraints gbc_labelNumberOfPlayers = new GridBagConstraints();
		gbc_labelNumberOfPlayers.anchor = GridBagConstraints.EAST;
		gbc_labelNumberOfPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_labelNumberOfPlayers.gridx = 0;
		gbc_labelNumberOfPlayers.gridy = 1;
		startPane.add(labelNumberOfPlayers, gbc_labelNumberOfPlayers);
		
		comboBoxNumberOfPlayers = new JComboBox<Integer>();
		comboBoxNumberOfPlayers.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4}));
		comboBoxNumberOfPlayers.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxNumberOfPlayers = new GridBagConstraints();
		gbc_comboBoxNumberOfPlayers.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxNumberOfPlayers.anchor = GridBagConstraints.WEST;
		gbc_comboBoxNumberOfPlayers.gridx = 1;
		gbc_comboBoxNumberOfPlayers.gridy = 1;
		startPane.add(comboBoxNumberOfPlayers, gbc_comboBoxNumberOfPlayers);
		
		btnStart = new JButton("Start");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridwidth = 2;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 2;
		startPane.add(btnStart, gbc_btnStart);
		return startPane;

	}

	private JPanel createLoginPane() {
		
		loginPane = new JPanel();
		
		return loginPane;
		
	}
	
	private JPanel createGamePane() {

		gamePane = new JPanel();
		gamePane.setLayout(new BorderLayout(0, 0));

		gamePane.add(createScorePane(), BorderLayout.WEST);

		gamePane.add(createBoardPane(), BorderLayout.CENTER);

		return gamePane;
	}

	private JPanel createScorePane() {
		
		scorePane = new JPanel();
		scorePane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		GridBagLayout gbl_scorePane = new GridBagLayout();
		gbl_scorePane.columnWidths = new int[]{0, 0, 0};
		gbl_scorePane.rowHeights = new int[]{0, 0, 0};
		gbl_scorePane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_scorePane.rowWeights = new double[]{0.0, 0.0, 0.0};
		scorePane.setLayout(gbl_scorePane);


		JLabel labelScores = new JLabel("Scores");
		labelScores.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		GridBagConstraints gbc_labelScores = new GridBagConstraints();
		gbc_labelScores.anchor = GridBagConstraints.NORTH;
		gbc_labelScores.insets = new Insets(0, 0, 5, 0);
		gbc_labelScores.gridwidth = 2;
		gbc_labelScores.gridx = 0;
		gbc_labelScores.gridy = 0;
		scorePane.add(labelScores, gbc_labelScores);


		labelPlayer1Score = new JLabel("Player 1: 0");
		GridBagConstraints gbc_labelPlayer1Score = new GridBagConstraints();
		gbc_labelPlayer1Score.insets = new Insets(0, 10, 0, 10);
		gbc_labelPlayer1Score.gridx = 0;
		gbc_labelPlayer1Score.gridy = 1;
		scorePane.add(labelPlayer1Score, gbc_labelPlayer1Score);
		

		labelPlayer2Score = new JLabel("Player 2: 0");
		GridBagConstraints gbc_labelPlayer2Score = new GridBagConstraints();
		gbc_labelPlayer2Score.insets = new Insets(10, 10, 0, 10);
		gbc_labelPlayer2Score.gridx = 0;
		gbc_labelPlayer2Score.gridy = 2;
		scorePane.add(labelPlayer2Score, gbc_labelPlayer2Score);


		labelPlayer3Score = new JLabel("Player 3: 0");
		GridBagConstraints gbc_labelPlayer3Score = new GridBagConstraints();
		gbc_labelPlayer3Score.insets = new Insets(10, 10, 0, 10);
		gbc_labelPlayer3Score.gridx = 0;
		gbc_labelPlayer3Score.gridy = 3;
		scorePane.add(labelPlayer3Score, gbc_labelPlayer3Score);


		labelPlayer4Score = new JLabel("Player 4: 0");
		GridBagConstraints gbc_labelPlayer4Score = new GridBagConstraints();
		gbc_labelPlayer4Score.anchor = GridBagConstraints.NORTH;
		gbc_labelPlayer4Score.insets = new Insets(10, 10, 0, 10);
		gbc_labelPlayer4Score.weighty = 1.0;
		gbc_labelPlayer4Score.gridx = 0;
		gbc_labelPlayer4Score.gridy = 4;
		scorePane.add(labelPlayer4Score, gbc_labelPlayer4Score);


		return scorePane;
	}

	private JPanel createBoardPane() {
		boardPane = new JPanel();
		boardPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		boardPane.setLayout(new BorderLayout(0, 0));
		return boardPane;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
