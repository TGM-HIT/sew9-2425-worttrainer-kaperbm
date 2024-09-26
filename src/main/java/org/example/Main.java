package org.example;

public class Main {
    public static void main(String[] args) {


        TrainingsDaten uebung1 = new TrainingsDaten("Ziege","https://images2.minutemediacdn.com/image/upload/c_fill,w_1200,ar_1:1,f_auto,q_auto,g_auto/shape/cover/sport/iStock-177369626-1-0e8d40eaabe65d2cb2d745ef45f09229.jpg");
        TrainingsDaten uebung2 = new TrainingsDaten("Hund", "https://www.meiko.ch/media/wysiwyg/Suesser_Hund_guter_Hund_header_1000x540.jpg");
        TrainingsDaten uebung3 = new TrainingsDaten("Katze","https://image.geo.de/34423086/t/u8/v1/w1440/r0/-/katze-as-97589769.jpg");
        TrainingsDaten uebung4 = new TrainingsDaten("Schwein","https://www.srf.ch/static/cms/images/960w/d13975.jpg");
        TrainingsDaten uebung5 = new TrainingsDaten("Kuh", "https://www.planet-wissen.de/gesellschaft/trinken/milch/milchkuhwdrgjpg100~_v-gseapremiumxl.jpg");

        Rechtschreibtrainer trainer = new Rechtschreibtrainer();


    }
}