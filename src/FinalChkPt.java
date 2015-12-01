/*******************************************************************************
 * file: CheckPt_2.java 
 * author: Josue Miramontes, Anthony Guzman, Gerret Kubota
 * class: CS 445
 * 
 * assignment: Checkpoint 2 
 * date last modified: 11/19/2015
 * 
 * purpose: The purpose of this program is to act as a driver.
 *          This class initialize the display needed to
 *          run the rest of the program. 
 *
 ******************************************************************************/
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class FinalChkPt {
    private static FPCameraController fp;
    private DisplayMode displayMode = new DisplayMode(640,480);
    private FloatBuffer lightPosition;
    private FloatBuffer whiteLight;

    // method: start
    // purpose: this method acts as a psuedo-driver for the program. It calls
    // some the methods that provide critical information and initialization 
    // for the program.
    public void start() {
        try {
            
            createWindow();
            initGL();
            fp = new FPCameraController(0f,0f,0f);
            fp.gameLoop();//render();
            } catch (Exception e) { e.printStackTrace(); }
    }
    private void initLightArrays() {
        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(0.0f).put(0.0f).put(0.0f).put(1.0f).flip();
        whiteLight = BufferUtils.createFloatBuffer(4);
        whiteLight.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();
}
    
    // method: createWindow
    // purpose: this method initializes and provides the size of the window for
    // use throughtout the remainder of the program. The Keyboard is then linked
    // to the program in order to give additional functionality at the users
    // request (i.e. press 'ESC' key to terminate program)
    private void createWindow() throws Exception{
        Display.setFullscreen(false);
        DisplayMode d[] = Display.getAvailableDisplayModes();

        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640 && d[i].getHeight() == 480 && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }

        Display.setDisplayMode(displayMode);
        Display.setTitle("Final");
        Display.create();
    }
    
    // method: initGL
    // purpose: this method provides additional internal details for the window, 
    // such as the mode to be used, background color and how to display the
    // pixels being mapped to the display window. coordinate system set to origin.
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)
        displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        
        // For storing Block Data
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnable(GL_DEPTH_TEST);
        
        // For Texture Mapping
        glEnable(GL_TEXTURE_2D);
        glEnableClientState (GL_TEXTURE_COORD_ARRAY);
        
        // For our light source
        initLightArrays();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition); //sets our light’s position
        glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);//sets our specular light
        glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);//sets our diffuse light
        glLight(GL_LIGHT0, GL_AMBIENT, whiteLight);//sets our ambient light
        glEnable(GL_LIGHTING);//enables our lighting
        glEnable(GL_LIGHT0);//enables light0
    }
    
    // method: main
    // purpose: this method creates an instance of CheckPt_2 and calls the start
    // method, initiating the bulk of the program
    public static void main(String[] args) {
        
        FinalChkPt basic = new FinalChkPt();
        basic.start();
    }
    
}