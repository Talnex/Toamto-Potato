package Beans;

import javafx.scene.paint.Color;


public class ColorSetter {

        public static Color getColor(int i){
            switch (i){
                case 0: return Color.rgb(132,133,130);
                case 1: return Color.rgb(255,133,126);
                case 2: return Color.rgb(240,255,129);
                case 3: return Color.rgb(124,255,132);
                case 4: return Color.rgb(132,219,255);
                default:return Color.rgb(0,0,0);
            }
        }
}
