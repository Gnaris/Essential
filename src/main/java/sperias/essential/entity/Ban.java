package sperias.essential.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Ban {

    private final Timestamp expiration;
    private final String reason;

    public Ban(Timestamp expiration, String reason) {
        this.expiration = expiration;
        this.reason = reason;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    public String getBannedMessage()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy 'à' HH 'heure(s)' mm 'minute(s)' 'et' ss 'seconde(s)'");
        return "§cVous avez été bannis du serveur \n\n" +
               "§cRaison : §b" + this.reason + "\n\n" +
               "§cDébannissement dans : " + simpleDateFormat.format(new Date(this.expiration.getTime()));
    }
}
