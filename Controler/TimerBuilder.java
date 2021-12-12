package Controler;

/**
 * source : https://forum.minestrator.com/threads/java-creer-un-cooldown.14/
 * @author Maxlego08
 *
 */

public class TimerBuilder {
	public static String getFormatLongHours(long temps) {
        long totalSecs = temps / 1000L;
        return String.format("%02d heure(s) %02d minute(s) %02d seconde(s)", new Object[]{Long.valueOf(totalSecs / 3600L), Long.valueOf(totalSecs % 3600L / 60L), Long.valueOf(totalSecs % 60L)});
    }

    public static String getFormatLongMinutes(long temps) {
        long totalSecs = temps / 1000L;
        return String.format("%02d minute(s) %02d seconde(s)", new Object[]{Long.valueOf(totalSecs % 3600L / 60L), Long.valueOf(totalSecs % 60L)});
    }

    public static String getFormatLongSecondes(long temps) {
        long totalSecs = temps / 1000L;
        return String.format("%02d seconde(s)", new Object[]{Long.valueOf(totalSecs % 60L)});
    }
}
