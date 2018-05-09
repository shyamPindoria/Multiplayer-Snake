import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import javax.swing.JTextField;

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
	
	private JLabel[] invalidLoginDetails;
	private JTextField[] usernames;
	private JPasswordField[] passwords;

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
		
		JButton btnStart = new JButton("Start");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridwidth = 2;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 2;
		btnStart.addActionListener(this);
		btnStart.setActionCommand("Start");
		this.startPane.add(btnStart, gbc_btnStart);
		return this.startPane;

	}

	private JPanel createLoginPane() {
		
		this.loginPane = new JPanel();
		GridBagLayout gbl_loginPane = new GridBagLayout();
		gbl_loginPane.columnWidths = new int[]{0, 0, 0};
		gbl_loginPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_loginPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_loginPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		this.loginPane.setLayout(gbl_loginPane);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.gridwidth = 2;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		this.loginPane.add(lblLogin, gbc_lblLogin);
		
		for (int i = 0; i < Game.numberOfPlayers; i++) {
			
			JPanel playerLoginPane = createPlayerLoginPane(i);
			GridBagConstraints gbc_playerLoginPane = new GridBagConstraints();
			gbc_playerLoginPane.insets = new Insets(0, 0, 0, 5);
			int x = i % 2 == 0 ? 0 : 1;
			int y = i < 2 ? 1 : 2;
			gbc_playerLoginPane.gridx = x;
			gbc_playerLoginPane.gridy = y;
			this.loginPane.add(playerLoginPane, gbc_playerLoginPane);
			
		}
		
		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.gridwidth = 2;
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 5;
		btnLogin.addActionListener(this);
		btnLogin.setActionCommand("Login");
		this.loginPane.add(btnLogin, gbc_btnLogin);
		
		return this.loginPane;
		
	}
	
	private JPanel createPlayerLoginPane(int player) {
		
		JPanel playerLoginPane = new JPanel();
		GridBagLayout gbl_playerLoginPane = new GridBagLayout();
		gbl_playerLoginPane.columnWidths = new int[]{0};
		gbl_playerLoginPane.rowHeights = new int[]{0};
		gbl_playerLoginPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_playerLoginPane.rowWeights = new double[]{Double.MIN_VALUE};
		playerLoginPane.setLayout(gbl_playerLoginPane);
		
		JLabel lblPlayer = new JLabel("Player " + (player+1));
		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
		gbc_lblPlayer.gridwidth = 2;
		gbc_lblPlayer.insets = new Insets(50, 0, 5, 0);
		gbc_lblPlayer.gridx = 0;
		gbc_lblPlayer.gridy = 0;
		playerLoginPane.add(lblPlayer, gbc_lblPlayer);
		
		this.invalidLoginDetails[player] = new JLabel("Invalid username or password");
		this.invalidLoginDetails[player].setForeground(Color.RED);
		this.invalidLoginDetails[player].setVisible(false);
		GridBagConstraints gbc_lblInvalidLogin = new GridBagConstraints();
		gbc_lblInvalidLogin.gridwidth = 2;
		gbc_lblInvalidLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblInvalidLogin.gridx = 0;
		gbc_lblInvalidLogin.gridy = 1;
		playerLoginPane.add(this.invalidLoginDetails[player], gbc_lblInvalidLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		playerLoginPane.add(lblUsername, gbc_lblUsername);
		
		this.usernames[player] = new JTextField();
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUsername.anchor = GridBagConstraints.WEST;
		gbc_textFieldUsername.gridx = 1;
		gbc_textFieldUsername.gridy = 2;
		this.usernames[player].setColumns(10);
		this.usernames[player].setText("user" + (player + 1));
		playerLoginPane.add(this.usernames[player], gbc_textFieldUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		playerLoginPane.add(lblPassword, gbc_lblPassword);
		
		this.passwords[player] = new JPasswordField();
		GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
		gbc_textFieldPassword.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPassword.anchor = GridBagConstraints.WEST;
		gbc_textFieldPassword.gridx = 1;
		gbc_textFieldPassword.gridy = 3;
		this.passwords[player].setColumns(10);
		this.passwords[player].setText("password" + (player + 1));
		playerLoginPane.add(this.passwords[player], gbc_textFieldPassword);
		
		return playerLoginPane;
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

	public void showGameBoard() {
		// All users logged
		boolean allLoggedIn = true;

		// Check if all players have successfully logged in
		for (int i = 0; i < Game.numberOfPlayers; i++) {

			if (!Game.humanPlayers.get(i).getCredentials().isValid()) {
				allLoggedIn = false;
				this.invalidLoginDetails[i].setVisible(true);
				System.out.println(Game.humanPlayers.get(i).getName() + "'s login details are invalid. Result: " + Game.humanPlayers.get(i).getCredentials().isValid());
			}
			
		}
		
		// Show game pan if all have logged in
		if (allLoggedIn) {
			this.contentCardLayout.show(this.contentPane, "gamePane");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// If start button was clicked
		if (e.getActionCommand().equals("Start")) {
			// Number of players
			Game.numberOfPlayers = (int) this.comboBoxNumberOfPlayers.getSelectedItem();
			// Initialize arrays
			this.invalidLoginDetails = new JLabel[Game.numberOfPlayers];
			this.usernames = new JTextField[Game.numberOfPlayers];
			this.passwords = new JPasswordField[Game.numberOfPlayers];
			// Create login pane
			this.contentPane.add(this.createLoginPane(), "loginPane");
			
			// Show login pane
			this.contentCardLayout.show(this.contentPane, "loginPane");
		
		// Login button clicked
		} else if (e.getActionCommand().equals("Login")) {
			
			// Reset all invalid details label
			for (JLabel lbl : this.invalidLoginDetails) {
				lbl.setVisible(false);
			}
			
			// Array to be passed to Game class
			Credentials[] credentials = new Credentials[Game.numberOfPlayers];
			
			// Get the usernames and passwords from the textfields
			for (int i = 0; i < Game.numberOfPlayers; i++) {
				
				// Create new credentials
				credentials[i] = new Credentials(this.usernames[i].getText(), new String(this.passwords[i].getPassword()));
					
			}
			
			// Login users
			Game.createPlayers(credentials);

		}
		
	}
	
}
