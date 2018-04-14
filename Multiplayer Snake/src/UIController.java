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


	private JPanel contentPane;
	private JPanel gamePane;
	private JPanel startPane;
	private JPanel loginPane;

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
		
		this.setTitle("Multiplayer Snake");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1000, 700);
		this.setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		
		this.contentCardLayout = new CardLayout(0, 0);
		
		this.contentPane.setLayout(this.contentCardLayout);

		this.contentPane.add(this.createStartPane(), "startPane");

		this.contentPane.add(this.createGamePane(), "gamePane");
		
		this.contentCardLayout.show(this.contentPane, "startPane");
		

		this.setVisible(true);

	}

	private JPanel createStartPane() {

		this.startPane = new JPanel();
		GridBagLayout gbl_startPane = new GridBagLayout();
		gbl_startPane.columnWidths = new int[]{0, 0, 0};
		gbl_startPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_startPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_startPane.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		this.startPane.setLayout(gbl_startPane);
		
		JLabel labelTitle = new JLabel("Multiplayer Snake");
		labelTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.gridwidth = 2;
		gbc_labelTitle.insets = new Insets(0, 0, 5, 0);
		gbc_labelTitle.gridx = 0;
		gbc_labelTitle.gridy = 0;
		this.startPane.add(labelTitle, gbc_labelTitle);
		
		JLabel labelNumberOfPlayers = new JLabel("Select the number of players:");
		GridBagConstraints gbc_labelNumberOfPlayers = new GridBagConstraints();
		gbc_labelNumberOfPlayers.anchor = GridBagConstraints.EAST;
		gbc_labelNumberOfPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_labelNumberOfPlayers.gridx = 0;
		gbc_labelNumberOfPlayers.gridy = 1;
		this.startPane.add(labelNumberOfPlayers, gbc_labelNumberOfPlayers);
		
		this.comboBoxNumberOfPlayers = new JComboBox<Integer>();
		this.comboBoxNumberOfPlayers.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4}));
		this.comboBoxNumberOfPlayers.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxNumberOfPlayers = new GridBagConstraints();
		gbc_comboBoxNumberOfPlayers.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxNumberOfPlayers.anchor = GridBagConstraints.WEST;
		gbc_comboBoxNumberOfPlayers.gridx = 1;
		gbc_comboBoxNumberOfPlayers.gridy = 1;
		this.startPane.add(this.comboBoxNumberOfPlayers, gbc_comboBoxNumberOfPlayers);
		
		this.btnStart = new JButton("Start");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridwidth = 2;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 2;
		this.btnStart.addActionListener(this);
		this.btnStart.setActionCommand("Start");
		this.startPane.add(this.btnStart, gbc_btnStart);
		return startPane;

	}

	private JPanel createLoginPane() {
		
		this.loginPane = new JPanel();
		System.out.println(this.comboBoxNumberOfPlayers.getSelectedItem());
		return this.loginPane;
		
	}
	
	private JPanel createGamePane() {

		this.gamePane = new JPanel();
		this.gamePane.setLayout(new BorderLayout(0, 0));

		this.gamePane.add(createScorePane(), BorderLayout.WEST);

		this.gamePane.add(createBoardPane(), BorderLayout.CENTER);

		return this.gamePane;
	}

	private JPanel createScorePane() {
		
		this.scorePane = new JPanel();
		this.scorePane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		GridBagLayout gbl_scorePane = new GridBagLayout();
		gbl_scorePane.columnWidths = new int[]{0, 0, 0};
		gbl_scorePane.rowHeights = new int[]{0, 0, 0};
		gbl_scorePane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_scorePane.rowWeights = new double[]{0.0, 0.0, 0.0};
		this.scorePane.setLayout(gbl_scorePane);


		JLabel labelScores = new JLabel("Scores");
		labelScores.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		GridBagConstraints gbc_labelScores = new GridBagConstraints();
		gbc_labelScores.anchor = GridBagConstraints.NORTH;
		gbc_labelScores.insets = new Insets(0, 0, 5, 0);
		gbc_labelScores.gridwidth = 2;
		gbc_labelScores.gridx = 0;
		gbc_labelScores.gridy = 0;
		this.scorePane.add(labelScores, gbc_labelScores);


		this.labelPlayer1Score = new JLabel("Player 1: 0");
		GridBagConstraints gbc_labelPlayer1Score = new GridBagConstraints();
		gbc_labelPlayer1Score.insets = new Insets(0, 10, 0, 10);
		gbc_labelPlayer1Score.gridx = 0;
		gbc_labelPlayer1Score.gridy = 1;
		this.scorePane.add(this.labelPlayer1Score, gbc_labelPlayer1Score);
		

		this.labelPlayer2Score = new JLabel("Player 2: 0");
		GridBagConstraints gbc_labelPlayer2Score = new GridBagConstraints();
		gbc_labelPlayer2Score.insets = new Insets(10, 10, 0, 10);
		gbc_labelPlayer2Score.gridx = 0;
		gbc_labelPlayer2Score.gridy = 2;
		this.scorePane.add(this.labelPlayer2Score, gbc_labelPlayer2Score);


		this.labelPlayer3Score = new JLabel("Player 3: 0");
		GridBagConstraints gbc_labelPlayer3Score = new GridBagConstraints();
		gbc_labelPlayer3Score.insets = new Insets(10, 10, 0, 10);
		gbc_labelPlayer3Score.gridx = 0;
		gbc_labelPlayer3Score.gridy = 3;
		this.scorePane.add(this.labelPlayer3Score, gbc_labelPlayer3Score);


		this.labelPlayer4Score = new JLabel("Player 4: 0");
		GridBagConstraints gbc_labelPlayer4Score = new GridBagConstraints();
		gbc_labelPlayer4Score.anchor = GridBagConstraints.NORTH;
		gbc_labelPlayer4Score.insets = new Insets(10, 10, 0, 10);
		gbc_labelPlayer4Score.weighty = 1.0;
		gbc_labelPlayer4Score.gridx = 0;
		gbc_labelPlayer4Score.gridy = 4;
		this.scorePane.add(this.labelPlayer4Score, gbc_labelPlayer4Score);


		return this.scorePane;
	}

	private JPanel createBoardPane() {
		this.boardPane = new JPanel();
		this.boardPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.boardPane.setLayout(new BorderLayout(0, 0));
		return this.boardPane;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			this.contentPane.add(this.createLoginPane(), "loginPane");
			this.contentCardLayout.show(this.contentPane, "loginPane");
			this.createLoginPane();
		}
		
	}
	
}
