package org.jdi.springboot.websockets;

public class Datos
{
    
    private long maxima = Long.MIN_VALUE;
    private long total = Long.MIN_VALUE;
    private long libre = Long.MIN_VALUE;
    
    public Datos(){}
    
    public long getMaxima()
    {
        return this.maxima;
    }
    
    public void setMaxima(long value)
    {
        this.maxima = value;
    }
    
    public long getTotal()
    {
        return this.total;
    }
    
    public void setTotal(long value)
    {
        this.total = value;
    }
    
    public long getLibre()
    {
        return this.libre;
    }
    
    public void setLibre(long value)
    {
        this.libre = value;
    }
    
    
}