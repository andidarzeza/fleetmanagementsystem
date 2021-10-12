package com.rayonit.fleetmanager.general.classes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RouteGenerator {
    private static List<Route> route = new ArrayList<>();
    static public List<Route> getRoutes(){
        int counter = 0;
        try {
//            File file = new File("C:\\Users\\darze\\Desktop\\routes.log");
//            Scanner sc = new Scanner(file);
//            List<Position> points = new ArrayList<>();
//            while (sc.hasNextLine()){
//                String string = sc.nextLine();
//                if (string.equals("NewRoute")){
//                    route.add(new Route(points));
//                    points = new ArrayList<>();
//                    System.out.println(counter);
//                    counter = 0;
//                }else{
//                    counter++;
//                    points.add(new Position(
//                            Double.valueOf(string.substring(20).split(", ")[0]),
//                            Double.valueOf(string.substring(20).split(", ")[1])
//                    ));
//                }
//            }
//            System.out.println("Size is : " + route.size());

            return route;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
