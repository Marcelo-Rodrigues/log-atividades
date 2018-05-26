package com.rod.log.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class LogDocumentListener implements DocumentListener {
	String newline = "\n";
	ActionListener listener;

	public LogDocumentListener(ActionListener listener) {
		this.listener = listener;
	}

	public void insertUpdate(DocumentEvent e) {
		updateLog(e);
	}

	public void removeUpdate(DocumentEvent e) {
		updateLog(e);
	}

	public void changedUpdate(DocumentEvent e) {
		// Plain text components do not fire these events
	}

	public void updateLog(DocumentEvent e) {
		Document doc = e.getDocument();

		try {
			listener.actionPerformed(new ActionEvent(doc, 0, doc.getText(0, doc.getLength())));
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
}
