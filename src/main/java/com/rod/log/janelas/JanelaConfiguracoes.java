package com.rod.log.janelas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class JanelaConfiguracoes extends JFrame {

	private static final String ICONE_JANELA = "/box-config-icon.png";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JanelaConfiguracoes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaConfiguracoes.class.getResource(ICONE_JANELA)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
