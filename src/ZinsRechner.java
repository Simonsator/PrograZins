public class ZinsRechner {
	private static double startBetrag = 0;
	// Der Zinssatz, so wie er eingegeben wurde
	private static double zinssatz = 0;
	// Diese Variable wird genutzt um mit dem Zins rechnen zu koennen
	private static double zinssatzRechnen;

	public static void main(String[] args) {
		start();
	}

	private static void start() {
		while (startBetrag < 1) {
			startBetrag = SimpleIO.getDouble("Bitte geben Sie den Startbetrag ein");
		}
		while (zinssatz < 1) {
			zinssatz = SimpleIO.getDouble("Bitte geben Sie den Zinssatz als Prozentwert ein");
		}
		zinssatzRechnen = (zinssatz / 100) + 1;
		auswahlEinlesen();
	}

	private static void auswahlEinlesen() {
		String auswahl = SimpleIO.getString("Bitte wählen Sie aus:\n" +
				"Ziel: Berechnet die Zeit, bis ein gegebener Betrag angespart wurde.\n" +
				"Zeit: Berechnet den Betrag, der nach einer gegebenen Zeit angespart wurde.");
		switch (auswahl) {
			case "Ziel":
				ziel();
				break;
			case "Zeit":
				zeit();
				break;
			default:
				SimpleIO.output("Ihre eingegebene Auswahl existiert nicht.", "Error");
				// Erneutes fragen, was gemacht werden soll, in der Hoffnung, dass der Nutzer jetzt eine korrekte Auswahl trifft.
				auswahlEinlesen();
				break;
		}
	}

	private static void ziel() {
		double zielBetrag = 0;
		while (zielBetrag < 1) {
			zielBetrag = SimpleIO.getDouble("Bitte geben Sie den Zielbetrag ein");
		}
		double momentanerBetrag = startBetrag;
		int dauer;
		for (dauer = 0; momentanerBetrag < zielBetrag; dauer++) {
			momentanerBetrag = zinssatzRechnen * momentanerBetrag;
		}
		SimpleIO.output("Es dauert " + dauer + " Jahre bei einem Zinssatz von " + zinssatz + "%, um von " + startBetrag + " auf den Betrag " + zielBetrag + " zu sparen. Nach dieser Zeit hat man " + momentanerBetrag + ".", "Ziel");
	}

	private static void zeit() {
		int laufzeit = 0;
		while (laufzeit < 1) {
			laufzeit = SimpleIO.getInt("Wie lange wollen Sie sparen?");
		}
		double momentanerBetrag = startBetrag;
		for (int i = 0; i < laufzeit; i++) {
			momentanerBetrag = zinssatzRechnen * momentanerBetrag;
		}
		if (momentanerBetrag == Double.POSITIVE_INFINITY || momentanerBetrag == Double.NEGATIVE_INFINITY) {
			SimpleIO.output("Ein Fehler ist aufgetreten. Bitte versuchen Sie kleinere Zahlen zu verwenden.", "Error");
			zinssatz = 0;
			startBetrag = 0;
			start();
			return;
		}
		SimpleIO.output("Bei einem Zinssatz von " + zinssatz + "% und einem Startbetrag von " + startBetrag + " hat man nach " + laufzeit + " Jahren " + momentanerBetrag + " gespart", "Zeit");
	}
}
