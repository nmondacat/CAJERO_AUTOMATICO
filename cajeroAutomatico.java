import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class cajeroAutomatico{

    //Parámetros principales que usaremos más adelante, en donde, se definen como final, para que el valor se mantenga en esa cantidad durante todo el proceso.
    private static final double saldoInicial = 10000.00;
    private static final double maxRetiro = 6000.00;
    private static final double multDeposito = 50.00;
    private static final double pagoTarjeta = 200.10;
    private static final double transferencia = 666.22;
    private double saldo;
    private int intentosFallidos;

    //Valores de inicialización de cajeroAutomatico.
    public cajeroAutomatico() {
        saldo = saldoInicial;
        intentosFallidos = 0;
    }

    //Aquí inicia el programa, donde se llamamos a las opciones (métodos) que están asignados en el switch.
    public void iniciar() {
        Scanner scannerCajero = new Scanner(System.in);
        System.out.println("Bienvenido al sistema virtual de Banco Generations!");

        while (true) {
            menuCajero();
            System.out.print("Para continuar, seleccione una de las siguientes opciones:");
            int opcionCajero = scannerCajero.nextInt();
            scannerCajero.nextLine();

            switch (opcionCajero) {
                case 1 -> retirarDinero(scannerCajero);
                case 2 -> hacerDeposito(scannerCajero);
                case 3 -> mostrarEstadoCuenta();
                case 4 -> {
                    System.out.println("Nuestros ejecutivos no se encuentran disponible, por favor, intente más tarde.");
                    return;
                }
                case 5 -> mostrarUltimoMovimiento("");
                case 6 -> System.out.println("Gracias por preferir Banco Generation, vuelva pronto!");
                default -> {
                    System.out.println("Opción desconocida, seleccionar una opción válida.");
                    intentosFallidos++;
                }
            }

            if (intentosFallidos >= 3) {
                System.out.println("Ha excedido el número de intentos fallidos. Sesión terminada.");
                return;
            }
        }
    }

    //Este es el menú del cajero.
    private void menuCajero(){
        System.out.println("MENU BANCO GENERATION:");
        System.out.println("1. Retirar dinero");
        System.out.println("2. Hacer depósitos");
        System.out.println("3. Estado de cuenta");
        System.out.println("4. Asistencia Virtual");
        System.out.println("5. Último movimiento");
        System.out.println("6. Salir del cajero");

    }

    //Opción retirar dinero.
    private void retirarDinero(Scanner scannerCajero) {
        System.out.println("Ha seleccionado la opción: RETIRAR DINERO.");
        System.out.println("Cantidad disponible a retirar: $" + saldo);
        System.out.println("Por favor, ingrese la cantidad a retirar: ");
        double cantidad = scannerCajero.nextDouble();
        scannerCajero.nextLine();

        //Condicionales
        if (cantidad % multDeposito == 0 && cantidad <= saldo && cantidad <= maxRetiro) {
            saldo -= cantidad;
            System.out.println("Retiro exitoso. Retiraste: $" + cantidad);
            mostrarUltimoMovimiento("Retiro de $" + cantidad);
        } else if (cantidad % multDeposito != 0) {
            System.out.println("Solo se pueden retirar múltiplos de 50.");
        } else if (cantidad > saldo) {
            System.out.println("No tienes suficiente saldo en tu cuenta.");
        } else if (cantidad > maxRetiro) {
            System.out.println("No puedes retirar más de $" + maxRetiro);
        } else {
            System.out.println("Opción inválida. Por favor, ingrese una cantidad válida.");
            intentosFallidos++;
        }
    }
    //Método donde seleccionamos la sub opción dentro de la opción de hacer depósitos.
    private void hacerDeposito(Scanner scannerCajero){
        System.out.println("Ha seleccionado la opción: HACER DEPOSITO.");
        System.out.println("¿Dónde desea depositar?");
        System.out.println("1. Cuenta de cheques");
        System.out.println("2. Tarjeta de crédito");
        System.out.println("3. Cuenta de terceros");
        System.out.print("Ingrese una opción: ");
        int opcionCajero = scannerCajero.nextInt();
        scannerCajero.nextLine();

        //Switch de las opciones del cajero.
        switch (opcionCajero) {
            case 1 -> depositarEnCuentaCheques();
            case 2 -> depositarEnTarjetaCredito();
            case 3 -> transferirACuentaTerceros();
            default -> {
                System.out.println("Opción desconocida. Por favor, seleccione una opción válida.");
                intentosFallidos++;
            }
        }
    }

    //Método para depositar en la opción cuenta de cheques.
    private void depositarEnCuentaCheques(){
        System.out.println("Has seleccionado la opción: DEPOSITO EN TARJETA DE CRÉDITO");
        System.out.println("Hacer pago de $" + pagoTarjeta);
        if(saldo >= pagoTarjeta){
            saldo -= pagoTarjeta;
            System.out.println("Pago exitoso. Se realizó un pago de $" + pagoTarjeta);
            mostrarUltimoMovimiento("Pago de tarjeta de $" +pagoTarjeta);
        }else {
            System.out.println("No tiene suficiente saldo en su cuenta");
        }
    }

    //Método para depositar en tarjeta de crédito
    private void depositarEnTarjetaCredito(){
        System.out.println("Has seleccionado la opción: DEPOSITAR EN TARJETA DE CRÉDITO");
        System.out.println("Ingrese el monto a pagar: $" + pagoTarjeta);
        if (saldo >= pagoTarjeta) {
            saldo -= pagoTarjeta;
            System.out.println("Operación realizada con éxito. Se realizó un pago de $" + pagoTarjeta);
            mostrarUltimoMovimiento("Pago de tarjeta de $" + pagoTarjeta);
        } else {
            System.out.println("No tienes suficiente saldo en tu cuenta.");
        }
    }

    //Método para transferir a cuenta de terceros.
    private void transferirACuentaTerceros() {
        System.out.println("Has seleccionado la opción: TRANSFERENCIA A CUENTA TERCEROS");
        System.out.println("Ingrese el monto a transferir $" + transferencia);
        if (saldo >= transferencia) {
            saldo -= transferencia;
            System.out.println("Transferencia exitosa. Se transfirió $ " + transferencia + " a una cuenta de terceros.");
            mostrarUltimoMovimiento("Transferencia de $ " + transferencia);
        } else {
            System.out.println("No tienes suficiente saldo en tu cuenta.");
        }
    }

    //Muestra el estado de cuenta.
    private void mostrarEstadoCuenta() {
        System.out.println("Has seleccionado la opción: ESTADO DE CUENTA");
        System.out.println("Cantidad disponible: $" + saldo);
    }

    //Muestra el último movimiento acorde a lo ingresado, mostrando hora y fecha.
    private void mostrarUltimoMovimiento(String movimiento) {
        System.out.println("Has seleccionado la opción: ÚlTIMO MOVIMIENTO");
        LocalDateTime fechaHora = LocalDateTime.now();
        String ultimoMovimiento = fechaHora.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + "hrs. " + movimiento;
        System.out.println("Último movimiento registrado: " + ultimoMovimiento);
    }

    //Constructor de cajero automático
    public static void main(String[] args) {
        cajeroAutomatico cajero = new cajeroAutomatico();
        cajero.iniciar();
    }

}



