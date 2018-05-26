package com.rod.log.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.rod.log.actions.ResumoAtividades;
import com.rod.log.model.EntradaLog;
import com.rod.log.model.TarefaJira;
import com.rod.log.service.AssociadorTarefa;

public class JanelaLog {

	private static final String ICONE_JANELA = "/tasks.png";
	private static final String DESCRICAO_ATIVIDADE_NAO_ASSOCIADA = "<html>Insira na descrição a palavra chave para associar uma atividade</html>";
	private AssociadorTarefa associadorTarefa;
	private JFrame frame;
	private JLabel lblAtividadeRelacionada;
	private JTextArea txtLog;
	private JButton btnConfigurar;
	private JButton btnSalvarLog;
	private TarefaJira tarefaAssociada;

	/**
	 * Create the application.
	 */
	public JanelaLog(AssociadorTarefa associadorTarefa) {
		this.associadorTarefa = associadorTarefa;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaLog.class.getResource(ICONE_JANELA)));
		frame.setType(Type.NORMAL);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(3, 3));

		// topo
		lblAtividadeRelacionada = new JLabel(DESCRICAO_ATIVIDADE_NAO_ASSOCIADA);
		frame.getContentPane().add(lblAtividadeRelacionada, BorderLayout.NORTH);

		// esquerda
		Component horizontalStrut_4 = Box.createHorizontalStrut(10);
		frame.getContentPane().add(horizontalStrut_4, BorderLayout.WEST);

		// centro
		txtLog = new JTextArea();
		txtLog.setRows(1);
		txtLog.setLineWrap(true);
		txtLog.setWrapStyleWord(true);
		frame.getContentPane().add(txtLog, BorderLayout.CENTER);
		txtLog.getDocument().addDocumentListener(new LogDocumentListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textoAlterado(e.getActionCommand());
			}
		}));

		// direita
		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		frame.getContentPane().add(horizontalStrut_5, BorderLayout.EAST);

		// baixo
		Box horizontalBox = Box.createHorizontalBox();
		frame.getContentPane().add(horizontalBox, BorderLayout.SOUTH);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);

		btnConfigurar = new JButton("Configurar");
		btnConfigurar.setMnemonic('o');
		horizontalBox.add(btnConfigurar);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton button = new JButton("?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JanelaSobre().setVisible(true);
			}
		});
		horizontalBox.add(button);
		
		JButton btnDia = new JButton("Dia");
		btnDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new ResumoAtividades().exibirResumoDia(Instant.now());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		horizontalBox.add(btnDia);

		btnSalvarLog = new JButton("Salvar log");
		btnSalvarLog.setMnemonic('s');
		horizontalBox.add(btnSalvarLog);
		frame.getRootPane().setDefaultButton(btnSalvarLog);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setMnemonic('c');
		horizontalBox.add(btnCancelar);

		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				fecharJanela();
			}
		});

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_3);

	}

	private void textoAlterado(String texto) {
		List<TarefaJira> tarefas = associadorTarefa.procurarTarefasRelacionadas(texto, false);
		if (tarefas.size() > 0) {
			definirTarefaAssociada(tarefas.get(0));
		} else {
			definirTarefaAssociada(null);
		}
	}

	private void definirTarefaAssociada(TarefaJira tarefa) {
		tarefaAssociada = tarefa;
		if(tarefa == null ) {
			lblAtividadeRelacionada.setText(DESCRICAO_ATIVIDADE_NAO_ASSOCIADA);
			lblAtividadeRelacionada.setToolTipText(null);
		} else {
			lblAtividadeRelacionada.setText(tarefa.getTitulo());
			lblAtividadeRelacionada.setToolTipText(tarefa.getDescricao());
		}
	}

	public void addConfigurarListener(ActionListener configurarListener) {
		btnConfigurar.addActionListener(configurarListener);
	}

	public void addSalvarListener(ActionListener salvarListener) {		
		btnSalvarLog.addActionListener(salvarListener);
	}

	public EntradaLog obterLog() {
		return new EntradaLog(txtLog.getText(), tarefaAssociada);
	}

	public void fecharJanela() {
		frame.dispose();
	}

	public void exibir() {
		frame.setVisible(true);
	}

}
