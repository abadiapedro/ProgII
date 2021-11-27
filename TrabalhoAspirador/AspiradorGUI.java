import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

interface InterfaceGraficoUI
{

}

class GraficoUI extends JFrame implements InterfaceGraficoUI
{
    public GraficoUI(String titulo)
    {
        //TODO Auto-generated construtor stub
        super(titulo);
    }

    public void sensorSujeira(){};

    public void sensorParede(){};

    public void moverAspirador(int linha, int coluna){};

    public void moverAspirador(int linha, int coluna, String direcao){};

    public void mudarDirecaoAspirador(String direcao){};

    public void sugador(){};
    
    public void mostrarSujeira(int linha, int coluna){}

    public void apagarSujeira (int linha, int coluna){}

    public void acaoRealizar(){};
}

class GUI extends GraficoUI
{
    JPanel sala;
    JLabel sujeira, parede, mover, direcao, sugador, acao;
    JLabel sujeiras [] [] = new JLabel[5][5];
    JLabel aspirador;
    int linha=0, coluna=0;

    public GUI (String titulo)
    {
        super(titulo);
        setSize(700, 400);
        setLocation(250,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        montarLayout();
        setVisible(true);
    }

    protected void tempo(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch
            (InterruptedException e){}
    }

    //-----INICIALIZAÇÃO DA MATRIZ DE SUJEIRA--------------------------------
    protected void initMatrizSujeira(JPanel sala)
    {
        for (int i=0; i<5; i++)
        {
            for (int j = 0; j<5; j++)
            {
                sujeiras[i][j] = new JLabel(new ImageIcon("lixo.gif"));
                sujeiras[i][j].setBounds(0+(i*64), 0+(j*64), 64,64);
                sujeiras[i][j].setVisible(false); //mostra ou não as sujeiras
                sala.add(sujeiras[i][j]);
            }
        }
    }
    
    //---INICIALIZAÇÃO DOS SENSORES---------------------------------------------------------------------
    protected void initSensores()
    {
        JPanel sensores = new JPanel();
        sensores.setLayout(new FlowLayout(FlowLayout.LEFT));
        sujeira = new JLabel("sujeira", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        parede = new JLabel("parede", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        sensores.setBounds(350, 10, 165, 60);
        sensores.setBorder(new TitledBorder(new LineBorder(Color.black,1), "Sensores"));
        sensores.add(sujeira);
        sensores.add(parede);
        getContentPane().add(sensores);
    }

    //---INICIALIZAÇÃO DOS ATUADORES---------------------------------------------------------------------
    protected void initAtuadores()
    {
        JPanel atuadores = new JPanel();
        atuadores.setLayout(new FlowLayout(FlowLayout.LEFT));
        mover = new JLabel("movimento", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        direcao = new JLabel ("direcao", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        sugador = new JLabel("sugador", new ImageIcon("luzbranca.png"), JLabel.LEFT);

        atuadores.setBounds(350, 75, 165, 110);
        atuadores.setBorder(new TitledBorder(new LineBorder(Color.black,1), "Atuadores"));
        atuadores.add(sugador);
        atuadores.add(mover);
        atuadores.add(direcao);
        getContentPane().add(atuadores);     
    }

    protected void initControle()
    {
        JPanel controle = new JPanel();
        controle.setLayout(new FlowLayout(FlowLayout.LEFT));
        acao = new JLabel("acao", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        controle.setBounds(350,190,165,60);
        controle.setBorder(new TitledBorder(new LineBorder(Color.black,1), "Controle"));
        controle.add(acao);
        getContentPane().add(controle);
    }

    protected void montarLayout()
    {
        setLayout(null);
        sala = new JPanel();
        sala.setLayout(null);
        sala.setBounds(10,10,320,320);
        sala.setBackground(new Color(224,248,227));
        //initAspirador(sala);
        initMatrizSujeira(sala);
        sala.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(sala);
        initSensores();
        initAtuadores();
        initControle();
    }

    public void sensorSujeira()
    {
        sujeira.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(500);
        sujeira.setIcon(new ImageIcon("luzbranca.png"));
        tempo(500);
    };

    public void sensorParede()
    {
        parede.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(500);
        parede.setIcon(new ImageIcon("luzbranca.png"));
        tempo(500);
    };

    public void moverAspirador(int linha, int coluna)
    {
        mover.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(500);
        this.linha = (linha*64);
        aspirador.setLocation(this.coluna, this.linha);
        mover.setIcon(new ImageIcon("luzbranca.png"));
        tempo(500);
    }

    public void moverAspirador(int linha, int coluna, String direcao)
    {
        int i, j, delta;
        mover.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(500);

        if(direcao.equalsIgnoreCase("sul"))
        {
            delta = this.linha;
            for(i=0; i<(Math.abs((linha*64)-delta));i++)
            {
                this.linha++;
                aspirador.setLocation(this.coluna, this.linha);
                try
                {
                    Thread.sleep(5);
                }
                catch (InterruptedException e){

                }
            }
        }

        if(direcao.equalsIgnoreCase("norte"))
        {
            delta = this.linha;
            for(i=0; i<(Math.abs((linha*64)-delta));i++)
            {
                this.linha--;
                aspirador.setLocation(this.coluna, this.linha);
                try
                {
                    Thread.sleep(5);
                }
                catch (InterruptedException e)
                {

                }
            }
        }

        if(direcao.equalsIgnoreCase("leste"))
        {
            delta = this.coluna;
            for(i=0; i<(Math.abs((coluna*64)-delta));i++)
            {
                this.coluna++;
                aspirador.setLocation(this.coluna, this.linha);
                try
                {
                    Thread.sleep(5);
                }
                catch (InterruptedException e)
                {

                }
            }
        }

        if(direcao.equalsIgnoreCase("oeste"))
        {
            delta = this.coluna;
            for(i=0; i<(Math.abs((coluna*64)-delta));i++)
            {
                this.coluna--;
                aspirador.setLocation(this.coluna, this.linha);

                try
                {
                    Thread.sleep(5);
                }
                catch(InterruptedException e)
                {

                }
            }
        }

        mover.setIcon(new ImageIcon("luzbranca.png"));
        tempo(500);
    };


    public void mudarDirecaoAspirador(String direcao)
    {
        this.direcao.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(500);
        this.direcao.setIcon(new ImageIcon("luzbranca.png"));
        tempo(500);
    };

    public void sugador()
    {
        sugador.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(500);
        sugador.setIcon(new ImageIcon("luzbranca.png"));
        tempo(500);
    };

    public void acaoRealizar()
    {
        acao.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(500);
        acao.setIcon(new ImageIcon("luzbranca.png"));
        tempo(500);
    };

    public void mostrarSujeira(int linha, int coluna)
    {
        tempo(500);
        sujeiras[linha][coluna].setVisible(true);
    };

    public void apagarSujeira(int linha, int coluna)
    {
        tempo(500);
        sujeiras[linha][coluna].setVisible(false);
    };

}

public class AspiradorGUI
{
    public static void main(String[] args) 
    {
        GraficoUI tela = new GUI("Aspirador de pós");
        tela.moverAspirador(0,0);

        try
        {
            Thread.sleep(1500);
        }
        catch (InterruptedException e){

        }
        
        tela.sensorSujeira();
        tela.sensorSujeira();
        tela.sensorParede();
        tela.sensorParede();
        tela.sugador();
        tela.mudarDirecaoAspirador("");
        tela.mudarDirecaoAspirador("");

        tela.acaoRealizar();
        tela.acaoRealizar();

        tela.mostrarSujeira(3, 3);
        tela.mostrarSujeira(2, 4);
        tela.mostrarSujeira(1, 2);

        tela.moverAspirador(4, 0, "sul");
        tela.moverAspirador(4, 4, "leste");
        tela.moverAspirador(0, 4, "norte");
        tela.moverAspirador(0, 0, "oeste");

        tela.moverAspirador(4, 0, "sul");
        tela.moverAspirador(4, 1, "leste");
        tela.moverAspirador(0, 1, "norte");
        tela.moverAspirador(0, 0, "oeste");

        tela.apagarSujeira(3, 3);
        tela.apagarSujeira(2, 4);
        tela.apagarSujeira(1, 2);
    }
}
