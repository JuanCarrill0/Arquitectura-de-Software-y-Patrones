import java.util.ArrayList;
import java.util.List;

public class ClientManager {

	public static void main(String[] args) throws InterruptedException {
        List<FileProcess> threads = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            FileProcess fp = new FileProcess("Thread " + i + " is writing");
            fp.start();
            threads.add(fp);
        }

        for (Thread t : threads) {
            t.join();
        }

        // Mostrar resultados después de la ejecución
        System.out.println("Ejecución completada.\nResultados por hilo:");
        for (FileProcess fp : threads) {
            System.out.println(fp.getName() + 
                " - Tiempo getLogger: " + fp.getLoggerTime() + " ns" +
                ", Tiempo escritura: " + fp.getWriteTime() + " ns");
        }
    }
}
class FileProcess extends Thread {
    private final String msg;
    private long loggerTime;
    private long writeTime;

    public FileProcess(String msg) {
        this.msg = msg;
    }

    public long getLoggerTime() {
        return loggerTime;
    }

    public long getWriteTime() {
        return writeTime;
    }

    @Override
    public void run() {
        long startLogger = System.nanoTime();
        Logger logger = FileLogger.getFileLogger();
        long endLogger = System.nanoTime();
        loggerTime = endLogger - startLogger;

        long startWrite = System.nanoTime();
        for (int i = 1; i <= 15; i++) {
            logger.log(msg + " - mensaje " + i);
        }
        long endWrite = System.nanoTime();
        writeTime = endWrite - startWrite;
    }
}
