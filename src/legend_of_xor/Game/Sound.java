/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author parke
 */
public class Sound {

    static AudioClip clip;

    public static void loadSound() {
        URL url = null;

        url = Sound.class.getResource("/Sounds/pp.wav");

        System.out.println(url);
        clip = Applet.newAudioClip(url);
    }

    public static void playSound() {
        clip.play();
    }
}
