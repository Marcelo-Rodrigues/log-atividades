package com.rod.log.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.rod.log.service.AnaliseAtividades;
import com.rod.log.service.DescritorAtividade;

public class JanelaResumoDia extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextArea textArea;

	/**
	 * Create the dialog.
	 */
	public JanelaResumoDia(Date dia) {
		AnaliseAtividades analisador = new AnaliseAtividades();
		DescritorAtividade descritor = new DescritorAtividade(analisador);

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		textArea = new JTextArea();
		contentPanel.add(textArea, BorderLayout.CENTER);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		{
			UtilDateModel model = new UtilDateModel();
			Properties p = new Properties();
			p.put("text.today", "Hoje");
			p.put("text.month", "Mês");
			p.put("text.year", "Ano");
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

			model.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					try {
						setResumoDia(descritor.obterDetalhes(model.getValue().toInstant()));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			model.setValue(dia);
			JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
			contentPanel.add(datePicker, BorderLayout.NORTH);
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

	private void setResumoDia(String resumoDia) {
		textArea.setText(resumoDia);
	}

}
