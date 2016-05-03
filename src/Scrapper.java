import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

/**
 * Created by prasadt on 5/3/2016.
 */
public class Scrapper
{
    public void loggin(){
        try{
            UserAgent userAgent = new UserAgent();
            userAgent.visit( "http://www.sonrich.lk/sonrichsys/login.php");

            Form form = userAgent.doc.getForm(0);       //get the document's first Form
            form.setTextField( "userid", "XXXXX" ); //or form.set("email", "tom@mail.com");
            form.setPassword( "passw", "YYYYYY" );           //or form.set("pw", "secret");
            userAgent.doc.submit();                          //submit the form
            System.out.println( userAgent.getLocation() );     //print the current location (url)
            userAgent.visit( "http://www.sonrich.lk/sonrichsys/showcomm.php" );
            Element table = userAgent.doc.findFirst("<table width=\"100%\" style=\"text-align: center;\">");  //find table element
            Elements trs = table.findEach( "<tr>" );                         //find non-nested td/th elements
            for(Element tr: trs){                                             //iterate through td/th's
                Elements tds = tr.findEach( "<td|th>" );
                System.out.println("==============================================");
                for(Element td : tds){
                    //System.out.println(td.outerHTML());
                    System.out.println(td.getText());
                }
                                            //print each td/th element
            }
        }
        catch(JauntException e){
            System.err.println(e);
        }
    }

}
