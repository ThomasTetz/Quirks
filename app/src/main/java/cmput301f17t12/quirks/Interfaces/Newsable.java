package cmput301f17t12.quirks.Interfaces;

import java.util.Date;

public interface Newsable {
    public String buildNewsHeader(String extra);
    public String buildDate();
    public String buildNewsDescription();
    public Date getDate();
}
