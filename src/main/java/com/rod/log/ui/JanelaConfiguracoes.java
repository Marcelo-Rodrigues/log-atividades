package com.rod.log.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.rod.log.model.Tag;
import com.rod.log.model.TagsFixasModel;
import com.rod.utils.ArquivoConfiguracoes;

public class JanelaConfiguracoes extends JDialog {

	private static final String ICONE_JANELA = "/toolbox.png";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private List<Tag> tags;
	private TagsFixasModel model;
	/* DefaultTableModel model; */

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public JanelaConfiguracoes() throws FileNotFoundException, IOException {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaConfiguracoes.class.getResource(ICONE_JANELA)));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		tags = ArquivoConfiguracoes.obterTarefas();
		model = new TagsFixasModel(tags);

		table = new JTable(model);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		JScrollPane scrollPane = new JScrollPane(table);
		tabbedPane.addTab("Atividades do Jira", null, scrollPane, null);
		table.setColumnSelectionAllowed(true);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setMnemonic('s');
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (salvarAlteracoes()) {
						dispose();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(btnSalvar);
		getRootPane().setDefaultButton(btnSalvar);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut_2);

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setMnemonic('i');
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarLinha();
			}
		});
		panel_1.add(btnIncluir);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic('e');
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerLinha();
			}
		});
		panel_1.add(btnExcluir);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setMnemonic('c');
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_1.add(btnCancelar);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut_1);

		this.pack();
	}

	private void criarLinha() {
		tags.add(new Tag());
		model.fireTableDataChanged();
		table.editCellAt(table.getRowCount() - 1, 0);
	}

	private void removerLinha() {
		int linhaSelecionada = table.getSelectedRow();
		if (linhaSelecionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para exclusão", "Exclusão",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			tags.remove(linhaSelecionada);
			model.fireTableDataChanged();
		}
	}

	private boolean salvarAlteracoes() throws FileNotFoundException, IOException {

		if (table.isEditing())
			table.getCellEditor().stopCellEditing();

		try {
			ArquivoConfiguracoes.salvarTarefas(tags);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, String
					.format("Ocorreu uma falha durante a gravação do arquivo de configurações: %s", e.getMessage()),
					"Salvar", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public void exibir() {
		setVisible(true);
	}
}
