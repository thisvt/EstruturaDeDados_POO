import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class CalculadorIMC {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Calculador de IMC");
        frame.setSize(420, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ── Formulário ──────────────────────────────────────────
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JTextField campoNome   = new JTextField();
        JTextField campoIdade  = new JTextField();
        JTextField campoPeso   = new JTextField(); // kg
        JTextField campoAltura = new JTextField(); // cm

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(campoNome);

        formPanel.add(new JLabel("Idade:"));
        formPanel.add(campoIdade);

        formPanel.add(new JLabel("Peso (kg):"));
        formPanel.add(campoPeso);

        formPanel.add(new JLabel("Altura (cm):"));
        formPanel.add(campoAltura);

        // ── Botões ──────────────────────────────────────────────
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar   = new JButton("Limpar");
        JButton btnSair     = new JButton("Sair");

        buttonPanel.add(btnCalcular);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnSair);

        // ── Área de resultado ───────────────────────────────────
        JTextArea resultadoArea = new JTextArea(4, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado"));

        panel.add(formPanel,    BorderLayout.NORTH);
        panel.add(resultadoArea, BorderLayout.CENTER);
        panel.add(buttonPanel,  BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // ── BOTÃO CALCULAR ──────────────────────────────────────
        btnCalcular.addActionListener((ActionEvent e) -> {
            try {
                // Validação de campos vazios
                if (campoNome.getText().isEmpty()   ||
                    campoIdade.getText().isEmpty()   ||
                    campoPeso.getText().isEmpty()    ||
                    campoAltura.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(frame,
                            "Preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nome   = campoNome.getText().trim();
                int    idade  = Integer.parseInt(campoIdade.getText().trim());
                double peso   = Double.parseDouble(campoPeso.getText().trim().replace(",", "."));
                double altura = Double.parseDouble(campoAltura.getText().trim().replace(",", "."));

                // Validações de intervalo
                if (peso <= 0 || altura <= 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Peso e altura devem ser maiores que zero!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (idade < 1 || idade > 120) {
                    JOptionPane.showMessageDialog(frame,
                            "Idade inválida!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Converte altura de cm para metros
                double alturaM = altura / 100.0;

                // Cálculo do IMC
                double imc = peso / (alturaM * alturaM);

                // Classificação
                String classificacao;
                if      (imc < 18.5) classificacao = "Abaixo do peso";
                else if (imc < 25.0) classificacao = "Peso normal";
                else if (imc < 30.0) classificacao = "Sobrepeso";
                else if (imc < 35.0) classificacao = "Obesidade Grau I";
                else if (imc < 40.0) classificacao = "Obesidade Grau II";
                else                 classificacao = "Obesidade Grau III";

                // Exibe resultado formatado
                String imcFormatado = String.format("%.2f", imc);
                resultadoArea.setText(
                    "Paciente : " + nome + "\n" +
                    "IMC      : " + imcFormatado + "\n" +
                    "Status   : " + classificacao
                );

                // Salva no CSV
                salvarCSV(nome, idade, peso, altura, imc, classificacao);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Digite apenas números válidos para idade, peso e altura!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // ── BOTÃO LIMPAR ────────────────────────────────────────
        btnLimpar.addActionListener(e -> {
            campoNome.setText("");
            campoIdade.setText("");
            campoPeso.setText("");
            campoAltura.setText("");
            resultadoArea.setText("");
        });

        // ── BOTÃO SAIR ──────────────────────────────────────────
        btnSair.addActionListener(e -> System.exit(0));
    }

    // ── MÉTODO CSV ──────────────────────────────────────────────
    private static void salvarCSV(String nome, int idade, double peso,
                                   double altura, double imc, String classificacao) {
        try {
            File file = new File("imc_resultados.csv");

            boolean novoArquivo = !file.exists();

            FileWriter   fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Cabeçalho apenas na primeira vez
            if (novoArquivo) {
                bw.write("Nome,Idade,Peso(kg),Altura(cm),IMC,Classificacao");
                bw.newLine();
            }

            bw.write(String.format("%s,%d,%.1f,%.1f,%.2f,%s",
                    nome, idade, peso, altura, imc, classificacao));
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar no arquivo CSV!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
