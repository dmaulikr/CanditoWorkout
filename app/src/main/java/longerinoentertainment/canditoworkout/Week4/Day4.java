package longerinoentertainment.canditoworkout.Week4;

import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import longerinoentertainment.canditoworkout.R;

public class Day4 extends Fragment {
    Button bench1;
    Button bench2;
    Button bench3;
    Button stopper;
    Chronometer chronometer;
    private long timeWhenStopped = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View infoTab = inflater.inflate(R.layout.activity_day44, container, false);

        bench1 = (Button) infoTab.findViewById(R.id.benchText1);
        bench2 = (Button) infoTab.findViewById(R.id.benchText2);
        bench3 = (Button) infoTab.findViewById(R.id.benchText3);
        stopper = (Button) infoTab.findViewById(R.id.stopperButton);
        chronometer = (Chronometer) infoTab.findViewById(R.id.chronometer);

        final File dir = new File(getContext().getFilesDir() + "/CanditoWorkoutApp");
        final File file = new File(dir, "savedFile.txt");
        String[] values = readFromFile(new File(dir, "savedFile.txt"));
        readFromFile(file);

        double benchNumber = round(values[0]);

        String benchText1 = Double.toString(benchNumber+10) + "x3";
        String benchText2 = Double.toString(benchNumber+12.5) + "x2-4";
        String benchText3 = Double.toString(benchNumber+15) + "x1-2";
        bench1.setText(benchText1);
        bench2.setText(benchText2);
        bench3.setText(benchText3);

        stopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
            }
        });

        return infoTab;
    }
    public String[] readFromFile(File file){
        String[] values = new String[3];
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null){
                values[i] = line;
                i++;
            }
            br.close();
        }
        catch (IOException ignored){
        }
        return values;
    }
    public static double round(String valueString) {
        double value = Math.floor(Math.floor(Double.parseDouble(valueString)/2.5)*2.5 * 0.8/2.5)*2.5;
        return value;
    }
}