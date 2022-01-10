import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "ValCurs")
public class ValCurs {
    @XmlAttribute(name = "Date")
    public String date;

    @XmlAttribute(name = "name")
    public String name;

    public ArrayList<Valute> getValutes() {
        return valutes;
    }

    @XmlElement(name = "Valute")
    public ArrayList<Valute> valutes;

    public static class Valute {
        public String getCharCode() {
            return charCode;
        }

        public String getValue() {
            return value;
        }

        public String getNominal() {
            return nominal;
        }

        @XmlAttribute(name = "ID")
        public String id;

        @XmlElement(name = "Name")
        public String name;

        @XmlElement(name = "Value")
        public String value;

        @XmlElement(name = "Nominal")
        public String nominal;

        @XmlElement(name = "CharCode")
        public String charCode;

        @XmlElement(name = "NumCode")
        public String numCode;

        @Override
        public String toString() {
            return "Valute {" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", nominal='" + nominal + '\'' +
                    ", charCode='" + charCode + '\'' +
                    ", numCode='" + numCode + '\'' +
                    " }";
        }
    }

    @Override
    public String toString() {
        return "ValCurs {" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", valutes=" + valutes +
                " }";
    }
}
