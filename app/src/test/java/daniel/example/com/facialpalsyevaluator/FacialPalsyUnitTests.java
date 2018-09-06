package daniel.example.com.facialpalsyevaluator;

import android.content.Context;
import android.widget.EditText;


import com.example.daniel.facialpalsyevaluator.Appointment;
import com.example.daniel.facialpalsyevaluator.HomePageActivity;
import com.example.daniel.facialpalsyevaluator.LoginActivity;
import com.example.daniel.facialpalsyevaluator.Patient;
import com.example.daniel.facialpalsyevaluator.PatientActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class FacialPalsyUnitTests {

    @Mock
    Context mMockContext;

    @Test
    public void loadAppointmentsTest () {

        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient());
        patientList.add(new Patient());
        patientList.add(new Patient());
        patientList.add(new Patient());
        patientList.add(new Patient());

        HomePageActivity.loadAppointments(patientList);

        assert(patientList.get(0).appointments.get(0).apNum==1);

    }

    @Test
    public void authenticateTest1(){

        EditText username = Mockito.mock(EditText.class);
        EditText password = Mockito.mock(EditText.class);

        assert (LoginActivity.authenticate("test@user","qwerty",username,password));

    }

    @Test
    public void authenticateTest2(){

        EditText username = Mockito.mock(EditText.class);
        EditText password = Mockito.mock(EditText.class);

        assert (!LoginActivity.authenticate("test","qwerty",username,password));

    }

    @Test
    public void authenticateTest3(){

        EditText username = Mockito.mock(EditText.class);
        EditText password = Mockito.mock(EditText.class);

        assert (!LoginActivity.authenticate("test@user","qwert",username,password));

    }

    @Test
    public void authenticateTest4(){

        EditText username= Mockito.mock(EditText.class);;
        EditText password= Mockito.mock(EditText.class);;

        assert (!LoginActivity.authenticate("user","qwert",username,password));

    }

    @Test
    public void toListTest(){

        Patient p = new Patient();
        assert (p.toList().size()==4);

    }

    @Test
    public void matchTest1(){

        Patient p = new Patient();
        String input = "Scott";

        p.fname = "scott";

        assert (p.match(input));
    }

    @Test
    public void matchTest2(){

        Patient p = new Patient();
        String input = "Scott";

        assert (!p.match(input));
    }

    @Test
    public void GenerateAptHeadersTest(){

        List<Appointment> apt = new ArrayList<>();
        Appointment a = new Appointment();
        a.apNum = 100;
        a.apDate = "01/01/2010";
        apt.add(a);

        assert(PatientActivity.GenerateAptHeaders(apt).get(0).equals("100" + ". " + "01/01/2010"));
    }

}