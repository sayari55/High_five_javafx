/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;



import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class OSMMap extends JFrame {

    private JMapViewer mapViewer;

    public OSMMap() {
        super("Select Event Location");

        // Create the map viewer and set its properties
       
        mapViewer.setDisplayPosition(new Coordinate(48.858222, 2.2945), 11);
        
        // Add a marker to the map
        MapMarkerDot marker = new MapMarkerDot(48.858222, 2.2945);
        mapViewer.addMapMarker(marker);

        // Add the map viewer to the frame
        getContentPane().add(mapViewer);

        // Set the frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        // Add mouse listener to the map
        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the clicked location
                double latitude = mapViewer.getPosition(e.getPoint()).getLat();
                double longitude = mapViewer.getPosition(e.getPoint()).getLon();

                // Add a marker to the map at the clicked location
                addMarker(latitude, longitude);
            }
        });
    }

    public void addMarker(double latitude, double longitude) {
        MapMarkerDot marker = new MapMarkerDot(latitude, longitude);
        mapViewer.addMapMarker(marker);
    }

}