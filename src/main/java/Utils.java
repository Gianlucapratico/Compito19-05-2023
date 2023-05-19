import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static Date calcolaDataRestituzionePrevista(Date dataInizioPrestito) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataInizioPrestito);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        return cal.getTime();
    }
}
