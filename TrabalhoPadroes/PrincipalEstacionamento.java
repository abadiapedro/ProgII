class Veiculo 
{
    public Veiculo()
    {

    }
}

class Passeio extends Veiculo 
{
    public Passeio () 
    {
        super();
    }
}

class Carga extends Veiculo 
{
    int qde_eixos;
    public Carga (int qde_eixos) 
    {
        super();
        this.qde_eixos = qde_eixos;
    }
    
    public int getQdeEixos() 
    { 
        return qde_eixos;
    }
}

class ContaEstacionamento 
{
     
    final long HORA = 1*1000*60*60; //hora a partir de milessegundos.
    final long DIA = 24*HORA;
    final long MES = 30*DIA;

    private Veiculo veiculo;
    private long inicio, fim;

    public ContaEstacionamento(Veiculo veiculo)
    {
        this.veiculo = veiculo;
        inicio = System.currentTimeMillis();
    }
    
     
    public double valorConta() 
    {
        double valor_conta = 0;
        fim = System.currentTimeMillis(); //timestap ou data do computador em milessegundos.
        long periodo = fim - inicio;
        System.out.println("periodo = " + periodo);
        if (veiculo instanceof Passeio) //instanceof = instancia de
        { 
            if (periodo <= 12 * HORA) 
            {
                valor_conta =  2.0 * Math.ceil((double)periodo / HORA);
            } 
            else
            {
                if (periodo > 12 * HORA && periodo <= 15 * DIA) 
                {
                    valor_conta =  26.0 * Math.ceil((double)periodo / DIA);
                } 
                else 
                {
                    valor_conta =  300.0 * Math.ceil((double)periodo / MES);
                }
            }
        }
        
        else 
        {
            if (veiculo instanceof Carga) 
            {  
                if(((Carga)veiculo).getQdeEixos() == 1)
                {
                    if (periodo <= (12 * HORA))
                    {
                        valor_conta =  3.0 * Math.ceil((double)periodo / HORA);
                    } 
                        
                    else 
                    {
                        if (periodo > 12 * HORA && periodo <= 15 * DIA) 
                        {
                            valor_conta =  30.0 * Math.ceil((double)periodo / DIA);
                        } 
                        else 
                        {
                            valor_conta =  350.0 * Math.ceil((double)periodo / MES);
                        }
                    }
                }

                else 
                {
                    if (periodo <= 12 * HORA) 
                    {
                        valor_conta =  5.0 * Math.ceil((double) periodo / HORA);
                    }

                    else 
                    {
                        if (periodo > 12 * HORA && periodo <= 15 * DIA) 
                        {
                            valor_conta =  35.0 * Math.ceil((double)periodo / DIA);
                        } 
                        else 
                        {
                            valor_conta =  400.0 * Math.ceil((double)periodo / MES);
                        }
                    }
                    
                }
            }
            // outras regras para outros tipos de veículo
        }
        return valor_conta;
    }
}
        
                
public class PrincipalEstacionamento
{
    public static void main(String args[])
    {
        //ContaEstacionamento ce = new ContaEstacionamento(new Passeio());
        Passeio p = new Passeio();       
        ContaEstacionamento ce = new ContaEstacionamento(p);        
        try { Thread.sleep (1000); } catch (InterruptedException ex) {}
        System.out.println("Valor do estacionamento:" + ce.valorConta());
    }
}
   