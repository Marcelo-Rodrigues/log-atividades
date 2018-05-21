package com.rod.log.janelas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.rod.log.TarefaJira;
import com.rod.log.TarefaJiraModel;
import com.rod.utils.ArquivoConfiguracoes;
import javax.swing.JTabbedPane;

public class JanelaConfiguracoes extends JDialog {

	private static final String ICONE_JANELA = "/box-config-icon.png";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private List<TarefaJira> tarefas;
	private TarefaJiraModel model;
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

		/*
		 * 
		 * model = new DefaultTableModel(new Object[][] {}, new String[] {
		 * "Titulo", "Descrição", "Palavra Chave", "Número da Tarefa (Jira)" });
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);


		tarefas = ArquivoConfiguracoes.obterTarefas();
		model = new TarefaJiraModel(tarefas);
		
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

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut_2);

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarLinha();
			}
		});
		panel_1.add(btnIncluir);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerLinha();
			}
		});
		panel_1.add(btnExcluir);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);

		JButton btnCancelar = new JButton("Cancelar");
		panel_1.add(btnCancelar);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut_1);

		/* atualizarTabela(ArquivoConfiguracoes.obterTarefas()); */

		this.pack();
	}

	private void criarLinha() {
		tarefas.add(new TarefaJira());
	}

	private void removerLinha() {
		int linhaSelecionada = table.getSelectedRow();
		if (linhaSelecionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para exclusão", "Exclusão",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			tarefas.remove(linhaSelecionada);
		}
	}

	/*
	 * private void atualizarTabela(List<TarefaJira> atividades) { for
	 * (TarefaJira atividade : atividades) { Object[] o = new Object[4]; o[0] =
	 * atividade.getTitulo(); o[1] = atividade.getDescricao(); o[2] =
	 * atividade.getPalavraChave(); o[3] = atividade.getNumeroTarefa();
	 * model.addRow(o); } }
	 */

	private boolean salvarAlteracoes() throws FileNotFoundException, IOException {
		/*List<TarefaJira> novasTarefas = new ArrayList<>();
		table.updateUI();
		for (int linha = 0; linha < model.getRowCount(); linha++) {
			TarefaJira tarefa = new TarefaJira();
			tarefa.setTitulo((String) table.getValueAt(linha, 0));
			tarefa.setDescricao((String) table.getValueAt(linha, 1));
			tarefa.setPalavraChave((String) table.getValueAt(linha, 2));

			try {
				tarefa.setNumeroTarefa((int) table.getValueAt(linha, 3));
			} catch (ClassCastException e) {
				JOptionPane.showMessageDialog(this, String
						.format("Número de tarefa inválido para a tarefa %s (linha %d)", tarefa.getTitulo(), linha),
						"Salvar", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			novasTarefas.add(tarefa);
		}

		List<TarefaJira> tarefas = ArquivoConfiguracoes.obterTarefas();
		tarefas.clear();
		tarefas.addAll(novasTarefas);*/
		if (table.isEditing())
		    table.getCellEditor().stopCellEditing();
		
		try {
			ArquivoConfiguracoes.salvarTarefas(tarefas);

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
