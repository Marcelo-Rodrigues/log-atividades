package com.rod.log.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaSobre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ICONE_JANELA = "/copyright.png";
	private final JPanel contentPanel = new JPanel();	

	private final String CREDITOS = String.format("<html><center><h1>Créditos:</h1></center><ul>%s</ul></html>", String.join("\n",
			Arrays.asList(
					"<li>Icons made by Freepik (http://www.freepik.com) from https://www.flaticon.com is licensed by CC 3.0 BY (http://creativecommons.org/licenses/by/3.0/)</li>"
					)
			)
);

	/**
	 * Create the dialog.
	 */
	public JanelaSobre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaLog.class.getResource(ICONE_JANELA)));
		setModal(true);
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel(CREDITOS);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {						
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
