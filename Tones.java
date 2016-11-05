// sample files need to be same environment, same pitch, same shape
// input file needs to be same pitch and shape as sample files
// gets frequency vs amplitude fingerprint
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.lang.Math;
public class Tones {
    public static double[] freqArray(String file) {
        final int GROUP = 10; // instead of comparing by Hz, compare by 10Hz. requires (almost) same pitch tho
        final int HI_FREQ = 30000/GROUP; // ***add 0
        final int DURATION = 100;
        final int SKIP = 5;
        double[] amp = new double[HI_FREQ];
        In in = new In(file);
        for (int i = 0; i < SKIP; i++) in.readLine(); // skip over first 5 lines
        while (!in.isEmpty()) { // 100 samples for (int i = 0; i < DURATION; i++)
            String line = in.readLine();
            String[] fields = line.split(" ");
            int max = Integer.parseInt(fields[1])*3+2;
            for (int x = 2; x < max; x+=3) {
                // makes tones that are within 10Hz equal (truncate) // *** delete
                double divided = Double.parseDouble(fields[x+1])/GROUP;
                int d = (int)Math.round(divided);
                amp[d] += Double.parseDouble(fields[x+2]);
            }
        }
        for (int i = 0; i < amp.length; i++) amp[i] = amp[i]/DURATION;
        // normalize amp
        double max = 0;
        for (int i = 0; i < amp.length; i++) if (amp[i] > max) max = amp[i];
        for (int i = 0; i < amp.length; i++) amp[i] = amp[i]/max;
        for (int i = 0; i < amp.length; i++) {
            //StdDraw.line(i/(amp.length+0.0), 0, i/(amp.length+0.0), amp[i]*50); // ***
           // System.out.println(i + " " + amp[i]);
        }
        // ambient noise OK because will produce equal errors in both.
        return amp;
    }
}