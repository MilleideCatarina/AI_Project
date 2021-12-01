package br.edu.project;

import javax.swing.*;


public class Main {

    public static void main(String[] args) throws Exception {


        JFrame janela = new JFrame("Classificação");
        janela.setContentPane(new Tela().getClassificacao());
        janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janela.pack();
        janela.setVisible(true);




    }


}
