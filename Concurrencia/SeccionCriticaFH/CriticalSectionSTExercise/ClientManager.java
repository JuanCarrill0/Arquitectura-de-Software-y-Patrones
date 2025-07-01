public class ClientManager {

	public static void main(String[] args) {

		FileProcess proceso1 = new FileProcess("Thread 1 is wrtting");
		proceso1.start();
		FileProcess proceso2 = new FileProcess("Thread 2 is writting");
		proceso2.start();
	}
}

class FileProcess extends Thread {
	private String msgLog;

	public FileProcess(String msg) {
		this.msgLog = msg;
	}

	@Override
	public void run() {
		Logger fileLogger = FileLogger.getFileLogger();
		for (int i = 0; i < 20; i++) {
			fileLogger.log(msgLog);
		}
	}

}
