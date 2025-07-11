import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientManager {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Uso: java ClientManager <numero_experimento>");
			System.exit(1);
		}

		int experimento = Integer.parseInt(args[0]);

		try {

			FileProcess proceso1 = new FileProcess("Thread 1 is wrtting", 1, experimento);
			proceso1.start();
			FileProcess proceso2 = new FileProcess("Thread 2 is wrtting", 2, experimento);
			proceso2.start();
			FileProcess proceso3 = new FileProcess("Thread 3 is wrtting", 3, experimento);
			proceso3.start();
			FileProcess proceso4 = new FileProcess("Thread 4 is wrtting", 4, experimento);
			proceso4.start();

			proceso1.join();
			proceso2.join();
			proceso3.join();
			proceso4.join();

			List<MensajeRegistro> mensajesGlobales = new ArrayList<>();
			mensajesGlobales.addAll(proceso1.getMensajesLocales());
			mensajesGlobales.addAll(proceso2.getMensajesLocales());
			mensajesGlobales.addAll(proceso3.getMensajesLocales());
			mensajesGlobales.addAll(proceso4.getMensajesLocales());
			mensajesGlobales.sort(Comparator.comparingLong(MensajeRegistro::getTimestamp));

			boolean append = experimento != 0;
			try (FileWriter writer = new FileWriter("resultados.csv", append)) {
				if (!append) {
					writer.write("experimento,hilo,tiempo_obtener_logger_ns,timestamp_ns,mensaje,tiempo_escritura_ns,tiempo_total_registro_ns\n");
				}
				for (MensajeRegistro m : mensajesGlobales) {
					writer.write(m + "\n");
				}
			}
		} catch (InterruptedException | IOException e) {
			
		}
	}
}

class FileProcess extends Thread {
	private final String msgLog;
	private final int id;
	private final int experimento;
	private final List<MensajeRegistro> mensajesLocales = new ArrayList<>();

	public FileProcess(String msg, int id, int experimento) {
		this.msgLog = msg;
		this.id = id;
		this.experimento = experimento;
	}

	public List<MensajeRegistro> getMensajesLocales() {
		return mensajesLocales;
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

			mensajesLocales.add(new MensajeRegistro(experimento, id, tiempoObtenerLogger, tFinMensaje, i, tiempoMensaje, 0));
		}

		long tFinRegistro = System.nanoTime();
		long tiempoTotalRegistro = tFinRegistro - tInicioRegistro;

		mensajesLocales.add(new MensajeRegistro(experimento, id, tiempoObtenerLogger, -1, -1, -1, tiempoTotalRegistro));
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

	public MensajeRegistro(int experimento, int hilo, long tiempoLogger, long timestamp, int mensaje,
			long tiempoEscritura, long tiempoTotal) {
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
			return experimento + "," + hilo + "," + tiempoLogger + "," + timestamp + "," + mensaje + ","
					+ tiempoEscritura + ",";
		}
	}
}
