package org.example.Controller;

import org.example.Integration.RepositorioBillete;
import org.example.Integration.RepositorioCarro;
import org.example.Integration.RepositorioRenta;
import org.example.Model.*;
import org.example.Utils.Exeptions.ErrorPago;

import java.util.ArrayList;
import java.util.Calendar;

public class FacadeOCR {
    private Integer numeroLinea;
    private Renta rentaActual;
    private RepositorioCarro carroContro = new RepositorioCarro();
    private RepositorioRenta rentaContro = new RepositorioRenta();

    private RepositorioBillete billete = new RepositorioBillete();

    public DTOResumen respuestaRenta (Renta renta) {
        DTOResumen resumen = new DTOResumen();
        Integer totalRenta = 0;
        Integer saldoBilletes = 0;
        Double descuento;
        Integer lineasRenta;
        resumen.setMensajeError(null);
        resumen.setFecha(renta.getFechaHora());
        resumen.setLineas(renta.getLineas());
        if(renta.getLineas().size()==0){
            resumen.setTotalRenta(0);
            resumen.setVueltas(0);
            return resumen;
        }
        totalRenta = valorTotalRenta(renta);
        lineasRenta = this.carroContro.cantidadCarrosRenta(renta.getNumero());
        descuento = this.carroContro.calcularDescuento(lineasRenta);
        resumen.setTotalRenta((int) (totalRenta - totalRenta*descuento));

        for (Billete bll: renta.getPagoBilletes()){
            saldoBilletes += (bll.getDenominacion()*bll.getCantidad());
        }
        resumen.setSaldoBilletes(saldoBilletes);
        return resumen;
    }

    public DTOResumen crearRenta (Renta dtoRenta) throws ErrorPago {
        DTOResumen resumen = new DTOResumen();
        if (carroContro.consultarCarros() == null){
            resumen = respuestaRenta(this.rentaActual);
            resumen.setMensajeError("No hay carros en la base de datos");
            return null;
        }
        rentaContro.insertarRenta(resumen, dtoRenta.getFechaHora());
        return resumen;
    }

    public DTOResumen agregarLinea (Linea dtoLinea) throws ErrorPago {
        this.numeroLinea ++;
        DTOResumen resumen;
        Linea lineaTemp;
        dtoLinea.setNumero(numeroLinea);
        //this.rentaActual.setNumero(rentaActual.getNumero());

        if (carroContro.existeCarro(dtoLinea.getCarroRentado().getPlaca()) == null){
            resumen = respuestaRenta(this.rentaActual);
            resumen.setMensajeError("El carro seleccionado no se encuentra en la Base de Datos");
            return resumen;
        }
        if (carroContro.verificarExistencias(dtoLinea.getCarroRentado().getPlaca(), dtoLinea.getCantidad()) == null){
            resumen = respuestaRenta(this.rentaActual);
            resumen.setMensajeError("El carro seleccionado no cuenta con las existencias necesarias");
            return resumen;
        }
        if ((lineaTemp = carroContro.existeCarroEnLinea(dtoLinea.getCarroRentado().getPlaca())) != null){
            this.numeroLinea --;
            Integer cantidad = lineaTemp.getCantidad() + dtoLinea.getCantidad();
            carroContro.updateLinea(cantidad, lineaTemp.getID());
            for (Linea ln : this.rentaActual.getLineas()){
                if (ln.getNumero() == lineaTemp.getNumero()){
                    int subTotal = cantidad*ln.getCarroRentado().getPrecio();
                    ln.setCantidad(cantidad);
                    ln.setSubTotal(subTotal);
                }
            }
            resumen = respuestaRenta(this.rentaActual);
            return resumen;
        }
        System.out.println("renta :" + this.rentaActual.getNumero() + "Linea: "+ numeroLinea);
        carroContro.insertarLinea(dtoLinea, this.rentaActual.getNumero());
        this.rentaActual.getLineas().add(dtoLinea);
        resumen = respuestaRenta(this.rentaActual);
        System.out.println("Renta: " +this.rentaActual.getNumero()+ " Linea" + this.numeroLinea);
        return resumen;
    }

    public DTOResumen eliminarLinea (Linea dtoLinea) throws ErrorPago{
        DTOResumen resumen;
        this.numeroLinea --;

        if (carroContro.existeCarro(dtoLinea.getCarroRentado().getPlaca()) == null){
            resumen = respuestaRenta(this.rentaActual);
            resumen.setMensajeError("El carro seleccionado no se encuentra en la Base de Datos");
            return resumen;
        }

        System.out.println("renta :" + this.rentaActual.getNumero() + "Linea: "+ numeroLinea);
        carroContro.eliminar(dtoLinea,this.rentaActual.getNumero());
        this.rentaActual.getLineas().remove(dtoLinea);

        resumen=respuestaRenta(this.rentaActual);
        return resumen;

    }

    public DTOResumen agregarBillete (Billete dtoBillete) throws  ErrorPago {
        DTOResumen resumen;
        Billete billeteTemp;

        if (billete.existeBillete(dtoBillete.getDenominacion()) == null){
            resumen = respuestaRenta(this.rentaActual);
            resumen.setMensajeError("El billete seleccionado no se encuentra en la Base de Datos");
            return resumen;
        }
        if((billeteTemp=billete.existeBillete(dtoBillete.getDenominacion())) != null){
            Integer cantidad = billeteTemp.getCantidad();
            dtoBillete.setId(billete.existeIdBillete(billeteTemp));
            System.out.println("id "+ dtoBillete.getId());
            for (Billete bl : this.rentaActual.getPagoBilletes()){
                int total =(cantidad*bl.getDenominacion());
                bl.setCantidad(cantidad);
                bl.setTotal(total);
            }
            billete.insertarBillete(dtoBillete,this.rentaActual.getNumero());
            this.rentaActual.getPagoBilletes().add(dtoBillete);
            resumen=respuestaRenta(this.rentaActual);
            return resumen;
        }

      return null;
    }

    public DTOResumen terminarRenta (){
        return null;
    }

   public DTOResumen consultarRenta (Renta dtoRenta){
        Renta rentaConsultada = rentaContro.ConsultarRenta(dtoRenta.getNumero());
        DTOResumen resumen;
        if (rentaConsultada.getNumero() == null){
            resumen = respuestaRenta(rentaConsultada);
            resumen.setMensajeError("La renta consultada no existe");
            return resumen;
        }
        System.out.println(rentaConsultada.toString());
        resumen = respuestaRenta(rentaConsultada);
        return resumen;
    }

    public ArrayList<DTOReporte> consultarAcomulados (){
       return rentaContro.ConsultarAcumulados();
    }

    public ArrayList <Carro> consultarCarros (){
        return this.carroContro.consultarCarros();
    }

    public ArrayList <Billete> consultarBilletes (){
        return this.billete.consultarTiposBillete();
    }

    public RepositorioCarro getCarroContro() {
        return carroContro;
    }

    public RepositorioRenta getRentaContro() {
        return rentaContro;
    }

    public Renta getRentaActual() {
        return rentaActual;
    }

    public RepositorioBillete getBillete() {
        return billete;
    }

    /*
    * funcion que crea una nueva renta - esto para no tener que usar metodos set y hacer un poco mas limpio el codigo
    * asigna nueva instancia de renta con el numero dado por el controlador de la pantalla y el numero de lineas de setea en 0
    * ya que son nuevas lineas
    * */
    public Renta buildNuevaRenta (Integer numeroRenta, Renta nuevaRenta, Calendar fecha){
        this.rentaActual = nuevaRenta;
        this.rentaActual.setNumero(numeroRenta);
        this.numeroLinea = 0;
        this.rentaActual.setFechaHora(fecha);
        return rentaActual;
    }
    /*
    * Funcion que calcula el total de una renta, para terminar renta es necesario mandar
    * la renta actual (this.rentaActual)
    * */
    public Integer valorTotalRenta (Renta renta){
        Integer totalRenta = 0;
        for (Linea ln : renta.getLineas()){
            totalRenta += ln.getSubTotal();
        }
        return totalRenta;
    }
}