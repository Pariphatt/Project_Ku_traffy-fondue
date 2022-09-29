//package ku.cs.models;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class RegisterStaff {
//    private String directoryName = "assets";
//
//
//    public void validateRegisterStaff(String Name,String Username,String password,String confirmPassword,String Path,String Agency) {
//        String filePath = directoryName + File.separator + "staff.csv";
//        File file = new File(filePath);
//        FileWriter writer = null;
//        BufferedWriter buffer = null;
//
//        try {
//            writer = new FileWriter(file,true);
//            buffer = new BufferedWriter(writer);
//            if (Path == null){
//                String user = Name + "," + Username + "," + password+","+"false"+","+"false"+","+"staff-user.png"+","+ Agency;
//                buffer.append(user);
//                buffer.newLine();
//            }
//            else {
//                String user = Name + "," + Username + "," + password+","+"false"+","+"true"+","+ Path +","+ Agency;
//                buffer.append(user);
//                buffer.newLine();
//            }
//
//
//
//
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                buffer.close();
//                writer.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//
//    }
//}
