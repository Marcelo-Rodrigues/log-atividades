package com.rod.log;

import java.awt.EventQueue;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Fabrica().criar(args).run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
