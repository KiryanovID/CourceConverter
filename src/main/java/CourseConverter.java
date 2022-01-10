import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CourseConverter {

    public static void main(String[] args) throws JAXBException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Конвертер валют \nВведите сокращенное название валюты:");
            String v = br.readLine().toUpperCase();
            System.out.println("Введите количество:");
            double d = Double.parseDouble(br.readLine());
            CourseConverter.getXML();
            System.out.println(CourseConverter.getCourse(v, d) + "руб");


    }

    public static void getXML() throws IOException {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + localDate.format(formatter));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        String resString = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        FileWriter fw = new FileWriter("./src/main/resources/result.xml", false);
        fw.write(resString);
        fw.close();

    }

    public static double getCourse(String v, double d) throws JAXBException {
        File file = new File("./src/main/resources/result.xml");
        JAXBContext context = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ValCurs curs = (ValCurs) unmarshaller.unmarshal(file);
        List<ValCurs.Valute> valuteList = curs.getValutes();
        double result = 0;
        for(ValCurs.Valute x: valuteList){
            if(x.getCharCode().equals(v)){
                String res = x.getValue().replace(',','.');
                result = d * Double.parseDouble(res) * Double.parseDouble(x.getNominal());
            }
        }
        return result;
    }
}