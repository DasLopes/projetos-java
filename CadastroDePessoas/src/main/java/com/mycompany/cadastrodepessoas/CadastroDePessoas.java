/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cadastrodepessoas;

/**
 *
 * @author robson
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CadastroDePessoas extends JFrame {
    private ArrayList<String> registros;
    private JTextField nomeTextField, pesoTextField, alturaTextField;
    private JTextArea resultadoTextArea;

    public CadastroDePessoas() {
        registros = new ArrayList<>();

        // Configuração da janela
        setTitle("Cadastro de Pessoas");
        setSize(740, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel pesoLabel = new JLabel("Peso:");
        JLabel alturaLabel = new JLabel("Altura:");
        nomeTextField = new JTextField(20);
        pesoTextField = new JTextField(5);
        alturaTextField = new JTextField(5);
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton limparButton = new JButton("Limpar");
        resultadoTextArea = new JTextArea(10, 30);
        resultadoTextArea.setEditable(false);

        // Configuração do painel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(nomeLabel);
        panel.add(nomeTextField);
        panel.add(pesoLabel);
        panel.add(pesoTextField);
        panel.add(alturaLabel);
        panel.add(alturaTextField);
        panel.add(cadastrarButton);
        panel.add(limparButton);

        // Adiciona a ação ao botão "Cadastrar"
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPessoa();
            }
        });
        
        // Adiciona a ação ao botão "Limpar"
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoTextArea.setText("");
                registros.clear();
                /*
                for (String registro : registros) {
                    resultadoTextArea.append(registros + "\n");
                } 
                */
            }
        });

        // Adiciona os componentes à janela
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel, BorderLayout.NORTH);
        container.add(new JScrollPane(resultadoTextArea), BorderLayout.CENTER);

        // Exibe a janela
        setVisible(true);
    }

    private void cadastrarPessoa() {
        String nome = nomeTextField.getText();
        String peso = pesoTextField.getText();        
        float imc = Integer.parseInt(Math.round(Double.parseDouble(peso)) + "") / (Integer.parseInt(Math.round(Double.parseDouble(alturaTextField.getText())) + "") * Integer.parseInt(Math.round(Double.parseDouble(alturaTextField.getText())) + ""));        
        String msg;
        
        if(imc < 18.5){
            msg = "abaixo do peso, procure um médico";
        }else if(imc >= 18.5 && imc <= 24.9){
            msg = "peso normal, parabéns! continue praticando esportes";
        }else if(imc >= 25.0 && imc <= 29.9){
            msg = "pré-obesidade, procure um nutricionista";
        }else if(imc >= 30.0 && imc <= 34.9){
            msg = "obesidade grau 1, procure um médico";
        }else if(imc >= 35 && imc <= 39.9){
            msg = "obesidade grau 2, procure um médico";
        }else{
            msg = "obesidade grau 3, procure um médico";
        }
        
        if (!nome.isEmpty() && !peso.isEmpty()) {
            registros.add("Nome: " + nome + ", Peso: " + peso + ", Mensagem: " + msg);
            nomeTextField.setText("");
            pesoTextField.setText("");
            alturaTextField.setText("");
            exibirRegistros();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
        }
    }

    private void exibirRegistros() {
        resultadoTextArea.setText("");
        for (String registro : registros) {
            resultadoTextArea.append(registro + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroDePessoas();
            }
        });
    }
}
