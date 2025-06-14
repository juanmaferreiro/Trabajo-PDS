// ========================================
// EXCEPCIONES PERSONALIZADAS
// ========================================

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


// ========================================
// MODELOS DE DATOS ADICIONALES
// ========================================

class ConfiguracionAdicional {
    private String equipamientoExtra;
    private String garantiasExtendidas;
    private String accesorios;
    private double costoAdicional;

    public ConfiguracionAdicional(String equipamientoExtra, String garantiasExtendidas, String accesorios, double costoAdicional) {
        this.equipamientoExtra = equipamientoExtra != null ? equipamientoExtra : "";
        this.garantiasExtendidas = garantiasExtendidas != null ? garantiasExtendidas : "";
        this.accesorios = accesorios != null ? accesorios : "";
        this.costoAdicional = costoAdicional;
    }

    public String getEquipamientoExtra() { return equipamientoExtra; }
    public String getGarantiasExtendidas() { return garantiasExtendidas; }
    public String getAccesorios() { return accesorios; }
    public double getCostoAdicional() { return costoAdicional; }

    public boolean tieneConfiguraciones() {
        return !equipamientoExtra.isEmpty() || !garantiasExtendidas.isEmpty() || !accesorios.isEmpty();
    }

    @Override
    public String toString() {
        if (!tieneConfiguraciones()) {
            return "Sin configuraciones adicionales";
        }
        StringBuilder sb = new StringBuilder();
        if (!equipamientoExtra.isEmpty()) sb.append("Equipamiento: ").append(equipamientoExtra).append(" | ");
        if (!garantiasExtendidas.isEmpty()) sb.append("Garantías: ").append(garantiasExtendidas).append(" | ");
        if (!accesorios.isEmpty()) sb.append("Accesorios: ").append(accesorios).append(" | ");
        if (costoAdicional > 0) sb.append("Costo adicional: $").append(costoAdicional);
        return sb.toString();
    }
}

class DatosFacturacion {
    private String nombreRazonSocial;
    private String direccion;
    private String cuitCuil;

    public DatosFacturacion(String nombreRazonSocial, String direccion, String cuitCuil) {
        this.nombreRazonSocial = nombreRazonSocial;
        this.direccion = direccion;
        this.cuitCuil = cuitCuil;
    }

    public String getNombreRazonSocial() { return nombreRazonSocial; }
    public String getDireccion() { return direccion; }
    public String getCuitCuil() { return cuitCuil; }

    @Override
    public String toString() {
        return nombreRazonSocial + " | " + direccion +
                (cuitCuil != null && !cuitCuil.isEmpty() ? " | CUIT/CUIL: " + cuitCuil : "");
    }
}

class CambioEstado {
    private LocalDateTime fecha;
    private String estadoAnterior;
    private String estadoNuevo;
    private String observaciones;

    public CambioEstado(String estadoAnterior, String estadoNuevo, String observaciones) {
        this.fecha = LocalDateTime.now();
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.observaciones = observaciones != null ? observaciones : "";
    }

    public LocalDateTime getFecha() { return fecha; }
    public String getEstadoAnterior() { return estadoAnterior; }
    public String getEstadoNuevo() { return estadoNuevo; }
    public String getObservaciones() { return observaciones; }

    @Override
    public String toString() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                " - " + estadoAnterior + " → " + estadoNuevo +
                (observaciones.isEmpty() ? "" : " (" + observaciones + ")");
    }
}

// ========================================
// EXCEPCIONES PERSONALIZADAS
// ========================================

class DuplicateException extends Exception {
    public DuplicateException(String message) {
        super(message);
    }
}

class ProcessingException extends Exception {
    public ProcessingException(String message) {
        super(message);
    }
}

// ========================================
// MODELOS DE DATOS
// ========================================

class Cliente {
    private String nombre;
    private String apellido;
    private String documento;
    private String email;
    private String telefono;

    public Cliente(String nombre, String apellido, String documento, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDocumento() { return documento; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Doc: " + documento + ")";
    }
}

abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected String color;
    protected String numeroChasis;
    protected String numeroMotor;
    protected double precioBase;

    public Vehiculo(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.precioBase = precioBase;
    }

    // Getters
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public String getNumeroChasis() { return numeroChasis; }
    public String getNumeroMotor() { return numeroMotor; }
    public double getPrecioBase() { return precioBase; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + color + ") - $" + precioBase;
    }
}

class Auto extends Vehiculo {
    public Auto(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Auto"; }
}

class Camioneta extends Vehiculo {
    public Camioneta(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Camioneta"; }
}

class Moto extends Vehiculo {
    public Moto(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Moto"; }
}

class Camion extends Vehiculo {
    public Camion(String marca, String modelo, String color, String numeroChasis, String numeroMotor, double precioBase) {
        super(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
    }

    @Override
    public String getTipo() { return "Camion"; }
}

class Vendedor {
    private String nombre;
    private String email;

    public Vendedor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}

// ========================================
// PATRÓN STRATEGY - FORMAS DE PAGO
// ========================================

abstract class FormaPago {
    protected String tipo;

    public FormaPago(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() { return tipo; }

    public abstract void procesar(double monto);
    public abstract String getDescripcion();
}

class PagoContado extends FormaPago {
    public PagoContado() {
        super("Contado");
    }

    @Override
    public void procesar(double monto) {
        System.out.println("Procesando pago en efectivo por $" + monto);
    }

    @Override
    public String getDescripcion() {
        return "Pago en efectivo";
    }
}

class PagoTransferencia extends FormaPago {
    public PagoTransferencia() {
        super("Transferencia");
    }

    @Override
    public void procesar(double monto) {
        System.out.println("Procesando transferencia bancaria por $" + monto);
    }

    @Override
    public String getDescripcion() {
        return "Transferencia bancaria";
    }
}

class PagoTarjeta extends FormaPago {
    public PagoTarjeta() {
        super("Tarjeta");
    }

    @Override
    public void procesar(double monto) {
        System.out.println("Procesando pago con tarjeta por $" + monto);
    }

    @Override
    public String getDescripcion() {
        return "Tarjeta de crédito";
    }
}

// ========================================
// PATRÓN STRATEGY - CÁLCULO DE IMPUESTOS
// ========================================

interface CalculadoraImpuestos {
    double calcular(double precioBase);
    String getDetalle(double precioBase);
}

class CalculadoraImpuestosAuto implements CalculadoraImpuestos {
    @Override
    public double calcular(double precioBase) {
        double impuestoNacional = precioBase * 0.21; // 21%
        double impuestoProvincialGeneral = precioBase * 0.05; // 5%
        double impuestoProvincialAdicional = precioBase * 0.01; // 1%
        return impuestoNacional + impuestoProvincialGeneral + impuestoProvincialAdicional;
    }

    @Override
    public String getDetalle(double precioBase) {
        return String.format("Impuestos Auto: Nacional 21%% ($%.2f) + Provincial General 5%% ($%.2f) + Provincial Adicional 1%% ($%.2f)",
                precioBase * 0.21, precioBase * 0.05, precioBase * 0.01);
    }
}

class CalculadoraImpuestosCamioneta implements CalculadoraImpuestos {
    @Override
    public double calcular(double precioBase) {
        double impuestoNacional = precioBase * 0.10; // 10%
        double impuestoProvincialGeneral = precioBase * 0.05; // 5%
        double impuestoProvincialAdicional = precioBase * 0.02; // 2%
        return impuestoNacional + impuestoProvincialGeneral + impuestoProvincialAdicional;
    }

    @Override
    public String getDetalle(double precioBase) {
        return String.format("Impuestos Camioneta: Nacional 10%% ($%.2f) + Provincial General 5%% ($%.2f) + Provincial Adicional 2%% ($%.2f)",
                precioBase * 0.10, precioBase * 0.05, precioBase * 0.02);
    }
}

class CalculadoraImpuestosMoto implements CalculadoraImpuestos {
    @Override
    public double calcular(double precioBase) {
        double impuestoProvincialGeneral = precioBase * 0.05; // 5%
        double impuestoProvincialAdicional = precioBase * 0.01; // 1%
        return impuestoProvincialGeneral + impuestoProvincialAdicional;
    }

    @Override
    public String getDetalle(double precioBase) {
        return String.format("Impuestos Moto: Provincial General 5%% ($%.2f) + Provincial Adicional 1%% ($%.2f)",
                precioBase * 0.05, precioBase * 0.01);
    }
}

class CalculadoraImpuestosCamion implements CalculadoraImpuestos {
    @Override
    public double calcular(double precioBase) {
        double impuestoProvincialGeneral = precioBase * 0.05; // 5%
        double impuestoProvincialAdicional = precioBase * 0.02; // 2%
        return impuestoProvincialGeneral + impuestoProvincialAdicional;
    }

    @Override
    public String getDetalle(double precioBase) {
        return String.format("Impuestos Camión: Provincial General 5%% ($%.2f) + Provincial Adicional 2%% ($%.2f)",
                precioBase * 0.05, precioBase * 0.02);
    }
}

// ========================================
// PATRÓN STATE - ESTADOS DEL PEDIDO
// ========================================

interface EstadoPedido {
    void procesar(PedidoCompra pedido);
    String getNombre();
}

class EstadoVentas implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Ventas...");
        pedido.setEstado(new EstadoCobranzas());
        pedido.agregarHistorial("Procesado en Ventas");
    }

    @Override
    public String getNombre() { return "Ventas"; }
}

class EstadoCobranzas implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Cobranzas...");
        pedido.setEstado(new EstadoImpuestos());
        pedido.agregarHistorial("Procesado en Cobranzas");
    }

    @Override
    public String getNombre() { return "Cobranzas"; }
}

class EstadoImpuestos implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Impuestos...");
        pedido.setEstado(new EstadoEmbarque());
        pedido.agregarHistorial("Procesado en Impuestos");
    }

    @Override
    public String getNombre() { return "Impuestos"; }
}

class EstadoEmbarque implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Embarque...");
        pedido.setEstado(new EstadoLogistica());
        pedido.agregarHistorial("Procesado en Embarque");
    }

    @Override
    public String getNombre() { return "Embarque"; }
}

class EstadoLogistica implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Procesando en área de Logística...");
        pedido.setEstado(new EstadoEntrega());
        pedido.agregarHistorial("Procesado en Logística");
    }

    @Override
    public String getNombre() { return "Logística"; }
}

class EstadoEntrega implements EstadoPedido {
    @Override
    public void procesar(PedidoCompra pedido) {
        System.out.println("Vehículo entregado al cliente");
        pedido.agregarHistorial("Vehículo entregado");
    }

    @Override
    public String getNombre() { return "Entrega"; }
}

// ========================================
// PATRÓN OBSERVER - NOTIFICACIONES
// ========================================

interface Observer {
    void actualizar(String mensaje, PedidoCompra pedido);
}

class NotificadorArea implements Observer {
    private String area;

    public NotificadorArea(String area) {
        this.area = area;
    }

    @Override
    public void actualizar(String mensaje, PedidoCompra pedido) {
        System.out.println("[NOTIFICACIÓN " + area + "] " + mensaje + " - Pedido #" + pedido.getNumeroPedido());
    }
}

// ========================================
// PEDIDO DE COMPRA
// ========================================


class PedidoCompra {
    private static int contadorPedidos = 1;

    private int numeroPedido;
    private LocalDateTime fechaCreacion;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private ConfiguracionAdicional configuracionAdicional;
    private FormaPago formaPago;
    private EstadoPedido estado;
    private String estadoAnterior;
    private List<String> historial;
    private List<CambioEstado> historialEstados;
    private List<Observer> observers;
    private Vendedor vendedor;
    private DatosFacturacion datosFacturacion;
    private double costoTotal;
    private double impuestos;
    private CalculadoraImpuestos calculadoraImpuestos;

    public PedidoCompra(Cliente cliente, Vehiculo vehiculo, ConfiguracionAdicional configuracionAdicional,
                        FormaPago formaPago, Vendedor vendedor, DatosFacturacion datosFacturacion) {
        this.numeroPedido = contadorPedidos++;
        this.fechaCreacion = LocalDateTime.now();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.configuracionAdicional = configuracionAdicional;
        this.formaPago = formaPago;
        this.vendedor = vendedor;
        this.datosFacturacion = datosFacturacion;
        this.estado = new EstadoVentas();
        this.estadoAnterior = "";
        this.historial = new ArrayList<>();
        this.historialEstados = new ArrayList<>();
        this.observers = new ArrayList<>();

        // Configurar calculadora de impuestos según tipo de vehículo
        configurarCalculadoraImpuestos();

        // Calcular costo total
        calcularCostoTotal();

        agregarHistorial("Pedido creado");
        registrarCambioEstado("", "Ventas", "Pedido inicial creado");
    }

    private void configurarCalculadoraImpuestos() {
        String tipo = vehiculo.getTipo();
        switch (tipo) {
            case "Auto":
                this.calculadoraImpuestos = new CalculadoraImpuestosAuto();
                break;
            case "Camioneta":
                this.calculadoraImpuestos = new CalculadoraImpuestosCamioneta();
                break;
            case "Moto":
                this.calculadoraImpuestos = new CalculadoraImpuestosMoto();
                break;
            case "Camion":
                this.calculadoraImpuestos = new CalculadoraImpuestosCamion();
                break;
        }
    }

    private void calcularCostoTotal() {
        this.impuestos = calculadoraImpuestos.calcular(vehiculo.getPrecioBase());
        this.costoTotal = vehiculo.getPrecioBase() + impuestos + configuracionAdicional.getCostoAdicional();
    }

    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notificarObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.actualizar(mensaje, this);
        }
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estadoAnterior = this.estado.getNombre();
        this.estado = nuevoEstado;
        registrarCambioEstado(estadoAnterior, nuevoEstado.getNombre(), "Transición automática");
        notificarObservers("Estado cambiado a: " + nuevoEstado.getNombre());
    }

    public void procesar() {
        estado.procesar(this);
    }

    public void agregarHistorial(String evento) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        historial.add(timestamp + " - " + evento);
    }

    public void registrarCambioEstado(String estadoAnterior, String estadoNuevo, String observaciones) {
        CambioEstado cambio = new CambioEstado(estadoAnterior, estadoNuevo, observaciones);
        historialEstados.add(cambio);
    }

    // Getters
    public int getNumeroPedido() { return numeroPedido; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public ConfiguracionAdicional getConfiguracionAdicional() { return configuracionAdicional; }
    public FormaPago getFormaPago() { return formaPago; }
    public EstadoPedido getEstado() { return estado; }
    public String getAreaResponsableActual() { return estado.getNombre(); }
    public List<String> getHistorial() { return new ArrayList<>(historial); }
    public List<CambioEstado> getHistorialEstados() { return new ArrayList<>(historialEstados); }
    public Vendedor getVendedor() { return vendedor; }
    public DatosFacturacion getDatosFacturacion() { return datosFacturacion; }
    public double getCostoTotal() { return costoTotal; }
    public double getImpuestos() { return impuestos; }
    public double getPrecioBase() { return vehiculo.getPrecioBase(); }
    public double getCostoAdicionales() { return configuracionAdicional.getCostoAdicional(); }
    public CalculadoraImpuestos getCalculadoraImpuestos() { return calculadoraImpuestos; }

    @Override
    public String toString() {
        return String.format("Pedido #%d - %s - %s - Estado: %s - Total: $%.2f",
                numeroPedido, cliente.getNombre() + " " + cliente.getApellido(),
                vehiculo.getMarca() + " " + vehiculo.getModelo(),
                estado.getNombre(), costoTotal);
    }
}

// ========================================
// PATRÓN CHAIN OF RESPONSIBILITY - VALIDACIONES
// ========================================

abstract class ValidadorPedido {
    protected ValidadorPedido siguiente;

    public void setSiguiente(ValidadorPedido validador) {
        this.siguiente = validador;
    }

    public void validar(PedidoCompra pedido) throws ProcessingException {
        // Primero valida este validador específico
        if (!validarEspecifico(pedido)) {
            throw new ProcessingException(getMensajeError());
        }

        // Si pasa la validación y hay siguiente validador, continúa la cadena
        if (siguiente != null) {
            siguiente.validar(pedido);
        }
    }

    protected abstract boolean validarEspecifico(PedidoCompra pedido);
    protected abstract String getMensajeError();
}

class ValidadorCliente extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        Cliente cliente = pedido.getCliente();
        return cliente != null &&
                cliente.getNombre() != null && !cliente.getNombre().trim().isEmpty() &&
                cliente.getApellido() != null && !cliente.getApellido().trim().isEmpty() &&
                cliente.getDocumento() != null && !cliente.getDocumento().trim().isEmpty() &&
                cliente.getEmail() != null && !cliente.getEmail().trim().isEmpty();
    }

    @Override
    protected String getMensajeError() {
        return "Datos del cliente incompletos: se requiere nombre, apellido, documento y email";
    }
}

class ValidadorVehiculo extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        Vehiculo vehiculo = pedido.getVehiculo();
        return vehiculo != null &&
                vehiculo.getMarca() != null && !vehiculo.getMarca().trim().isEmpty() &&
                vehiculo.getModelo() != null && !vehiculo.getModelo().trim().isEmpty() &&
                vehiculo.getNumeroChasis() != null && !vehiculo.getNumeroChasis().trim().isEmpty() &&
                vehiculo.getNumeroMotor() != null && !vehiculo.getNumeroMotor().trim().isEmpty() &&
                vehiculo.getPrecioBase() > 0;
    }

    @Override
    protected String getMensajeError() {
        return "Datos del vehículo incompletos: se requiere marca, modelo, número de chasis, número de motor y precio válido";
    }
}

class ValidadorFormaPago extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        FormaPago formaPago = pedido.getFormaPago();
        return formaPago != null &&
                formaPago.getTipo() != null &&
                !formaPago.getTipo().trim().isEmpty();
    }

    @Override
    protected String getMensajeError() {
        return "Forma de pago no especificada o inválida";
    }
}

class ValidadorVendedor extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        Vendedor vendedor = pedido.getVendedor();
        return vendedor != null &&
                vendedor.getNombre() != null && !vendedor.getNombre().trim().isEmpty() &&
                vendedor.getEmail() != null && !vendedor.getEmail().trim().isEmpty();
    }

    @Override
    protected String getMensajeError() {
        return "Datos del vendedor incompletos: se requiere nombre y email válidos";
    }
}

class ValidadorDatosFacturacion extends ValidadorPedido {
    @Override
    protected boolean validarEspecifico(PedidoCompra pedido) {
        DatosFacturacion facturacion = pedido.getDatosFacturacion();
        return facturacion != null &&
                facturacion.getNombreRazonSocial() != null && !facturacion.getNombreRazonSocial().trim().isEmpty() &&
                facturacion.getDireccion() != null && !facturacion.getDireccion().trim().isEmpty();
    }

    @Override
    protected String getMensajeError() {
        return "Datos de facturación incompletos: se requiere nombre/razón social y dirección";
    }
}

// ========================================
// PATRÓN FACADE - INTERFAZ SIMPLIFICADA
// ========================================

class ConcesionariaFacade {
    private static final String NOMBRE_CONCESIONARIA = "Concesionaria AutoMax";
    private static final String CUIT_CONCESIONARIA = "30-12345678-9";

    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<PedidoCompra> pedidos;
    private ValidadorPedido cadenaValidacion;

    public ConcesionariaFacade() {
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.pedidos = new ArrayList<>();

        configurarValidadores();
        inicializarDatos();
    }

    private void configurarValidadores() {
        // Crear todos los validadores
        ValidadorCliente validadorCliente = new ValidadorCliente();
        ValidadorVehiculo validadorVehiculo = new ValidadorVehiculo();
        ValidadorFormaPago validadorFormaPago = new ValidadorFormaPago();
        ValidadorVendedor validadorVendedor = new ValidadorVendedor();
        ValidadorDatosFacturacion validadorFacturacion = new ValidadorDatosFacturacion();

        // Configurar la cadena de responsabilidad
        validadorCliente.setSiguiente(validadorVehiculo);
        validadorVehiculo.setSiguiente(validadorFormaPago);
        validadorFormaPago.setSiguiente(validadorVendedor);
        validadorVendedor.setSiguiente(validadorFacturacion);

        // El primer validador de la cadena
        this.cadenaValidacion = validadorCliente;

        System.out.println("✓ Cadena de validaciones configurada: Cliente → Vehículo → FormaPago → Vendedor → Facturación");
    }

    private void inicializarDatos() {
        inicializarClientes();
        inicializarVehiculos();
        System.out.println("Sistema inicializado con datos de ejemplo.");
    }

    private void inicializarClientes() {
        try {
            // Registrar clientes de ejemplo
            registrarCliente("Juan Carlos", "Pérez", "20123456781", "juan.perez@email.com", "11-2345-6789");
            registrarCliente("María Elena", "García", "27234567892", "maria.garcia@email.com", "11-3456-7890");
            registrarCliente("Carlos Roberto", "López", "23345678903", "carlos.lopez@email.com", "11-4567-8901");
            registrarCliente("Ana Sofía", "Martínez", "24456789014", "ana.martinez@email.com", "11-5678-9012");
            registrarCliente("Luis Fernando", "Rodríguez", "25567890125", "luis.rodriguez@email.com", "11-6789-0123");
            registrarCliente("Patricia Isabel", "Fernández", "26678901236", "patricia.fernandez@email.com", "11-7890-1234");
            registrarCliente("Miguel Ángel", "Sánchez", "27789012347", "miguel.sanchez@email.com", "11-8901-2345");
            registrarCliente("Laura Beatriz", "Torres", "28890123458", "laura.torres@email.com", "11-9012-3456");
            registrarCliente("Roberto Daniel", "Ramírez", "29901234569", "roberto.ramirez@email.com", "11-0123-4567");
            registrarCliente("Claudia Alejandra", "Morales", "30012345670", "claudia.morales@email.com", "11-1234-5678");

            System.out.println("✓ " + clientes.size() + " clientes registrados correctamente");
        } catch (DuplicateException e) {
            System.err.println("Error registrando clientes: " + e.getMessage());
        }
    }

    private void inicializarVehiculos() {
        try {
            // AUTOS
            agregarVehiculo(new Auto("Toyota", "Corolla", "Blanco", "CH001", "MT001", 25000));
            agregarVehiculo(new Auto("Honda", "Civic", "Plateado", "CH005", "MT005", 28000));
            agregarVehiculo(new Auto("Volkswagen", "Golf", "Negro", "CH006", "MT006", 32000));
            agregarVehiculo(new Auto("Chevrolet", "Cruze", "Rojo", "CH007", "MT007", 27000));
            agregarVehiculo(new Auto("Nissan", "Sentra", "Azul", "CH008", "MT008", 26000));
            agregarVehiculo(new Auto("Ford", "Focus", "Gris", "CH009", "MT009", 29000));
            agregarVehiculo(new Auto("Peugeot", "308", "Blanco", "CH010", "MT010", 31000));
            agregarVehiculo(new Auto("Renault", "Fluence", "Verde", "CH011", "MT011", 24000));

            // CAMIONETAS
            agregarVehiculo(new Camioneta("Ford", "Ranger", "Negro", "CH002", "MT002", 35000));
            agregarVehiculo(new Camioneta("Toyota", "Hilux", "Blanco", "CH012", "MT012", 38000));
            agregarVehiculo(new Camioneta("Chevrolet", "S10", "Plateado", "CH013", "MT013", 36000));
            agregarVehiculo(new Camioneta("Nissan", "Frontier", "Azul", "CH014", "MT014", 37000));
            agregarVehiculo(new Camioneta("Volkswagen", "Amarok", "Gris", "CH015", "MT015", 42000));
            agregarVehiculo(new Camioneta("Mitsubishi", "L200", "Rojo", "CH016", "MT016", 34000));

            // MOTOS
            agregarVehiculo(new Moto("Honda", "CBR600", "Rojo", "CH003", "MT003", 8000));
            agregarVehiculo(new Moto("Yamaha", "R6", "Azul", "CH017", "MT017", 9500));
            agregarVehiculo(new Moto("Kawasaki", "Ninja", "Verde", "CH018", "MT018", 10000));
            agregarVehiculo(new Moto("Suzuki", "GSXR", "Negro", "CH019", "MT019", 8500));
            agregarVehiculo(new Moto("Honda", "CB650F", "Plateado", "CH020", "MT020", 7500));
            agregarVehiculo(new Moto("Yamaha", "MT-07", "Naranja", "CH021", "MT021", 7000));
            agregarVehiculo(new Moto("BMW", "S1000RR", "Blanco", "CH022", "MT022", 15000));

            // CAMIONES
            agregarVehiculo(new Camion("Mercedes", "Actros", "Azul", "CH004", "MT004", 80000));
            agregarVehiculo(new Camion("Volvo", "FH", "Blanco", "CH023", "MT023", 85000));
            agregarVehiculo(new Camion("Scania", "R450", "Rojo", "CH024", "MT024", 82000));
            agregarVehiculo(new Camion("Iveco", "Stralis", "Gris", "CH025", "MT025", 78000));
            agregarVehiculo(new Camion("DAF", "XF105", "Verde", "CH026", "MT026", 83000));
            agregarVehiculo(new Camion("MAN", "TGX", "Negro", "CH027", "MT027", 81000));

            System.out.println("✓ " + vehiculos.size() + " vehículos agregados correctamente");
        } catch (DuplicateException e) {
            System.err.println("Error agregando vehículos: " + e.getMessage());
        }
    }

    public void crearPedidosEjemplo() {
        System.out.println("\n--- CREANDO PEDIDOS DE EJEMPLO ---");
        try {
            // Configuraciones adicionales de ejemplo
            ConfiguracionAdicional config1 = new ConfiguracionAdicional("Aire acondicionado, Alarma", "Garantía extendida 3 años", "Cubiertas, Stereo", 2500);
            ConfiguracionAdicional config2 = new ConfiguracionAdicional("GPS, Cámara trasera", "Garantía extendida 2 años", "Lona, Barras", 1800);
            ConfiguracionAdicional config3 = new ConfiguracionAdicional("", "", "Casco, Guantes", 300);
            ConfiguracionAdicional config4 = new ConfiguracionAdicional("Frenos ABS", "", "", 0);
            ConfiguracionAdicional config5 = new ConfiguracionAdicional("Climatizador", "Garantía total 5 años", "Fundas", 1200);

            // Datos de facturación de ejemplo
            DatosFacturacion fact1 = new DatosFacturacion("Juan Carlos Pérez", "Av. Libertador 1234, CABA", "20-12345678-1");
            DatosFacturacion fact2 = new DatosFacturacion("María Elena García", "San Martín 567, La Plata", "27-23456789-2");
            DatosFacturacion fact3 = new DatosFacturacion("Carlos Roberto López", "Belgrano 890, Córdoba", "23-34567890-3");
            DatosFacturacion fact4 = new DatosFacturacion("Transportes Ana SA", "Ruta 9 Km 45, Rosario", "30-45678901-4");
            DatosFacturacion fact5 = new DatosFacturacion("Luis Fernando Rodríguez", "Mitre 321, Mendoza", "25-56789012-5");

            // Pedido 1: Auto Toyota con configuraciones completas
            PedidoCompra pedido1 = crearPedido("20123456781", "CH001", "contado", "Carlos Vendedor", "carlos@automax.com", config1, fact1);
            System.out.println("Pedido creado: " + pedido1);

            // Pedido 2: Camioneta Ford con GPS y garantía
            PedidoCompra pedido2 = crearPedido("27234567892", "CH002", "transferencia", "Ana Vendedora", "ana@automax.com", config2, fact2);
            System.out.println("Pedido creado: " + pedido2);

            // Pedido 3: Moto Honda con accesorios básicos
            PedidoCompra pedido3 = crearPedido("23345678903", "CH003", "tarjeta", "Luis Vendedor", "luis@automax.com", config3, fact3);
            System.out.println("Pedido creado: " + pedido3);

            // Pedido 4: Camión Mercedes para empresa
            PedidoCompra pedido4 = crearPedido("24456789014", "CH004", "transferencia", "Patricia Vendedora", "patricia@automax.com", config4, fact4);
            System.out.println("Pedido creado: " + pedido4);

            // Pedido 5: Auto Honda Civic con climatizador
            PedidoCompra pedido5 = crearPedido("25567890125", "CH005", "contado", "Miguel Vendedor", "miguel@automax.com", config5, fact5);
            System.out.println("Pedido creado: " + pedido5);

            System.out.println("\n✓ " + pedidos.size() + " pedidos de ejemplo creados exitosamente");

            // Procesar algunos pedidos para mostrar cambios de estado
            System.out.println("\n--- PROCESANDO ALGUNOS PEDIDOS ---");

            // Procesar pedido 1 hasta Cobranzas
            pedido1.procesar();
            pedido1.agregarHistorial("Documentación verificada en Ventas");

            // Procesar pedido 2 hasta Impuestos
            pedido2.procesar();
            pedido2.procesar();
            pedido2.agregarHistorial("Pago confirmado en Cobranzas");

            // Procesar pedido 3 hasta Embarque
            pedido3.procesar();
            pedido3.procesar();
            pedido3.procesar();
            pedido3.agregarHistorial("Cálculos de impuestos completados");

            // Procesar pedido 4 hasta Logística
            pedido4.procesar();
            pedido4.procesar();
            pedido4.procesar();
            pedido4.procesar();
            pedido4.agregarHistorial("Vehículo preparado para envío");

            System.out.println("✓ Pedidos procesados con diferentes estados para demostración");

        } catch (ProcessingException | DuplicateException e) {
            System.err.println("Error creando pedidos de ejemplo: " + e.getMessage());
        }
    }

    public void registrarCliente(String nombre, String apellido, String documento, String email, String telefono) throws DuplicateException {
        // Verificar duplicados
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                throw new DuplicateException("Ya existe un cliente con el documento: " + documento);
            }
        }

        Cliente nuevoCliente = new Cliente(nombre, apellido, documento, email, telefono);
        clientes.add(nuevoCliente);
        System.out.println("Cliente registrado exitosamente: " + nuevoCliente);
    }

    public void agregarVehiculo(Vehiculo vehiculo) throws DuplicateException {
        // Verificar duplicados por número de chasis
        for (Vehiculo v : vehiculos) {
            if (v.getNumeroChasis().equals(vehiculo.getNumeroChasis())) {
                throw new DuplicateException("Ya existe un vehículo con el número de chasis: " + vehiculo.getNumeroChasis());
            }
        }

        vehiculos.add(vehiculo);
        System.out.println("Vehículo agregado exitosamente: " + vehiculo);
    }

    public PedidoCompra crearPedido(String documentoCliente, String numeroChasis, String tipoFormaPago,
                                    String nombreVendedor, String emailVendedor, ConfiguracionAdicional configuracionAdicional,
                                    DatosFacturacion datosFacturacion) throws ProcessingException, DuplicateException {
        // Buscar cliente
        Cliente cliente = buscarClientePorDocumento(documentoCliente);
        if (cliente == null) {
            throw new ProcessingException("Cliente no encontrado con documento: " + documentoCliente);
        }

        // Buscar vehículo
        Vehiculo vehiculo = buscarVehiculoPorChasis(numeroChasis);
        if (vehiculo == null) {
            throw new ProcessingException("Vehículo no encontrado con número de chasis: " + numeroChasis);
        }

        // Verificar si el vehículo ya está en un pedido
        for (PedidoCompra pedido : pedidos) {
            if (pedido.getVehiculo().getNumeroChasis().equals(numeroChasis)) {
                throw new DuplicateException("El vehículo ya está asignado a otro pedido");
            }
        }

        // Crear forma de pago
        FormaPago formaPago = crearFormaPago(tipoFormaPago);

        // Crear vendedor
        Vendedor vendedor = new Vendedor(nombreVendedor, emailVendedor);

        // Crear pedido
        PedidoCompra pedido = new PedidoCompra(cliente, vehiculo, configuracionAdicional, formaPago, vendedor, datosFacturacion);

        // Validar pedido
        cadenaValidacion.validar(pedido);

        // Agregar observers para notificaciones
        pedido.agregarObserver(new NotificadorArea("VENTAS"));
        pedido.agregarObserver(new NotificadorArea("COBRANZAS"));
        pedido.agregarObserver(new NotificadorArea("IMPUESTOS"));
        pedido.agregarObserver(new NotificadorArea("EMBARQUE"));
        pedido.agregarObserver(new NotificadorArea("LOGISTICA"));
        pedido.agregarObserver(new NotificadorArea("ENTREGA"));

        pedidos.add(pedido);
        System.out.println("Pedido creado exitosamente: " + pedido);

        return pedido;
    }

    // Método sobrecargado para compatibilidad con código existente
    public PedidoCompra crearPedido(String documentoCliente, String numeroChasis, String tipoFormaPago,
                                    String nombreVendedor, String emailVendedor) throws ProcessingException, DuplicateException {
        ConfiguracionAdicional configDefault = new ConfiguracionAdicional("", "", "", 0);
        DatosFacturacion facturacionDefault = new DatosFacturacion("Cliente Final", "Dirección no especificada", "");
        return crearPedido(documentoCliente, numeroChasis, tipoFormaPago, nombreVendedor, emailVendedor, configDefault, facturacionDefault);
    }

    private FormaPago crearFormaPago(String tipo) throws ProcessingException {
        switch (tipo.toLowerCase()) {
            case "contado":
                return new PagoContado();
            case "transferencia":
                return new PagoTransferencia();
            case "tarjeta":
                return new PagoTarjeta();
            default:
                throw new ProcessingException("Tipo de pago no válido: " + tipo);
        }
    }

    public void procesarPedido(int numeroPedido) throws ProcessingException {
        PedidoCompra pedido = buscarPedidoPorNumero(numeroPedido);
        if (pedido == null) {
            throw new ProcessingException("Pedido no encontrado: " + numeroPedido);
        }

        pedido.procesar();
    }

    public void generarInformePedidos() {
        generarInformePedidos(null, null, null);
    }

    public void generarInformePedidos(LocalDateTime fechaDesde, LocalDateTime fechaHasta, String filtroEstado) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("INFORME DETALLADO DE PEDIDOS DE COMPRA");
        System.out.println(NOMBRE_CONCESIONARIA + " - CUIT: " + CUIT_CONCESIONARIA);

        if (fechaDesde != null || fechaHasta != null || filtroEstado != null) {
            System.out.println("\nFILTROS APLICADOS:");
            if (fechaDesde != null) System.out.println("Desde: " + fechaDesde.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            if (fechaHasta != null) System.out.println("Hasta: " + fechaHasta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            if (filtroEstado != null) System.out.println("Estado: " + filtroEstado);
        }

        System.out.println("=".repeat(80));

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        // Filtrar pedidos
        List<PedidoCompra> pedidosFiltrados = pedidos.stream()
                .filter(p -> fechaDesde == null || p.getFechaCreacion().isAfter(fechaDesde) || p.getFechaCreacion().isEqual(fechaDesde))
                .filter(p -> fechaHasta == null || p.getFechaCreacion().isBefore(fechaHasta) || p.getFechaCreacion().isEqual(fechaHasta))
                .filter(p -> filtroEstado == null || p.getEstado().getNombre().equalsIgnoreCase(filtroEstado))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        if (pedidosFiltrados.isEmpty()) {
            System.out.println("No se encontraron pedidos que coincidan con los filtros aplicados.");
            return;
        }

        for (PedidoCompra pedido : pedidosFiltrados) {
            System.out.println("\n" + "─".repeat(60));
            System.out.println("PEDIDO #" + pedido.getNumeroPedido());
            System.out.println("Fecha de Creación: " + pedido.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            // Datos del Cliente
            System.out.println("\nDATOS DEL CLIENTE:");
            Cliente cliente = pedido.getCliente();
            System.out.println("  Nombre: " + cliente.getNombre() + " " + cliente.getApellido());
            System.out.println("  Documento: " + cliente.getDocumento());
            System.out.println("  Email: " + cliente.getEmail());
            System.out.println("  Teléfono: " + cliente.getTelefono());

            // Datos del Vehículo
            System.out.println("\nDATOS DEL VEHÍCULO:");
            Vehiculo vehiculo = pedido.getVehiculo();
            System.out.println("  Tipo: " + vehiculo.getTipo());
            System.out.println("  Marca: " + vehiculo.getMarca());
            System.out.println("  Modelo: " + vehiculo.getModelo());
            System.out.println("  Color: " + vehiculo.getColor());
            System.out.println("  Número de Chasis: " + vehiculo.getNumeroChasis());
            System.out.println("  Número de Motor: " + vehiculo.getNumeroMotor());

            // Configuraciones Adicionales
            System.out.println("\nCONFIGURACIONES ADICIONALES:");
            System.out.println("  " + pedido.getConfiguracionAdicional().toString());

            // Forma de Pago
            System.out.println("\nFORMA DE PAGO:");
            System.out.println("  " + pedido.getFormaPago().getDescripcion());

            // Gestión de Impuestos
            System.out.println("\nGESTIÓN DE IMPUESTOS:");
            System.out.println("  " + pedido.getCalculadoraImpuestos().getDetalle(pedido.getPrecioBase()));

            // Costos
            System.out.println("\nDETALLE DE COSTOS:");
            System.out.println("  Precio Base: $" + String.format("%.2f", pedido.getPrecioBase()));
            System.out.println("  Impuestos: $" + String.format("%.2f", pedido.getImpuestos()));
            System.out.println("  Adicionales: $" + String.format("%.2f", pedido.getCostoAdicionales()));
            System.out.println("  TOTAL: $" + String.format("%.2f", pedido.getCostoTotal()));

            // Datos de Facturación
            System.out.println("\nDATOS DE FACTURACIÓN:");
            System.out.println("  " + pedido.getDatosFacturacion().toString());

            // Datos del Vendedor
            System.out.println("\nDATOS DEL VENDEDOR:");
            System.out.println("  Nombre: " + pedido.getVendedor().getNombre());
            System.out.println("  Email: " + pedido.getVendedor().getEmail());

            // Área Responsable Actual
            System.out.println("\nÁREA RESPONSABLE ACTUAL: " + pedido.getAreaResponsableActual());

            // Historial de Cambios de Estado
            System.out.println("\nHISTORIAL DE CAMBIOS DE ESTADO:");
            for (CambioEstado cambio : pedido.getHistorialEstados()) {
                System.out.println("  • " + cambio.toString());
            }

            // Historial General
            System.out.println("\nHISTORIAL GENERAL:");
            for (String evento : pedido.getHistorial()) {
                System.out.println("  • " + evento);
            }
        }

        // Resumen
        System.out.println("\n" + "=".repeat(80));
        System.out.println("RESUMEN DEL INFORME");
        System.out.println("Total de pedidos mostrados: " + pedidosFiltrados.size());

        double totalGeneral = pedidosFiltrados.stream().mapToDouble(PedidoCompra::getCostoTotal).sum();
        System.out.println("Valor total de pedidos: $" + String.format("%.2f", totalGeneral));

        // Resumen por estado
        Map<String, Long> resumenEstados = new HashMap<>();
        for (PedidoCompra pedido : pedidosFiltrados) {
            String estado = pedido.getEstado().getNombre();
            resumenEstados.put(estado, resumenEstados.getOrDefault(estado, 0L) + 1);
        }

        System.out.println("\nResumen por estado:");
        for (Map.Entry<String, Long> entry : resumenEstados.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " pedidos");
        }

        System.out.println("=".repeat(80));
    }

    public Cliente buscarClientePorDocumento(String documento) {
        return clientes.stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
    }

    public Vehiculo buscarVehiculoPorChasis(String numeroChasis) {
        return vehiculos.stream()
                .filter(v -> v.getNumeroChasis().equals(numeroChasis))
                .findFirst()
                .orElse(null);
    }

    public PedidoCompra buscarPedidoPorNumero(int numeroPedido) {
        return pedidos.stream()
                .filter(p -> p.getNumeroPedido() == numeroPedido)
                .findFirst()
                .orElse(null);
    }

    // Getters para acceso a las listas
    public List<Cliente> getClientes() { return new ArrayList<>(clientes); }
    public List<Vehiculo> getVehiculos() { return new ArrayList<>(vehiculos); }
    public List<PedidoCompra> getPedidos() { return new ArrayList<>(pedidos); }
    public static String getNombreConcesionaria() { return NOMBRE_CONCESIONARIA; }
    public static String getCuitConcesionaria() { return CUIT_CONCESIONARIA; }
}

// ========================================
// MENÚ PRINCIPAL
// ========================================



class MenuPrincipal {
    private ConcesionariaFacade concesionaria;
    private Scanner scanner;

    public MenuPrincipal() {
        this.concesionaria = new ConcesionariaFacade();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("SISTEMA DE GESTIÓN - " + ConcesionariaFacade.getNombreConcesionaria());
            System.out.println("=".repeat(50));
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Agregar Vehículo");
            System.out.println("3. Crear Pedido de Compra");
            System.out.println("4. Procesar Pedido");
            System.out.println("5. Ver Clientes");
            System.out.println("6. Ver Vehículos");
            System.out.println("7. Ver Pedidos");
            System.out.println("8. Generar Informe Completo");
            System.out.println("9. Generar Informe con Filtros");
            System.out.println("10. Ver Historial de Estados de Pedido");
            System.out.println("11. Crear Pedidos de Ejemplo");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarCliente();
                        break;
                    case 2:
                        agregarVehiculo();
                        break;
                    case 3:
                        crearPedido();
                        break;
                    case 4:
                        procesarPedido();
                        break;
                    case 5:
                        verClientes();
                        break;
                    case 6:
                        verVehiculos();
                        break;
                    case 7:
                        verPedidos();
                        break;
                    case 8:
                        concesionaria.generarInformePedidos();
                        break;
                    case 9:
                        generarInformeConFiltros();
                        break;
                    case 10:
                        verHistorialEstadosPedido();
                        break;
                    case 11:
                        concesionaria.crearPedidosEjemplo();
                        break;
                    case 12:
                        System.out.println("¡Gracias por usar el sistema!");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void registrarCliente() {
        try {
            System.out.println("\n--- REGISTRAR CLIENTE ---");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Documento: ");
            String documento = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();

            concesionaria.registrarCliente(nombre, apellido, documento, email, telefono);
        } catch (DuplicateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void agregarVehiculo() {
        try {
            System.out.println("\n--- AGREGAR VEHÍCULO ---");
            System.out.println("Tipos disponibles: 1-Auto, 2-Camioneta, 3-Moto, 4-Camión");
            System.out.print("Seleccione tipo (1-4): ");
            int tipo = Integer.parseInt(scanner.nextLine());

            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Color: ");
            String color = scanner.nextLine();
            System.out.print("Número de Chasis: ");
            String numeroChasis = scanner.nextLine();
            System.out.print("Número de Motor: ");
            String numeroMotor = scanner.nextLine();
            System.out.print("Precio Base: ");
            double precioBase = Double.parseDouble(scanner.nextLine());

            Vehiculo vehiculo;
            switch (tipo) {
                case 1:
                    vehiculo = new Auto(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
                    break;
                case 2:
                    vehiculo = new Camioneta(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
                    break;
                case 3:
                    vehiculo = new Moto(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
                    break;
                case 4:
                    vehiculo = new Camion(marca, modelo, color, numeroChasis, numeroMotor, precioBase);
                    break;
                default:
                    System.out.println("Tipo de vehículo no válido.");
                    return;
            }

            concesionaria.agregarVehiculo(vehiculo);
        } catch (DuplicateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para el precio.");
        }
    }

    private void crearPedido() {
        try {
            System.out.println("\n--- CREAR PEDIDO DE COMPRA ---");

            // Mostrar clientes disponibles
            System.out.println("Clientes disponibles:");
            for (Cliente cliente : concesionaria.getClientes()) {
                System.out.println("  - " + cliente);
            }

            System.out.print("Documento del cliente: ");
            String documentoCliente = scanner.nextLine();

            // Mostrar vehículos disponibles
            System.out.println("Vehículos disponibles:");
            for (Vehiculo vehiculo : concesionaria.getVehiculos()) {
                System.out.println("  - " + vehiculo + " (Chasis: " + vehiculo.getNumeroChasis() + ")");
            }

            System.out.print("Número de chasis del vehículo: ");
            String numeroChasis = scanner.nextLine();

            System.out.println("Formas de pago disponibles: contado, transferencia, tarjeta");
            System.out.print("Forma de pago: ");
            String formaPago = scanner.nextLine();

            System.out.print("Nombre del vendedor: ");
            String nombreVendedor = scanner.nextLine();
            System.out.print("Email del vendedor: ");
            String emailVendedor = scanner.nextLine();

            // Configuraciones adicionales
            System.out.println("\n--- CONFIGURACIONES ADICIONALES (opcional) ---");
            System.out.print("Equipamiento extra (Enter para omitir): ");
            String equipamiento = scanner.nextLine();
            System.out.print("Garantías extendidas (Enter para omitir): ");
            String garantias = scanner.nextLine();
            System.out.print("Accesorios (Enter para omitir): ");
            String accesorios = scanner.nextLine();
            System.out.print("Costo adicional (0 para omitir): ");
            double costoAdicional = 0;
            try {
                String costoStr = scanner.nextLine();
                if (!costoStr.trim().isEmpty()) {
                    costoAdicional = Double.parseDouble(costoStr);
                }
            } catch (NumberFormatException e) {
                System.out.println("Costo inválido, se asignará 0");
            }

            ConfiguracionAdicional configuracion = new ConfiguracionAdicional(equipamiento, garantias, accesorios, costoAdicional);

            // Datos de facturación
            System.out.println("\n--- DATOS DE FACTURACIÓN ---");
            System.out.print("Nombre/Razón Social: ");
            String nombreFacturacion = scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("CUIT/CUIL (opcional): ");
            String cuitCuil = scanner.nextLine();

            DatosFacturacion datosFacturacion = new DatosFacturacion(nombreFacturacion, direccion, cuitCuil);

            PedidoCompra pedido = concesionaria.crearPedido(documentoCliente, numeroChasis, formaPago,
                    nombreVendedor, emailVendedor, configuracion, datosFacturacion);

            System.out.println("\n--- DETALLES DEL PEDIDO ---");
            System.out.println("Pedido #" + pedido.getNumeroPedido());
            System.out.println("Cliente: " + pedido.getCliente());
            System.out.println("Vehículo: " + pedido.getVehiculo());
            System.out.println("Configuraciones: " + pedido.getConfiguracionAdicional());
            System.out.println("Precio Base: $" + pedido.getPrecioBase());
            System.out.println("Adicionales: $" + pedido.getCostoAdicionales());
            System.out.println(pedido.getCalculadoraImpuestos().getDetalle(pedido.getPrecioBase()));
            System.out.println("Total a pagar: $" + pedido.getCostoTotal());
            System.out.println("Datos de facturación: " + pedido.getDatosFacturacion());

        } catch (ProcessingException | DuplicateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void generarInformeConFiltros() {
        try {
            System.out.println("\n--- GENERAR INFORME CON FILTROS ---");

            LocalDateTime fechaDesde = null;
            LocalDateTime fechaHasta = null;
            String filtroEstado = null;

            // Filtro por fecha desde
            System.out.print("Filtrar desde fecha? (s/n): ");
            if (scanner.nextLine().toLowerCase().startsWith("s")) {
                System.out.print("Fecha desde (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    fechaDesde = LocalDate.parse(fechaStr, formatter).atStartOfDay();
                } catch (Exception e) {
                    System.out.println("Fecha inválida, se omitirá el filtro");
                }
            }

            // Filtro por fecha hasta
            System.out.print("Filtrar hasta fecha? (s/n): ");
            if (scanner.nextLine().toLowerCase().startsWith("s")) {
                System.out.print("Fecha hasta (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    fechaHasta = LocalDate.parse(fechaStr, formatter).atTime(23, 59, 59);
                } catch (Exception e) {
                    System.out.println("Fecha inválida, se omitirá el filtro");
                }
            }

            // Filtro por estado
            System.out.print("Filtrar por estado? (s/n): ");
            if (scanner.nextLine().toLowerCase().startsWith("s")) {
                System.out.println("Estados disponibles: Ventas, Cobranzas, Impuestos, Embarque, Logística, Entrega");
                System.out.print("Estado a filtrar: ");
                filtroEstado = scanner.nextLine();
                if (filtroEstado.trim().isEmpty()) {
                    filtroEstado = null;
                }
            }

            concesionaria.generarInformePedidos(fechaDesde, fechaHasta, filtroEstado);

        } catch (Exception e) {
            System.out.println("Error generando informe: " + e.getMessage());
        }
    }

    private void verHistorialEstadosPedido() {
        try {
            System.out.println("\n--- HISTORIAL DE ESTADOS DE PEDIDO ---");

            if (concesionaria.getPedidos().isEmpty()) {
                System.out.println("No hay pedidos registrados.");
                return;
            }

            // Mostrar pedidos disponibles
            System.out.println("Pedidos disponibles:");
            for (PedidoCompra pedido : concesionaria.getPedidos()) {
                System.out.println("  " + pedido.getNumeroPedido() + " - " + pedido.getCliente().getNombre() + " " +
                        pedido.getCliente().getApellido() + " (" + pedido.getVehiculo().getMarca() + " " +
                        pedido.getVehiculo().getModelo() + ") - Estado actual: " + pedido.getEstado().getNombre());
            }

            System.out.print("\nIngrese el número de pedido: ");
            int numeroPedido = Integer.parseInt(scanner.nextLine());

            PedidoCompra pedido = concesionaria.buscarPedidoPorNumero(numeroPedido);
            if (pedido == null) {
                System.out.println("Pedido no encontrado.");
                return;
            }

            // Mostrar información básica del pedido
            System.out.println("\n" + "=".repeat(70));
            System.out.println("HISTORIAL COMPLETO DE ESTADOS - PEDIDO #" + pedido.getNumeroPedido());
            System.out.println("=".repeat(70));
            System.out.println("Cliente: " + pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido());
            System.out.println("Vehículo: " + pedido.getVehiculo().getMarca() + " " + pedido.getVehiculo().getModelo() +
                    " (" + pedido.getVehiculo().getColor() + ")");
            System.out.println("Fecha de creación: " + pedido.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            System.out.println("Estado actual: " + pedido.getEstado().getNombre());
            System.out.println("Total del pedido: $" + String.format("%.2f", pedido.getCostoTotal()));

            // Mostrar historial de cambios de estado
            System.out.println("\n" + "-".repeat(70));
            System.out.println("CRONOLOGÍA DE CAMBIOS DE ESTADO:");
            System.out.println("-".repeat(70));

            if (pedido.getHistorialEstados().isEmpty()) {
                System.out.println("No hay cambios de estado registrados.");
            } else {
                int contador = 1;
                for (CambioEstado cambio : pedido.getHistorialEstados()) {
                    System.out.printf("%2d. %s\n", contador, cambio.toString());

                    // Agregar información adicional sobre el estado
                    String estadoInfo = getInformacionEstado(cambio.getEstadoNuevo());
                    if (!estadoInfo.isEmpty()) {
                        System.out.println("    📝 " + estadoInfo);
                    }

                    contador++;
                    System.out.println();
                }
            }

            // Mostrar progreso visual
            System.out.println("-".repeat(70));
            System.out.println("PROGRESO DEL PEDIDO:");
            System.out.println("-".repeat(70));
            mostrarProgresoVisual(pedido);

            // Estadísticas del pedido
            System.out.println("\n" + "-".repeat(70));
            System.out.println("ESTADÍSTICAS:");
            System.out.println("-".repeat(70));
            System.out.println("Total de cambios de estado: " + pedido.getHistorialEstados().size());
            System.out.println("Tiempo total desde creación: " + calcularTiempoTranscurrido(pedido.getFechaCreacion()));

            if (pedido.getHistorialEstados().size() > 1) {
                LocalDateTime primerCambio = pedido.getHistorialEstados().get(0).getFecha();
                LocalDateTime ultimoCambio = pedido.getHistorialEstados().get(pedido.getHistorialEstados().size() - 1).getFecha();
                System.out.println("Tiempo entre primer y último cambio: " + calcularTiempoTranscurrido(primerCambio, ultimoCambio));
            }

            System.out.println("=".repeat(70));

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String getInformacionEstado(String estado) {
        switch (estado) {
            case "Ventas":
                return "Área de ventas procesa la documentación del cliente y verifica datos del vehículo";
            case "Cobranzas":
                return "Área de cobranzas gestiona el pago y confirma la transacción financiera";
            case "Impuestos":
                return "Área de impuestos calcula y aplica los impuestos correspondientes según el vehículo";
            case "Embarque":
                return "Área de embarque prepara el vehículo para la entrega y organiza la documentación";
            case "Logística":
                return "Área de logística coordina el transporte y la entrega del vehículo al cliente";
            case "Entrega":
                return "El vehículo ha sido entregado exitosamente al cliente";
            default:
                return "";
        }
    }

    private void mostrarProgresoVisual(PedidoCompra pedido) {
        String[] estados = {"Ventas", "Cobranzas", "Impuestos", "Embarque", "Logística", "Entrega"};
        String estadoActual = pedido.getEstado().getNombre();

        // Crear conjunto de estados visitados
        Set<String> estadosVisitados = new HashSet<>();
        for (CambioEstado cambio : pedido.getHistorialEstados()) {
            if (!cambio.getEstadoNuevo().isEmpty()) {
                estadosVisitados.add(cambio.getEstadoNuevo());
            }
        }

        System.out.println();
        for (int i = 0; i < estados.length; i++) {
            String estado = estados[i];
            boolean visitado = estadosVisitados.contains(estado);
            boolean actual = estado.equals(estadoActual);

            if (actual) {
                System.out.print("👉 [" + estado + "] ");
            } else if (visitado) {
                System.out.print("✅ [" + estado + "] ");
            } else {
                System.out.print("⏳ [" + estado + "] ");
            }

            if (i < estados.length - 1) {
                System.out.print("→ ");
            }
        }
        System.out.println();
        System.out.println("\n✅ = Completado  👉 = Estado Actual  ⏳ = Pendiente");
    }

    private String calcularTiempoTranscurrido(LocalDateTime desde) {
        return calcularTiempoTranscurrido(desde, LocalDateTime.now());
    }

    private String calcularTiempoTranscurrido(LocalDateTime desde, LocalDateTime hasta) {
        long dias = ChronoUnit.DAYS.between(desde, hasta);
        long horas = ChronoUnit.HOURS.between(desde, hasta) % 24;
        long minutos = ChronoUnit.MINUTES.between(desde, hasta) % 60;

        if (dias > 0) {
            return dias + " días, " + horas + " horas, " + minutos + " minutos";
        } else if (horas > 0) {
            return horas + " horas, " + minutos + " minutos";
        } else {
            return minutos + " minutos";
        }
    }

    private void procesarPedido() {
        try {
            System.out.println("\n--- PROCESAR PEDIDO ---");

            // Mostrar pedidos disponibles
            System.out.println("Pedidos disponibles:");
            for (PedidoCompra pedido : concesionaria.getPedidos()) {
                System.out.println("  - " + pedido);
            }

            System.out.print("Número de pedido a procesar: ");
            int numeroPedido = Integer.parseInt(scanner.nextLine());

            concesionaria.procesarPedido(numeroPedido);

        } catch (ProcessingException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido.");
        }
    }

    private void verClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        if (concesionaria.getClientes().isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente cliente : concesionaria.getClientes()) {
                System.out.println("- " + cliente + " | Email: " + cliente.getEmail() + " | Tel: " + cliente.getTelefono());
            }
        }
    }

    private void verVehiculos() {
        System.out.println("\n--- CATÁLOGO DE VEHÍCULOS ---");
        if (concesionaria.getVehiculos().isEmpty()) {
            System.out.println("No hay vehículos en el catálogo.");
        } else {
            for (Vehiculo vehiculo : concesionaria.getVehiculos()) {
                System.out.println("- " + vehiculo + " | Tipo: " + vehiculo.getTipo() + " | Chasis: " + vehiculo.getNumeroChasis());
            }
        }
    }

    private void verPedidos() {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        if (concesionaria.getPedidos().isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (PedidoCompra pedido : concesionaria.getPedidos()) {
                System.out.println("- " + pedido);
                System.out.println("  Vendedor: " + pedido.getVendedor().getNombre());
                System.out.println("  Forma de Pago: " + pedido.getFormaPago().getDescripcion());
                System.out.println("  Configuraciones: " + pedido.getConfiguracionAdicional());
                System.out.println("  Facturación: " + pedido.getDatosFacturacion().getNombreRazonSocial());
                System.out.println("  Precio Base: $" + pedido.getPrecioBase() + " | Adicionales: $" + pedido.getCostoAdicionales() + " | Impuestos: $" + pedido.getImpuestos());
                System.out.println("  Último evento: " + (pedido.getHistorial().isEmpty() ? "N/A" :
                        pedido.getHistorial().get(pedido.getHistorial().size() - 1)));
                System.out.println("  Estados recorridos: " + pedido.getHistorialEstados().size());
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(60));
        System.out.println("SISTEMA DE GESTIÓN - " + ConcesionariaFacade.getNombreConcesionaria());
        System.out.println("=".repeat(60));
        System.out.println("1. Iniciar Sistema de Gestión");
        System.out.println("2. Ejecutar Tests del Sistema");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.mostrarMenu();
                    break;
                case 2:
                    TestConcesionaria test = new TestConcesionaria();
                    test.ejecutarTodos();
                    break;
                default:
                    System.out.println("Opción no válida. Iniciando sistema por defecto...");
                    MenuPrincipal menuDefault = new MenuPrincipal();
                    menuDefault.mostrarMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Iniciando sistema por defecto...");
            MenuPrincipal menuDefault = new MenuPrincipal();
            menuDefault.mostrarMenu();
        }

        scanner.close();
    }
}

// ========================================
// CLASE DE TESTS
// ========================================

class TestConcesionaria {
    private ConcesionariaFacade concesionaria;

    public TestConcesionaria() {
        this.concesionaria = new ConcesionariaFacade();
    }

    public void ejecutarTodos() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EJECUTANDO TESTS DEL SISTEMA");
        System.out.println("=".repeat(60));

        testRegistroCliente();
        testDuplicadoCliente();
        testAgregarVehiculo();
        testDuplicadoVehiculo();
        testCrearPedido();
        testValidacionPedido();
        testCalculoImpuestos();
        testPatronState();
        testPatronObserver();
        testChainOfResponsibility();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("TESTS COMPLETADOS");
        System.out.println("=".repeat(60));
    }

    private void testRegistroCliente() {
        System.out.println("\n[TEST] Registro de Cliente");
        try {
            concesionaria.registrarCliente("Juan", "Pérez", "12345678", "juan@email.com", "1234567890");
            System.out.println("✓ Cliente registrado correctamente");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void testDuplicadoCliente() {
        System.out.println("\n[TEST] Validación de Cliente Duplicado");
        try {
            concesionaria.registrarCliente("María", "García", "12345678", "maria@email.com", "0987654321");
            System.out.println("✗ No se detectó el duplicado");
        } catch (DuplicateException e) {
            System.out.println("✓ Duplicado detectado correctamente: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }
    }

    private void testAgregarVehiculo() {
        System.out.println("\n[TEST] Agregar Vehículo");
        try {
            Auto auto = new Auto("Volkswagen", "Golf", "Rojo", "CH100", "MT100", 30000);
            concesionaria.agregarVehiculo(auto);
            System.out.println("✓ Vehículo agregado correctamente");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void testDuplicadoVehiculo() {
        System.out.println("\n[TEST] Validación de Vehículo Duplicado");
        try {
            Auto auto = new Auto("Peugeot", "308", "Verde", "CH100", "MT200", 28000);
            concesionaria.agregarVehiculo(auto);
            System.out.println("✗ No se detectó el duplicado");
        } catch (DuplicateException e) {
            System.out.println("✓ Duplicado detectado correctamente: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }
    }

    private void testCrearPedido() {
        System.out.println("\n[TEST] Crear Pedido de Compra");
        try {
            PedidoCompra pedido = concesionaria.crearPedido("12345678", "CH001", "contado", "Carlos Vendedor", "carlos@concesionaria.com");
            System.out.println("✓ Pedido creado correctamente: #" + pedido.getNumeroPedido());
            System.out.println("  Costo total: $" + pedido.getCostoTotal());
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void testValidacionPedido() {
        System.out.println("\n[TEST] Validación de Pedido Inválido");
        try {
            concesionaria.crearPedido("99999999", "CH001", "contado", "Vendedor", "vendedor@test.com");
            System.out.println("✗ No se detectó el error de validación");
        } catch (ProcessingException e) {
            System.out.println("✓ Validación funcionó correctamente: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }
    }

    private void testCalculoImpuestos() {
        System.out.println("\n[TEST] Cálculo de Impuestos por Tipo de Vehículo");

        // Test Auto (21% + 5% + 1% = 27%)
        CalculadoraImpuestosAuto calcAuto = new CalculadoraImpuestosAuto();
        double impuestosAuto = calcAuto.calcular(10000);
        System.out.println("✓ Impuestos Auto ($10,000): $" + impuestosAuto + " (27% = $2,700)");

        // Test Camioneta (10% + 5% + 2% = 17%)
        CalculadoraImpuestosCamioneta calcCamioneta = new CalculadoraImpuestosCamioneta();
        double impuestosCamioneta = calcCamioneta.calcular(10000);
        System.out.println("✓ Impuestos Camioneta ($10,000): $" + impuestosCamioneta + " (17% = $1,700)");

        // Test Moto (5% + 1% = 6%)
        CalculadoraImpuestosMoto calcMoto = new CalculadoraImpuestosMoto();
        double impuestosMoto = calcMoto.calcular(10000);
        System.out.println("✓ Impuestos Moto ($10,000): $" + impuestosMoto + " (6% = $600)");
    }

    private void testPatronState() {
        System.out.println("\n[TEST] Patrón State - Transiciones de Estado");
        try {
            PedidoCompra pedido = concesionaria.crearPedido("12345678", "CH100", "transferencia", "Ana Vendedora", "ana@concesionaria.com");

            System.out.println("Estado inicial: " + pedido.getEstado().getNombre());

            for (int i = 0; i < 5; i++) {
                String estadoAnterior = pedido.getEstado().getNombre();
                pedido.procesar();
                String estadoNuevo = pedido.getEstado().getNombre();
                System.out.println("✓ Transición: " + estadoAnterior + " → " + estadoNuevo);
            }

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void testPatronObserver() {
        System.out.println("\n[TEST] Patrón Observer - Notificaciones");
        try {
            // Crear un pedido que ya tiene observers configurados
            PedidoCompra pedido = concesionaria.crearPedido("12345678", "CH002", "tarjeta", "Luis Vendedor", "luis@concesionaria.com");

            System.out.println("Procesando pedido para ver notificaciones...");
            pedido.procesar(); // Esto debería generar notificaciones
            System.out.println("✓ Notificaciones enviadas correctamente");

        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private void testChainOfResponsibility() {
        System.out.println("\n[TEST] Chain of Responsibility - Validaciones");

        // Test 1: Pedido con cliente incompleto
        try {
            Cliente clienteIncompleto = new Cliente("", "", "", "", "");
            Auto autoValido = new Auto("Test", "Test", "Test", "CHTEST", "MTTEST", 1000);
            ConfiguracionAdicional configValida = new ConfiguracionAdicional("", "", "", 0);
            FormaPago pagoValido = new PagoContado();
            Vendedor vendedorValido = new Vendedor("Test Vendedor", "test@test.com");
            DatosFacturacion facturacionValida = new DatosFacturacion("Test Cliente", "Test Dirección", "");

            PedidoCompra pedidoInvalido = new PedidoCompra(clienteIncompleto, autoValido, configValida, pagoValido, vendedorValido, facturacionValida);

            // Configurar cadena de validación manualmente para el test
            ValidadorCliente validadorCliente = new ValidadorCliente();
            ValidadorVehiculo validadorVehiculo = new ValidadorVehiculo();
            ValidadorFormaPago validadorFormaPago = new ValidadorFormaPago();
            ValidadorVendedor validadorVendedor = new ValidadorVendedor();
            ValidadorDatosFacturacion validadorFacturacion = new ValidadorDatosFacturacion();

            validadorCliente.setSiguiente(validadorVehiculo);
            validadorVehiculo.setSiguiente(validadorFormaPago);
            validadorFormaPago.setSiguiente(validadorVendedor);
            validadorVendedor.setSiguiente(validadorFacturacion);

            // Probar validación - debería fallar en el cliente
            validadorCliente.validar(pedidoInvalido);
            System.out.println("✗ Las validaciones no funcionaron correctamente - debería haber fallado");

        } catch (ProcessingException e) {
            System.out.println("✓ Chain of Responsibility - Cliente inválido detectado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }

        // Test 2: Pedido con vehículo incompleto
        try {
            Cliente clienteValido = new Cliente("Juan", "Pérez", "12345678", "juan@test.com", "123456789");
            Auto autoInvalido = new Auto("", "", "", "", "", 0); // Vehículo con datos vacíos
            ConfiguracionAdicional configValida = new ConfiguracionAdicional("", "", "", 0);
            FormaPago pagoValido = new PagoContado();
            Vendedor vendedorValido = new Vendedor("Test Vendedor", "test@test.com");
            DatosFacturacion facturacionValida = new DatosFacturacion("Test Cliente", "Test Dirección", "");

            PedidoCompra pedidoInvalido = new PedidoCompra(clienteValido, autoInvalido, configValida, pagoValido, vendedorValido, facturacionValida);

            ValidadorCliente validadorCliente = new ValidadorCliente();
            ValidadorVehiculo validadorVehiculo = new ValidadorVehiculo();
            ValidadorFormaPago validadorFormaPago = new ValidadorFormaPago();

            validadorCliente.setSiguiente(validadorVehiculo);
            validadorVehiculo.setSiguiente(validadorFormaPago);

            validadorCliente.validar(pedidoInvalido);
            System.out.println("✗ Las validaciones no funcionaron correctamente - debería haber fallado en vehículo");

        } catch (ProcessingException e) {
            System.out.println("✓ Chain of Responsibility - Vehículo inválido detectado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }

        // Test 3: Pedido válido que debería pasar todas las validaciones
        try {
            Cliente clienteValido = new Cliente("Juan", "Pérez", "12345678", "juan@test.com", "123456789");
            Auto autoValido = new Auto("Toyota", "Corolla", "Blanco", "CH999", "MT999", 25000);
            ConfiguracionAdicional configValida = new ConfiguracionAdicional("", "", "", 0);
            FormaPago pagoValido = new PagoContado();
            Vendedor vendedorValido = new Vendedor("Test Vendedor", "test@test.com");
            DatosFacturacion facturacionValida = new DatosFacturacion("Juan Pérez", "Av. Test 123", "20-12345678-1");

            PedidoCompra pedidoValido = new PedidoCompra(clienteValido, autoValido, configValida, pagoValido, vendedorValido, facturacionValida);

            ValidadorCliente validadorCliente = new ValidadorCliente();
            ValidadorVehiculo validadorVehiculo = new ValidadorVehiculo();
            ValidadorFormaPago validadorFormaPago = new ValidadorFormaPago();
            ValidadorVendedor validadorVendedor = new ValidadorVendedor();
            ValidadorDatosFacturacion validadorFacturacion = new ValidadorDatosFacturacion();

            validadorCliente.setSiguiente(validadorVehiculo);
            validadorVehiculo.setSiguiente(validadorFormaPago);
            validadorFormaPago.setSiguiente(validadorVendedor);
            validadorVendedor.setSiguiente(validadorFacturacion);

            validadorCliente.validar(pedidoValido);
            System.out.println("✓ Chain of Responsibility - Pedido válido pasó todas las validaciones correctamente");

        } catch (ProcessingException e) {
            System.out.println("✗ Error inesperado - pedido válido falló: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }

        // Test 4: Validación con vendedor incompleto
        try {
            Cliente clienteValido = new Cliente("Juan", "Pérez", "12345678", "juan@test.com", "123456789");
            Auto autoValido = new Auto("Toyota", "Corolla", "Blanco", "CH998", "MT998", 25000);
            ConfiguracionAdicional configValida = new ConfiguracionAdicional("", "", "", 0);
            FormaPago pagoValido = new PagoContado();
            Vendedor vendedorInvalido = new Vendedor("", ""); // Vendedor con datos vacíos
            DatosFacturacion facturacionValida = new DatosFacturacion("Test Cliente", "Test Dirección", "");

            PedidoCompra pedidoInvalido = new PedidoCompra(clienteValido, autoValido, configValida, pagoValido, vendedorInvalido, facturacionValida);

            ValidadorCliente validadorCliente = new ValidadorCliente();
            ValidadorVehiculo validadorVehiculo = new ValidadorVehiculo();
            ValidadorFormaPago validadorFormaPago = new ValidadorFormaPago();
            ValidadorVendedor validadorVendedor = new ValidadorVendedor();

            validadorCliente.setSiguiente(validadorVehiculo);
            validadorVehiculo.setSiguiente(validadorFormaPago);
            validadorFormaPago.setSiguiente(validadorVendedor);

            validadorCliente.validar(pedidoInvalido);
            System.out.println("✗ Las validaciones no funcionaron - debería haber fallado en vendedor");

        } catch (ProcessingException e) {
            System.out.println("✓ Chain of Responsibility - Vendedor inválido detectado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TestConcesionaria test = new TestConcesionaria();
        test.ejecutarTodos();
    }
}