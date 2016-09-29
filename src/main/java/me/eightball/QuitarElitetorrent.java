package me.eightball;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class QuitarElitetorrent implements Runnable {

	public static void main(String[] args) throws Exception {
		QuitarElitetorrent qe = new QuitarElitetorrent();
		qe.run();
	}

	private Pattern pattern1 = Pattern.compile("[ ]*[\\(]?elitetorrent\\.net[\\)]?", Pattern.CASE_INSENSITIVE);
	private Pattern pattern2 = Pattern.compile("[ \\.]*[\\[]?www\\.newpct[1]?\\.com[\\]]?", Pattern.CASE_INSENSITIVE);
	private Properties properties = new Properties();

	public QuitarElitetorrent() throws IOException {
		properties.load(ClassLoader.getSystemResourceAsStream("quitarElitetorrent.properties"));
	}

	@Override
	public void run() {
		try {
			while (true) {
				asegurarVideo();
				String raicesStr = properties.getProperty("raices");
				System.out.println("Raices: " + raicesStr);
				String[] raices = raicesStr.trim().split("[, ]+");
				for (String raiz : raices) {
					System.out.println("Tratando raiz " + raiz);
					tratarDirectorio(new File(raiz));
				}
				System.out.println("Durmiendo 3 horas");
				Thread.sleep(180 * 60 * 1000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void asegurarVideo() throws Exception {
		String puntoMontaje = properties.getProperty("punto_montaje");
		System.out.println("Punto de montaje: " + puntoMontaje);
		File puntoMontajeFile = new File(puntoMontaje);
		if (!puntoMontajeFile.exists()) {
			puntoMontajeFile.mkdirs();
		} else if (puntoMontajeFile.isFile()) {
			throw new Exception("El punto de montaje es un fichero");
		}
		// ['mount_afp', 'afp://' + config['usuario'] + ':' + config['password']
		// + '@' + config['ip_synology'] + '/' + config['carpeta_compartida'],
		// punto_montaje]
		String usuario = properties.getProperty("usuario");
		String password = properties.getProperty("password");
		String ipSynology = properties.getProperty("ip_synology");
		String carpetaCompartida = properties.getProperty("carpeta_compartida");
		ProcessBuilder pb = new ProcessBuilder("mount_afp",
				"afp://" + usuario + ':' + password + '@' + ipSynology + '/' + carpetaCompartida, puntoMontaje);
		File log = new File("mount_afp.log");
		pb.redirectErrorStream(true);
		pb.redirectOutput(Redirect.appendTo(log));
		Process p = pb.start();
		System.out.println("esperando por el mount_afp");
		p.waitFor();
		System.out.println("se termino de esperar por el mount_afp");
	}

	private void tratarDirectorio(File directorio) {
		for (File file : directorio.listFiles()) {
			if (file.isFile()) {
				tratarFichero(file);
			} else {
				tratarDirectorio(file);
			}
		}
	}

	private void tratarFichero(File fichero) {
		String nombreFichero = fichero.getName();
		String[] intermediateResult = pattern1.split(nombreFichero);
		StringBuilder sb = new StringBuilder();
		for (String intResult : intermediateResult) {
			sb.append(intResult);
		}
		intermediateResult = pattern2.split(sb.toString());
		sb = new StringBuilder();
		for (String intResult : intermediateResult) {
			sb.append(intResult);
		}
		String nombreTratado = sb.toString();

		if (!StringUtils.equals(nombreFichero, nombreTratado)) {
			System.out.println(String.format("Renombrando fichero %s", nombreFichero));
			System.out.println(String.format("a                   %s", nombreTratado));
			boolean renombrado = fichero
					.renameTo(new File(String.format("%s%s%s", fichero.getParent(), File.separator, nombreTratado)));
			if (renombrado) {
				System.out.println("Correcto");
			} else {
				System.out.println("Error");
			}
		}

	}

}
