import db.MyJDBC;
import gui.LoginFormGUI;
import gui.RegisterFormGUI;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {

        // InvokeLater() makes updates to GUI more thread safe and efficient
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                // instantiate a LoginFormGUI object and make it visible\
                new LoginFormGUI().setVisible(true);


                // check user test
//                System.out.println(MyJDBC.checkUsername("username1234"));

                // check register test
//                System.out.println(MyJDBC.register("username1234", "password"));
//                System.out.println(MyJDBC.checkUsername("username1234"));

                //  check validate login test
                System.out.println(MyJDBC.validateLogin("username1234", "password"));
            }
        });
    }
}
