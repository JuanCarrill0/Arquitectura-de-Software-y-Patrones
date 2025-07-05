package LaboratorioClinico;

public class ClinicaTest {
    public static void main(String[] args) {
        LaboratorioClinico lab = new LaboratorioClinico();
        new Paciente("Paciente1", lab);
        new Paciente("Paciente2", lab);
        new Paciente("Paciente3", lab);
        new Paciente("Paciente4", lab);
        new Paciente("Paciente5", lab);
    }
}

class LaboratorioClinico {
    // Aforo máximo de 3 pacientes
    public static final int AFORO_MAXIMO = 3;
    private int pacientesEnAtencion = 0;

    public synchronized void ingresarPaciente(String nombrePaciente) {
        while (pacientesEnAtencion >= AFORO_MAXIMO) {
            try {
                System.out.println("El laboratorio está lleno. " + nombrePaciente + " debe esperar.");
                wait();
            } catch (InterruptedException e) {
                //
            }
        }
        pacientesEnAtencion++;
        System.out.println(nombrePaciente + " ha ingresado al laboratorio. " + "Aforo: (" + pacientesEnAtencion + "/" + AFORO_MAXIMO + ")");
    }

    public synchronized void salirPaciente(String nombrePaciente) {
        pacientesEnAtencion--;
        System.out.println(nombrePaciente + " ha terminado su atención. " +"Aforo actual: (" + pacientesEnAtencion + "/" + AFORO_MAXIMO + ")");
        notify();
    }
}

class Paciente extends Thread {
    private LaboratorioClinico laboratorio;
    private String nombre;

    Paciente(String n, LaboratorioClinico l) {
        nombre = n;
        laboratorio = l;
        start();
    }

    public void run() {
        System.out.println(nombre + " llega al laboratorio");
        laboratorio.ingresarPaciente(nombre);
        try {
            // Simular tiempo de atención (12 segundos = 12000 ms)
            sleep(12000);
        } catch (InterruptedException e) {
            //
        }
        laboratorio.salirPaciente(nombre);
    }
}