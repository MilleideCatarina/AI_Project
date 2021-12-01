package br.edu.project;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import weka.core.*;


import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Tela {
    private JPanel Classificacao;
    private JPanel Dados;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField9;
    private JTextField textField8;
    private JTextField textField7;
    private JTextField textField6;
    private JTextField textField5;
    private JTextField textField3;
    private JTextField textField4;
    private JButton classificarButton;

    public JPanel getClassificacao() {
        return Dados;
    }

    public Tela() {
        classificarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)  {
                try {
                    ConverterUtils.DataSource arquivo = new ConverterUtils.DataSource("data/plants.arff");
                    Instances dados = arquivo.getDataSet();

                    String[] parametros = new String[]{"-R", "1"};
                    Remove filtro = new Remove();
                    filtro.setOptions(parametros);
                    filtro.setInputFormat(dados);
                    dados = Filter.useFilter(dados, filtro);

                    AttributeSelection selAtributo = new AttributeSelection();
                    InfoGainAttributeEval avaliador = new InfoGainAttributeEval();
                    Ranker busca = new Ranker();
                    selAtributo.setEvaluator(avaliador);
                    selAtributo.setSearch(busca);
                    selAtributo.SelectAttributes(dados);
                    int[] indices = selAtributo.selectedAttributes();

                    String[] opcoes = new String[1];
                    opcoes[0] = "-U";
                    J48 arvore = new J48();
                    arvore.setOptions(opcoes);
                    arvore.buildClassifier(dados);

                    double[] vals = new double[dados.numAttributes()];
                    vals[0] = (Double.parseDouble(textField2.getText()));
                    vals[1] = (Double.parseDouble(textField3.getText()));
                    vals[2] = (Double.parseDouble(textField4.getText()));
                    vals[3] = (Double.parseDouble(textField5.getText()));
                    vals[4] = (Double.parseDouble(textField6.getText()));
                    vals[5] = (Double.parseDouble(textField7.getText()));
                    vals[6] = (Double.parseDouble(textField8.getText()));
                    vals[7] = (Double.parseDouble(textField9.getText()));


                    //criar uma instância baseada nestes atributos
                    Instance novaPlanta = new DenseInstance(1.0, vals);

                    //adicionar a instancia nos dados
                    novaPlanta.setDataset(dados);

                    //Classificar esta nova instância
                    double label = arvore.classifyInstance(novaPlanta);

                    //Imprimir o resultado da classificação
                    JOptionPane.showMessageDialog(null,dados.classAttribute().value((int) label));


                } catch (Exception ex) {
                    ex.printStackTrace();
                }



            }
        });
    }


}
