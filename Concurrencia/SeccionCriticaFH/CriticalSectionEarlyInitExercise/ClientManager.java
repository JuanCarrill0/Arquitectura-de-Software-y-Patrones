import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClientManager {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Uso: java ClientManager <numero_experimento>");
			System.exit(1);
		}

		int experimento = Integer.parseInt(args[0]);
		Thread[] procesos = new Thread[4];
		List<MensajeRegistro> mensajesGlobales = Collections.synchronizedList(new ArrayList<>());

		try {
			for (int i = 0; i < procesos.length; i++) {
				FileProcess hilo = new FileProcess("Thread " + (i + 1) + " is writing", i + 1, experimento, mensajesGlobales);
				procesos[i] = hilo;
				hilo.start();
			}

			for (Thread t : procesos) {
				t.join();
			}

			// Ordenar los mensajes por timestamp
			mensajesGlobales.sort(Comparator.comparingLong(MensajeRegistro::getTimestamp));

			// Escribir al archivo
			boolean append = experimento != 0;
			try (FileWriter writer = new FileWriter("resultados.csv", append)) {
				if (!append) {
					writer.write("experimento,hilo,tiempo_obtener_logger_ns,timestamp_ns,mensaje,tiempo_escritura_ns,tiempo_total_registro_ns\n");
				}
				for (MensajeRegistro m : mensajesGlobales) {
					writer.write(m + "\n");
				}
			}

			System.out.println("Experimento " + experimento + " completado.");

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}

class FileProcess extends Thread {
	private final String msgLog;
	private final int id;
	private final int experimento;
	private final List<MensajeRegistro> mensajesGlobales;

	public FileProcess(String msg, int id, int experimento, List<MensajeRegistro> mensajesGlobales) {
		this.msgLog = msg;
		this.id = id;
		this.experimento = experimento;
		this.mensajesGlobales = mensajesGlobales;
	}

	@Override
	public void run() {
		long tInicioLogger = System.nanoTime();
		Logger fileLogger = FileLogger.getFileLogger();
		long tFinLogger = System.nanoTime();
		long tiempoObtenerLogger = tFinLogger - tInicioLogger;

		long tInicioRegistro = System.nanoTime();

		for (int i = 0; i < 15; i++) {
			long tInicioMensaje = System.nanoTime();
			fileLogger.log(msgLog + " - message " + i);
			long tFinMensaje = System.nanoTime();
			long tiempoMensaje = tFinMensaje - tInicioMensaje;

			mensajesGlobales.add(new MensajeRegistro(experimento, id, tiempoObtenerLogger, tInicioMensaje, i, tiempoMensaje, 0));
		}

		long tFinRegistro = System.nanoTime();
		long tiempoTotalRegistro = tFinRegistro - tInicioRegistro;

		// Agregar mensaje final TOTAL con tiempo total de registro (timestamp se pone -1)
		mensajesGlobales.add(new MensajeRegistro(experimento, id, tiempoObtenerLogger, -1, -1, -1, tiempoTotalRegistro));
	}
}

class MensajeRegistro {
	private final int experimento;
	private final int hilo;
	private final long tiempoLogger;
	private final long timestamp;
	private final int mensaje;
	private final long tiempoEscritura;
	private final long tiempoTotal;

	public MensajeRegistro(int experimento, int hilo, long tiempoLogger, long timestamp, int mensaje, long tiempoEscritura, long tiempoTotal) {
		this.experimento = experimento;
		this.hilo = hilo;
		this.tiempoLogger = tiempoLogger;
		this.timestamp = timestamp;
		this.mensaje = mensaje;
		this.tiempoEscritura = tiempoEscritura;
		this.tiempoTotal = tiempoTotal;
	}

	public long getTimestamp() {
		return timestamp == -1 ? Long.MAX_VALUE : timestamp;
	}

	@Override
	public String toString() {
		if (mensaje == -1) {
			return experimento + "," + hilo + "," + tiempoLogger + ",,,TOTAL,," + tiempoTotal;
		} else {
			return experimento + "," + hilo + "," + tiempoLogger + "," + timestamp + "," + mensaje + "," + tiempoEscritura + ",";
		}
	}
}
