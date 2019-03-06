package model.io;

import javax.sound.midi.*;

import model.exceptions.io.TetrisIOException;

import java.io.*;

public class MidiPlayer {
	
	/**
	 * Reproduce una cancion midi.
	 * @param filePath, la ruta al archivo.
	 * @param tempo, el tepo (velocidad) a la que se quiere reproducir. 1F = velocidad normal, 2F el doble de rapido.. en float!
	 * @throws TetrisIOException
	 */
    public static void play(String filePath, float tempo ) throws TetrisIOException {
    	
        if(!filePath.contains(".mid")) {
            throw new TetrisIOException("El archivo tiene que ser de fotmaro midi");
        }
        
        File midiFile = new File(filePath);
        if(!midiFile.exists() || midiFile.isDirectory() || !midiFile.canRead()) {
            throw new TetrisIOException("El archivo de musica no existe o no se puede leer.");
        }
    	
        /**
         * Invoca el secuenciador midi que tiene el dispositivo.
         * Solo hay un secuenciador midi, por lo tanto la clase es estatica.
         */
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
            sequencer.open();
            sequencer.setTempoFactor(tempo);
            sequencer.start();
            
            /**
             * Revisa si el reproductor sigue reproduciendo la cancion,
             * si se ha parado de reproducir (porque ha acabado), lo vuelve a lanzar,
             * asi la cancion funciona en bucle.
             */
            while(true) {
                if(sequencer.isRunning()) {
                    try {
                    	 /**
     					* Hace que el proceso espere durante un segundo antes de continuar.
     					* De esta manera el bucle no se ejecuta continuamente, sino durante un intervalo especifico.
     					* Y como hemos mencionado en el main, debido a que este es un proceso aparte, esto no afecta
     					* al proceso del juego.
     					*/
                        Thread.sleep(1000);
                        
                        /**
                         * InteruptedException se lanza cuando el proceso se interrupe mientras
                         * estaba ocupado, durmiendo (thread.sleep()) o en espera.
                         */
                    } catch(InterruptedException ignore) {
                        break;
                    }
                } else {
                	sequencer.stop();
                    sequencer.start();
                }
            }
        
        } catch(MidiUnavailableException mue) {
            System.out.println("Midi device unavailable!");
        } catch(InvalidMidiDataException imde) {
            System.out.println("Invalid Midi data!");
        } catch(IOException ioe) {
            System.out.println("I/O Error!");
        } 
    }  
}