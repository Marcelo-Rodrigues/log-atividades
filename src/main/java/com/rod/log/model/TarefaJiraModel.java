package com.rod.log.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TarefaJiraModel extends AbstractTableModel
	{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private final List<TarefaJira> tarefasJira;
	     
	    private final String[] columnNames = new String[] {
	    		"Titulo", "Descrição", "Palavra Chave", "Número da Tarefa (Jira)"
	    };
	    @SuppressWarnings("rawtypes")
		private final Class[] columnClass = new Class[] {
	        String.class, String.class, String.class, Integer.class
	    };
	 
	    public TarefaJiraModel(List<TarefaJira> tarefas)
	    {
	        this.tarefasJira = tarefas;
	    }
	     
	    @Override
	    public String getColumnName(int column)
	    {
	        return columnNames[column];
	    }
	 
	    @Override
	    public Class<?> getColumnClass(int columnIndex)
	    {
	        return columnClass[columnIndex];
	    }
	 
	    @Override
	    public int getColumnCount()
	    {
	        return columnNames.length;
	    }
	 
	    @Override
	    public int getRowCount()
	    {
	        return tarefasJira.size();
	    }
	 
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	        TarefaJira row = tarefasJira.get(rowIndex);
	        if(0 == columnIndex) {
	            return row.getTitulo();
	        }
	        else if(1 == columnIndex) {
	            return row.getDescricao();
	        }
	        else if(2 == columnIndex) {
	            return row.getPalavraChave();
	        }
	        else if(3 == columnIndex) {
	            return row.getNumeroTarefa();
	        }
	        
	        return null;
	    }
	    
	    @Override
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	    {
	        TarefaJira row = tarefasJira.get(rowIndex);
	        if(0 == columnIndex) {
	            row.setTitulo((String) aValue);
	        }
	        else if(1 == columnIndex) {
	            row.setDescricao((String) aValue);
	        }
	        else if(2 == columnIndex) {
	            row.setPalavraChave((String) aValue);
	        }
	        else if(3 == columnIndex) {
	            row.setNumeroTarefa((int) aValue);
	        }
	    }
	    
	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex)
	    {
	        return true;
	    }	    
	}
