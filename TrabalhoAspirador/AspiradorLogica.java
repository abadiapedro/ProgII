import java.util.Random;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.StyleConstants.ParagraphConstants;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;


/* Parte gráfica inicio
 * 
 */
interface InterfaceGraficoUI
{

}

class GraficoUI extends JFrame implements InterfaceGraficoUI
{
    public GraficoUI(String titulo)
    {
        // TODO Auto-generated constructor stud
        super(titulo);
    }

    public void sensorSujeira(){}
    public void sensorParede(){}
    public void moverAspirador(int linha, int coluna){}
    public void moverAspirador(int linha, int coluna, String direcao){}
    public void mudarDirecaoAspirador(String direcao){}
    public void sugador(){}
    public void mostrarSujeira(int linha, int coluna){}
    public void apagarSujeira(int linha, int coluna){}
    public void acaoRealizar(){}
}

class GUI extends GraficoUI
{
    JPanel sala;
    JLabel sujeira, parede, mover, direcao, sugador, acao;
    JLabel sujeiras[][] = new JLabel [5][5], rastro[][] = new JLabel[5][5];
    JLabel aspirador;
    int linha=0, coluna=0;

    public GUI (String titulo)
    {
        super(titulo);
        setSize(700, 400);
        setLocation(250, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        montarLayout();
        setVisible(true);
    }

    protected void tempo(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }catch (InterruptedException e){}
    }

    protected void initMatrizSujeira(JPanel sala)
    {
        for (int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                rastro[i][j] = new JLabel(new ImageIcon("rastro.png"));
                rastro[i][j].setBounds(0+(i*64), 0+(j*64), 64, 64);
                rastro[i][j].setVisible(false); //mostra ou não os rastros
                sala.add(rastro[i][j]);
            }
        }
    }

    protected void initAspirador(JPanel sala)
    {
        aspirador = new JLabel(new ImageIcon("aspiradorSul.gif"));
        aspirador.setBounds(0,0,64,64);
        sala.add(aspirador);
    }

    protected void initSensores()
    {
        JPanel sensores = new JPanel();

        sensores.setLayout(new FlowLayout(FlowLayout.LEFT));
        sujeira = new JLabel("sujeira", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        parede = new JLabel("parede", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        sensores.setBounds(350,10,165,60);
        sensores.setBorder(new TitledBorder(new LineBorder(Color.black,1), "Sensores"));
        sensores.add(sujeira);
        sensores.add(parede);
        getContentPane().add(sensores);
    }

    protected void initAtuadores()
    {
        JPanel atuadores = new JPanel();

        atuadores.setLayout(new FlowLayout(FlowLayout.LEFT));
        mover = new JLabel("movimento", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        direcao = new JLabel("direção", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        sugador = new JLabel("sugador", new ImageIcon("luzbranca.png"), JLabel.LEFT);

        atuadores.setBounds(350,75,165,110);
        atuadores.setBorder(new TitledBorder(new LineBorder(Color.black,1),"Atuadores"));
        atuadores.add(sugador);
        atuadores.add(mover);
        atuadores.add(direcao);
        getContentPane().add(atuadores);
    }

    protected void initControle()
    {
        JPanel controle = new JPanel();

        controle.setLayout(new FlowLayout(FlowLayout.LEFT));
        acao = new JLabel("ação", new ImageIcon("luzbranca.png"), JLabel.LEFT);
        controle.setBounds(350, 190, 165, 60);
        controle.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Controle"));
        controle.add(acao);
        getContentPane().add(controle);
    }

    protected void montarLayout()
    {
        setLayout(null);
        sala = new JPanel();
        sala.setLayout(null);
        sala.setBounds(10, 10, 320, 320);
        sala.setBackground(new Color (224, 248, 227));
        initAspirador(sala);
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
        mostrarRastro(this.linha, this.coluna);
        this.linha = (linha*64);
        this.coluna = (coluna*64);
        aspirador.setLocation(this.coluna, this.linha);
        mover.setIcon(new ImageIcon("luzbranca.png"));
        tempo(500);
    }

    public void moverAspirador(int linha, int coluna, String direcao)
    {
        int i, j, delta;

        mostrarRastro(this.linha, this.coluna);
        mover.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(500);

        if(direcao.equalsIgnoreCase("sul"))
        {
            delta = this.linha;
            for(i=0; i<(Math.abs((linha*64)-delta));i++)
            {
                this.linha++;
                aspirador.setIcon(new ImageIcon("aspiradorSul.png"));
                aspirador.setLocation(this.coluna, this.linha);

                try{
                    Thread.sleep(5);
                }catch(InterruptedException e){

                }
            }
        }

        if(direcao.equalsIgnoreCase("norte"))
        {
            delta = this.linha;
            for(i=0; i<(Math.abs((linha*64)-delta)); i++)
            {
                this.linha--;
                aspirador.setIcon(new ImageIcon("aspiradorNorte.png"));
                aspirador.setLocation(this.coluna, this.linha);
                try{
                    Thread.sleep(5);
                }catch (InterruptedException e){

                }
            }
        }

        if(direcao.equalsIgnoreCase("oeste"))
        {
            delta = this.coluna;
            for(i=0; i<(Math.abs((coluna*64)-delta));i++)
            {
                this.coluna--;
                aspirador.setIcon(new ImageIcon("aspiradorOeste.png"));
                aspirador.setLocation(this.coluna, this.linha);
                try{
                    Thread.sleep(5);
                }catch (InterruptedException e){

                }
            }
        }


        mover.setIcon(new ImageIcon("luzbranca.png"));
        tempo(50);
    };

    public void mudarDirecaoAspirador(String direcao)
    {
        this.direcao.setIcon(new ImageIcon("luzbranca.png"));
        tempo(50);
        this.direcao.setIcon(new ImageIcon("luzbranca.png"));
        tempo(50);
    };

    public void sugador ()
    {
        sugador.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(50);
        sugador.setIcon(new ImageIcon("luzbranca.png"));
    };

    public void acaoRealizar()
    {
        acao.setIcon(new ImageIcon("luzvermelha.png"));
        tempo(50);
        acao.setIcon(new ImageIcon("luzbranca.png"));
        tempo(50);
    };

    protected void mostrarRastro(int linha, int coluna)
    {
        rastro[(int)coluna/64][(int)linha/64].setVisible(true);
    }

    public void mostrarSujeira(int linha, int coluna)
    {
        tempo(50);
        sujeiras[coluna][linha].setVisible(true);
    };

    public void apagarSujeira(int linha, int coluna)
    {
        tempo(50);
        sujeiras[coluna][linha].setVisible(false);
    };
}

/* Parte grafica fim
 * 
 */

class ParametroPorReferencia
{
    /**
     * Classe de aplicação porque foi desenvolvida para
     * resolver problemas de implementação: passagem de
     * parametro por referencia
     */
    public int valor;
}

class AdaptadorGUI
{
    /**
     * Classe adaptadora para a Graphical User Interface
     */

    protected GUI display;
    protected static AdaptadorGUI instancia=null;
    protected AdaptadorGUI()
    {
        display = null;
    }

    public static AdaptadorGUI obterInstancia()
    {
        if(instancia == null)
        {
            instancia = new AdaptadorGUI();
        }

        return instancia;
    }

    public void setDisplay (GUI display)
    {
        this.display = display;
    }

    public void sensorSujeira()
    {
        display.sensorSujeira();
    }

    public void sensorParede()
    {
        display.sensorParede();
    }
     
    public void moverAspirador(int linha, int coluna, String direcao)
    {
        display.moverAspirador(linha, coluna, direcao);
    }

    public void mudarDirecaoAspirador(String direcao)
    {
        display.mudarDirecaoAspirador(direcao);
    }

    public void sugador()
    {
        display.sugador();
    }

    public void mostrarSujeira(int linha, int coluna)
    {
        display.mostrarSujeira(linha, coluna);
    }

    public void apagarSujeira(int linha, int coluna)
    {
        display.apagarSujeira(linha, coluna);
    }

     public void acaoRealizar()
    {
        display.acaoRealizar();
    }

    public void mostrarSala(Sala sala)
    {
        //Metodo provisorio para mostrar toda a sala em modo texto
        sala.texto();
    }
}

class Aspirador
{
    protected int linha;
    protected int coluna;
    protected int direcao;
    protected boolean interruptor;
    protected Esteira [] direcoes = new Esteira[4];
    protected Esteira esteira;
    protected Sensor sensorSujeira, sensorParede;
    protected Sugador sugador;
    protected boolean [][] caminho_percorrido;

    public Aspirador (int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
        interruptor = false; //estado de inicialização

        direcoes[0] = new Norte();
        direcoes[1] = new Leste();
        direcoes[2] = new Sul();
        direcoes[3] = new Oeste();
        direcao = 2; // O aspirador comeca virado para o sul
        esteira = direcoes[direcao];
        sensorSujeira = new SensorSujeira();
        sensorParede = new SensorParede();
        sugador = new Sugador();
    }

    protected void initCaminhoPercorrido(Sala sala)
    {
        caminho_percorrido = new boolean[sala.qdeLinhas()][sala.qdeColunas()];
        for (int i=0;i<sala.qdeColunas();i++)
        {
            for(int j=0; j<sala.qdeColunas();j++)
            {
                caminho_percorrido[i][j] = false;
            }
        }
    }

    protected boolean jaPercorreuTodaSala(Sala sala)
    {
        for (int i=0; i<sala.qdeLinhas();i++)
        {
            for(int j=0; j<sala.qdeColunas();j++)
            {
                if(!caminho_percorrido[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void ligar(){interruptor = true;}
    protected void desligar(){interruptor = false;}
    protected boolean estaLigado(){return interruptor;}

    protected int qualAcaoRealizar()
    {
        Random moeda = new Random();
        AdaptadorGUI.obterInstancia().acaoRealizar();
        return (moeda.nextInt(100)%2);
    }

    protected void mudarDirecao()
    {
        Random moeda = new Random();
        int direcao = (moeda.nextInt(100)%2); //0 esquerda 1 direita

        if(direcao ==0)
        {
            //esquerda
            this.direcao--;
            if(this.direcao == -1) 
            {
                this.direcao =3;
            }
        }
        else
        {
            //direita
            this.direcao++;
            if(this.direcao ==4)
            {
                this.direcao=0;
            }
        }

        esteira = direcoes[this.direcao]; //virando a esteira
        AdaptadorGUI.obterInstancia().mudarDirecaoAspirador(esteira.direcao());
    }

    protected boolean mover(Sala sala)
    {
        int linha = this.linha, coluna = this.coluna;
        ParametroPorReferencia prlinha = new ParametroPorReferencia(),
                                prcoluna = new ParametroPorReferencia();
        prlinha.valor = linha;
        prcoluna.valor = coluna;

        esteira.ativar(prlinha, prcoluna); // Passagem de parametro entrada e saida (por referencia)
        /**
         * A mensagem esteira.ativar(prlinha, prcoluna) gera a provavel posicao para onde o aspirador
         * deseja mover. Pode ser ou para o Norte, ou Leste etc...
         */
    
         linha = prlinha.valor;
         coluna = prcoluna.valor;
    
         if(!sensorParede.ativar(linha, coluna, sala))
         {
             this.linha = linha;
             this.coluna = coluna;
             AdaptadorGUI.obterInstancia().moverAspirador(this.linha, this.coluna, esteira.direcao());
             return true; //conseguiu mover
         }

         return false; // nao conseguiu mover
    }

    public void limpar(Sala sala)
    {
        boolean condicional;
        initCaminhoPercorrido(sala);

        while(estaLigado())
        {
            if(sensorSujeira.ativar(linha, coluna, sala))
            {
                sugador.ativar(linha, coluna, sala);
            }

            condicional = false;
            while(!condicional)
            {
                switch (qualAcaoRealizar())
                {
                    case 0: mudarDirecao(); condicional = true; break;
                    case 1: if(mover(sala))
                    {
                        //marcarCaminhoPercorrido(); //criar esse método
                        condicional = true;
                    }
                }// fim switch
            }// fim while (!condicional)

            if (jaPercorreuTodaSala(sala)){desligar();}

            AdaptadorGUI.obterInstancia().mostrarSala(sala);
        }//fim while (estaLigado())
    }
}

interface Sensor
{
    public boolean ativar (int linha, int coluna, Sala sala);
}

class SensorSujeira implements Sensor
{
    public boolean ativar (int linha, int coluna, Sala sala)
    {
        AdaptadorGUI.obterInstancia().sensorSujeira();
        return sala.estaSujo(linha, coluna);
    }
}

class SensorParede implements Sensor
{
    public boolean ativar(int linha, int coluna, Sala sala)
    {
        AdaptadorGUI.obterInstancia().sensorParede();
        return sala.estaNoLimite(linha, coluna);
    }
}

abstract class Esteira
{
    abstract public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna);
    public String direcao()
    {
        return this.getClass().getName();
    }
}

class Norte extends Esteira
{
    public void ativar (ParametroPorReferencia linha, ParametroPorReferencia coluna)
    {
        linha.valor--;
        //super.ativar(linha, coluna);
    }
}

class Leste extends Esteira
{
    public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna)
    {
        coluna.valor++;
        ativar(linha, coluna); //ANTES: super.ativar(linha, coluna);
    }
}

class Sul extends Esteira
{
    public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna)
    {
        linha.valor++;
        ativar(linha, coluna); //ANTES: super.ativar(linha, coluna);
    }
}

class Oeste extends Esteira
{
    public void ativar(ParametroPorReferencia linha, ParametroPorReferencia coluna)
    {
        coluna.valor--;
        ativar(linha, coluna); //ANTES: super.ativar(linha, coluna);
    }
}

class Sugador
{
    public void ativar(int linha, int coluna, Sala sala)
    {
        AdaptadorGUI.obterInstancia().sugador();
        sala.limpar(linha, coluna);
    }
}

class Sala
{
    protected boolean[][] sala = new boolean[5][5];
    int qde_linha, qde_coluna;

    public Sala()
    {
        qde_linha = 5;
        qde_coluna = 5;

        for(int i=0; i<qde_linha; i++)
        {
            for(int j=0; j<qde_coluna; j++)
            {
                sala[i][j] = false;
            }
        }
    }

    public void sujar(int linha, int coluna)
    {
        AdaptadorGUI.obterInstancia().mostrarSujeira(linha, coluna);
        sala[linha][coluna] = true;
    }

    public Boolean estaSujo(int linha, int coluna)
    {
        return sala[linha][coluna];
    }

    public void limpar(int linha, int coluna)
    {
        AdaptadorGUI.obterInstancia().apagarSujeira(linha, coluna);
        sala[linha][coluna] = false;
    }

    public boolean estaNoLimite(int linha, int coluna)
    {
        return !((linha >-1)&&(linha<qde_linha)&&(coluna>-1)&&(coluna<qde_coluna));
    }

    public int qdeLinhas()
    {
        return qde_linha;
    }

    public int qdeColunas()
    {
        return qde_coluna;
    }

    public void texto()
    {
        System.out.println("");
        System.out.println("");
        System.out.println("------SALA------");
        System.out.println("");
        System.out.println("");

        for (int i=0; i<qde_linha; i++)
        {
            for(int j=0; j<qde_coluna;j++)
            {
                if(sala[i][j])
                {
                    System.out.println("X");
                }
                else
                {
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
    }
}

class Porco
{
    public Porco()
    {

    }

    public void sujar(Sala sala)
    {
        Random valor_aleatorio = new Random();
        int qde_sujeiras;
        qde_sujeiras = valor_aleatorio.nextInt(sala.qdeLinhas()*sala.qdeColunas());

        for(int i = 1; i<=qde_sujeiras; i++)
        {
            int linha = valor_aleatorio.nextInt(sala.qdeLinhas());
            int coluna = valor_aleatorio.nextInt(sala.qdeColunas());
            if(!sala.estaSujo(linha, coluna))
            {
                sala.sujar(linha, coluna);
            }
        }
    }
}

class Controle
{
    public void simular()
    {
        Porco porco = new Porco();
        Sala sala = new Sala();
        AdaptadorGUI.obterInstancia().setDisplay(new GUI("ASPIRADOR DE PO"));
        Aspirador aspirador = new Aspirador(0,0);

        sala.texto();
        porco.sujar(sala);
        sala.texto();
        aspirador.ligar();
        aspirador.limpar(sala);
        sala.texto();
    }
}

public class AspiradorLogica
{
    public static void main(String[] args) {
        Controle controle = new Controle();
        controle.simular();
        System.out.println("Ok");
    }
}

